package com.gzz.createcode.test;

import java.util.Date;
import java.util.List;

import com.gzz.createcode.common.base.BaseCondition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:测试用户查询条件实体类
 * @author:高振中
 * @date:2018-03-21 09:09:51
 **/
@Setter
@Getter
@ApiModel(value = "SysUserCond", description = "用户查询条件实体")
public class SysUserCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, "AND t.id = ?");
		add(birthday, "AND t.birthday = ?");
		add(giving, "AND t.giving = ?");
		add(enable, "AND t.enable = ?");
		add(name, "AND t.name LIKE ?", 3);
		add(ids, "AND t.id IN ");
	}

	// 查询条件,把不用的条件清理掉
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id;// 主键
	@ApiModelProperty(value = "生日", dataType = "Date")
	private Date birthday;// 生日
	@ApiModelProperty(value = "赠送金额", dataType = "Float")
	private Float giving;// 赠送金额
	@ApiModelProperty(value = "状态", dataType = "Byte")
	private Byte enable;// 状态
	@ApiModelProperty(value = "储值卡名称", dataType = "String")
	private String name;// 储值卡名称
	@ApiModelProperty(value = "主键数组", dataType = "List<Long>")
	private List<Long> ids;// 主键列表

}