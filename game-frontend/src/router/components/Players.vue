<script setup>

import Player from 'Player.vue';
const props = defineProps(['players'])


</script>

<template>

    <Player v-for="player in props.players" :username  = "player.username"></Player>


</template>

<style scoped>



</style>