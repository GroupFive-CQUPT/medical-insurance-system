<template>
  <div class="login-page">
    <div class="login-container">
      <!-- 标题 -->
      <div class="login-title">
        <h1>医疗保险报销中心</h1>
        <p>为确保安全，请妥善保存您的密码并定期更换。</p>
      </div>

      <!-- 登录表单 -->
      <el-form
          class="login-form"
          :model="loginForm"
          ref="loginForm"
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

        <!-- 登录按钮 -->
        <el-button
            class="login-button"
            type="primary"
            @click="handleLogin"
            :disabled="!loginForm.agreement"
            :loading="loading">
          立即登录
        </el-button>

      </el-form>
    </div>
  </div>
</template>

<script>
import {login} from "@/api/userApi";
import {setSessionStorage} from "@/utils/common";

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
    handleLogin: function () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          login(this.loginForm).then(res => {
            {
              if(res.flag){
                //将token存入storage中
                sessionStorage.setItem('token',res.message)
                //将当前登录用户信息存入storage中
                setSessionStorage('user',res.data)

                this.$store.commit('addMenus',res.data.menuList)
                //路由到第一个子菜单
                this.$router.push(res.data.menuList[0].children[0].path)
              }else{
                this.$message.error(res.message)
              }
            }
          })
        }
      });
    },

    handleForgotPassword() {
      //跳转处理
    },
  }
}
</script>

<style scoped>
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

.login-form .el-input__inner {
  height: 48px;
  border-radius: 8px;
  border: 1px solid #dcdfe6;
  padding-left: 45px;
  font-size: 14px;
  transition: all 0.3s;
}

.login-form .el-input__inner:focus {
  border-color: #409eff;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1);
}

.login-form .el-input__prefix {
  left: 15px;
  color: #c0c4cc;
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

/* 响应式设计 */
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