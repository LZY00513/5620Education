<template>
  <div>
    <div class="home_box">
      <!-- Teacher Home Display -->
      <div class="homeList_view">
        <div class="homeList_title">
          <span>Teacher </span>
        </div>
        <div class="homeList">
          <div class="item" v-for="(item,index) in jiaoshiHomeList" :key="index" @click="detailClick('jiaoshi',item.id)">
            <div class="img-box">
              <img v-if="isHttp(item.touxiang)" :src="item.touxiang.split(',')[0]" alt="">
              <img v-else :src="item.touxiang?$config.url + item.touxiang.split(',')[0]:''" alt="">
            </div>
            <div class="content-box">
              <div class="title">{{item.jiaoshixingming}}</div>
              <div class="title">Major: {{item.zhuanye}}</div>
              <div class="statistic">
                <div class="collect">
                  <span class="iconfont icon-likeline4"></span>
                  <div class="num">{{item.storeupNumber}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="homeList_more_view" @click="moreClick('jiaoshi')">
          <span class="homeList_more_text">View More +</span>
        </div>
      </div>

      <!-- Assignment Information Home Display -->
      <div class="homeList_view">
        <div class="homeList_title">
          <span>Assignment</span>
        </div>
        <div class="homeList">
          <div class="item" v-for="(item,index) in zuoyexinxiHomeList" :key="index" @click="detailClick('zuoyexinxi',item.id)">
            <div class="img-box">
              <img v-if="isHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="">
              <img v-else :src="item.fengmian?$config.url + item.fengmian.split(',')[0]:''" alt="">
            </div>
            <div class="content-box">
              <div class="title">{{item.zuoyemingcheng}}</div>
              <div class="title">Release Time: {{item.fabushijian}}</div>
              <div class="statistic">
                <div class="collect">
                  <span class="iconfont icon-likeline4"></span>
                  <div class="num">{{item.storeupNumber}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="homeList_more_view" @click="moreClick('zuoyexinxi')">
          <span class="homeList_more_text">View More +</span>
        </div>
      </div>

      <!-- Teaching Resources Home Display -->
      <div class="homeList_view">
        <div class="homeList_title">
          <span>Resources</span>
        </div>
        <div class="categoryList">
          <div class="item" @click="jiaoxueziyuanCategoryChange(-1)" style="cursor: pointer" :class="{active:jiaoxueziyuan_index==-1}">All</div>
          <div class="item" v-for="(item,index) in jiaoxueziyuanCategorys" @click="jiaoxueziyuanCategoryChange(index)" :class="{active:jiaoxueziyuan_index==index}" style="cursor: pointer">
            {{item}}
          </div>
        </div>
        <div class="homeList">
          <div class="item" v-for="(item,index) in jiaoxueziyuanHomeList" :key="index" @click="detailClick('jiaoxueziyuan',item.id)">
            <div class="img-box">
              <img v-if="isHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="">
              <img v-else :src="item.fengmian?$config.url + item.fengmian.split(',')[0]:''" alt="">
            </div>
            <div class="content-box">
              <div class="title">{{item.ziyuanmingcheng}}</div>
              <div class="statistic">
                <div class="collect">
                  <span class="iconfont icon-likeline4"></span>
                  <div class="num">{{item.storeupNumber}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="homeList_more_view" @click="moreClick('jiaoxueziyuan')">
          <span class="homeList_more_text">View More +</span>
        </div>
      </div>

      <!-- Announcement Information -->
      <div class="newsList_view">
        <div class="ntitle">
          <div class="n1">Announcement</div>
          <div class="n2">NEWS INFORMATION</div>
        </div>
        <div class="nlist">
          <ul>
            <li v-for="(item,index) in newsList" :key="index" @click="newsDetailClick(item)">
              <div class="imgbox">
                <img :src="item.imgUrl">
              </div>
              <div class="infobox">
                <div class="nam">{{item.title}}</div>
                <div class="info">{{item.introduction}}</div>
              </div>
              <div class="tim">
                <div class="t1">{{moment(item.addtime).format('DD')}}</div>
                <div class="t2">{{moment(item.addtime).format('YYYY-MM-DD')}}</div>
              </div>
            </li>
          </ul>
        </div>
        <div class="nmore" @click="moreClick('news')" style="cursor: pointer">View More +</div>
      </div>
    </div>

    <formModel ref="newsFormModelRef"></formModel>
    <!-- Add AI Assistant Component -->
    <ChatAssistant />
  </div>
</template>


<script setup>
import ChatAssistant from '@/views/pages/ChatAssistant.vue';

	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import moment from 'moment'
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//教师首页展示
	const jiaoshiHomeList = ref([])
	const getjiaoshiHomeList = () => {
		let params = {
			page: 1,
			limit: 6
		}
		context?.$http({
			url: 'jiaoshi/list',
			method: 'get',
			params: params
		}).then(res => {
			jiaoshiHomeList.value = res.data.data.list
			jiaoshiHomeList.value.forEach(item=>{
				if(!isHttp(item.touxiang)){
					item.imgUrls = item.touxiang.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//作业信息首页展示
	const zuoyexinxiHomeList = ref([])
	const getzuoyexinxiHomeList = () => {
		let params = {
			page: 1,
			limit: 6
		}
		context?.$http({
			url: 'zuoyexinxi/list',
			method: 'get',
			params: params
		}).then(res => {
			zuoyexinxiHomeList.value = res.data.data.list
			zuoyexinxiHomeList.value.forEach(item=>{
				if(!isHttp(item.fengmian)){
					item.imgUrls = item.fengmian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//教学资源首页展示
	const jiaoxueziyuanCategorys = ref([])
	const jiaoxueziyuan_index = ref(-1)
	const getjiaoxueziyuanCategorys = () => {
		context?.$http({
			url: 'option/ziyuanleixing/ziyuanleixing',
			method: 'get'
		}).then(res => {
			jiaoxueziyuanCategorys.value = res.data.data
		})
	}
	getjiaoxueziyuanCategorys()
	const jiaoxueziyuanCategoryChange = (index)=>{
		jiaoxueziyuan_index.value = index
		getjiaoxueziyuanHomeList()
	}
	const jiaoxueziyuanHomeList = ref([])
	const getjiaoxueziyuanHomeList = () => {
		let params = {
			page: 1,
			limit: 6
		}
		if(jiaoxueziyuan_index.value>=0){
            params.ziyuanleixing = jiaoxueziyuanCategorys.value[jiaoxueziyuan_index.value]
		}
		context?.$http({
			url: 'jiaoxueziyuan/list',
			method: 'get',
			params: params
		}).then(res => {
			jiaoxueziyuanHomeList.value = res.data.data.list
			jiaoxueziyuanHomeList.value.forEach(item=>{
				if(!isHttp(item.fengmian)){
					item.imgUrls = item.fengmian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//公告信息弹窗
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	//公告信息
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 4
			}
		}).then(res=>{
			newsList.value = res.data.data.list
			newsList.value.forEach(item=>{
				if(!isHttp(item.picture)){
					item.imgUrl=context.$config.url+item.picture.split(',')[0]
				}
			})
		})
	}
	const newsDetailClick = (item) => {
		if (item && item.id){
			newsFormModelRef.value.init(item.id)
		}
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//教师首页展示
		getjiaoshiHomeList()
		//作业信息首页展示
		getzuoyexinxiHomeList()
		//教学资源首页展示
		getjiaoxueziyuanHomeList()
		//公告信息
		getNewsList()
	}

  init()
</script>


<style lang="scss">
	.home_box {
	}

	// 推荐
	.recomList_view {
		.recomList_title {
		}
		// list
		.recommend_list_one {
			.recommend_item {
			}
			.recommend_img_box {
				.recommend_img {
				}
			}
			.recommend_content {
				.recommend_title {
				}
				.recommend_price {
				}
				.recommend_bottom {
					.recommend_like {
						.like_icon {
						}
						.num {
						}
					}
					.recommend_collect {
						.el-icon {
						}
						.num {
						}
					}
					.recommend_clickNum {
						color: #999;
						display: flex;
						font-size: 16px;
						align-items: center;
						.el-icon {
							margin: 0 4px 0 0;
							color: inherit;
						}
						.num {
							color: inherit;
						}
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.recommend_more_view {
			.recommend_more_text {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {

		.newsList_title {
		}
		// list
		.news_list_one {
			display: flex;
			flex-wrap: wrap;
			.news_item {
				box-shadow: 0 4px 6px rgba(0,0,0,.3);
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(33% - 20px);
				align-items: center;
				.news_img_box {
					width: 40%;
					font-size: 0;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 200px;
					}
				}
				.news_content {
					margin: 0 0 0 20px;
					width: calc(60% - 20px);
					.news_title {
						font-weight: bold;
						font-size: 20px;
					}
					.news_text {
						font-size: 14px;
						line-height: 1.5;
					}
					.news_time {
						color: #999;
						width: 100%;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.news_more_view {
			.news_more_text {
			}
			.el-icon {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {

		.homeList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.homeList_more_view {
			cursor: pointer;
			.homeList_more_text {
			}
		}
	}
	// 首页展示
</style>
<style>
.home_box{
    width: 100%;
    margin: 0px auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;

}
.home_box .appendBox1{
    width: 100%;
    height:299px;
    background:url(http://clfile.zggen.cn/20240927/708c7343aeeb4d728265133426b664a4.jpg) no-repeat center top / 100% 100%;
    order:5;
}
/* 总盒子 */
.homeList_view{
    width: 100%;
    margin:0;
    padding: 20px calc((100% - 1200px)/2) 50px;
    background: #fff ;
    overflow: hidden;
    position:relative;
    order: 8;
    display:flex;
    flex-wrap:wrap;
    justify-content:space-between;
}
/* 标题 */
.homeList_view .homeList_title{
    width: 101%;
    margin:30px auto;
    background:url() no-repeat center bottom;
    text-align: center;
    padding: 0 0 20px;
    font-size: 42px;
    color:#7282a4;
    font-weight:600;
}
/* 分类 */
.homeList_view .categoryList{
    width: 240px;
    display: block;
    flex-wrap: wrap;
    margin-left:50px;
    order:4;
}
.homeList_view .categoryList .item{
    background: #7282a4;
    line-height:50px;
    color:#fff;
    text-align: center;
    margin:0 0 8px;
    cursor:pointer;
    font-size: 16px;
}
.homeList_view .categoryList .item:hover{
    background:#000;
    color:#fff;
}

/* 样式七 总盒子 */
.homeList_view .homeList{
    width: calc(100% - 320px);
    margin: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content:space-between;
    order:2;
    flex:1;
}
.homeList_view .homeList .item{
    width: calc(32% - 0px);
    margin: 0px 0px 20px;
    background: none;
    cursor: pointer;
    overflow:hidden;
    position:relative;
}
.homeList_view .homeList .item .img-box{
    width: 100%;
    height: 250px;
    overflow: hidden;
    margin: 0px;
    text-align:center;
}
.homeList_view .homeList .item .img-box img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.homeList_view .homeList .item .content-box{
    width: 100%;
    position:absolute; left:0px; bottom:-100%; background:rgba(255, 255, 255, .8);  padding:10px 40px; transition:all 0.5s; display:flex; flex-wrap:wrap; align-items:center;  align-content: center;
}
.homeList_view .homeList .item:hover .content-box{ bottom:0px; background:rgba(0, 0, 0, .5); height:250px; }

.homeList_view .homeList .item .content-box .title{
    width: 100%;
    line-height: 24px;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
.homeList_view .homeList .item:hover .content-box .title{
    color: #eee;
}
.homeList_view .homeList .item .content-box .price{
    width: 100%;
    color: #f00;
    font-size:16px;
}
.homeList_view .homeList .item:hover .content-box .price{
}
.homeList_view .homeList .item .content-box .statistic{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    margin:10px 0 0;
}
.homeList_view .homeList .item:hover .content-box .statistic{
    color: #ccc;
}
.homeList_view .homeList .item .content-box .statistic .iconfont{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .num{
    color: inherit;
}
.homeList_view .homeList .item .content-box .statistic .like{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .collect{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .clickNum{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList_more_view{
    width: 101%;
    color: #7282a4;
    border:1px solid #7282a450;
    padding:10px;
    clear:both;
    text-align:center;
    order:22;
}
.ai-chat {
  margin-top: 20px; /* 增加组件顶部的间距，使其与上方内容隔开 */
}

/* 总盒子 */
.newsList_view {
    width: 100%;
    margin:0;
    padding: 60px 0;
    background: url(http://clfile.zggen.cn/20241016/15abf18c1acf46b6b9672797b06597d5.gif);
    overflow: hidden;
    position:relative;
    order: 12;
    display:block;
}
/* 自定义 start*/
.newsList_view .ntitle{
    width: 1200px;
    margin:0 auto;
    background:url() no-repeat center bottom;
    text-align: center;
    padding: 0 0 20px;
}
.newsList_view .ntitle .n1{
    display:block;
    font-size: 42px;
    color:#7282a4;
    font-weight:600;
}
.newsList_view .ntitle .n2{
    display:block;
    font-size: 18px;
    line-height:30px;
    color:#ccc;
    margin-left:5px;
}
.newsList_view .nlist{
    width: 1200px;
    margin:30px auto 0;
}
.newsList_view .nlist ul{
    margin:0;
    padding:0;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
}
.newsList_view .nlist ul li{
    width: 48%;
    text-align: left;
    background: none;
    border: 0px dashed #aaa;
    cursor:pointer;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    margin:0 0 30px;
}
.newsList_view .nlist ul li:last-child{
    border-bottom: none;
}
.newsList_view .nlist ul li .imgbox{
    width: 100px;
    height: 100px;
}
.newsList_view .nlist ul li .imgbox img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.newsList_view .nlist ul li .tim {
    display: flex;
    flex-wrap: wrap;
    width: 80px;
}
.newsList_view .nlist ul li .tim .t1 {
    width: 100%;
    text-align: center;
    font-size: 48px;
    line-height: 1;
    border-bottom:1px solid #ddd;
    color: #ddd;
}
.newsList_view .nlist ul li .tim .t2 {
    width: 100%;
    text-align: center;
    line-height: 30px;
    color: #ddd;
}
.newsList_view .nlist ul li .infobox {
    width: calc(100% - 110px);
    flex: 1;
    padding:0 20px 20px;
}
.newsList_view .nlist ul li .infobox .nam {
    color: #ccc;
    font-size: 16px;
    font-weight: 600;
}
.newsList_view .nlist ul li .infobox .info {
    color: #888;
    font-size: 15px;
    line-height: 24px;
    height:48px;
    overflow:hidden;
    margin-top: 5px;
}
/* 更多 */
.newsList_view .nmore{
    width: 1200px;
    margin: 10px auto;
    display: block;
    color: #ccc;
    border:1px solid #ffffff50;
    padding:10px;
    clear:both;
    text-align:center;
}
/* 自定义 end*/

</style>