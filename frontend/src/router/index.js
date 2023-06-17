import { createRouter, createWebHistory } from 'vue-router'
import App from '../App.vue'
import addCategory from '../views/Category/AddCategory.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: App,
    },
    {
      path: "/category/add",
      name: "addCategory",
      component: addCategory
    }
  ]
})

export default router
