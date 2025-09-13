<script setup>
import { useRouter } from 'vue-router'
import { ref } from 'vue'
import Button from 'primevue/button';



const router = useRouter()

const username = ref('');

const goToLobby = () => {
  router.push("/lobby")
  sendUsername()
}

async function sendUsername() {
  const url = "http://localhost:8080/api/connect";
  try {
    const response = await fetch(url, {
      method: "POST",
      body: JSON.stringify({username: username.value}),
    });
    
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }

    const result = await response.json();
    console.log(result);
  } catch (error) {
    console.error(error);
  }
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
