
<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h2 class="pt-3">All Categories</h2>
          <!-- <router-link :to="{ name: 'AddCategory' }">
            <button class="btn" style="float:right">Add Category</button>
          </router-link> -->
        </div>
      </div>
      <div class="row">
        <div
          v-for="category of categories"
          :key="category.id"
          class="col-xl-4 col-md-6 col-12 pt-3 d-flex"
        >
          <UserCategoryBox :category="category"> </UserCategoryBox>
        </div>
      </div>
    </div>
  </template>
  <script>
  import axios from "axios";
  import UserCategoryBox from "../../components/UserCategoryBox.vue";
  export default {
    name: "CategoryList",
    components: { UserCategoryBox },
    data() {
      return {
        baseURL: "http://localhost:8080",
        categories: [],
      };
    },
    methods: {
      async getCategories() {
        await axios
          .get(`${this.baseURL}/category/list`)
          .then((res) => (this.categories = res.data))
          .catch((err) => console.log(err));
      },
    },
    mounted() {
      this.getCategories();
    },
  };
  </script>
  <style scoped></style>
  