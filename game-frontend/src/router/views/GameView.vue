<script setup>

import { useRouter } from 'vue-router'

import { useSocket } from '@/stores/store';
import { ref } from 'vue';

const router = useRouter() // Uses router from main.js to push Lobby page
const socket = useSocket()
var url = ref()

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
getImage().then((response)=>{
  url.value = (response.data[0]['node']['main_picture']['large']);
});

</script>

<template>

    <div class='page'>
    <img :src="url"></img>
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
  background-image: linear-gradient(45deg, #E7BD8A 50%, #5B000B 50%);

  h2 {
    font-size: 6.5vw;
      position: fixed;

    background: linear-gradient(45deg,
        #5B000B 50%,
        #E7BD8A 50%);
    -webkit-text-fill-color: transparent;
    background-clip: text;

  }
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