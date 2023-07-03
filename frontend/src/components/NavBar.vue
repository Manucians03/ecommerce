<template>
    <nav class="navbar">
        <!--    Logo-->
        <router-link class="navbar-brand" :to="{name : 'Home'}">
            <img id="logo" src="../assets/amazon.jpeg" style="max-height: 70px; max-width: 100px "/>
        </router-link>

        <!--      Search Bar-->
        <div id="navbarSearch">
            <form class="form-inline ms-auto me-auto">
            <div class="input-group">
                <input size="100" type="text" class="form-control" placeholder="Search Items" aria-label="Username" aria-describedby="basic-addon1">
                <div class="input-group-prepend">
                <span class="input-group-text" id="search-button-navbar">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                    </svg>
                </span>
                </div>
            </div>
            </form>
        </div>
    
        <div id="cart">
            <span id="nav-cart-count">{{cartCount}}</span>
            <router-link class="text-light" :to="{ name: 'Cart' }">
                <i class="fa fa-shopping-cart" style="font-size:36px"></i>
            </router-link>
        </div>
        <div id="account">
            <a v-if="token" href="#" @click.prevent="logOut">
                <i class="fa fa-sign-out" style="font-size:36px"></i>
            </a>

            <a v-if="!token" href="/signin">
                <i class="fa fa-sign-in" style="font-size:36px"></i>
            </a>
        </div> 

  
    </nav>
  
  </template>
  
  <script>
  import swal from "sweetalert";
  export default {
    name : "NavBar",
    props: ["cartCount"],
    data() {
      return{
        token: null
      }
    },
    methods: {
      logOut(){
        localStorage.removeItem("token")
        this.token = null;
        swal({
          text: "You have logged out. See you later!",
          icon: "success"
        });
        this.$emit("resetCartCount");
      }
    },
    mounted() {
      this.token = localStorage.getItem("token");
    }
  }
  </script>
  
  <style scoped>
  #logo {
    width: 150px;
    margin-left: 20px;
    margin-right: 20px;
  }
  
  .navbar {
    background-color: #e3e6ef;
  }

  #search-button-navbar {
    background-color: #febd69;
    border-color: #febd69;
    border-top-right-radius: 2px;
    border-bottom-right-radius: 2px;
  }
  #cart {
    position: relative;
  }
  #nav-cart-count {
    background-color: red;
    color: white;
    border-radius: 50%;
    height: 20px;
    width: 20px;
    font-size: 15px;
    align-items: center;
    display: flex;
    justify-content: center;
    position: absolute;
    margin-left: 10px;
  }

  i {
    color: black;
  }

  </style>