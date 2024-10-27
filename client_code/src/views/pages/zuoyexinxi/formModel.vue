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
            <span>{{detail.zuoyemingcheng}}</span>
          </div>
        </div>
        <div class="info_item">
          <div class="info_label">Countdown</div>
          <count-down class="countDown" v-if="endTime&&startTime" :startTime="startTime" :endTime="endTime" :tipText="'Time until start'"
                      tipTextEnd="Time until end " :endText="`Countdown has ended`" dayTxt="days " hourTxt="hours "
                      minutesTxt="minutes " secondsTxt="seconds">
          </count-down>
        </div>
        <div class="info_item">
          <div class="info_label">Release Time</div>
          <div  class="info_text" >{{detail.fabushijian}}</div>
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
          <div class="info_label">Assignment Content</div>
          <div class="info_text">
            <el-button class="info_down" v-if="detail.zuoyeneirong" @click="downClick(detail.zuoyeneirong)">Click to Download</el-button>
            <el-button class="info_undown" v-else>No Available</el-button>
          </div>
        </div>
        <div class="btn_view">
          <el-button class="cross_btn" v-if="btnFrontAuth(tableName,'Submit')" @click="tijiaozuoyeonAcross('Submit','tijiaozuoye','','','[1]','Submitted')" type="warning">Submit</el-button>
          <el-button v-if="centerType&&(detail.ispay=='Not Paid'||!detail.ispay)&&btnFrontAuth('zuoyexinxi','Pay')" class="approval_btn" @click="payClick">Pay</el-button>
          <el-button class="edit_btn" v-if="centerType&&btnAuth('zuoyexinxi','Modify')" type="primary" @click="editClick">Modify</el-button>
          <el-button class="del_btn" v-if="centerType&&btnAuth('zuoyexinxi','Delete')" type="danger" @click="delClick">Delete</el-button>
        </div>
      </div>
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
import countDown from '@/components/countDown'
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
const tableName = 'zuoyexinxi'
const formName = 'Assignment Information'
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
// View permission verification
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
const startTime = ref('')
const endTime = ref('')
const getDetail = () => {
  context?.$http({
    url: `${tableName}/detail/${route.query.id}`,
    method: 'get'
  }).then(res => {
    bannerList.value = res.data.data.fengmian?res.data.data.fengmian.split(','):[]
    title.value = res.data.data.zuoyemingcheng
    startTime.value = new Date().getTime()
    endTime.value = new Date(res.data.data.reversetime).getTime()
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
    // This line is compatible with Firefox
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
// Modify
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
// Delete
const delClick = () => {
  ElMessageBox.confirm(`Are you sure to delete this ${formName}?`, 'Tip', {
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
const tijiaozuoyeonAcross = async (btnType,table,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1) => {
  if(!context?.$toolUtil.storageGet('frontToken')){
    context?.$toolUtil.message('Please log in to operate!','error')
    return false
  }
  if(!btnAuth(tableName,btnType)){
    context?.$toolUtil.message('No permission to operate!','error')
    return false
  }
  if(crossOptPay=='Yes'&&detail.value.ispay!='Paid') {
    context.$message.error('Please complete the payment before operating')
    return;
  }
  if (new Date().getTime() > new Date(detail.value.reversetime).getTime()) {
    context?.$toolUtil.message('Countdown has ended!','error')
    return false
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
	// 返回盒子
	.back_view {
		border-radius: 4px;
		padding: 10px 0px;
		margin: 10px auto;
		background: none;
		width: 100%;
		text-align: right;
		// 返回按钮
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
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	// 面包屑盒子
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
		// 轮播图
		.swiper_view {
		}
		// 文字区
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
				// 跨表-按钮
				.cross_btn {
				}
				// 悬浮
				.cross_btn:hover {
				}
				// 修改-按钮
				.edit_btn {
				}
				// 悬浮
				.edit_btn:hover {
				}
				// 删除-按钮
				.del_btn {
				}
				// 悬浮
				.del_btn:hover {
				}
			}
		}
	}

	//底部盒子
	.tabs_view {
		:deep(.el-tabs__header) {
			background: transparent;
			border: none;
		}
		// 头部
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
		// 内容区
		:deep(.el-tabs__content) {
		}
	}
</style>