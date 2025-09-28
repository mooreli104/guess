<script setup>

import { useRouter } from 'vue-router'
import { ref } from 'vue';
import Button from 'primevue/button';
import Player from "../components/Player.vue"
import { useSocket } from '@/stores/store';

const router = useRouter() // Uses router from router.js to push Lobby page
const socket = useSocket()
var players = ref()

const goToHome = () => {
  router.push('/')
  socket.socket.close();
}

const startGame = () => {
  router.push('/game')
}


async function getPlayers() {
    socket.socket.send(JSON.stringify({"action": "getLobby"}));
}

socket.socket.addEventListener("message", (event) => {
  players.value = JSON.parse(event.data)
});

getPlayers()

</script>

<template>
    <div class = "fullscreen">
      <div class = "players">
          <Player v-if= "players" :player = players></Player>
          <Button @click="goToHome">HOME</Button>
          <Button @click="startGame">START</Button>
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