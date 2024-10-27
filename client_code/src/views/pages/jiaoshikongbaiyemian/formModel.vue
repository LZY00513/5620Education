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

      <div class="info_view">
        <div class="title_view">
          <div class="detail_title">
          </div>
        </div>
        <div class="info_item">
          <div class="info_label">Teacher ID</div>
          <div  class="info_text" >{{detail.jiaoshigonghao}}</div>
        </div>
        <div class="btn_view">
          <el-button v-if="centerType&&(detail.ispay=='Unpaid'||!detail.ispay)&&btnFrontAuth('jiaoshikongbaiyemian','Pay')" class="approval_btn" @click="payClick">Pay</el-button>
          <el-button class="edit_btn" v-if="centerType&&btnAuth('jiaoshikongbaiyemian','Edit')" type="primary" @click="editClick">Edit</el-button>
          <el-button class="del_btn" v-if="centerType&&btnAuth('jiaoshikongbaiyemian','Delete')" type="danger" @click="delClick">Delete</el-button>
        </div>
      </div>
    </div>
    <div class="detail-box">
      <div class="title"><span>Content</span></div>
      <div class="content" v-html="detail.neirong"></div>
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
// Basic information
const tableName = 'jiaoshikongbaiyemian'
const formName = 'Teacher Blank Page'
// Basic information
const breadList = ref([{
  name: formName
}])
// Permission verification
const btnAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isAuth(e,a)
  }
}
// Viewing permission verification
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
const detail = ref({})
const activeName = ref('false')
const getDetail = () => {
  context?.$http({
    url: `${tableName}/detail/${route.query.id}`,
    method: 'get'
  }).then(res => {
    detail.value = res.data.data
  })
}
// Download file
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
    // This writing is compatible with Firefox
    a.dispatchEvent(new MouseEvent('click', {
      bubbles: true,
      cancelable: true,
      view: window
    }))
    window.URL.revokeObjectURL(data)
  })
}
// Determine if it is redirected from the personal center
const centerType = ref(false)
const init = () => {
  if(route.query.centerType){
    centerType.value = true
  }
  getDetail()
}
// Edit
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
// Delete
const delClick = () => {
  ElMessageBox.confirm(`Are you sure you want to delete this ${formName}?`, 'Prompt', {
    confirmButtonText: 'Yes',
    cancelButtonText: 'No',
    type: 'warning',
  }).then(()=>{
    context?.$http({
      url: `${tableName}/delete`,
      method: 'post',
      data: [detail.value.id]
    }).then(res=>{
      context?.$toolUtil.message('Delete successful','success',()=>{
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
// Back box
.back_view {
  border-radius: 4px;
  padding: 10px 0px;
  margin: 10px auto;
  background: none;
  width: 100%;
  text-align: right;
  // Back button
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
  // Hover over the back button
  .back_btn:hover {
  }
}
// Breadcrumb box
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
  // Text area
  .info_view {

    .title_view {

      .detail_title {
      }
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
    }
    .btn_view {
      // Edit button
      .edit_btn {
      }
      // Hover
      .edit_btn:hover {
      }
      // Delete button
      .del_btn {
      }
      // Hover
      .del_btn:hover {
      }
    }
  }
}

// Bottom box
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
  // Content area
  :deep(.el-tabs__content) {
  }
}
</style>
