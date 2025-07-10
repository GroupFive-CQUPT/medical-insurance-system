<template>
  <div class="agreement-page">
    <div class="agreement-container">
      <div class="agreement-header">
        <h1>医疗保险报销中心使用条款</h1>
        <div class="header-actions">
          <el-button @click="goBack" type="primary" plain>
            <el-icon><ArrowLeft /></el-icon>
            返回
          </el-button>
        </div>
      </div>

      <div class="agreement-content">
        <section class="agreement-section">
          <h2>第一条 总则</h2>
          <p>欢迎使用医疗保险报销中心服务！为了保护您的合法权益，请您务必仔细阅读本使用条款的全部内容。您使用本服务即表示您已阅读、理解并同意受本条款的约束。</p>
        </section>

        <section class="agreement-section">
          <h2>第二条 服务内容</h2>
          <p>医疗保险报销中心为用户提供以下服务：</p>
          <ul>
            <li>医疗费用报销申请提交</li>
            <li>报销进度查询</li>
            <li>历史报销记录查看</li>
            <li>个人账户信息管理</li>
            <li>政策法规查询</li>
            <li>在线客服咨询</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第三条 用户权利和义务</h2>
          <h3>用户权利：</h3>
          <ul>
            <li>依法享受医疗保险报销服务</li>
            <li>对个人信息安全的保护要求</li>
            <li>对服务质量的监督建议权</li>
            <li>及时获得报销进度信息</li>
          </ul>
          <h3>用户义务：</h3>
          <ul>
            <li>提供真实、准确的个人信息</li>
            <li>按规定提交完整的报销材料</li>
            <li>遵守相关法律法规</li>
            <li>保护账户安全，不得转让账户</li>
            <li>配合必要的身份验证</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第四条 隐私保护</h2>
          <p>我们承诺保护您的个人隐私，具体措施包括：</p>
          <ul>
            <li>采用行业标准的加密技术保护数据传输</li>
            <li>严格限制员工访问个人信息的权限</li>
            <li>不向第三方泄露用户个人信息</li>
            <li>定期进行安全检查和漏洞修复</li>
            <li>用户有权查询、更正个人信息</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第五条 服务规范</h2>
          <p>为确保服务质量，我们承诺：</p>
          <ul>
            <li>7×24小时系统运行保障</li>
            <li>报销申请受理后3个工作日内完成初审</li>
            <li>提供多渠道客户服务支持</li>
            <li>定期发布系统维护公告</li>
            <li>持续优化用户体验</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第六条 免责声明</h2>
          <p>以下情况下，本平台不承担责任：</p>
          <ul>
            <li>因用户提供虚假信息造成的损失</li>
            <li>因不可抗力因素导致的服务中断</li>
            <li>因用户账户密码泄露造成的损失</li>
            <li>因第三方系统故障影响服务的情况</li>
            <li>超出合理技术和商业标准的服务要求</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第七条 服务变更与终止</h2>
          <p>本平台有权在以下情况下变更或终止服务：</p>
          <ul>
            <li>政策法规发生变化需要调整服务内容</li>
            <li>技术升级需要临时暂停服务</li>
            <li>用户违反使用条款的情况</li>
            <li>其他影响服务正常运行的情况</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第八条 争议解决</h2>
          <p>如发生争议，优先通过友好协商解决。协商不成的，可通过以下途径解决：</p>
          <ul>
            <li>向相关监管部门投诉</li>
            <li>申请第三方调解</li>
            <li>通过法律途径解决</li>
          </ul>
        </section>

        <section class="agreement-section">
          <h2>第九条 条款修改</h2>
          <p>本条款如有修改，将通过以下方式通知用户：</p>
          <ul>
            <li>在平台首页发布公告</li>
            <li>通过短信或邮件通知</li>
            <li>在用户登录时弹窗提醒</li>
          </ul>
          <p>修改后的条款自发布之日起生效，继续使用服务即视为接受修改后的条款。</p>
        </section>

        <section class="agreement-section">
          <h2>第十条 联系方式</h2>
          <p>如对本条款有任何疑问，请通过以下方式联系我们：</p>
          <ul>
            <li>客服电话：13272629583</li>
            <li>服务邮箱：1215282843@qq.com</li>
            <li>工作时间：周一至周五 9:00-18:00</li>
            <li>地址：重庆邮电大学综合实验楼A511</li>
          </ul>
        </section>

        <div class="agreement-footer">
          <p>本条款最终解释权归医疗保险中心GroupFive-CQUPT所有。</p>
          <p>条款生效日期：2025年7月10日</p>
          <p>最后更新日期：3025年7月10日</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ArrowLeft } from '@element-plus/icons-vue'

export default {
  name: 'Agreement',
  components: {
    ArrowLeft
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
.agreement-page {
  min-height: 100vh;
  background: #f5f7fa;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.agreement-container {
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
}

.agreement-header {
  background: white;
  border-radius: 12px;
  padding: 30px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.agreement-header h1 {
  font-size: 28px;
  color: #303133;
  margin: 0;
  font-weight: 600;
}

.agreement-content {
  background: white;
  border-radius: 12px;
  padding: 40px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  line-height: 1.6;
}

.agreement-section {
  margin-bottom: 30px;
}

.agreement-section h2 {
  font-size: 20px;
  color: #303133;
  margin-bottom: 15px;
  font-weight: 600;
  border-left: 4px solid #409eff;
  padding-left: 15px;
}

.agreement-section h3 {
  font-size: 16px;
  color: #606266;
  margin: 15px 0 10px 0;
  font-weight: 500;
}

.agreement-section p {
  color: #606266;
  margin-bottom: 10px;
  text-indent: 2em;
}

.agreement-section ul {
  margin: 10px 0;
  padding-left: 20px;
}

.agreement-section li {
  color: #606266;
  margin-bottom: 8px;
  list-style-type: disc;
}

.agreement-footer {
  margin-top: 40px;
  padding-top: 20px;
  border-top: 1px solid #ebeef5;
  text-align: center;
}

.agreement-footer p {
  color: #909399;
  font-size: 14px;
  margin: 5px 0;
}

@media (max-width: 768px) {
  .agreement-container {
    padding: 10px;
  }

  .agreement-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }

  .agreement-header h1 {
    font-size: 24px;
  }

  .agreement-content {
    padding: 20px;
  }

  .agreement-section h2 {
    font-size: 18px;
  }
}
</style>