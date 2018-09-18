<template>
  <div>
    <Form ref="form" :model="form" :rules="rules" inline>
      <FormItem prop="search">
        <Input type="text" v-model="form.search" placeholder="名称/前端过滤">
        </Input>
      </FormItem>
      <FormItem prop="url">
        <Input type="text" v-model="form.url" placeholder="app center 地址">
        </Input>
      </FormItem>
      <!--<FormItem prop="token">-->
      <!--<Input v-model="form.token" placeholder="正常完整的token">-->
      <!--</Input>-->
      <!--</FormItem>-->
      <FormItem>
        <Button type="primary" @click="refresh">刷新</Button>
        <Button type="primary" @click="genCode" :disabled="selectionRows.length == 0">生成</Button>
      </FormItem>
    </Form>
    <hr/>
    <Table border ref="selection" :columns="columns" :data="data" @on-selection-change="selectionData"></Table>
  </div>
</template>
<script>

  export default{
    data: function () {
      return {
        form: {
          search: '',
          url: 'http://localhost:8003/v2/api-docs',
//          token: 'Bearer+eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMDMwNCIsImNyZWF0ZWQiOjE1MjIxMTcxNjU0MTMsImV4cCI6MTUyOTg5MzE2NX0.TXtlWAt5JK4B2_GjYoi8_Q6YtsjAaCLxHlxhLQmuNeL1ZfCsv4y8LsGws4yW64FSk82FaUl0ncD8pKP8stL5HA'
        },
        rules: {
          url: [{required: true, message: '请输入访问地址', trigger: 'blur'}
          ],
//          token: [{required: true, message: '请输入token', trigger: 'blur'}
//          ]
        },
        columns: [{
          type: 'selection',
          width: 60,
          align: 'center'
        }, {
          title: 'controller 名称',
          key: 'name'
        }, {
          title: '描述',
          key: 'description'
        }
        ],
        data: [],
        selectionRows: []
      }
    },
    computed: {},
    created: function () {

    },
    methods: {
      refresh(){
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (valid) {
            that.$http.post("/api/msiteMock/queryList", JSON.stringify(that.form)).then(res => {
              that.data = res.data;
            });
          }
        })
      },
      genCode(){
        const that = this;
        this.$refs['form'].validate((valid) => {
          if (valid) {
            const requestData = {
              url: that.form.url,
              token: that.form.token,
              tags: that.selectionRows
            }
            that.$http.post("/api/msiteMock/gencode", JSON.stringify(requestData)).then(res => {
              location.href = "/api/msiteMock/download?filePath=" + res.data;
            });
          }
        });
      },
      selectionData(selectRows){
        this.selectionRows = selectRows;
      }
    },
    components: {}
  }
</script>
<style>

</style>
