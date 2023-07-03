<template>
    <div id="home">
        <div class="container">
            <div class="row">
              <div class="col-1"></div>
              <div class="col-10 text-center">
                  <h2 class="pt-3">Top Categories</h2>
              </div>
              <div class="col-1 show-all">
                  <router-link :to="{name: 'ViewCategory'}">
                      Show All
                  </router-link>
              </div>
            </div>
            <div class="row">
            <div v-for="index in this.categorySize" :key="index" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
                <UserCategoryBox :category="categories[index -1]" />
            </div>
            </div>
        </div>
        <div class="container py-2">
            <div class="row">
              <div class="col-1"></div>
              <div class="col-10 text-center">
                  <h2 class="pt-3">Top Products</h2>
              </div>
              <div class="col-1 show-all">
                  <router-link :to="{name: 'ViewProduct'}">
                      Show All
                  </router-link>
              </div>
            </div>
            <div class="row">
                <div v-for="index in this.productSize" :key="index" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
                    <UserProductBox :product="products[index -1]" />
                </div>
                </div>
            </div>
        </div>
  </template>
  
  <script>
  
  import UserCategoryBox from "../components/UserCategoryBox.vue"
  import UserProductBox from "../components/UserProductBox.vue";
  import swal from "sweetalert";
  export default {
    name: 'HomeView',
    components: {
      UserProductBox,
      UserCategoryBox
    },
    props: ["categories", "products"],
    data(){
      return {
        categorySize:0,
        productSize: 0
      }
    },
    mounted() {
      if (!window.localStorage.getItem("token")) {
        swal({
          text: "You need to sign in first!",
          icon: "error"
        });
        this.$router.push({name: 'SignIn'});
      }
      this.categorySize=Math.min(6, this.categories.length);
      this.productSize=Math.min(6, this.products.length);
    }
  }
  </script>
  <style>
  .page-holder{
    min-height: 100vh;
  }
  .bg-cover{
    background-size: cover !important;
  }
  #heading{
    font-weight: 400;
    justify-content: center;
  }
  .show-all{
    display: flex;
    align-items: center;
    justify-content: center;
  }
  </style>
  