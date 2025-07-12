<template>
  <div class="common-layout">
    <el-header>
      <strong>客户姓名: {{ selectedPatientName }}</strong>
    </el-header>
    <el-container>
      <el-row :gutter="30">
        处方药品医嘱:
      </el-row>
      <el-header>
        <div>
          <el-row :gutter="30">
            <el-col :span="7">
              <el-input
                  placeholder="名称"
                  v-model="queryParams.medicineName"
                  @clear="query"
                  clearable
                  size="large"
              >
                <template #append>
                  <el-button type="info" @click="query" style="color:black">搜索</el-button>
                </template>
              </el-input>
            </el-col>
          </el-row>
        </div>
      </el-header>
      <el-divider style="margin:0"></el-divider>
      <el-main>
        <div>
          <!-- 表格 -->
          <el-table :data="medicineList" style="width: 100% ;color:black;" stripe>
            <el-table-column
                align="center"
                type="index"
                :index="indexMethod"
                label="序号"
                width="60"
            />
            <el-table-column align="center" prop="id" label="编号" width="120" />
            <el-table-column align="center" prop="medicineName" label="药品中文名称" width="120" />
            <el-table-column align="center" prop="unit" label="规格" width="120" />
            <el-table-column align="center" prop="icdCode" label="生产企业" width="180" />
            <el-table-column align="center" prop="medicinePrice" label="价格" width="120" />
            <el-table-column align="center" fixed="right" label="操作" width="220">
              <template #default="scope">
                <el-button type="primary" icon="Edit" link @click="addItem(scope.row)">增加药品</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- 分页插件 -->
        <div style="margin-top:15px">
          <!--
              page-size:每页显示条目个数
              current-page:页码 配合@current-change使用
              disabled:是否禁用分页
              background:是否为分页按钮添加背景色
              layout:组件布局，子组件名用逗号分隔
              total:总条目数-->
          <el-pagination
              :page-size="page.pageSize"
              background
              :current-page="page.currentPag"
              layout=" prev, pager, next"
              :total="page.total"
              @current-change="handleCurrentChange"
          />
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { findAllmedicine } from "@/api/medicineApi.js";
export default {
  computed: {
    indexMethod() {
      return this.page.currentPag * this.page.pageSize - this.page.pageSize + 1;
    }
  },
  data() {
    return {
      PatientId:this.$route.query.PatientId,
      selectedPatientName:this.$route.query.patientName,
      //分页属性封装
      page: {
        total: 0,
        pageSize: 6,
        currentPag: 1,
        pagCount: 0
      },
      btnFlag: true,
      queryParams: {
        medicineName:"",
        status: "1", //查询默认状态1 -启用
        itemName: "",
        pageSize: "1" //默认第一页
      },
      medicineList: []
    };
  },
  mounted() {
    this.getMedicineList();

  },
  methods: {
    //点击查询
    query() {
      this.queryParams.pageSize = "1"; //回到第一页
      this.getMedicineList();
    },
    //选中页码
    handleCurrentChange(curPage) {
      this.page.currentPag = curPage;
      this.queryParams.pageSize = curPage; //参数pageSize是服务端接收页码参数名
      //重新渲染表格
      this.getMedicineList();
    },

    //点击添加按钮
    addItem() {
      this.dialog.tops = "增加";
      this.dialog.dialogVisible = true;
    },
    handleClose() {
      this.dialog.dialogVisible = false;
      this.resetForm("itemForm"); //重置表单
    },
    //重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //api-保存医嘱到病人医嘱
    save(formName) {
      this.$refs[formName].validate(valid => {

      });
    },

    //api-查询医嘱项目(分页)
    getMedicineList() {
      findAllmedicine(this.queryParams).then(res => {
        this.drugList = res.data.records;
        this.page.total = res.data.total; //总记录数
        this.page.pageSize = res.data.size; //每页显示条数
        this.page.currentPag = res.data.current; //当前页码
        this.page.pagCount = res.data.pages; //总页数
      });
    }
  }
};
</script>

<style scoped >
.activeBtn {
  color: #1890ff;
  background: rgb(232, 244, 255);
}
</style>