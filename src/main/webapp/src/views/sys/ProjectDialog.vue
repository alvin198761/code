<template>
    <el-dialog title="字段编辑" :visible.sync="show" width="80%">
        <el-row>
            <el-col span="8">实体类名:<b>{{entity.name}}</b></el-col>
            <el-col span="8">实体作者:<b>{{entity.author}}</b></el-col>
            <el-col span="8">实体注释:<b>{{entity.remark}}</b></el-col>
        </el-row>
        <el-table :data="entity.fields">
            <el-table-column prop="name" label="属性名"></el-table-column>
            <el-table-column prop="type" label="类型" width="150"></el-table-column>
            <el-table-column prop="ref_name" label="引用类" width="150"></el-table-column>
            <el-table-column prop="length" label="长度" width="150"></el-table-column>
            <el-table-column prop="remark" label="注释"></el-table-column>
            <el-table-column prop="isNull" label="为空" width="100">
                <template slot-scope="fprops">
                    {{fprops.row.isNull == 'NULL'? '空':'非空'}}
                </template>
            </el-table-column>
            <el-table-column   label="主键" width="50">
                <template slot-scope="fprops">
                    {{entity.idName == fprops.row.name ? '是' :''}}
                </template>
            </el-table-column>
            <el-table-column  label="标签" width="50">
                <template slot-scope="fprops">
                    {{entity.labelName == fprops.row.name ? '是' :''}}
                </template>
            </el-table-column>
            <el-table-column  label="操作" width="150">
                <template slot-scope="fprops">
                    <el-button type="text" size="small">删除</el-button>
                    <el-button type="text" size="small">追加</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div slot="footer">
            <el-button>取 消</el-button>
            <el-button type="primary">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>

    export default{
        components: {},
        data: function () {
            return {
                show: false,
                form: {
                    //属性名称
                    name: null,
                    //属性类型
                    type: null,
                    //引用类型对应的实体
                    ref_name: null,
                    //列名称
                    col_name: null,
                    //列备注
                    remark: null,
                    //数据长度
                    length: 50,
                    //数据库类型（包含长度）
                    sql_type: null,
                    //是否为空
                    isNull: 'NULL'
                },
                rules: {
                    name: [
                        {required: true, message: '请输入项目名称', trigger: 'blur'},
                    ],

                },
                types:[],
                project:{},
                entity:{},
                oldName: null
            }
        },
        computed: {},
        created: function () {

        },
        methods: {
            types(){
                const that = this;
                this.$http.get("/api/project/types").then(res =>{
                    that.types = res.data;
                }).catch(err => {
                    that.$message.error("获取类型失败")
                })
            },
            editDialog(project,entity){
                this.project = project;
                this.entity = entity;
                this.oldName = entity.name;
                if( this.entity.fields == null ||  this.entity.fields.length == 0){
                    this.entity.fields =[{}];
                }
                this.show = true;
            }
        }
    }
</script>
<style>

</style>
