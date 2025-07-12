import { createRouter, createWebHistory } from 'vue-router';
import LoginView from "@/views/Login.vue";
import RegisterView from "@/views/Register.vue"; // 注册页面
import ForgotPasswordView from "@/views/ForgotPassword.vue"; // 忘记密码页面
import AgreementView from "@/views/Agreement.vue"; // 用户协议页面
import CheckIn from "../views/doctor/CheckIn.vue";//暂时以静态页面呈现
import store from "@/store";

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
    },

    {
        path: "/checkIn",
        name: "CheckIn",
        component: CheckIn,
    }//暂时写为静态页面用以检查，动态页面路由配置尚未完善
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

/**
 * 初始路由信息
 */
function initRouter() {
    // 定义一个用于添加子路由的根路由对象
    let appendRoute = {};
    // 设置根路由的路径
    appendRoute.path = "/";
    // 为根路由命名
    appendRoute.name = "layout";
    // 动态导入布局组件作为根路由对应的组件
    appendRoute.component = () => import(`@/views/layout/Layout.vue`);
    // 初始化根路由的子路由数组
    appendRoute.children = [];

    // 从 Vuex store 中获取当前用户的菜单权限信息
    let menus = store.getters.menus;
    // 遍历菜单列表
    for(let menu of menus){
        // 遍历每个菜单的子菜单
        menu.children.forEach((temp) => {
            // 定义一个子路由对象
            let item = {};
            // 获取子菜单的路径
            let vueName = temp.path;
            // 找到路径中最后一个 / 的索引位置，并加 1
            let indexN = temp.path.lastIndexOf("/") + 1;
            // 将路径中最后一个 / 后的首字母转换为大写，生成组件路径
            vueName = vueName.substr(0, indexN) + vueName.substring(indexN).charAt(0).toUpperCase() + vueName.substring(indexN + 1);
            // 动态导入对应的页面组件
            item.component = () => import(`@/views${vueName}.vue`);
            // 设置子路由的路径
            item.path = temp.path;
            // 设置子路由的名称
            item.name = temp.title;
            // 将子路由添加到根路由的子路由数组中
            appendRoute.children.push(item);
        });
    }
    // 将生成的根路由及其子路由添加到路由实例中
    router.addRoute(appendRoute);
}
// 路由守卫：检查是否需要认证
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('userToken'); // 获取本地存储的用户令牌
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

    if (requiresAuth && !token) {
        next('/login'); // 如果需要认证但没有令牌，则重定向到登录页面
    } else {
        next(); // 否则正常跳转
    }
        // 获取当前跳转的完整路径
        let currentPath = to.fullPath;
        // 从 Vuex store 中获取当前用户的菜单权限信息
        let menuArray=store.getters.menus;
        // 判断路由实例中的路由数量为 3，且菜单权限信息存在且不为空
        if(router.getRoutes().length==3 && menuArray!=null && menuArray.length>0 && menuArray!='undefined') {
            // 需要动态加载路由信息
            initRouter();
            // 导出路由实例，供其他组件使用
            // 放行路由跳转
            // 调用 Vuex 的 addTab mutation，将当前菜单添加到标签页状态中
            // 如果找到了对应的菜单对象
            // 若一致，则找到对应的菜单对象
            // 判断当前二级菜单的路径是否与当前访问路径一致
            // 遍历二级菜单
            // 获取当前一级菜单下二级菜单的数量
            // 遍历一级菜单
            // 用于存储二级菜单的数量
            // 获取一级菜单的数量
            // 用于存储当前访问路径对应的菜单对象
            // 从 Vuex store 中获取当前用户的菜单权限信息
            // 重新跳转当前目标路径
            next({path: to.path});
            return;
        }
        let menus = store.getters.menus;
        let currentMenu = null;

        let firstLevelSize = menus.length;
        let secondLevelSize = 0;

        for (let i = 0; i < firstLevelSize && currentMenu == null; i++) {
            secondLevelSize = menus[i].children.length;
            for (let j = 0; j < secondLevelSize; j++) {
                //说明找到了
                if (menus[i].children[j].path == currentPath) {
                    currentMenu = menus[i].children[j];
                    break;
                }
            }
        }

        if (currentMenu) {
            //调用vuex判断是否需要添加到state的tabs中
            store.commit("addTab", currentMenu);
        }
}
);

export default router;