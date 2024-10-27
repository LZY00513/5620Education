import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/iconfont.css'
import '@/assets/css/style.scss'

const app = createApp(App)
app.config.warnHandler = () => null;

// Element Plus
import 'element-plus/theme-chalk/dark/css-vars.css';
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import ElementPlus from 'element-plus'
app.use(ElementPlus, {
  locale: zhCn
})

// Map
import VueAMap, { initAMapApiLoader } from "@vuemap/vue-amap";
import "@vuemap/vue-amap/dist/style.css"
initAMapApiLoader({
  key: "5c1c06db19e483c5b00dc052ca37848b",
  securityJsCode: '4d8f1bda690b722d8a547dabb51a9933',
  plugins: [
    "AMap.Autocomplete",
    "AMap.PlaceSearch",
    "AMap.Scale",
    "AMap.OverView",
    "AMap.ToolBar",
    "AMap.MapType",
    "AMap.PolyEditor",
    "AMap.CircleEditor",
    "AMap.Geocoder",
    "AMap.Geolocation",
    "AMap.Marker"
  ],
});
app.use(VueAMap)

// Rich Text Editor
import Editor from "@/components/Editor";
app.component('editor', Editor)

// Upload Component
import upload from "@/components/upload";
app.component('uploads', upload)

// Carousel
import mySwiper from "@/components/mySwiper/index";
app.component('mySwiper', mySwiper)

import http from './utils/http.js'
// Base configuration
import config from './utils/config'
// Utility functions
import toolUtil from './utils/toolUtil.js'

app.config.globalProperties.$config = config.get()
app.config.globalProperties.$project = config.getProjectName()
app.config.globalProperties.$toolUtil = toolUtil
app.config.globalProperties.$http = http // Ajax request methods

app.use(store)
app.use(router)
app.mount('#app')
