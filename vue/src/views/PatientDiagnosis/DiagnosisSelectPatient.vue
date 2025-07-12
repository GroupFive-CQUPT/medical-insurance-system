<template>
  <div class="diagnosis-patient">
    <!-- 页面标题 -->
    <div class="page-header">
      <el-icon><Document /></el-icon>
      <h1>诊断患者选择: {{ selectedPatientName }}</h1>
    </div>

    <!-- 主要操作按钮 -->
    <div class="action-buttons">
      <el-button type="primary" @click="addAdmissionDiagnosis">增加入院诊断</el-button>
      <el-button type="primary" @click="addMajorDiagnosis">增加主要诊断</el-button>
      <el-button type="primary" @click="addOtherDiagnosis">增加其他诊断</el-button>
    </div>

    <!-- 搜索框功能 -->
    <div class="search-section">
      <el-input
        v-model="searchName"
        placeholder="患者姓名"
        clearable
        @keyup.enter="handleSearch"
        style="width: 200px; margin-right: 10px;"
      ></el-input>
      <el-button type="primary" @click="handleSearch">搜索</el-button>
    </div>

    <!-- 患者列表 -->
    <div class="patient-list">
      <el-table
        :data="patientTableData"
        border
        style="width: 100%"
        @row-click="handleRowClick"
      >
        <el-table-column type="index" width="50" label="序号"></el-table-column>
        <el-table-column prop="hospitalNo" label="住院号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="idCard" label="身份证号"></el-table-column>
        <el-table-column prop="gender" label="性别"></el-table-column>
        <el-table-column prop="workStatus" label="工作状态"></el-table-column>
        <el-table-column prop="settlementType" label="结算类别"></el-table-column>
        <el-table-column prop="admissionTime" label="入院时间"></el-table-column>
        <el-table-column align="center" fixed="right" label="操作" width="220">
          <template #default="scope">
            <el-button type="primary" icon="Edit" link @click="pick(scope.row)">选中</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination
        background
        layout="prev, pager, next"
        :total="page.total"
        :page-size="page.pageSize"
        @current-change="handleCurrentChange"
        style="margin-top: 20px; text-align: center;"
      ></el-pagination>
    </div>

    <!-- 患者详细信息 -->
    <div class="patient-details" v-if="selectedPatient">
      <h2>患者详细信息</h2>
      <el-row :gutter="20" style="margin-top: 15px;">
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">住院号：</span>
            <span class="value">{{ selectedPatient.hospitalNo }}</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">姓名：</span>
            <span class="value">{{ selectedPatient.name }}</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">身份证号：</span>
            <span class="value">{{ selectedPatient.idCard }}</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">年龄：</span>
            <div class="age-select">
              <el-input-number v-model="selectedPatient.age" :min="0" style="width: 80px;"></el-input-number>
              <span class="unit">岁</span>
            </div>
          </div>
        </el-col>
      </el-row>

      <el-row :gutter="20" style="margin-top: 15px;">
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">出生日期：</span>
            <span class="value">{{ selectedPatient.birthDate }}</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">性别：</span>
            <el-select v-model="selectedPatient.gender" placeholder="请选择性别" style="width: 100%;">
              <el-option label="男" value="male"></el-option>
              <el-option label="女" value="female"></el-option>
              <el-option label="未知" value="unknown"></el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">结算类别：</span>
            <el-select v-model="selectedPatient.settlementType" placeholder="请选择结算类别" style="width: 100%;">
              <el-option label="医保" value="medical"></el-option>
              <el-option label="自费" value="self"></el-option>
              <el-option label="其他" value="other"></el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">工作状态：</span>
            <el-select v-model="selectedPatient.workStatus" placeholder="请选择工作状态" style="width: 100%;">
              <el-option label="在职" value="employed"></el-option>
              <el-option label="退休" value="retired"></el-option>
              <el-option label="无业" value="unemployed"></el-option>
            </el-select>
          </div>
        </el-col>
      </el-row>

      <el-row :gutter="20" style="margin-top: 15px;">
        <el-col :span="6">
          <div class="detail-item">
            <span class="label">入院时间：</span>
            <span class="value">{{ selectedPatient.admissionTime }}</span>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="detail-item">
            <span class="label">家庭住址：</span>
            <span class="value">{{ selectedPatient.address }}</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { Document } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

export default {
  components: {
    Document
  },
  data() {
    return {
      searchName: '',
      patientTableData: [],
      selectedPatient: {},
      selectedPatientName: '',
      // 分页属性封装
      page: {
        total: 0,
        pageSize: 10,
        currentPag: 1,
        pagCount: 0
      },
      queryParams: {
        name: '',
        pageSize: 1
      }
    }
  },
  mounted() {
    this.getPatientList()
  },
  methods: {
    // 处理表格行点击事件
    handleRowClick(row) {
      this.selectedPatient = {
        ...row,
        age: row.age || 0,
        birthDate: row.birthDate || '',
        address: row.address || ''
      }
      this.selectedPatientName = row.name
    },
    // 搜索框表单提交
    handleSearch() {
      if (!this.searchName) {
        ElMessage.warning('请输入患者姓名进行搜索')
        return
      }
      this.queryParams.name = this.searchName
      this.queryParams.pageSize = 1
      this.getPatientList()
      ElMessage.success(`正在搜索: ${this.searchName}`)
    },
    // 增加入院诊断
    addAdmissionDiagnosis(row) {
      this.$router.push({
        path:"/PatientDiagnosis/AdmissionDiagnosis",
        query:{patientId:row.patientId,patientName:row.patientName},
      })
    },
    // 增加主要诊断
    addMajorDiagnosis(row) {
      this.$router.push({
        path:"/PatientDiagnosis/MajorDiagnosis",
        query:{patientId:row.patientId,patientName:row.patientName},
      })
    },
    // 增加其他诊断
    addOtherDiagnosis(row) {
      this.$router.push({
        path:"/PatientDiagnosis/OtherDiagnosis",
        query:{patientId:row.patientId,patientName:row.patientName},
      })
    },
    // 获取患者列表
    getPatientList() {
      // 这里添加获取患者列表的 API 调用逻辑
      // })
    },
    // 选中页码
    handleCurrentChange(curPage) {
      this.page.currentPag = curPage
      this.queryParams.pageSize = curPage
      this.getPatientList()
    }
    // 这里添加页面初始化逻辑
  }
}

</script>

<style scoped>
.diagnosis-patient {
  padding: 20px;
}

.page-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #e8e8e8;
}

.page-header .el-icon {
  margin-right: 10px;
  color: #606266;
}

.action-buttons {
  margin-bottom: 20px;
}

.search-section {
  margin-bottom: 20px;
}

.patient-list {
  margin-bottom: 30px;
}

.patient-details h2 {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #f0f0f0;
}

.detail-item {
  display: flex;
  align-items: center;
}

.label {
  min-width: 100px;
  font-weight: bold;
}

.value {
  flex: 1;
}

.age-select {
  display: flex;
  align-items: center;
}

.unit {
  margin-left: 5px;
  color: #606266;
}
</style>