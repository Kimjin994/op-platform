import { defineStore } from 'pinia'
import { menu, permission } from "../api/module/user";

export const useUserStore = defineStore({
  id: 'user',
  state: () => {
    return {
      token: '',
      userInfo: {},
      permissions: [],
      menus: [],
    }
  },
  actions: {
    async loadMenus(){
      debugger
      const { result, code } = await menu();
      if(code == 1) {
        this.menus = result;
      }
    },
    async loadPermissions(){
      const { result, code } = await permission();
      if(code == 1) {
        this.permissions = result;
      }
    }
  },
  persist: {
    storage: localStorage,
    paths: ['token', 'userInfo', 'permissions', 'menus' ],
  }
})
