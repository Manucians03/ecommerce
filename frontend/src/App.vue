<template>
  <router-view
  :baseURL="baseURL"
  :categories="categories"
  :products="products"
  >
  </router-view>
</template>

<script>
import axios from 'axios';
export default {
  components:  {},
  data() {
    return {
      baseURL : "http://localhost:8080",
      products: [],
      categories: []
    }
  },
  methods: {
    async fetchData() {

      await axios.get(this.baseURL + "/category/list")
      .then(res => {
        this.categories = res.data
      }).catch((err) => console.log('err', err));

      await axios.get(this.baseURL + "/product/list")
      .then(res => {
        this.products = res.data
      }).catch((err) => console.log('err', err));
    }
  },
  mounted() {
    this.fetchData();
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>