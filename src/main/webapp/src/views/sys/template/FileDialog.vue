<template>
    <el-dialog title="文件设置" :visible.sync="show" width="80%">
        <el-form :model="form" :rules="rules" ref="form" label-width="100px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="文件名称" prop="name">
                        <el-input v-model="form.name" placeholder="输入文件名" size="small"
                                  autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="文件类型" prop="type">
                        <el-select v-model="form.type"  size="small" placeholder="请选择文件类型">
                            <el-option label="普通模板" :value="1"></el-option>
                            <el-option label="实体模板" :value="2"></el-option>
                            <el-option label="非模板文件" :value="3"></el-option>
                            <el-option label="目录" :value="4"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="20">
                <el-col>
                    <el-form-item label="文件内容" prop="remark">
                        <el-tabs v-model="contentTab">
                            <el-tab-pane label="用户管理" name="content">
                                <el-input v-model="form.remark" placeholder="请输入模板描述" rows="10" type="textarea"
                                          size="small"
                                          autocomplete="off"></el-input>
                            </el-tab-pane>
                            <el-tab-pane label="配置管理" name="file">
                                <el-upload
                                        size="small"
                                        class="upload-demo"
                                        drag
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        multiple>
                                    <i class="el-icon-upload"></i>
                                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                    <div class="el-upload__tip" slot="tip">文件不得超过10m</div>
                                </el-upload>
                            </el-tab-pane>
                        </el-tabs>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer">
            <el-button @click="show = false"  size="small">取 消</el-button>
            <el-button type="primary" @click="save"  size="small">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>

    export default{
        components: {},
        data: function () {
            return {
                show: false,
                form: {},
                rules: {},
                contentTab: 'content'
            }
        },
        computed: {},
        created: function () {

        },
        methods: {
            save(){

            },
            addDialog(){
                this.show = true;
            }
        }
    }
</script>
<style>

</style>
