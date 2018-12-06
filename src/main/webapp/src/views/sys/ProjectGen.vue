<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="6">
                <el-input size="small"
                          placeholder="输入关键字进行过滤"
                          v-model="projectName">
                </el-input>

                <el-row style="margin-top: 10px" :gutter="20">
                    <el-col :span="12">
                        <el-button type="primary" style="width: 100%" @click="refresh" size="small">刷新</el-button>
                    </el-col>
                    <el-col :span="12">
                        <el-button size="small" style="width: 100%" @click="form ={ entitys:[{}]}">新增项目</el-button>
                    </el-col>
                </el-row>
                <el-card style="margin-top: 10px;">
                    <el-tree
                            :data="dataList"
                            :props="defaultProps"
                            default-expand-all
                            @node-click="clickNode"
                            ref="projectTree">
                    </el-tree>
                </el-card>
            </el-col>
            <el-col :span="18">
                <el-form :model="form" :rules="rules" label-width="100px">
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="项目名称" prop="name">
                                <el-input v-model="form.name" placeholder="请输入项目名称" size="small"
                                          autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="项目包名" prop="base_package">
                                <el-input v-model="form.base_package" placeholder="请输入项目包名" size="small"
                                          autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="项目作者" prop="author">
                                <el-input v-model="form.author" placeholder="请输入项目作者" size="small"
                                          autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col>
                            <el-form-item label="项目描述" prop="remark">
                                <el-input v-model="form.remark" placeholder="请输入项目作者" type="textarea" size="small"
                                          autocomplete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col>
                            <el-form-item label="实体类" prop="entitys">
                                <el-table :data="form.entitys" border
                                          size="small">
                                    <el-table-column type="expand">
                                        <template slot-scope="props">
                                            <el-table :data="props.row.fields">
                                                <el-table-column prop="name" label="属性名"></el-table-column>
                                                <el-table-column prop="type" label="类型" width="150"></el-table-column>
                                                <el-table-column prop="ref_name" label="引用类"
                                                                 width="150"></el-table-column>
                                                <el-table-column prop="length" label="长度" width="150"></el-table-column>
                                                <el-table-column prop="remark" label="注释"></el-table-column>
                                                <el-table-column prop="isNull" label="为空" width="100">
                                                    <template slot-scope="fprops">
                                                        {{fprops.row.isNull == 'NULL'? '空':'非空'}}
                                                    </template>
                                                </el-table-column>
                                                <el-table-column label="主键" width="50">
                                                    <template slot-scope="fprops">
                                                        {{props.row.idName == fprops.row.name ? '是' :''}}
                                                    </template>
                                                </el-table-column>
                                                <el-table-column label="标签" width="50">
                                                    <template slot-scope="fprops">
                                                        {{props.row.labelName == fprops.row.name ? '是' :''}}
                                                    </template>
                                                </el-table-column>
                                            </el-table>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="name" label="类名" width="150">
                                        <template slot-scope="props">
                                            <el-input v-model="props.row.name" size="small"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="table_name" label="表名" width="150">
                                        <template slot-scope="props">
                                            <el-input v-model="props.row.table_name" size="small"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="author" label="作者" width="150">
                                        <template slot-scope="props">
                                            <el-input v-model="props.row.author" size="small"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="date" label="时间" width="200">
                                        <template slot-scope="props">
                                            <el-input v-model="props.row.date" size="small"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="remark" label="注释">
                                        <template slot-scope="props">
                                            <el-input v-model="props.row.remark" size="small"></el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="操作">
                                        <template slot-scope="props">
                                            <el-button type="text" size="small" @click="editDialog(props.row)">编辑属性
                                            </el-button>
                                            <el-button type="text" size="small"
                                                       @click="removeArray(form.entitys,props.row)">删除
                                            </el-button>
                                            <el-button type="text" size="small" @click="form.entitys.push({})">追加
                                            </el-button>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label="操作">
                        <el-button type="primary" @click="save">保存</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <ProjectDialog ref="dialog" :saveProject="save"></ProjectDialog>
    </div>
</template>
<script>
    import ProjectDialog from './EntityDialog.vue';
    export default{
        components: {ProjectDialog},
        data: function () {
            return {
                dataList: [],
                loading: false,
                projectName: '',
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                form: {
                    name: '',
                    base_package: '',
                    author: '',
                    date: null, //创建日期
                    entitys: [], //实体类列表
                    remark: '' //备注
                },
                rules: {
                    name: [
                        {required: true, message: '请输入项目名称', trigger: 'blur'},
                    ],
                    base_package: [
                        {required: true, message: '请输入项目包名', trigger: 'blur'},
                    ],
                    author: [
                        {required: true, message: '请输入项目作者', trigger: 'blur'},
                    ]
                },
            }
        },
        computed: {},
        created: function () {
            this.refresh();
        },
        methods: {
            refresh(){
                this.form ={ entitys:[{}]};
                const that = this;
                that.loading = true;
                this.$http.post("/api/project/list", JSON.stringify({})).then(res => {
                    that.dataList = res.data.map(pro =>{
                        pro.entitys = pro.entitys.map(entity =>{
                            entity.fields = entity.fields.map(field =>{
                                field.isNullChecked = field.isNull == 'NULL'
                                field.isPrimaryKeyChecked = entity.idName == field.name;
                                field.isLabelChecked = entity.labalName == field.name;
                                return field;
                            });
                            return entity;
                        })
                        return pro;
                    });
                    that.loading = false;
                }).catch(res => {
                    that.$message.error("获取项目列表失败")
                    that.loading = false;
                })
            },
            onSubmit(){

            },
            editDialog(row){
                this.$refs["dialog"].editDialog(this.form, row);
            },
            clickNode(data){
                this.form = {...data};
            },
            removeArray (_arr, _obj) {
                var length = _arr.length;
                for (var i = 0; i < length; i++) {
                    if (_arr[i] == _obj) {
                        if (i == 0) {
                            _arr.shift(); //删除并返回数组的第一个元素
                            break;
                        }
                        else if (i == length - 1) {
                            _arr.pop();  //删除并返回数组的最后一个元素
                            break;
                        }
                        else {
                            _arr.splice(i, 1); //删除下标为i的元素
                            break;
                        }
                    }
                }
                if (_arr.length == 0) {
                    _arr.push({});
                }
            },
            save(){
                const that = this;
                that.$http.post("/api/project/save", JSON.stringify(this.form)).then(res => {
                }).catch(res => {
                    this.$message.error("保存出错");
                });
            }
        }
    }
</script>
<style>

</style>
