<template>
  <div class="login-page">
    <div class="login-container">
      <!-- 标题 -->
      <div class="login-title">
        <h1>医疗保险报销中心</h1>
        <p>注册时需要进行手机号验证，后续可通过账号密码登录，<br>为确保安全，请妥善保存您的密码并定期更换。</p>
      </div>

      <!-- 登录表单 -->
      <el-form
          class="login-form"
          :model="loginForm"
          ref="loginFormRef"
          :rules="loginRules">

        <!-- 用户名输入 -->
        <div class="form-item">
          <label>用户名</label>
          <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              prefix-icon="el-icon-user"
              clearable
              prop="username">
          </el-input>
        </div>

        <!-- 密码输入 -->
        <div class="form-item">
          <label>密码</label>
          <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
              show-password
              clearable
              prop="password">
          </el-input>
        </div>

        <!-- 忘记密码链接 -->
        <div class="forgot-password">
          <el-link type="info" @click="handleForgotPassword">忘记密码？</el-link>
        </div>

        <!-- 用户协议 -->
        <el-checkbox v-model="loginForm.agreement">
          登录、注册即表示同意
          <el-link type="primary" @click="handleAgreement">《医疗保险报销中心使用条款》</el-link>
        </el-checkbox>

        <!-- 登录按钮 -->
        <el-button
            class="login-button"
            type="primary"
            @click="handleLogin"
            :disabled="!loginForm.agreement"
            :loading="loading">
          立即登录
        </el-button>

        <!-- 注册链接 -->
        <div class="register-link">
          还没有账号？
          <el-link type="primary" @click="handleRegister">立即注册</el-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'

export default {
  name: 'LoginForm',
  data() {
    return {
      loading: false,
      loginForm: {
        username: '',
        password: '',
        agreement: false
      },
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginFormRef.validate((valid) => {
        if (valid) {
          this.loading = true;
          // 模拟登录API调用
          this.loginAPI(this.loginForm).then(response => {
            this.loading = false;
            if (response.success) {
              localStorage.setItem('userToken', response.token);
              localStorage.setItem('userInfo', JSON.stringify(response.userInfo));
              ElMessage.success('登录成功！');
              // this.$router.push('/dashboard');
            } else {
              ElMessage.error(response.message || '登录失败');
            }
          }).catch(error => {
            this.loading = false;
            ElMessage.error('网络错误，请重试');
            console.error('登录错误:', error);
          });
        } else {
          ElMessage.error('请检查输入信息');
          return false;
        }
      });
    },
    async loginAPI(loginData) {
      // 模拟API调用
      return new Promise((resolve) => {
        setTimeout(() => {
          // 模拟登录验证
          if (loginData.username === 'admin' && loginData.password === '123456') {
            resolve({
              success: true,
              token: 'mock-token-' + Date.now(),
              userInfo: {
                username: loginData.username,
                name: '管理员',
                role: 'admin'
              }
            });
          } else {
            resolve({
              success: false,
              message: '用户名或密码错误'
            });
          }
        }, 1000);
      });
    },
    handleRegister() {
      this.$router.push('/register');
    },
    handleForgotPassword() {
      this.$router.push('/forgot-password');
    },
    handleAgreement() {
      this.$router.push('/agreement');
    }
  }
}
</script>

<style scoped>
/* 样式保持不变 */
.login-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: flex-end;
  padding-right: 100px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.login-container {
  width: 400px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  padding: 40px;
  position: relative;
  overflow: hidden;
}

.login-container::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 4px;
  background: linear-gradient(90deg, #409eff, #67c23a);
}

.login-title {
  text-align: center;
  margin-bottom: 30px;
}

.login-title h1 {
  font-size: 24px;
  color: #303133;
  margin-bottom: 8px;
  font-weight: 600;
}

.login-title p {
  font-size: 14px;
  color: #909399;
  line-height: 1.5;
  margin: 0;
}

.login-form {
  margin-top: 30px;
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

.login-form .el-input {
  width: 100%;
}

.login-form .el-checkbox {
  margin-bottom: 20px;
}

.login-form .el-checkbox__label {
  font-size: 14px;
  color: #606266;
}

.login-button {
  width: 100%;
  height: 48px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 8px;
  background: linear-gradient(90deg, #409eff, #67c23a);
  border: none;
  transition: all 0.3s;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(64, 158, 255, 0.3);
}

.login-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
  transform: none;
}

.register-link {
  text-align: center;
  margin-top: 20px;
  font-size: 14px;
  color: #909399;
}

.forgot-password {
  text-align: right;
  margin-top: 8px;
  margin-bottom: 20px;
}

.forgot-password .el-link {
  font-size: 14px;
}

@media (max-width: 768px) {
  .login-page {
    padding-right: 20px;
    padding-left: 20px;
    justify-content: center;
  }

  .login-container {
    width: 100%;
    max-width: 400px;
  }
}
</style>
