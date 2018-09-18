import http from '../utils/http';
//获取所有的表
export const queryTable = (table) => {
    return http.post("/code/queryList",JSON.stringify(table || {}));
}

//执行sql语句
export const executeSql = (sql) => {
    return http.get("/code/executeSql",{params: sql || ''});
}

//执行生成代码
export const gencode = (data) => {
    return http.post("code/create",JSON.stringify(data));
}
