<template>
    <div class="card w-100 h-100">
      <div class="embed-responsive embed-responsive-4by3">
        <img
          class="card-img-top embed-responsive-item"
          :src="category.imageUrl"
          alt="Card image caption"
        />
      </div>
      <div class="card-body">
        <router-link :to="{name: 'DetailCategory', params: {id: category.id}}">
          <h5 class="card-title">{{ category.categoryName }}</h5>
        </router-link>
        <p class="card-text"> {{ category.description }} </p>
      </div>
    </div>
  </template>
  <script>
  export default {
    name: "UserCategoryBox",
    props: ["category"],
    methods: {},
  };
  </script>
  
  <style scoped>
  .card-img-top {
    object-fit: cover;
  }
  
  .card-title {
    color: black;
  }
  </style>