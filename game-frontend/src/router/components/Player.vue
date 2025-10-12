<script setup>

import Avatar from 'primevue/avatar';
const props = defineProps(['player'])
defineOptions({
  inheritAttrs: false
})


</script>

<template>
<Avatar>{{ props.player[0]["username"]}}</Avatar>
</template>

<style scoped>



</style>