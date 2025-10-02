<script setup>

import { useRouter } from 'vue-router'

import { useSocket } from '@/stores/store';

const router = useRouter() // Uses router from main.js to push Lobby page
const socket = useSocket()

async function getImage() {
  const url = "http://localhost:8080/game";
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }

    const result = await response.json();
    console.log(result);
  } catch (error) {
    console.error(error.message);
  }
}

//getImage()


</script>

<template>
    
</template>

<style scoped>


</style>