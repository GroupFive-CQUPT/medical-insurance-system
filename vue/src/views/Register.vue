<template>
  <div class="register-page">
    <div class="register-container">
      <div class="register-title">
        <h1>用户注册</h1>
        <p>创建您的医疗保险报销中心账户</p>
      </div>

      <el-form
          class="register-form"
          :model="registerForm"
          ref="registerFormRef"
          :rules="registerRules">

        <div class="form-item">
          <label>用户名</label>
          <el-input
              v-model="registerForm.username"
              placeholder="请输入用户名"
              prefix-icon="el-icon-user"
              clearable
              prop="username">
          </el-input>
        </div>

        <div class="form-item">
          <label>手机号</label>
          <el-input
              v-model="registerForm.phone"
              placeholder="请输入手机号"
              prefix-icon="el-icon-phone"
              clearable
              prop="phone">
          </el-input>
        </div>

        <div class="form-item">
          <label>验证码</label>
          <div class="verify-code-container">
            <el-input
                v-model="registerForm.verifyCode"
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
          <label>密码</label>
          <el-input
              v-model="registerForm.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
              show-password
              prop="password">
          </el-input>
        </div>

        <div class="form-item">
          <label>确认密码</label>
          <el-input
              v-model="registerForm.confirmPassword"
              type="password"
              placeholder="请再次输入密码"
              prefix-icon="el-icon-lock"
              show-password
              prop="confirmPassword">
          </el-input>
        </div>

        <el-checkbox v-model="registerForm.agreement">
          我已阅读并同意
          <el-link type="primary" @click="handleAgreement">《医疗保险报销中心使用条款》</el-link>
        </el-checkbox>

        <el-button
            class="register-button"
            type="primary"
            @click="handleRegister"
            :disabled="!registerForm.agreement"
            :loading="loading">
          立即注册
        </el-button>

        <div class="login-link">
          已有账号？
          <el-link type="primary" @click="goToLogin">立即登录</el-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'

export default {
  name: 'Register',
  data() {
    const validateConfirmPassword = (rule, value, callback) => {
      if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }

    return {
      loading: false,
      countdown: 0,
      registerForm: {
        username: '',
        phone: '',
        verifyCode: '',
        password: '',
        confirmPassword: '',
        agreement: false
      },
      registerRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
        ],
        verifyCode: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          { len: 6, message: '验证码为6位数字', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
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
      if (!this.registerForm.phone) {
        ElMessage.error('请先输入手机号');
        return;
      }

      // 模拟发送验证码
      this.countdown = 60;
      const timer = setInterval(() => {
        this.countdown--;
        if (this.countdown === 0) {
          clearInterval(timer);
        }
      }, 1000);

      ElMessage.success('验证码已发送');
    },
    handleRegister() {
      this.$refs.registerFormRef.validate((valid) => {
        if (valid) {
          this.loading = true;
          // 模拟注册API调用
          setTimeout(() => {
            this.loading = false;
            ElMessage.success('注册成功！请登录');
            this.$router.push('/login');
          }, 1500);
        } else {
          ElMessage.error('请检查输入信息');
        }
      });
    },
    goToLogin() {
      this.$router.push('/login');
    },
    handleAgreement() {
      this.$router.push('/agreement');
    }
  }
}
</script>

<style scoped>
.register-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.register-container {
  width: 450px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  padding: 40px;
  position: relative;
  overflow: hidden;
}

.register-container::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 4px;
  background: linear-gradient(90deg, #409eff, #67c23a);
}

.register-title {
  text-align: center;
  margin-bottom: 30px;
}

.register-title h1 {
  font-size: 24px;
  color: #303133;
  margin-bottom: 8px;
  font-weight: 600;
}

.register-title p {
  font-size: 14px;
  color: #909399;
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

.register-button {
  width: 100%;
  height: 48px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 8px;
  background: linear-gradient(90deg, #409eff, #67c23a);
  border: none;
  margin-top: 20px;
}

.login-link {
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
  color: #909399;
}
</style>