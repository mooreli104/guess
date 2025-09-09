<script setup>

import { useRouter } from 'vue-router'
import Button from 'primevue/button';


const router = useRouter() // Uses router from main.js to push Lobby page


const goToHome = () => {
  router.push('/')
}

</script>

<template>
    <div class = "fullscreen">
    <Button @click="goToHome">HOME</Button>
    </div>
</template>

<style scoped>

.fullscreen {
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  overflow: auto;
  
}


</style>