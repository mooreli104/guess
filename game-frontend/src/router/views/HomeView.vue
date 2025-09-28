<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue'
import Button from 'primevue/button';
import { useSocket } from '@/stores/store';

const router = useRouter()
const socket = useSocket()
const username = ref('');

const goToLobby = () => {
  router.push("/lobby")
  createLobby()
}

async function createLobby() {
      socket.socket.send(JSON.stringify({"username": username.value,
                    "action": "createLobby"}));
}



</script>

<template>
  <div class="fullscreen">
    <input type="text" v-model="username" placeholder="Enter a nickname!">
    <Button v-if="username" type="submit" value="Start" @click.prevent="goToLobby" label="normal" raised rounded style = "top: 1%">Start</Button>
    <Button v-else label="normal" severity="secondary" raised rounded >Start</Button>
  </div>
</template>

<style scoped>


*{
  box-sizing: border-box;
}

body{
	font-family: 'Playfair Display', serif;
	color: rgba(172,170,190, 1);
	text-rendering: optimizeLegibility;
	font-smooth: always;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	background-color: purple;
	font-size: 14px;
	overflow: hidden;
}


.fullscreen {
  display: flex;
  flex-direction: column;
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
