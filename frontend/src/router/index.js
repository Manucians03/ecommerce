import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AddCategory from '../views/Category/AddCategory.vue'
import ViewCategory from '../views/Category/ViewCategory.vue'
import EditCategory from '../views/Category/EditCategory.vue'
import DetailCategory from '../views/Category/DetailCategory.vue'
import AddProduct from '../views/Product/AddProduct.vue'
import ViewProduct from '../views/Product/ViewProduct.vue'
import EditProduct from '../views/Product/EditProduct.vue'
import DetailProduct from '../views/Product/DetailProduct.vue'
import SignIn from '../views/SignIn.vue'
import SignUp from '../views/SignUp.vue'
import WishList from '../views/WishList.vue'
import Cart from '../views/Cart.vue'
import CheckOut from '../views/CheckOut.vue'
import SearchResult from '../views/SearchResult.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: "/category/add",
      name: "AddCategory",
      component: AddCategory
    },
    {
      path: "/category",
      name: "ViewCategory",
      component: ViewCategory
    },
    {
      path: "/category/edit/:id",
      name: "EditCategory",
      component: EditCategory
    },
    {
      path: "/category/:id",
      name: "DetailCategory",
      component: DetailCategory
    },
    {
      path: "/product/add",
      name: "AddProduct",
      component: AddProduct
    },
    {
      path: "/product",
      name: "ViewProduct",
      component: ViewProduct
    },
    {
      path: "/product/edit/:id",
      name: "EditProduct",
      component: EditProduct
    },
    {
      path: "/product/:id",
      name: "DetailProduct",
      component: DetailProduct
    },
    {
      path: "/signin",
      name: "SignIn",
      component: SignIn
    },
    {
      path: "/signup",
      name: "SignUp",
      component: SignUp
    },
    {
      path: "/wishlist",
      name: "WishList",
      component: WishList
    },
    {
      path: "/cart",
      name: "Cart",
      component: Cart
    },
    {
      path: "/checkout",
      name: "CheckOut",
      component: CheckOut
    },
    {
      path: "/search/:query",
      name: "SearchResult",
      component: SearchResult
    }
  ]
})

export default router
