<template>
  <div class="detail-page" :style='{}'>
    <div class="bread_view">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
        <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="back_view">
      <el-button class="back_btn" @click="backClick" type="primary">Return</el-button>
    </div>
    <div class="detail_view">
      <div class="swiper_view">
        <mySwiper :data="bannerList" :type="3"
                  :loop="false"
                  :navigation="true"
                  :pagination="false"
                  :paginationType="1"
                  :scrollbar="false"
                  :slidesPerView="1"
                  :spaceBetween="20"
                  :autoHeight="false"
                  :centeredSlides="false"
                  :freeMode="false"
                  :effectType="0"
                  :direction="horizontal"
                  :autoplay="false"
                  :slidesPerColumn="1">
          <template #default="scope">
            <img :style='{"objectFit":"contain","width":"100%","height":"480px"}' :src="scope.row?$config.url + scope.row:''">
          </template>
        </mySwiper>
      </div>

      <div class="info_view">
        <div class="title_view">
          <div class="detail_title">
            <span>{{detail.ziyuanmingcheng}}</span>
          </div>
          <div class="collect_view" v-if="!collectType" @click="collectClick(1)">
            <i class="iconfont icon-likeline1"></i>
            <span>Collect</span>
          </div>
          <div class="collect_view" v-if="collectType" @click="collectClick(-1)">
            <i class="iconfont iconfontActive icon-likefill1"></i>
            <span class="textActive">Cancel Collection</span>
          </div>
        </div>
        <div class="info_item">
          <div class="info_label">Resource Type</div>
          <div  class="info_text" >{{detail.ziyuanleixing}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Resource Summary</div>
          <div  class="info_text" >{{detail.ziyuanjianshu}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Teacher ID</div>
          <div  class="info_text" >{{detail.jiaoshigonghao}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Teacher Name</div>
          <div  class="info_text" >{{detail.jiaoshixingming}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Collection Count</div>
          <div  class="info_text" >{{detail.storeupNumber}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Resource Attachment</div>
          <div class="info_text">
            <el-button class="info_down" v-if="detail.ziyuanfujian" @click="downClick(detail.ziyuanfujian)">Click to Download</el-button>
            <el-button class="info_undown" v-else>No Resources</el-button>
          </div>
        </div>
        <div class="btn_view">
          <el-button v-if="centerType&&(detail.ispay=='Unpaid'||!detail.ispay)&&btnFrontAuth('jiaoxueziyuan','Pay')" class="approval_btn" @click="payClick">Pay</el-button>
          <el-button class="edit_btn" v-if="centerType&&btnAuth('jiaoxueziyuan','Modify')" type="primary" @click="editClick">Modify</el-button>
          <el-button class="del_btn" v-if="centerType&&btnAuth('jiaoxueziyuan','Delete')" type="danger" @click="delClick">Delete</el-button>
        </div>
      </div>
    </div>
    <div class="detail-box">
      <div class="title"><span>Resource Details</span></div>
      <div class="content" v-html="detail.ziyuanxiangqing"></div>
    </div>
  </div>
</template>
<script setup>
import axios from 'axios'
import moment from 'moment'
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed
} from 'vue';
import {
  ElMessageBox
} from 'element-plus'
import {
  useRoute,
  useRouter
} from 'vue-router';
import {
  useStore
} from 'vuex';
const store = useStore()
const user = computed(()=>store.getters['user/session'])
const userAvatar = computed(()=>store.getters['user/avatar'])
const context = getCurrentInstance()?.appContext.config.globalProperties;
const route = useRoute()
const router = useRouter()
//Basic Information
const tableName = 'jiaoxueziyuan'
const formName = 'Teaching Resource'
//Basic Information
const breadList = ref([{
  name: formName
}])
//Permission Validation
const btnAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isAuth(e,a)
  }
}
//View Permission Validation
const btnFrontAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isFrontAuth(e,a)
  }
}
// Return
const backClick = () =>{
  history.back()
}
// Carousel
const bannerList = ref([])
// Details
const title = ref('')
const detail = ref({});
const activeName = ref('false')
const getDetail = () => {
  context?.$http({
    url: `${tableName}/detail/${route.query.id}`,
    method: 'get'
  }).then(res => {
    bannerList.value = res.data.data.fengmian?res.data.data.fengmian.split(','):[]
    title.value = res.data.data.ziyuanmingcheng
    detail.value = res.data.data
  })
}
// Download File
const downClick = (file) => {
  if(!file){
    context?.$toolUtil.message('File does not exist','error')
  }
  let arr = file.replace(new RegExp('file/', "g"), "")
  axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
    headers: {
      token: context?.$toolUtil.storageGet('frontToken')
    },
    responseType: "blob"
  }).then(({
             data
           }) => {
    const binaryData = [];
    binaryData.push(data);
    const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
      type: 'application/pdf;chartset=UTF-8'
    }))
    const a = document.createElement('a')
    a.href = objectUrl
    a.download = arr
    // a.click()
    // This method is compatible with Firefox
    a.dispatchEvent(new MouseEvent('click', {
      bubbles: true,
      cancelable: true,
      view: window
    }))
    window.URL.revokeObjectURL(data)
  })
}
// Check if navigating from personal center
const centerType = ref(false)
const init = () => {
  if(route.query.centerType){
    centerType.value = true
  }
  getDetail()
  // Collection
  getCollect()
}
// Collection
const collectType = ref(false)
const collectInfo = ref({})
const getCollect = () => {
  if (context?.$toolUtil.storageGet('frontToken')) {
    context?.$http({
      url: 'storeup/list',
      method: 'get',
      params: {
        page: 1,
        limit: 1,
        type: 1,
        refid: route.query.id,
        tablename: tableName,
        userid: context?.$toolUtil.storageGet('userid')
      }
    }).then(res => {
      if (res.data.data.list.length) {
        collectType.value = true
        collectInfo.value = res.data.data.list[0]
      }else{
        collectType.value = false
        collectInfo.value = {}
      }
    })
  }
}
// Collect button
const collectClick = (type) => {
  if (type == 1 && !collectType.value) {
    let params = {
      name: title.value,
      picture: bannerList.value[0],
      refid: detail.value.id,
      type: type,
      tablename: tableName,
      userid: context?.$toolUtil.storageGet('userid')
    }
    context?.$http({
      url: 'storeup/add',
      method: 'post',
      data: params
    }).then(res => {
      detail.value.storeupNumber += 1
      context?.$http({
        url: `${tableName}/update`,
        method: 'post',
        data: detail.value
      })
      collectType.value = true
      getCollect()
      context?.$toolUtil.message('Collection successful', 'success')
    })
  }
  else if (type == -1 && collectType.value) {
    let ids = []
    ids.push(collectInfo.value.id)
    context?.$http({
      url: 'storeup/delete',
      method: 'post',
      data: ids
    }).then(res => {
      detail.value.storeupNumber -= 1
      context?.$http({
        url: `${tableName}/update`,
        method: 'post',
        data: detail.value
      })
      collectInfo.value = {}
      collectType.value = false
      context?.$toolUtil.message('Cancellation successful', 'success')
    })
  }
}
//Modify
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
//Delete
const delClick = () => {
  ElMessageBox.confirm(`Do you want to delete this ${formName}？`, 'Tip', {
    confirmButtonText: 'Yes',
    cancelButtonText: 'No',
    type: 'warning',
  }).then(()=>{
    context?.$http({
      url: `${tableName}/delete`,
      method: 'post',
      data: [detail.value.id]
    }).then(res=>{
      context?.$toolUtil.message('Deletion successful','success',()=>{
        history.back()
      })
    })

  })
}
onMounted(()=>{
  init()
})
</script>

<style lang="scss" scoped>
// Return Box
.back_view {
  border-radius: 4px;
  padding: 10px 0px;
  margin: 10px auto;
  background: none;
  width: 100%;
  text-align: right;
  // Return Button
  .back_btn {
    border: 1px solid #0076ca50;
    cursor: pointer;
    border-radius: 4px;
    padding: 0 24px;
    color: #0076ca;
    background: #fff;
    width: auto;
    font-size: 14px;
    height: 34px;
  }
  // Return Button - Hover
  .back_btn:hover {
  }
}
// Breadcrumb Box
.bread_view {
  :deep(.breadcrumb) {
    .el-breadcrumb__separator {
    }
    .first_breadcrumb {
      .el-breadcrumb__inner {
      }
    }
    .second_breadcrumb {
      .el-breadcrumb__inner {
      }
    }
  }
}

.detail_view{
  // Carousel
  .swiper_view {
  }
  // Text Area
  .info_view {

    .title_view {

      .detail_title {
      }
      // Collection Box
      .collect_view {
        border: 0px solid #eee;
        cursor: pointer;
        border-radius: 10px;
        padding: 10px 0px;
        color: #999;
        background: none;
        display: flex;
        width: auto;
        line-height: 1;
        justify-content: center;
        align-items: center;
        .iconfont {
          margin: 0 5px 0 0;
          color: #666;
        }
        .iconfontActive {
          margin: 0 5px 0 0;
          color: #fc6a20;
        }
        span {
          color: #666;
        }
        .textActive {
          color: #fc6a20;
        }
      }
      .collect_view:hover {
      }
      .collect_view:active {
        transform: scale(0.8);
      }
    }

    .info_item {

      .info_label {
      }
      .info_text {
      }
      .info_down {
        padding: 5px 20px;
        background: #fff;
      }
      .info_down:hover {
        color: #333;
        border-color: #ddd;
      }
      .info_undown {
        padding: 5px 20px;
        color: #999;
        background: #fff;
      }
    }
    .btn_view {
      // Modify Button
      .edit_btn {
      }
      // Hover
      .edit_btn:hover {
      }
      // Delete Button
      .del_btn {
      }
      // Hover
      .del_btn:hover {
      }
    }
  }
}

// Bottom Box
.tabs_view {
  :deep(.el-tabs__header) {
    background: transparent;
    border: none;
  }
  // Header
  :deep(.el-tabs__nav-scroll) {
    .el-tabs__nav {
      .el-tabs__item {
      }
      .el-tabs__item:hover {
      }
      .is-active {
      }
    }
  }
  // Content Area
  :deep(.el-tabs__content) {
  }
}
</style>
