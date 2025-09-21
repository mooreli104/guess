<script setup>
const socket = new WebSocket("ws://localhost:8080/game");
</script>

<template>
  <RouterView :="socket"></RouterView>
</template>

<style scoped></style>
