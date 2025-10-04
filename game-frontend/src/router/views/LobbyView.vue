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
    <div class = "page">
      <div class = "players">
          <Player v-if= "players" :player = players></Player>
          <Button @click="goToHome">HOME</Button>
          <Button @click="startGame">START</Button>
      </div>
    </div>
</template>

<style scoped>


.page {
  font-family: 'Times New Roman', Times, serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background: repeating-linear-gradient(
    to right,
    #5B000B 0% 10%,
    #E7BD8A 10% 20%
  );

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

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: hidden;
}
</style>