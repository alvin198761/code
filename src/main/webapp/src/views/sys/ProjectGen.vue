<template>
    <div style="width: 1000px; margin-right: auto;margin-left: auto">
        <el-form inline ref="form" :model="form" :rules="rules" label-width="80px">
            <el-row>
                <el-col :span="8">
                    <el-form-item label="项目名称" prop="name">
                        <el-input v-model="form.name" placeholder="项目名称：如 test,demo"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="项目作者" prop="author">
                        <el-input v-model="form.author" placeholder="项目作者：如 唐植超"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="项目包名" prop="base_package">
                        <el-input v-model="form.base_package" placeholder="项目包名：如 org.alvin.code"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>

    export default{
        components: {},
        data: function () {
            return {
                form: {
                    name: '',
                    author: '唐植超',
                    base_package: 'org.alvin.home'
                },
                rules: {}
            }
        },
        computed: {},
        created: function () {

        },
        methods: {
            onSubmit(){
                this.$http.post("/api/project/gen",JSON.stringify(this.form)).then(res =>{
                    console.log(res.data)
                }).catch(res => {

                })
            }
        }
    }
</script>
<style>

</style>
