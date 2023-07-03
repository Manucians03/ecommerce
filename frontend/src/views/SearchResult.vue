<template>
    <div id="search-result">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h3 class="pt-3">Search Results</h3>
                </div>
            </div>
            <div class="row">
                <div v-for="result of results" :key="result.id"
                     class="col-md-6 col-xl-4 col-12 pt-3 d-flex">
                    <UserProductBox :product="result"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import UserProductBox from "../components/UserProductBox.vue";
export default {
name: 'SearchResult',
components: {
    UserProductBox
},
props: ["products"],
data(){
    return {
    results: [],
    }
},
mounted() {
    for (let i = 0; i < this.products.length; i++) {
        if (this.products[i].name.toLowerCase().includes(this.$route.params.query.toLowerCase())) {
        this.results.push(this.products[i]);
        }
    }
}
}
</script>

<style>

</style>
