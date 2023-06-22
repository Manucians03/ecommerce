import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AddCategory from '../views/Category/AddCategory.vue'
import ViewCategory from '../views/Category/ViewCategory.vue'
import AddProduct from '../views/Product/AddProduct.vue'
import ViewProduct from '../views/Product/ViewProduct.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: "/category/add",
      name: "AddCategory",
      component: AddCategory
    },
    {
      path: "/category",
      name: "Category",
      component: ViewCategory
    },
    {
      path: "/product/add",
      name: "AddProduct",
      component: AddProduct
    },
    {
      path: "/product",
      name: "Product",
      component: ViewProduct
    }
  ]
})

export default router
