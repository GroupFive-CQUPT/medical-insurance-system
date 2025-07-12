import {createStore} from 'vuex'
import {getSessionStorage,setSessionStorage} from '@/utils/common.js'

export default createStore({
    state: {
        tabs: [

        ],

        menus: getSessionStorage('menuList') || []

    },
    getters: {

        /**
         * 获取所有的tab
         * @param state
         * @returns
         */
        tabs(state) {
            return state.tabs;
        },

        /**
         * 获取所有的菜单
         * @param state
         * @returns
         */
        menus(state) {
            return state.menus;
        }
    },
    mutations: {
        addMenus(state,param){
            state.menus=param
            setSessionStorage('menuList',param)
        },
        deleteTabByIndex(state, index) {
            state.tabs.splice(index, 1);
        },
        /**
         * 清空tab
         */
        clearTab(state,param){
            state.tabs=param
        },

        /**
         * 向vuex中添加tab内容
         * @param state
         * @param payload
         */
        addTab(state, payload) {
            let path = payload.path;

            if (path) {
                let result = state.tabs.filter((item) => {
                    return item.path == path;
                });
                //判断是否存在,如果不存在才需要加入到state的tabs中
                if (result.length == 0) {
                    if(state.tabs.length==10){
                        //删除第二个
                        state.tabs.splice(1,1)
                    }
                    //需要添加
                    state.tabs.push(payload);
                }

            }
        },
    },
    actions: {
    },
    modules: {}
})