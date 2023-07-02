<template>
  <Navbar :cartCount="cartCount" @resetCartCount="resetCartCount" />
  <router-view v-if="products && categories"
               :baseURL="baseURL"
               :categories="categories"
               :products="products"
               @fetchData="fetchData" style="min-height: 60vh">
  </router-view>
</template>

<script>
import Navbar from "./components/NavBar.vue";
import axios from "axios";
export default {
  components: { Navbar },
  data(){
    return {
      baseURL: "http://localhost:8080",
      products: null,
      categories: null,
      cartCount: 0
    }
  },
  methods: {
    async fetchData() {
      await axios.get(`${this.baseURL}/category/list`)
          .then((res) => {
            this.categories = res.data;
          }).catch((err) => {
            console.log(err);
          });

      await axios.get(`${this.baseURL}/product/list`)
          .then((res) => {
            this.products = res.data;
          }).catch((err) => {
            console.log(err);
          });
      if(this.token){
        axios.get(`${this.baseURL}/cart/list?token=${this.token}`)
            .then(res => {
              const result = res.data;
              this.cartCount = result.cartItems.length;
            }).catch(err => console.log(err));
      }
    },
    resetCartCount(){
      this.cartCount = 0;
    }
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.fetchData();
  }
};
</script>

<style>
body {
  color: black;
}
</style>
