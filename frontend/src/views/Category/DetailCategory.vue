<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h4 class="pt-3">{{category.categoryName}}</h4>
          <h5 style="font-weight: 300; color: #686868">{{category.description}}</h5>
        </div>
      </div>
      <div class="row">
        <div v-for="p of this.product" :key="p.id" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
          <UserProductBox :product="p" />
        </div>
  
      </div>
    </div>
  </template>
  
  <script>
  import UserProductBox from "../../components/UserProductBox.vue";
  export default {
    name: "DetailCategory",
    components: {UserProductBox},
    props: ["categories", "products"],
    data(){
      return {
        id: null,
        category: {},
        product: {},
        msg: ''
      }
    },
    mounted() {
      this.id = this.$route.params.id;
      this.product = this.products.filter((p) => this.id == p.categoryId);
      this.category = this.categories.find((category) => category.id == this.id);
      if(this.product.length == 0)
        this.msg = "No products found";
      else if(this.product.length == 1)
        this.msg = "1 product found";
      else
        this.msg = this.product.length + " products found";
    }
  }
  </script>
  
  <style scoped>
  
  </style>