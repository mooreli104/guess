import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config';
import Nora from '@primeuix/themes/nora'




const app = createApp(App)

app.use(router)
app.use(PrimeVue, {
    theme:
    {
        preset: Nora
    }
    
})

app.mount('#app')