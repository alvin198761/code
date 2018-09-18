<template>
    <Modal v-model="show" title="创建表" width="800" @on-cancel="show=false">
        <div>
            <Input type="textarea" :rows="15" v-model="sql"/>
        </div>
        <div slot="footer">
            <Button :disabled="!sql" style="width: 300px" type="primary" @click="doRun">执行</Button>
        </div>
    </Modal>
</template>
<script>
import {executeSql} from '../../apis/Apis';
    export default{
        props: ['refresh'],
        data: function () {
            return {
                show:false,
                sql: null
            }
        },
        computed: {},
        created: function () {

        },
        methods: {
            showDialog(){
                this.show = true;
            },
            doRun(){
                executeSql(this.sql).then(res => {
                    that.$Message.success({
                        content: '执行成功'
                })
                    that.refresh();
                    that.show = false;
                }).catch(res => {
                    that.$Message.error({
                        content: '执行出错' + res
                })
                })
            }
        },
        components: {}
    }
</script>
<style>

</style>
