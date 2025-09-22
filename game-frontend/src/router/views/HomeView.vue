<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue'
import Button from 'primevue/button';
import { useSocket } from '@/stores/store';

const router = useRouter()
const socket = useSocket()
const username = ref('');

const goToLobby = () => {
  // router.push("/lobby")
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
    <Button type="submit" value="Start" @click.prevent="goToLobby">Start</Button>
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
