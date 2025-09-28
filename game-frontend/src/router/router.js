import { createRouter, createWebHistory } from 'vue-router'
import HomeView from './views/HomeView.vue'
import LobbyView from './views/LobbyView.vue'
import GameView from './views/GameView.vue'

const routes = [
  { path: '/', component: HomeView },
  { path: '/lobby', component: LobbyView },
  { path: '/game', component: GameView}

]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
