// // 引入封装好的axios  @等价于/src
// import http from '@/request/request.js';
//用户登录
import * as http from "node:http";

function login(data){
    return http.get('/user/login',{params:data})
}