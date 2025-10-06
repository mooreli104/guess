<script setup>

import { useRouter } from 'vue-router'

import { useSocket } from '@/stores/store';
import { ref } from 'vue';
import Player from '../components/Player.vue';
import { Button } from 'primevue';

const router = useRouter() // Uses router from main.js to push Lobby page
const socket = useSocket()
var url = ref()
var players = ref()
var playerGuess = ref()


async function getImage() {
  const url = "http://localhost:8080/game";
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }

    const result = await response.json();
    return result
  } catch (error) {
    console.error(error.message);
  }
}
getImage().then((response) => {
  url.value = (response.data[0]['node']['main_picture']['large']);
});

async function getPlayers() {
  socket.socket.send(JSON.stringify({ "action": "getLobby" }));
}
socket.socket.addEventListener("message", (event) => {
  players.value = JSON.parse(event.data)
});

getPlayers()

async function guess() {
  socket.socket.send(JSON.stringify({
    "guess": playerGuess.value,
    "action": "guess"
  }));
}

</script>

<template>

  <div class='page'>
    <div class="players">
      <Player v-if="players" :player=players></Player>
    </div>
    <div class = "guess">
    <img :src="url"></img>
      <div class='input'>
    <input type="text" v-model="playerGuess" placeholder="Guess">
    <Button type="submit" value="Guess" @click.prevent="guess" label="normal" raised rounded style="top: 1%">Guess</Button>
      </div>
      </div>
  </div>
</template>

<style scoped>
.page {
  font-family: 'Times New Roman', Times, serif;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background-image: linear-gradient(45deg, #E7BD8A 50%, #5B000B 50%);
}

img {
    width:75%; /* you can use % */
    height: auto;
}

.guess{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 5%;
}

.input{
  display: flex;
  margin-top: 5%;
  flex-direction: column;
  align-items: center;
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