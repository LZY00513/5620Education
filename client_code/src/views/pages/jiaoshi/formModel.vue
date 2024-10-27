<template>
  <div class="detail-page" :style='{}'>
    <div class="bread_view">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
        <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="back_view">
      <el-button class="back_btn" @click="backClick" type="primary">Back</el-button>
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
            <span>{{detail.jiaoshixingming}}</span>
          </div>
        </div>
        <div class="info_item">
          <div class="info_label">Teacher ID</div>
          <div  class="info_text" >{{detail.jiaoshigonghao}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Contact Number</div>
          <div  class="info_text" >{{detail.lianxidianhua}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Gender</div>
          <div  class="info_text" >{{detail.xingbie}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Major</div>
          <div  class="info_text" >{{detail.zhuanye}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Subjects Taught</div>
          <div  class="info_text" >{{detail.suoshoukemu}}</div>
        </div>
        <div class="info_item">
          <div class="info_label">Available Time</div>
          <div  class="info_text" >{{detail.keyueshijian}}</div>
        </div>
        <div class="info_item" v-if="centerType">
          <div class="info_label">Reviewed</div>
          <div class="info_text">{{detail.sfsh}}</div>
        </div>
        <div class="info_item" v-if="centerType">
          <div class="info_label">Response Content</div>
          <div class="info_text">{{detail.shhf}}</div>
        </div>
        <div class="btn_view">
          <el-button class="cross_btn" v-if="btnFrontAuth(tableName,'Parent Appointment')" @click="jiazhangyuyuejiaoshionAcross('Parent Appointment','jiazhangyuyuejiaoshi','否','','','')" type="warning">Parent Appointment</el-button>
          <el-button class="cross_btn" v-if="btnFrontAuth(tableName,'Student Appointment')" @click="xueshengyuyuejiaoshionAcross('Student Appointment','xueshengyuyuejiaoshi','否','','','')" type="warning">Student Appointment</el-button>
          <el-button class="approval_btn" v-if="btnAuth('jiaoshi','Review')" type="warning" @click="approvalClick()">Review</el-button>
          <el-button v-if="centerType&&(detail.ispay=='Unpaid'||!detail.ispay)&&btnFrontAuth('jiaoshi','Payment')" class="approval_btn" @click="payClick">Pay</el-button>
          <el-button class="edit_btn" v-if="centerType&&btnAuth('jiaoshi','Edit')" type="primary" @click="editClick">Edit</el-button>
          <el-button class="del_btn" v-if="centerType&&btnAuth('jiaoshi','Delete')" type="danger" @click="delClick">Delete</el-button>
        </div>
      </div>
    </div>
    <Approval ref="approvalRef" :tableName="tableName" @shChange="init()"></Approval>
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
// Basic Information
const tableName = 'jiaoshi'
const formName = 'Teacher'
// Basic Information
const breadList = ref([{
  name: formName
}])
// Permission validation
const btnAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isAuth(e,a)
  }
}
// Front permission validation
const btnFrontAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isFrontAuth(e,a)
  }
}
// Back
const backClick = () =>{
  history.back()
}
// Carousel
const bannerList = ref([])
// Detail
const title = ref('')
const detail = ref({})
const activeName = ref('false')
const getDetail = () => {
  context?.$http({
    url: `${tableName}/detail/${route.query.id}`,
    method: 'get'
  }).then(res => {
    bannerList.value = res.data.data.touxiang?res.data.data.touxiang.split(','):[]
    title.value = res.data.data.jiaoshixingming
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
    // The following statement is compatible with Firefox
    a.dispatchEvent(new MouseEvent('click', {
      bubbles: true,
      cancelable: true,
      view: window
    }))
    window.URL.revokeObjectURL(data)
  })
}
// Check if redirected from personal center
const centerType = ref(false)
const init = () => {
  if(route.query.centerType){
    centerType.value = true
  }
  getDetail()
}
// Review
import Approval from '@/components/approval.vue'
const approvalRef = ref(null)
const approvalClick = (btnType='Review') => {
  if(!context?.$toolUtil.storageGet('frontToken')){
    context?.$toolUtil.message('Please log in before operating!','error')
    return false
  }
  if(!btnAuth(tableName,btnType)){
    context?.$toolUtil.message('No permission to operate!','error')
    return false
  }
  let row = detail.value
  let params = {
    id: row.id,
    sfsh: row.sfsh,
    shhf: row.shhf,
  }
  nextTick(() => {
    approvalRef.value.approvalClick(params)
  })
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
      context?.$toolUtil.message('Deleted successfully','success',()=>{
        history.back()
      })
    })

  })
}
const jiazhangyuyuejiaoshionAcross = async (btnType,table,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1) => {
  if(!context?.$toolUtil.storageGet('frontToken')){
    context?.$toolUtil.message('Please log in before operating!','error')
    return false
  }
  if(!btnAuth(tableName,btnType)){
    context?.$toolUtil.message('No permission to operate!','error')
    return false
  }
  if(crossOptAudit=='Yes'&&detail.value.sfsh!='Yes') {
    context?.$toolUtil.message('Please review and approve before operating!','error')
    return false
  }
  if(crossOptPay=='Yes'&&detail.value.ispay!='Paid') {
    context.$message.error('Please complete payment before operating')
    return;
  }
  context?.$toolUtil.storageSet('crossObj',JSON.stringify(detail.value))
  context?.$toolUtil.storageSet('crossTable',tableName)
  context?.$toolUtil.storageSet('crossStatusColumnName',statusColumnName)
  context?.$toolUtil.storageSet('crossTips',tips)
  context?.$toolUtil.storageSet('crossStatusColumnValue',statusColumnValue)
  if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
    var obj = detail.value
    for (var o in obj){
      if(o==statusColumnName && obj[o]==statusColumnValue){
        context?.$toolUtil.message(tips,'warning')
        return;
      }
    }
  }
  nextTick(()=>{
    router.push(`/index/${table}Add?type=cross&&id=${detail.value.id}`)
  })
}
const xueshengyuyuejiaoshionAcross = async (btnType,table,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1) => {
  if(!context?.$toolUtil.storageGet('frontToken')){
    context?.$toolUtil.message('Please log in before operating!','error')
    return false
  }
  if(!btnAuth(tableName,btnType)){
    context?.$toolUtil.message('No permission to operate!','error')
    return false
  }
  if(crossOptAudit=='Yes'&&detail.value.sfsh!='Yes') {
    context?.$toolUtil.message('Please review and approve before operating!','error')
    return false
  }
  if(crossOptPay=='Yes'&&detail.value.ispay!='Paid') {
    context.$message.error('Please complete payment before operating')
    return;
  }
  context?.$toolUtil.storageSet('crossObj',JSON.stringify(detail.value))
  context?.$toolUtil.storageSet('crossTable',tableName)
  context?.$toolUtil.storageSet('crossStatusColumnName',statusColumnName)
  context?.$toolUtil.storageSet('crossTips',tips)
  context?.$toolUtil.storageSet('crossStatusColumnValue',statusColumnValue)
  if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
    var obj = detail.value
    for (var o in obj){
      if(o==statusColumnName && obj[o]==statusColumnValue){
        context?.$toolUtil.message(tips,'warning')
        return;
      }
    }
  }
  nextTick(()=>{
    router.push(`/index/${table}Add?type=cross&&id=${detail.value.id}`)
  })
}
onMounted(()=>{
  init()
})
</script>

<style lang="scss" scoped>
// Return box
.back_view {
  border-radius: 4px;
  padding: 10px 0px;
  margin: 10px auto;
  background: none;
  width: 100%;
  text-align: right;
  // Return button
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
  // Return button - hover
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

.detail_view {
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
      // Cross-table - button
      .cross_btn {
      }
      // Hover
      .cross_btn:hover {
      }
      // Approval - button
      .approval_btn {
      }
      // Hover
      .approval_btn:hover {
      }
      // Edit - button
      .edit_btn {
      }
      // Hover
      .edit_btn:hover {
      }
      // Delete - button
      .del_btn {
      }
      // Hover
      .del_btn:hover {
      }
    }
  }
}

// Footer box
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
