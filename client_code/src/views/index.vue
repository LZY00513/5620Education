<template>
  <div>
    <div class="home">
      <div class="index_top">
        <div class="index_top_title">Smart Education Platform</div>
        <div class="index_top_right">
          <template v-if="Token">
            <div v-if="date&&weather" class="weather_time_view">
              <div class="weather">
                <div class="city">{{weather.city}}</div>
                <div class="wea">
                  {{weather.day.wea}} | {{weather.day.tem}}℃
                </div>
              </div>
              <div class="time">
                <div class="date">{{date}}</div>
                <div class="timer">{{timer}}</div>
              </div>
            </div>
          </template>
          <el-button v-if="!Token" type="success" icon="User" circle @click="loginClick">
            Login
          </el-button>
          <div class="user" v-if="Token">
            <el-dropdown class="avatar-container" trigger="hover">
              <div class="avatar-wrapper">
                <img class="user-avatar" :src="store.getters['user/avatar']" style="width: 50px">
                <div class="nickname">{{$toolUtil.storageGet("frontName")}}</div>
                <el-icon class="el-icon-arrow-down">
                  <arrow-down />
                </el-icon>
              </div>
              <template #dropdown>
                <el-dropdown-menu class="user-dropDown" slot="dropdown">
                  <el-dropdown-item @click="menuHandler('center')" class="center">
                    <span>Personal Center</span>
                  </el-dropdown-item>
                  <el-dropdown-item @click="loginOut" class="loginOut">
                    <span>Logout</span>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </div>
      <el-scrollbar class="contain_view">
        <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
          <el-menu :default-openeds="[]" :unique-opened="true" :default-active="menuIndex"
                   class="menu_view" mode="horizontal" :ellipsis="false" @select="menuChange" :key="menuIndex">
            <el-menu-item class="first-item" index="0" @click="menuHandler('/')">
              <template #title>
                <span>Home</span>
              </template>
            </el-menu-item>
            <template v-for="(menu,index) in menuList" :key="menu.menu">
              <el-sub-menu v-if="menu.child.length>1" :index="index+2+''" class="first-item">
                <template #title>
                  <span>{{ menu.name }}</span>
                </template>
                <el-menu-item class="second-item" v-for=" (child,sort) in menu.child" :key="sort"
                              :index="(index+2)+'-'+sort" @click="menuHandler(child.url)">{{ child.name }}
                </el-menu-item>
              </el-sub-menu>
              <el-menu-item v-else :index="index+2+''" class="first-item" @click="menuHandler(menu.child[0].url)">
                <template #title>
                  <span>{{menu.child[0].name}}</span>
                </template>
              </el-menu-item>
            </template>
          </el-menu>
        </el-scrollbar>
        <div class="rotation_view">
          <mySwiper :type="3" :data="rotationList" :autoHeight="false" :autoplay="true"
                    :loop="false" :navigation="true" :pagination="false"
                    :paginationType="1" :scrollbar="false" :slidesPerView="1"
                    :spaceBetween="20" :centeredSlides="false"
                    :freeMode="false" :effectType="9"
                    :direction="horizontal">
            <template #default="scope">
              <img :style='{}' :src="scope.row.value?$config.url + scope.row.value:''" @click="carouselClick(scope.row.url)">
            </template>
          </mySwiper>
        </div>
        <router-view />
        <el-backtop :right="100" :bottom="100" />
        <div class="bottom_view">
          <img class="bottom_img" src="http://chy2.gdnxeco.com/20230912/0d5ae5e141c14b06a52a7c4c2f8ea4cf.jpg" alt="">
          <div class="bottom_company"></div>
          <div class="bottom_record"></div>
          <div class="bottom_desc"></div>
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script setup>
	import menu from '@/utils/menu'
	import axios from 'axios'
	import {
		ref,
		onBeforeUnmount,
		getCurrentInstance,
		nextTick,
		computed,
		watch,
	} from 'vue';
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const router = useRouter()
	const route = useRoute();
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const Token = ref('')
	const weather = ref(null)
	const date = ref('')
	const timer = ref('')
	const interval = ref(null)
	if(localStorage.getItem('frontToken') && !store.getters['user/session'].id){
		store.dispatch('user/getSession')
	}
	const getWeather = () => {
		axios({
			method: 'get',
			url: 'http://gfeljm.tianqiapi.com/free/v2031?appid=69475998&appsecret=rldbX1Zl&hours=1&city=深圳'
		}).then(res => {
			weather.value = res.data
		})
	}
	onBeforeUnmount(() => {
		clearInterval(interval.value)
	})
	// 获取默认菜单index
	const setMenuIndex = ()=>{
		menuIndex.value = ''
		nextTick(()=>{
			menuIndex.value = context?.$toolUtil.storageGet('menuIndex')?context?.$toolUtil.storageGet('menuIndex'): '0'
		})
	}
	// 默认菜单index
	const menuIndex = ref('0')
	watch(() => router.currentRoute.value,() => {
		Token.value = context?.$toolUtil.storageGet('frontToken')
		setMenuIndex()
	},{
		immediate:true
	})
	const init = () => {
		// 获取菜单
		getMenu()
		// 赋值token
		Token.value = context?.$toolUtil.storageGet('frontToken')
		// 时间
		interval.value = setInterval(() => {
			date.value = context?.$toolUtil.getCurDate()
			timer.value = context?.$toolUtil.getCurDateTime().split(' ')[1]
		}, 1000)
		// 天气
		getWeather()
		// 轮播图
		getRotationList()
		if(Token.value){
			getSession()
		}
	}
	// 切换菜单保存index
	const menuChange = (e)=>{
		if(e=='chat')return
		context?.$toolUtil.storageSet('menuIndex',e)
	}
	// 轮播图
	const rotationList = ref([])
	const baseUrl = context?.$config.url
	const getRotationList = () => {
		context?.$http({
			url: 'config/list',
			method: 'get',
			params: {
				page: 1,
				limit: 3
			}
		}).then(res => {
			rotationList.value = res.data.data.list
		})
	}

    // 轮播图跳转
    const carouselClick = (url)=>{
        if (url) {
            if (url.startsWith('http')) {
                window.open(url)
            } else {
                context.$router.push(url)
            }
        }
    }
	const menuList = ref([])
	const role = ref('')
	const getMenu = () => {
		let params = {
			page: 1,
			limit: 1,
			sort: 'id',
		}
		context?.$http({
			url: "menu/list",
			method: "get",
			params: params
		}).then(res => {
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
		})
		menuList.value = context?.$config.menuList
	}
	const loginClick = () => {
		context?.$toolUtil.storageSet('toPath',window.history.state.current)
		router.push('/login')
	}
	const loginOut = () => {
		context?.$toolUtil.message('exit success', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
		context?.$toolUtil.storageSet('menuIndex','0')
		Token.value = ''
	}
	//菜单跳转
	const menuHandler = (name) => {
		if (name == 'center') {
			name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
			menuChange('center')
		}
		router.push(name)
	}
	// 获取用户信息
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'users'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'jiaoshi'){
				context?.$toolUtil.storageSet("frontName", res.data.data.jiaoshigonghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'jiaoshi'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'xuesheng'){
				context?.$toolUtil.storageSet("frontName", res.data.data.xuehao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'xuesheng'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'jiazhang'){
				context?.$toolUtil.storageSet("frontName", res.data.data.jiazhangzhanghao)
			}
		})
	}
	init()
</script>
<style lang="scss" scoped>
	/* 头部 */
	.index_top {
		.index_top_title {
		}
		.index_top_right {
			line-height: 1;
			// 天气时间盒子
			.weather_time_view {
				display: flex;
				align-items: center;
				// 天气城市盒子
				.weather {
					padding: 0 10px;
					flex-direction: row;
					display: none;
					font-size: 16px;
					line-height: 1;
					justify-content: center;
					align-items: center;
					// 城市
					.city {
						padding: 0;
						margin: 0 10px 0 0;
					}
					// 天气
					.wea {
					}
				}
				// 时间盒子
				.time {
					padding: 0 20px;
					flex-direction: row;
					display: flex;
					font-size: inherit;
					line-height: 1;
					justify-content: center;
					align-items: center;
					// 日期
					.date {
						padding: 0;
						margin: 0 10px 0 0;
					}
					// 时间
					.timer {
						padding: 0;
						margin: 0;
					}
				}
			}
		}
		// 登录按钮
		:deep(.el-button--success) {
			border-radius: 4px;
			padding: 10px;
			margin: 0 10px 0 0;
			color: #333;
			background: none;
			font-size: 16px;
			border-color: transparent;
		}
		:deep(.el-button--success:hover) {
		}
		// 登出按钮
		:deep(.el-button--danger) {
			border-radius: 4px;
			padding: 10px;
			color: #333;
			background: none;
			font-size: 16px;
			border-color: transparent;
		}
		:deep(.el-button--danger:hover) {
		}
	}
	// 轮播盒子
	.rotation_view {
	}
	// 底部
	.bottom_view {
		// 图片
		.bottom_img {
			border-radius: 100%;
			object-fit: cover;
			display: none;
			width: 44px;
			height: 44px;
		}
		// 公司
		.bottom_company {
			margin: 10px 0 0;
		}
		// 备案号
		.bottom_record {
			margin: 10px 0 0;
		}
		// 联系方式
		.bottom_desc {
			margin: 10px 0 0;
		}
	}




	.contain_view {
		margin: 0;
		background: #fff;
		position: relative;
		height: calc(100% - 60px);
	}
	.el-aside {
		transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-moz-transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-o-transition: width 0.15s;
	}
	.el-sub-menu__hide-arrow{
		display: block !important;
	}
	.menu_scrollbar {
	}
	// 总盒子
	.menu_view {
		// 一级菜单
		:deep(.first-item) {
			transition: all 0s;

			.el-sub-menu__title:hover{
				background: none !important;
			}
			// 图标

			// 标题
			.el-sub-menu__title,
			span {
			}

			//箭头
			.el-sub-menu__icon-arrow {
			}
		}
		// 选中
		:deep(.is-active) {
			color: #fff !important;
		}

		// 悬浮
		:deep(.first-item:hover) {
			color: #fff !important;
		}
	}
</style>
<style lang="scss">
	// 二级
	.menu_popper {
		border:none !important;
		background: none !important;

		// 二级总盒子
		.el-menu--popup {
		}

		// 二级菜单
		.second-item {
		}

		// 选中
		.is-active {
		}

		// 悬浮
		.second-item:hover {
		}
	}
</style>
<style>
/* 盒子 */
.index_top {
    box-sizing: border-box;
    width: 100%;
    display: flex;
    align-items: center;
    height: 64px;
    background: #fff;
    color: #333;
    font-size: 14px;
    justify-content: space-between;
    padding: 0px calc((100% - 1200px) / 2);
    z-index: 1002;
    position: relative;
    border-bottom:0px solid #2cc3ca50;
}
/* 标题 */
.index_top .index_top_title{
    font-size: 16px;
    color: inherit;
    font-weight: bold;
    margin-left:20px;
}
/* 右部 盒子 */
.index_top .index_top_right{
    display: flex;
    align-items: center;
    margin-right:20px;
    color: inherit;
}
/* 系统公告 */
.index_top .notice-btn{
    background: none;
    border: 0px solid #dcdfe6;
    color: inherit;
    margin: 0;
    padding: 10px;
    font-size: inherit;
    border-radius: 0px;
}
.index_top .notice-btn:hover{
    color: #333;
}
/* 图标 */
.index_top .notice-btn .iconfont{
    font-size: 16px;
    color: inherit;
}

/* 客服聊天 */
.index_top .index_top_right .chat{
    margin-right:10px;
}
.index_top .index_top_right .chat:hover{
    color: #333;
}
.index_top .index_top_right .chat .iconfont{
    font-size: inherit;
}
.index_top .index_top_right .chat span{
    font-size: inherit;
}
/* 购物车 */
.index_top .index_top_right .cart{
    margin-right:10px;
    font-size: inherit;
}
.index_top .index_top_right .cart:hover{
    color: #333;
}
.index_top .index_top_right .cart .iconfont{
    font-size: inherit;
    margin-right:5px;
}

/* 用户 盒子 */
.index_top .user .el-dropdown{
    color: inherit;
}
.index_top .user .avatar-wrapper{
    display: flex;
    align-items: center;
    cursor:pointer;
    color: inherit;
}
/* 头像 */
.index_top .user .avatar-wrapper .user-avatar{
    width: 36px !important;
    height: 36px;
    border-radius: 100%;
    margin-right:5px;
}
/* 昵称 */
.index_top .user .avatar-wrapper .nickname{
    font-size: inherit;
    margin-right:5px;
    color: inherit;
}
/* 箭头 */
.index_top .user .avatar-wrapper .el-icon-arrow-down{
    color: inherit;
}

/* 用户下拉 盒子 */
.user-dropDown{
    padding: 10px 0;
    margin: 5px 0;
    background: #fff;
    border: 1px solid #e6ebf5;
    border-radius: 4px;
    -webkit-box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
}
.user-dropDown li{
    padding: 0 20px !important;
    line-height: 36px !important;
    font-size: 14px !important;
    color: #606266 !important;
}
.user-dropDown li:hover{
    color: #0076ca !important;
    background: none !important;
}

.user-dropDown li.loginOut{
    background: none !important;
    border-color: none !important;
    color: #666 !important;
}
.user-dropDown li.loginOut:hover{
    border-radius: 0px !important;
    background: none !important;
    color: #0076ca !important;
}
.bottom_view {
    width: 100%;
    margin:20px auto 0;
    background: #333;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 40px 0px 20px ;
    min-height: 108px;
    border-width: 0px 0px 0px;
    border-style: solid;
    border-color: rgb(254, 176, 67);
    font-size: 16px;
    color: rgb(51, 51, 51);
    line-height: 1.8;
}

/* 总盒子(可滚动容器) */
.menu_scrollbar {
    width: 100%;
    background:none;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(239, 239, 239);
}
.menu_scrollbar .el-scrollbar__view {
    padding-bottom: 0px;
}
/* 菜单盒子 */
.menu_scrollbar .menu_view {
    width: 1200px;
    margin:0 auto;
    background: #7282a4;
    border-bottom:none;
    padding:0 10px;
    border-radius: 10px;
    white-space: nowrap;
}

/* 一级菜单 item */
.menu_scrollbar .menu_view .el-menu-item,.menu_scrollbar .menu_view .el-sub-menu,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title{
    height: 80px;
    line-height: 80px;
    border: 0px;
    color: #fff;
    font-size: 16px;
    padding: 0px 10px;
    cursor: pointer;
    white-space: nowrap;
    list-style: none;
    background: transparent;
    min-width: 100px;
    text-align: center;
}
.menu_scrollbar .menu_view .el-menu-item.first-item{
    min-width: 100px !important;
}

.menu_scrollbar .menu_view .el-menu-item:hover,.menu_scrollbar .menu_view .el-sub-menu:hover{
    background: rgba(255,255, 255, .9);
    color: #333 !important;
    clip-path: polygon(20% 0, 100% 0%, 80% 100%, 0% 100%);
}
.menu_scrollbar .menu_view .el-menu-item.is-active,.menu_scrollbar .menu_view .el-sub-menu.is-active{
    height: 80px;
    line-height: 80px;
    font-size: 16px;
    border-bottom:none;
    background: rgba(255,255, 255, .9);
    color: #333 !important;
    min-width: 100px;
    clip-path: polygon(20% 0, 100% 0%, 80% 100%, 0% 100%);
}
/* 有下拉的 */
.menu_scrollbar .menu_view .el-sub-menu{
    transition: all 0s;
}
.menu_scrollbar .menu_view .el-sub-menu:hover .el-sub-menu__title{
    color: #333 !important;
}
.menu_scrollbar .menu_view .el-sub-menu.is-active .el-sub-menu__title{
    color: #333 !important;
}

.menu_scrollbar .menu_view .el-sub-menu:hover .el-sub-menu__title{
    color: #333 !important;
}
.menu_scrollbar .menu_view .el-sub-menu.is-active .el-sub-menu__title{
    color: #333 !important;
}

/* 图标 */
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-icon-menu,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .iconfont{
    vertical-align: middle;
    margin: 0px 3px;
    width: 34px;
    text-align: center;
    font-size: 18px;
    color: inherit;
}
/* 标题 */
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title span{
    font-size: 16px;
    vertical-align: middle;
    color: inherit;
}
/* 箭头 */
.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-sub-menu__icon-arrow{
    position: static;
    margin: -3px 0px 0px 8px;
    font-size: 12px;
    vertical-align: middle;
    color: inherit;
}

/* 二级菜单 总盒子 */
.el-menu--horizontal .el-menu{
    border: none;
}
.el-menu--horizontal .el-menu .el-menu-item{
    color: rgb(102, 102, 102);
    height: 40px;
    line-height: 40px;
    padding: 0px 20px;
    background: rgb(255, 255, 255);
}
.el-menu--horizontal .el-menu .el-menu-item:hover{
    color: #fff !important;
    background: #fc6a20 !important;
}
.el-menu--horizontal .el-menu .el-menu-item.is-active{
    color: #fff !important;
    background: #fc6a20 !important;
}

/*总盒子*/
.rotation_view{
    width: 100%;
    margin: 10px auto 0;
}
/*banner盒子*/
.rotation_view .swiper-container{
    width: 100%;
    height: 100%;
    border:0px solid #fff;
}
.rotation_view .swiper .swiper-wrapper{
    transition-duration: 0ms;
    transform: translate3d(0px, 0px, 0px);
    height: 480px;
}
.rotation_view .swiper .swiper-wrapper .swiper-slide{
}
.rotation_view .swiper .swiper-wrapper .swiper-slide img{
    width: 100%;
    height: 100%;
    object-fit:cover;
}
/*左箭头*/
.rotation_view .swiper .swiper-button-prev{
    margin-left:calc((100% - 1180px)/2);
    color:#7282a4;
}
/*右箭头*/
.rotation_view .swiper .swiper-button-next{
    margin-right:calc((100% - 1180px)/2);
    color:#7282a4;
}

/*总盒子*/
.bread_view {
    width:100%;
    background: none;
    color: rgb(51, 51, 51);
    text-align:center;
    font-size:20px;
    font-weight:600;
    border:0px solid #eee;
    padding:10px 0px 10px 0px;
    background: #fff;
}
/*面包屑盒子*/
.bread_view .el-breadcrumb{
    font-size: 16px;
    line-height: 1;
}
/*分隔符*/
.bread_view .el-breadcrumb .el-breadcrumb__separator{
    margin: 0px 9px;
    font-weight: 500;
    color: rgb(204, 204, 204);
}
/*一级*/
.bread_view .el-breadcrumb .first_breadcrumb span a{
    color: #333;
    display: inline-block;
}
/*二级*/
.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner{
    color: #666;
    display: inline-block;
}

</style>