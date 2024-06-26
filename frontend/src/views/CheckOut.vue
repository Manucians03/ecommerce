<template>
    <div class="main-class">
      <h3>You will be redirected to payment page.</h3>
      <div class="alert alert-primary">
        While making payment use card number 4242 4242 4242 4242 and enter random date and cvv (3 digit)
      </div>
      <button class="btn checkout-button" @click="goCheckOut">Make Payment</button>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    name: "CheckOut",
    props: ["baseURL"],
    methods: {
      getAllItems(){
        axios.get(`${this.baseURL}/cart/list?token=${this.token}`)
            .then((res) => {
              if(res.status === 200){
                let products = res.data;
                console.log(products);
                for(let i = 0; i< products.cartItems.length; i++){
                  this.checkoutBodyArray.push({
                    quantity: products.cartItems[i].quantity,
                    productId: products.cartItems[i].product.id
                  });
                }
              }
            }).catch(err => console.log(err));
      },
      goCheckOut(){
        axios.post(`${this.baseURL}/order/create-checkout-session`, this.checkoutBodyArray)
            .then(res => {
              localStorage.setItem("sessionId", res.data.sessionId);
              this.stripe.redirectToCheckout({
                sessionId: res.data.sessionId
              });
            }).catch(err => console.log(err));
      }
    },
    data(){
      return {
        stripeApiToken:"pk_test_51PNHWS01wH6xJXP6Q7OL2eRjpfr8DPrMEErb9V00lbO39yVFKKy0ThACM9nJhAKducpKgmwFuFzfsWEiF19tzLCn00tr7Ili5F",
        stripe: "",
        token: null,
        checkoutBodyArray: []
      }
    },
    mounted() {
      this.token = localStorage.getItem("token");
      this.stripe = window.Stripe(this.stripeApiToken);
      this.getAllItems();
    }
  }
  </script>
  
  <style scoped>
  .alert {
    width: 50%;
  }
  .main-class {
    margin-top: 5%;
    margin-left: 30%;
  }
  .checkout-button {
    background-color: #5d3dec;
    margin: 10px;
    color: white;
  }
  </style>