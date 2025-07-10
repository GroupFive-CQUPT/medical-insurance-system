<template>
  <div class="forgot-password-page">
    <div class="forgot-container">
      <div class="forgot-title">
        <h1>找回密码</h1>
        <p>请输入您的手机号，我们将发送验证码帮助您重置密码</p>
      </div>

      <el-form
          class="forgot-form"
          :model="forgotForm"
          ref="forgotFormRef"
          :rules="forgotRules">

        <div class="form-item">
          <label>手机号</label>
          <el-input
              v-model="forgotForm.phone"
              placeholder="请输入注册时的手机号"
              prefix-icon="el-icon-phone"
              clearable
              prop="phone">
          </el-input>
        </div>

        <div class="form-item">
          <label>验证码</label>
          <div class="verify-code-container">
            <el-input
                v-model="forgotForm.verifyCode"
                placeholder="请输入验证码"
                prefix-icon="el-icon-message"
                prop="verifyCode">
            </el-input>
            <el-button
                :disabled="countdown > 0"
                @click="sendVerifyCode"
                class="send-code-btn">
              {{ countdown > 0 ? `${countdown}s后重发` : '发送验证码' }}
            </el-button>
          </div>
        </div>

        <div class="form-item">
          <label>新密码</label>
          <el-input
              v-model="forgotForm.newPassword"
              type="password"
              placeholder="请输入新密码"
              prefix-icon="el-icon-lock"
              show-password
              prop="newPassword">
          </el-input>
        </div>

        <div class="form-item">
          <label>确认密码</label>
          <el-input
              v-model="forgotForm.confirmPassword"
              type="password"
              placeholder="请再次输入新密码"
              prefix-icon="el-icon-lock"
              show-password
              prop="confirmPassword">
          </el-input>
        </div>

        <el-button
            class="reset-button"
            type="primary"
            @click="handleReset"
            :loading="loading">
          重置密码
        </el-button>

        <div class="back-login">
          <el-link type="primary" @click="goToLogin">
            <el-icon><ArrowLeft /></el-icon>
            返回登录
          </el-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { ArrowLeft } from '@element-plus/icons-vue'

export default {
  name: 'ForgotPassword',
  components: {
    ArrowLeft
  },
  data() {
    const validateConfirmPassword = (rule, value, callback) => {
      if (value !== this.forgotForm.newPassword) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }

    return {
      loading: false,
      countdown: 0,
      forgotForm: {
        phone: '',
        verifyCode: '',
        newPassword: '',
        confirmPassword: ''
      },
      forgotRules: {
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
        ],
        verifyCode: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          { len: 6, message: '验证码为6位数字', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: validateConfirmPassword, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    sendVerifyCode() {
      if (!this.forgotForm.phone) {
        ElMessage.error('请先输入手机号');
        return;
      }

      this.countdown = 60;
      const timer = setInterval(() => {
        this.countdown--;
        if (this.countdown === 0) {
          clearInterval(timer);
        }
      }, 1000);

      ElMessage.success('验证码已发送');
    },
    handleReset() {
      this.$refs.forgotFormRef.validate((valid) => {
        if (valid) {
          this.loading = true;
          // 模拟重置密码API调用
          setTimeout(() => {
            this.loading = false;
            ElMessage.success('密码重置成功！请使用新密码登录');
            this.$router.push('/login');
          }, 1500);
        } else {
          ElMessage.error('请检查输入信息');
        }
      });
    },
    goToLogin() {
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>
.forgot-password-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.forgot-container {
  width: 450px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  padding: 40px;
  position: relative;
  overflow: hidden;
}

.forgot-container::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 4px;
  background: linear-gradient(90deg, #409eff, #67c23a);
}

.forgot-title {
  text-align: center;
  margin-bottom: 30px;
}

.forgot-title h1 {
  font-size: 24px;
  color: #303133;
  margin-bottom: 8px;
  font-weight: 600;
}

.forgot-title p {
  font-size: 14px;
  color: #909399;
  line-height: 1.5;
  margin: 0;
}

.form-item {
  margin-bottom: 24px;
}

.form-item label {
  display: block;
  margin-bottom: 8px;
  color: #606266;
  font-size: 14px;
  font-weight: 500;
}

.verify-code-container {
  display: flex;
  gap: 10px;
}

.verify-code-container .el-input {
  flex: 1;
}

.send-code-btn {
  white-space: nowrap;
  min-width: 100px;
}

.reset-button {
  width: 100%;
  height: 48px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 8px;
  background: linear-gradient(90deg, #409eff, #67c23a);
  border: none;
  margin-top: 20px;
}

.back-login {
  text-align: center;
  margin-top: 20px;
}
</style>