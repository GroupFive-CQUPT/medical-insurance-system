import { createRouter, createWebHistory } from 'vue-router';
import LoginView from "@/views/Login";
const routes = [
    {
        path:"/login",
        component:LoginView
    },

    {
        path:"/",
        redirect:"/login"
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});
export default router;