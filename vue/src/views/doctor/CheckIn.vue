<template>
  <div class="admission-registration">
    <!-- 页面标题 -->
    <div class="page-header">
      <el-icon><Document /></el-icon>
      <h1>入院登记</h1>
    </div>

    <!-- 主要操作按钮 -->
    <div class="action-buttons">
      <el-button type="primary" @click="handleSubmit">登记</el-button>
      <el-button @click="handleReset">清空</el-button>
    </div>

    <!-- 表单部分 -->
    <el-form
        ref="admissionFormRef"
        :model="admissionForm"
        :rules="formRules"
        label-width="100px"
        label-position="left"
    >
      <!-- 基本信息部分 -->
      <div class="form-section">
        <h2>基本信息</h2>
        <div class="form-row">
          <el-form-item label="住院号" prop="hospitalNo" class="required">
            <el-input v-model="admissionForm.hospitalNo" placeholder="请输入住院号"></el-input>
          </el-form-item>

          <el-form-item label="姓名" prop="name" class="required">
            <el-input v-model="admissionForm.name" placeholder="请输入姓名"></el-input>
          </el-form-item>

          <el-form-item label="身份证号" prop="idCard" class="required">
            <el-input v-model="admissionForm.idCard" placeholder="请输入身份证号"></el-input>
          </el-form-item>
        </div>

        <div class="form-row">
          <el-form-item label="年龄" prop="age" class="required">
            <el-input-number v-model="admissionForm.age" :min="0" :max="150"></el-input-number>
            <span class="unit">岁</span>
          </el-form-item>

          <el-form-item label="出生日期" prop="birthDate" class="required">
            <el-date-picker
                v-model="admissionForm.birthDate"
                type="date"
                placeholder="请输入出生日期"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>

          <el-form-item label="性别" prop="gender" class="required">
            <el-select v-model="admissionForm.gender" placeholder="请选择性别">
              <el-option label="男" value="male"></el-option>
              <el-option label="女" value="female"></el-option>
              <el-option label="未知" value="unknown"></el-option>
            </el-select>
          </el-form-item>
        </div>

        <div class="form-row">
          <el-form-item label="家庭住址">
            <el-input
                v-model="admissionForm.address"
                placeholder="请输入家庭住址(可选)"
                type="textarea"
                :rows="2"
            ></el-input>
          </el-form-item>
        </div>
      </div>

      <!-- 登记信息部分 -->
      <div class="form-section">
        <h2>登记信息</h2>
        <div class="form-row">
          <el-form-item label="结算类别" prop="settlementType" class="required">
            <el-select v-model="admissionForm.settlementType" placeholder="请选择结算类别">
              <el-option label="医保" value="medical"></el-option>
              <el-option label="自费" value="self"></el-option>
              <el-option label="其他" value="other"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="工作状态" prop="workStatus" class="required">
            <el-select v-model="admissionForm.workStatus" placeholder="请选择工作状态">
              <el-option label="在职" value="employed"></el-option>
              <el-option label="退休" value="retired"></el-option>
              <el-option label="无业" value="unemployed"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="入院时间" prop="admissionTime" class="required">
            <el-date-picker
                v-model="admissionForm.admissionTime"
                type="datetime"
                placeholder="选择入院时间"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD HH:mm:ss"
            ></el-date-picker>
          </el-form-item>
        </div>
      </div>
    </el-form>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { Document } from '@element-plus/icons-vue'

export default {
  name: 'CheckIn',
  components: {
    Document
  },
  data() {
    return {
      // 表单引用不需要在 data 中定义，通过 ref 访问
      // 表单数据
      admissionForm: {
        hospitalNo: '',
        name: '',
        idCard: '',
        age: null,
        birthDate: null,
        gender: '',
        address: '',
        settlementType: '',
        workStatus: '',
        admissionTime: null
      },
      // 表单验证规则
      formRules: {
        hospitalNo: [{ required: true, message: '请输入住院号', trigger: 'blur' }],
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        idCard: [{ required: true, message: '请输入身份证号', trigger: 'blur' }],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        birthDate: [{ required: true, message: '请选择出生日期', trigger: 'change' }],
        gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
        settlementType: [{ required: true, message: '请选择结算类别', trigger: 'change' }],
        workStatus: [{ required: true, message: '请选择工作状态', trigger: 'change' }],
        admissionTime: [{ required: true, message: '请选择入院时间', trigger: 'change' }]
      }
    }
  },
  methods: {
    // 表单提交处理
    handleSubmit() {
      this.$refs.admissionFormRef.validate((valid) => {
        if (valid) {
          ElMessage.success('入院登记成功！')
          // 这里可以添加表单提交逻辑
          console.log('提交的表单数据：', this.admissionForm)
        } else {
          ElMessage.error('请填写完整必填项！')
          return false
        }
      })
    },
    // 表单重置处理
    handleReset() {
      this.$refs.admissionFormRef.resetFields()
    }
  }
}
</script>

<style scoped>
.admission-registration {
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

.form-section {
  margin-bottom: 25px;
}

.form-section h2 {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 15px;
  padding-bottom: 10px;
  border-bottom: 1px solid #f0f0f0;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px;
}

.form-row > * {
  padding-right: 15px;
  padding-left: 15px;
  box-sizing: border-box;
}

:deep(.el-form-item) {
  width: 100%;
  margin-bottom: 20px;
}

:deep(.el-form-item__content) {
  width: 100%;
}

@media (min-width: 768px) {
  .form-row > * {
    width: 33.33%;
  }
}

@media (min-width: 1200px) {
  .form-row > * {
    width: 33.33%;
  }
}

@media (min-width: 1920px) {
  .form-row > * {
    width: 25%;
  }
}

.required :deep(.el-form-item__label):before {
  content: '*';
  color: #f56c6c;
  margin-right: 4px;
}

.unit {
  margin-left: 5px;
  color: #606266;
}
</style>