import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import LobbyView from './views/LobbyView.vue'

const routes = [
  { path: '/', component: HomeView },
  {path: '/lobby', component: LobbyView}

]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
