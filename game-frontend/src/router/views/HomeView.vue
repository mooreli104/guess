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
  socket.socket.send(JSON.stringify({
    "username": username.value,
    "action": "createLobby"
  }));
}



</script>

<template>

  <div class='page'>
    <h2>
      ShowGuessr
    </h2>
    <div class='input'>
      <input type="text" v-model="username" placeholder="Enter a nickname!">
      <Button v-if="username" type="submit" value="Start" @click.prevent="goToLobby" label="normal" raised rounded
        style="top: 1%">Start</Button>
      <Button v-else label="normal" severity="secondary" raised rounded>Start</Button>
    </div>
    <br>
  </div>

  <div class="wrap">
    <div class="svg"><img src="../../assets/svg/chinese_symbols/baozi-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/board-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/chinese-lion-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img
        src="../../assets/svg/chinese_symbols/chinese-style-spring-festival-element-icon-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/chinese-zodiac-signs-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/dragon-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/firecracker-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/fish-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/goat-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/horse-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/knot-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/lantern-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/lucky-coin-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/ox-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/plum-flower-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/rabbit-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/rat-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/rooster-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/snake-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/tangerine-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/tangyuan-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/the-monkey-svgrepo-com.svg" alt=""></div>
    <div class="svg"><img src="../../assets/svg/chinese_symbols/tiger-svgrepo-com.svg" alt=""></div>
  </div>


</template>

<style scoped>
* {
  box-sizing: border-box;
}
h2,
.input {
  z-index: 2;
}

.input{
  display: flex;
  flex-direction: column;
  position: fixed;
  margin-top: 20%;
}
input{
  border-radius: 5%;
  border: none;
  margin-bottom: 5%;
  padding: 5%;
}


.wrap {
  position: fixed;
  inset: 0;
  pointer-events: none;
}

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

.svg {
  position: absolute;
  bottom: 0;
  /* start at bottom of viewport */
  will-change: transform;
  /* hint for performance */
  pointer-events: none;
  transform-origin: center center;

  img {
    width: 10%;
    /* or any size you want */
    height: auto;
    /* keeps aspect ratio */
  }
}

.svg:nth-child(1) {
  left: 10%;
  animation: raise1 12s linear infinite;
  animation-delay: -2s;
  transform: scale(0.9) rotate(45deg);
}

@keyframes raise1 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(180deg);
  }
}

.svg:nth-child(2) {
  left: 35%;
  animation: raise2 18s linear infinite;
  animation-delay: -3s;
  transform: scale(0.9) rotate(120deg);
}

@keyframes raise2 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(300deg);
  }
}

.svg:nth-child(3) {
  left: 60%;
  animation: raise3 9s linear infinite;
  animation-delay: -4s;
  transform: scale(0.9) rotate(210deg);
}

@keyframes raise3 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(30deg);
  }
}

.svg:nth-child(4) {
  left: 20%;
  animation: raise4 14s linear infinite;
  animation-delay: -1s;
  transform: scale(0.9) rotate(330deg);
}

@keyframes raise4 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(100deg);
  }
}

.svg:nth-child(5) {
  left: 75%;
  animation: raise5 19s linear infinite;
  animation-delay: -5s;
  transform: scale(0.9) rotate(60deg);
}

@keyframes raise5 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(270deg);
  }
}

.svg:nth-child(6) {
  left: 45%;
  animation: raise6 16s linear infinite;
  animation-delay: -2s;
  transform: scale(0.9) rotate(150deg);
}

@keyframes raise6 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(360deg);
  }
}

.svg:nth-child(7) {
  left: 5%;
  animation: raise7 16s linear infinite;
  animation-delay: -3s;
  transform: scale(0.9) rotate(250deg);
}

@keyframes raise7 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(140deg);
  }
}

.svg:nth-child(8) {
  left: 55%;
  animation: raise8 11s linear infinite;
  animation-delay: -4s;
  transform: scale(0.9) rotate(320deg);
}

@keyframes raise8 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(80deg);
  }
}

.svg:nth-child(9) {
  left: 85%;
  animation: raise9 20s linear infinite;
  animation-delay: -1s;
  transform: scale(1.5) rotate(90deg);
}

@keyframes raise9 {
  to {
    bottom: 150vh;
    transform: scale(1.5) rotate(200deg);
  }
}

.svg:nth-child(10) {
  left: 40%;
  animation: raise10 13s linear infinite;
  animation-delay: -5s;
  transform: scale(0.9) rotate(180deg);
}

@keyframes raise10 {
  to {
    bottom: 150vh;
    transform: scale(0.9) rotate(300deg);
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
