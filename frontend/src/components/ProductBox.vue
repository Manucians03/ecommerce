<template>
    <div class="card h-100 w-100">
        <div class="embed-responsive embed-responsive-4by3">
            <img
                class="card-img-top embed-responsive-item"
                :src="product.imageUrl"
                alt="Card image caption"
            />
        </div>
        <div class="card-body">
            <router-link :to="{name: 'DetailProduct', params: {id: product.id}}">
                <h5 class="card-title">{{ product.name }}</h5>
            </router-link>
            <p class="card-text"> {{ product.description.substring(0, 65) }}</p>
            <router-link :to="{name: 'EditProduct', params: { id: product.id }}">
                <button class="btn btn-primary">Edit</button>
            </router-link>
        </div>
    </div>
</template>
<script>
    export default {
        name: "ProductBox",
        props: ["product"]
    }
</script>
<style scoped>
.card-img-top {
    object-fit: cover;
}
.card-title {
  color: black;
}
</style>
