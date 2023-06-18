import { createRouter, createWebHistory } from 'vue-router'
import App from '../App.vue'
import addCategory from '../views/Category/AddCategory.vue'
import Category from '../views/Category/Category.vue'

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
      name: "AddCategory",
      component: addCategory
    },
    {
      path: "/category",
      name: "category",
      component: Category
    }
  ]
})

export default router
