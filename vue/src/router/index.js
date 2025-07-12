import { createRouter, createWebHistory } from 'vue-router';
import LoginView from "@/views/Login.vue";
import RegisterView from "@/views/Register.vue"; // 注册页面
import ForgotPasswordView from "@/views/ForgotPassword.vue"; // 忘记密码页面
import AgreementView from "@/views/Agreement.vue"; // 用户协议页面


const routes = [
    {
        path: "/login",
        component: LoginView
        // meta: { title: '登录页面' }
    },
    {
        path: "/register",
        component: RegisterView
    },
    {
        path: "/forgot-password",
        component: ForgotPasswordView
    },
    {
        path: "/agreement",
        component: AgreementView
    },

    {
        path: "/",
        redirect: "/login"
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

// 路由守卫：检查是否需要认证
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('userToken'); // 获取本地存储的用户令牌
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

    if (requiresAuth && !token) {
        next('/login'); // 如果需要认证但没有令牌，则重定向到登录页面
    } else {
        next(); // 否则正常跳转
    }
});

export default router;