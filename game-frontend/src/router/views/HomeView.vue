<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue'
import Button from 'primevue/button';
import socket from '@/socket';
SVGAnimateElement

const router = useRouter()

const username = ref('');

const goToLobby = () => {
  createLobby()
  router.push("/lobby")
}

async function createLobby() {
      socket.send(JSON.stringify({"username": username.value,
                    "action": "createLobby"}));
}


</script>

<template>

  <div class="fullscreen">
    <input type="text" v-model="username" placeholder="Enter a nickname!">
    <Button type="submit" value="Start" @click="goToLobby">Start</Button>
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
