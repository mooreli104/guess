<script setup>

import { useRouter } from 'vue-router'
import { ref } from 'vue';
import Button from 'primevue/button';
import Players from "../components/Player.vue"


const router = useRouter() // Uses router from main.js to push Lobby page
const players = ref(null)

const goToHome = () => {
  router.push('/')
}

async function getPlayers() {
    socket.send(JSON.stringify({"action": "lobby"}));
}

getPlayers()

</script>

<template>
    <div class = "fullscreen">

      <div class = "players">
          <Players :players = players ></Players>
          <Button @click="goToHome">HOME</Button>
      </div>
    
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
.players{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  height: 25%;
  width: 25%
}


</style>