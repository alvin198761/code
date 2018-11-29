package com.gzz.createcode.mvc.service;

import com.alibaba.fastjson.JSONObject;
import com.alvin.mock.utils.VelocityUtil;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.gzz.createcode.common.Utils;
import com.gzz.createcode.common.config.ScanDriverService;
import com.gzz.createcode.mvc.dao.CodeDao;
import com.gzz.createcode.mvc.model.CodeCond;
import com.gzz.createcode.mvc.model.Field;
import com.gzz.createcode.mvc.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author gzz_gzz@163.com
 * @功能描述:生成列表类型代码的实现类
 * @date 2018-02-15
 */
@Service
public class CodeService {
	//	 private Log logger = LogFactory.getLog(CodeService.class);// 日志类
	@Autowired
	protected CodeDao dao;

	@Autowired
	private ScanDriverService scanDriverService;


	public void create(CodeCond cond) throws Exception {
		String baseUrl = "codetemplate";
		String dateFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		List<String> vms = scanDriverService.doScanService(baseUrl, ".vm");
		List<String> vues = scanDriverService.doScanService(baseUrl, ".vue");
		for (Table table : cond.getC_list()) {
			String auth = cond.getAuth();// 作者
			//用工参数
			cond.setT_name_eq(table.getT_name());// 表名
			List<Field> fList = dao.queryFields(cond).stream().map(item -> {
				item.setBigName(Utils.firstUpper(item.getName()));
				return item;
			}).collect(Collectors.toList());// 字段列表
			String cName = table.getC_name();// 表注释中文名
			String upp = table.getCls_upp();// 驼峰类名(首字母大写)
			String low = upp.toLowerCase();// 类名小写(只用包路径)
			String lowUpp = Utils.firstLower(upp);// 驼峰变量类名(首字母小写)
			String idType = Utils.keyType(fList);// 主键数据类型
			String idName = fList.get(0).getName();

			//组装对象
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("fList", fList);
			jsonObject.put("auth", auth);
			jsonObject.put("cName", cName);
			jsonObject.put("lowUpp", lowUpp);
			jsonObject.put("idType", idType);
			jsonObject.put("table", table.getT_name());
			jsonObject.put("id", fList.get(0));
			jsonObject.put("cond", cond);
			jsonObject.put("tName", table.getT_name());
			jsonObject.put("idName", fList.get(0).getName());
			//类名称
			jsonObject.put("clsUpp", upp);
			jsonObject.put("upp", upp);
			jsonObject.put("time", dateFormart);
			//各种参数追加
			jsonObject.put("selectFields", Utils.add(fList, "t.", ",", false, "select"));
			jsonObject.put("insertFields", Utils.add(fList, "", ",", true, "insert"));
			jsonObject.put("insertValuesFields", Utils.add(fList, ":", ",", true, "insert"));
			jsonObject.put("replaceFields", Utils.add(fList, "", ",", false, "sql"));
			jsonObject.put("replaceValuesFields", Utils.add(fList));
			jsonObject.put("paramsFields", Utils.add(fList, "vo.get", "(),", false));
			jsonObject.put("updateFields", Utils.add(fList, "", "=?,", true, "sql"));
			jsonObject.put("updateParams", Utils.add(fList, "vo.get", "(),", true) + ",vo.get" + Utils.firstUpper(idName) + "()");
			jsonObject.put("selectItems", Utils.add(fList, "t.", ","));

			//其他附属数据
			List<String> importList = Lists.newArrayList();
			importList.add(Utils.dateImport(fList));
			importList.add(Utils.bigImport(fList));
			jsonObject.put("importList", importList);
			//
			jsonObject.put("dollar", "$");
			//java 代码生成
			parseVmTemplate(vms, baseUrl, jsonObject, cond, upp, low);
		}
	}

	/**
	 * 只生成java 类，目前
	 *
	 * @param vms
	 * @param baseUrl
	 * @param jsonObject
	 * @param cond
	 * @param upp
	 * @param low
	 * @throws IOException
	 */
	public void parseVmTemplate(List<String> vms, String baseUrl, JSONObject jsonObject, CodeCond cond, String upp, String low) throws IOException {
		//循环模板，进行合并
		for (String vm : vms) {
			//获得文件名称
			System.out.println("template file :" + vm);
			String vmName = vm.substring(0, vm.length() - 3);
			vm = "/" + vmName.replaceAll("[.]", "/") + ".vm";
			//拼接文件地址
			String dirName = vmName.substring(("/" + baseUrl + "/").length() - 1);
			String clazzName = dirName.substring(dirName.lastIndexOf(".") + 1);
			dirName = dirName.substring(0, dirName.lastIndexOf("."));
			System.out.println(dirName);
			//计算包名
			String pName = cond.pack(dirName, low);
			String fileType = clazzName.substring(clazzName.lastIndexOf("_"));
			upp = clazzName.replaceAll("Model", jsonObject.getString("clsUpp"));
			upp = upp.substring(0, upp.length() - fileType.length());
			jsonObject.put("upp", upp);
			String path = cond.base(dirName, low, upp);

			System.out.println("target file :" + path + (fileType.replace('_', '.')));
			System.out.println("=================start VelocityEngine==================");
			jsonObject.put("pName", pName);
			Files.createDirectories(Paths.get(path).getParent());
			VelocityUtil.parse(vm, jsonObject, path + fileType.replace('_', '.'), VelocityUtil.classPathVelocityEngine());
			System.out.println("=================end VelocityEngine==================");
		}
	}

//	/**
//	 * @功能描述:生成代码
//	 */
//	public void create(CodeCond cond) {
//		String auth = cond.getAuth();// 作者
//		String pName;// 包名
//		String path;// 路径
//		for (Table table : cond.getC_list()) {
//			cond.setT_name_eq(table.getT_name());// 表名
//			List<Field> fList = dao.queryFields(cond);// 字段列表
//			String cName = table.getC_name();// 表注释中文名
//			String upp = table.getCls_upp();// 驼峰类名(首字母大写)
//			String low = upp.toLowerCase();// 类名小写(只用包路径)
//			String lowUpp = Utils.firstLower(upp);// 驼峰变量类名(首字母小写)
//			String idType = Utils.keyType(fList);// 主键数据类型
//
//
////			path = cond.base("ios", low, upp);
////			Utils.write(path + ".h", IosModelH.create( upp, fList, auth, cName));
////			Utils.write(path + ".m", IosModelM.create( upp, fList, auth, cName));
//
//
//			pName = cond.pack("common", low);
//			path = cond.base("common", low, upp);
//			Utils.write(path + ".java", Model.create(pName, upp, fList, auth, cName));
//			Utils.write(path + "Cond.java", Condition.create(pName, upp, fList, auth, cName));
//
//			pName = cond.pack("webdata", low);
//			path = cond.base("webdata", low, upp);
//			Utils.write(path + "Dao.java", Dao.create(pName, upp, auth, cName, fList, table.getT_name(), idType));
//			Utils.write(path + "Service.java", Serv.create(pName, upp, auth, cName, idType, lowUpp));
//			Utils.write(path + "Controller.java", Controller.create(pName, upp, auth, cName, idType, lowUpp));
//
//			pName = cond.pack("webcenter", low);
//			path = cond.base("webcenter", low, upp);
//			Utils.write(path + "Action.java", Action.create(pName, upp, auth, cName, idType, lowUpp));
//
//			Utils.write(path + "Bus.java", Bus.create(pName, upp, auth, cName, idType, lowUpp));
//			path = cond.base("webcenter", low, "I" + upp);
//			Utils.write(path + "Client.java", Client.create(pName, upp, auth, cName, idType, lowUpp));
//
//			pName = cond.pack("appcenter", low);
//			path = cond.base("appcenter", low, upp);
//			Utils.write(path + "Action.java", AppAction.create(pName, upp, auth, cName, idType, lowUpp));
//			Utils.write(path + "Bus.java", Bus.create(pName, upp, auth, cName, idType, lowUpp));
//			path = cond.base("appcenter", low, "I" + upp);
//			Utils.write(path + "Client.java", Client.create(pName, upp, auth, cName, idType, lowUpp));
//
//			path = cond.base("vue-element", low, upp);
//			Utils.write(path + "List.vue", VueList.create(fList, upp, cName, auth, lowUpp));
//			Utils.write(path + "Dialog.vue", VueDialog.create(fList, lowUpp, cName, auth));
//			Utils.write(path + "Mock.js", VueMockJs.create(fList, lowUpp, cName, auth));
//
//			path = cond.base("vue-iview", low, upp);
//			Utils.write(path + "List.vue", VueIviewList.create(fList, upp, cName, auth, lowUpp));
//			Utils.write(path + "Dialog.vue", VueIviewDialog.create(fList, lowUpp, cName, auth));
//			Utils.write(path + "Mock.js", VueMockJs.create(fList, lowUpp, cName, auth));
//			Utils.write(path + "Expand.vue", Expand.create(fList, cName, auth));
//
//			path = cond.base("vuex-iview", low, upp);
//			Utils.write(path + "Mock.js", VueMockJs.create(fList, lowUpp, cName, auth));
//			Utils.write(path + "List.vue", Page.create(fList, upp, cName, auth, lowUpp));
//			Utils.write(path + "Dialog.vue", Dialog.create(fList, cName, auth, lowUpp));
//			Utils.write(path + ".js", Js.create(fList, cName, auth, lowUpp));
//			Utils.write(path + "Expand.vue", Expand.create(fList, cName, auth));
//			Utils.write(path + "index.js", IndexJs.create(upp, low, lowUpp, cond.getModel()));
//
//			pName = cond.pack("android", low);
//			path = cond.base("android", low, upp);
//			Utils.write(path + "Request.java", Request.create(pName + low, upp, auth, cName, idType, lowUpp));
//			Utils.write(path + "Service.java", AppService.create(pName + low, upp, auth, cName, idType, lowUpp));
//			Utils.write(path + ".java", AppModel.create(pName + low, upp, fList, auth, cName));
//			Utils.write(path + "Cond.java", AppCondition.create(pName + low, upp, fList, auth, cName));
//		}
//	}

	/**
	 * @功能描述: 查询数据库中表名列表
	 */
	public List<Table> queryTables(CodeCond para) {
		List<Table> list = dao.queryTables(para);
		list.forEach(item -> {
			item.setCls_upp(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, Utils.delFirWord(item.getT_name())));
			item.setC_name(item.getComment());
		});
		return list;
	}

	/**
	 * @功能描述: 查询数据库中字段名列表
	 */
	public List<Field> queryFields(CodeCond cond) {
		return dao.queryFields(cond);
	}

	public void executeSql(String sql) {
		dao.executeSql(sql);
	}
}
