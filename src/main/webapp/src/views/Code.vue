<template>
    <div>
        <br/>
        <Form inline :label-width="61">
            <FormItem label="查询"><Input placeholder="表名/注释/类名/中文名" v-model="search"  /></FormItem>
            <FormItem :label-width="5">
                <Button icon="search" @click="refresh" title="根据输入的条件查询" type="primary" >刷新</Button>
            </FormItem>
            <FormItem label="作者">
                <Input placeholder="作者" v-model="form.auth"/>
            </FormItem>
            <FormItem label="公司名">
                <Input placeholder="公司名"  v-model="form.company"/>
            </FormItem>
            <FormItem label="模块名">
                <Input placeholder="模块名"  v-model="form.model"/>
            </FormItem>
            <FormItem :label-width="5">
            <Button  @click="doGenCode" title="根据勾选的表生成代码"  :disabled="canGen" >生成代码</Button>
            </FormItem>
            <FormItem :label-width="5">
                <Button title="创建表"   @click="showSqlCmdDialog">创建表</Button>
            </FormItem>
        </Form>
        <table border="0" cellpadding="6" cellspacing="6" width="100%">
            <tr>
                <th width="80" style="text-align: left">
                    <!--<Checkbox-->
                        <!--v-model="checkAll" :indeterminate="false" @on-change="checkAllTable"-->
                        <!--&gt;全选</Checkbox>-->
                </th>
                <th style="text-align: left">表名</th>
                <th style="text-align: left">注释</th>
                <th style="text-align: left">类名</th>
                <th style="text-align: left">中文名</th>
                <th style="text-align: left">操作</th>
            </tr>
            <tr v-for="item in getDataList" :key="item.id">
                <td>
                    <Checkbox v-model="item.selected"  :indeterminate="false"></Checkbox>
                </td>
                <td>{{item.t_name}}</td>
                <td>{{item.comment}}</td>
                <td><Input v-model="item.cls_upp"/></td>
                <td><Input v-model="item.c_name"/></td>
                <td>
                    <Button type="primary" @click="genThatClass(item)">单个生成</Button>
                </td>
            </tr>

        </table>
        <SqlCmdDialog ref="dialog" :refresh="refresh"></SqlCmdDialog>
    </div>
</template>
<script>
    import SqlCmdDialog from './code/SqlCmdDialog.vue';
    import {queryTable,gencode} from '../apis/Apis';
    export default {
        data (){
            return {
                dataList: [],
                loading: false,
                search: null,
                form:{
                    auth: 'gzz',
                    c_list: [],
                    company:"dl",
                    model:"card"
                },
                checkAll:false
            }
        },
        created(){
            this.refresh();
        },
        computed:{
            getDataList(){
                if(!this.search){
                    return this.dataList;
                }
                const that = this;
                return this.dataList.filter(item => {
                    return item.t_name.indexOf(that.search) != -1 ||
                            item.comment.indexOf(that.search) != -1 ||
                            item.cls_upp.indexOf(that.search) != -1||
                            item.c_name.indexOf(that.search) != -1
                    })

            },
            canGen(){
                return this.dataList.filter(item => item.selected == true).length == 0
            }
        },
        methods:{
            refresh(){
                const that = this;
                that.loading = true;
                queryTable().then(res => {
                    that.dataList = res.data.map(item => {
                        item.selected =false;
                        return item;
                    });
                that.loading = false;
                }).catch(res => {
                    that.$Message.error({
                        content: '获取信息失败'
                })
                that.loading = false;
                })
            },
            showSqlCmdDialog(){
                this.$refs['dialog'].showDialog();
            },
            doGenCode(){
                const that = this;
                const cList = this.dataList.filter(item =>   item.selected == true);
                this.form = {...this.form ,c_list : cList};
                gencode(this.form).then(res => {
                    that.$Message.success({
                        content: '生成成功'
                     })
                    location.href = "/code/downCode";
                }).catch(res => {
                    that.$Message.error({
                        content: '生成失败'
                    })
                })
            },
            genThatClass(item){
                const that = this;
                this.form = {...this.form ,c_list : [item]};
                gencode(this.form).then(res => {
                    that.$Message.success({
                    content: '生成成功'
                })
                location.href = "/code/downCode";
            }).catch(res => {
                    that.$Message.error({
                    content: '生成失败'
                })
            })
            }

        },
        components:{
            SqlCmdDialog
        }
    }
</script>
<style></style>
