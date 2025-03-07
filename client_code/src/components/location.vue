<template>
  <div>
    <el-dialog v-model="locationVisible" :title="'Select Location'" width="70%">
      <div class="aMapMain">
        <div v-if="isDisplayAMap">
          <div class="aMap">
            <el-amap ref="mapRef" :min-zoom="8" :max-zoom="22" :center="center" :zoom="zoom"
                     @click="clickMap" @init="initMap">
              <el-amap-search-box @select="selectPoi" @choose="selectPoi" v-if="isShowAMap" />
              <el-amap-marker :position="componentMarker.position" :content="componentMarker.content" />
              <el-amap-control-geolocation @complete="getLocation" :extensions="extensions" />
            </el-amap>
          </div>
        </div>
        <div v-else class="isDisplayAMap">
          <el-amap ref="mapRef" :min-zoom="8" :max-zoom="22" :center="center" :zoom="zoom" @click="clickMap"
                   @init="initMap">
            <el-amap-search-box @select="selectPoi" @choose="selectPoi" v-if="isShowAMap" />
            <el-amap-marker :position="componentMarker.position" :content="componentMarker.content" />
            <el-amap-control-geolocation @complete="getLocation" :extensions="extensions" />
          </el-amap>
        </div>
      </div>
      <template #footer>
				<span class="dialog-footer">
					<el-button @click="chooseMapClick">Confirm Location</el-button>
				</span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {
  Plus,
  Delete
} from '@element-plus/icons-vue'
import {
  toRefs
} from '@vueuse/shared'
import {
  reactive,
  ref,
  watch
} from 'vue'
import {
  defineProps,
  getCurrentInstance
} from '@vue/runtime-core'
const {
  proxy
} = getCurrentInstance()
const props = defineProps({
  toParentsMap: {
    type: Object, // Default data from parent component
    default: {}
  },
  isShowAMap: {
    type: Boolean, // Control whether to display search box
    default: true
  },
  isDisplayAMap: {
    type: Boolean, // Control whether to display map view
    default: true
  },
})
const locationVisible = ref(false)
const mapShow = () => {
  locationVisible.value = true
  initMapInfo()
}
// Declare parent call
defineExpose({
  mapShow
})
const emit = defineEmits(['mapData'])
const zoom = ref(16)
const center = ref([
  props.toParentsMap.lng || 116.397443,
  props.toParentsMap.lat || 39.909172
])
const componentMarker = ref({
  position: [
    props.toParentsMap.lng || 116.397443,
    props.toParentsMap.lat || 39.909172
  ],
  visible: true,
  draggable: false
})
const toParentsMapInfo = ref({})

/** Initialize map data using parent component data */
const initMapInfo = () => {
  toParentsMapInfo.value = props.toParentsMap
}

/** Initialize map */
const initMap = (e) => {}
// Get current location
const getLocation = (e) => {
  let position = [e.position.lng, e.position.lat]
  componentMarker.value.position = position
  center.value = position
  getAddress(position)
}
// Click marker
const clickMap = (e) => {
  let position = [e.lnglat.lng, e.lnglat.lat]
  center.value = position
  componentMarker.value.position = position
  getAddress(position)
}
/** Select location on map */
const selectPoi = (e) => {
  let poi = e.poi
  if (poi.address.length > 0 && poi.location != undefined &&
      poi.location != null && poi.location != '') {
    let position = [e.poi.location.lng, e.poi.location.lat]
    center.value = position
    componentMarker.value.position = position
    getAddress(position)
  } else {
    // proxy.$modal.msgWarning('The entered location failed to locate, please enter a detailed location for positioning!')
  }
}
// Get detailed address
const getAddress = (position) => {
  var geocoder = new AMap.Geocoder({
    radius: 1000,
    extensions: 'all'
  })
  geocoder.getAddress(position, function(status, result) {
    toParentsMapInfo.value = {
      lng: position[0], // Longitude
      lat: position[1], // Latitude
      fulladdress: '' // Detailed address
    }
    let reg = /.+?(省|市|自治区|自治州|盟|旗|县|区)/g // Extract map address
    let detailedAddress = result.regeocode.formattedAddress
    let districtList = detailedAddress.match(reg)
    if (districtList.length < 3) {
      toParentsMapInfo.value.city = districtList[0]
      toParentsMapInfo.value.zone = districtList[1]
    } else {
      toParentsMapInfo.value.city = districtList[1]
      toParentsMapInfo.value.zone = districtList[2]
    }
    toParentsMapInfo.value.province = districtList[0]
    toParentsMapInfo.value.fulladdress = detailedAddress
  })
}
const chooseMapClick = () => {
  emit('mapData', toParentsMapInfo.value) // Pass value to parent component
  locationVisible.value = false
}

</script>

<style lang="scss">
.amap-sug-result {
  z-index: 2099;
}

.amap-copyright {
  height: 24px;
}

.el-vue-search-box-container,
.el-vue-search-box-container input {
  height: 29px;
  border-radius: 8px;
}

.aMapMain {
  width: 100%;

  .aMap {
    width: 100%;
    height: 500px;
  }

  .aMapAddress {
    text-align: left;

    p {
      margin-top: 5px;
      margin-bottom: 0;
    }
  }

  .isDisplayAMap {
    width: 100%;
    height: 36px;

    .el-vue-amap {
      display: none;
    }

    .el-vue-search-box-container {
      position: initial;
      width: 100%;
    }
  }
}

.el-vue-search-box-container {
  border: 1px solid #e2e2e2;
  height: 36px;
  box-shadow: none;
}
</style>
