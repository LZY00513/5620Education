import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import jiaoshiList from '@/views/pages/jiaoshi/list'
import jiaoshiDetail from '@/views/pages/jiaoshi/formModel'
import jiaoshiAdd from '@/views/pages/jiaoshi/formAdd'
import zuoyexinxiList from '@/views/pages/zuoyexinxi/list'
import zuoyexinxiDetail from '@/views/pages/zuoyexinxi/formModel'
import zuoyexinxiAdd from '@/views/pages/zuoyexinxi/formAdd'
import tijiaozuoyeList from '@/views/pages/tijiaozuoye/list'
import tijiaozuoyeDetail from '@/views/pages/tijiaozuoye/formModel'
import tijiaozuoyeAdd from '@/views/pages/tijiaozuoye/formAdd'
import xueshengList from '@/views/pages/xuesheng/list'
import xueshengDetail from '@/views/pages/xuesheng/formModel'
import xueshengAdd from '@/views/pages/xuesheng/formAdd'
import xueshengRegister from '@/views/pages/xuesheng/register'
import xueshengCenter from '@/views/pages/xuesheng/center'
import newsList from '@/views/pages/news/list'
import messagesList from '@/views/pages/messages/list'
import forumList from '@/views/pages/forum/list'
import zuoyepigaiList from '@/views/pages/zuoyepigai/list'
import zuoyepigaiDetail from '@/views/pages/zuoyepigai/formModel'
import zuoyepigaiAdd from '@/views/pages/zuoyepigai/formAdd'
import ziyuanleixingList from '@/views/pages/ziyuanleixing/list'
import ziyuanleixingDetail from '@/views/pages/ziyuanleixing/formModel'
import ziyuanleixingAdd from '@/views/pages/ziyuanleixing/formAdd'
import jiaoxueziyuanList from '@/views/pages/jiaoxueziyuan/list'
import jiaoxueziyuanDetail from '@/views/pages/jiaoxueziyuan/formModel'
import jiaoxueziyuanAdd from '@/views/pages/jiaoxueziyuan/formAdd'
import storeupList from '@/views/pages/storeup/list'
import jiazhangList from '@/views/pages/jiazhang/list'
import jiazhangDetail from '@/views/pages/jiazhang/formModel'
import jiazhangAdd from '@/views/pages/jiazhang/formAdd'
import jiazhangRegister from '@/views/pages/jiazhang/register'
import jiazhangCenter from '@/views/pages/jiazhang/center'
import jiazhangyuyuejiaoshiList from '@/views/pages/jiazhangyuyuejiaoshi/list'
import jiazhangyuyuejiaoshiDetail from '@/views/pages/jiazhangyuyuejiaoshi/formModel'
import jiazhangyuyuejiaoshiAdd from '@/views/pages/jiazhangyuyuejiaoshi/formAdd'
import xueshengyuyuejiaoshiList from '@/views/pages/xueshengyuyuejiaoshi/list'
import xueshengyuyuejiaoshiDetail from '@/views/pages/xueshengyuyuejiaoshi/formModel'
import xueshengyuyuejiaoshiAdd from '@/views/pages/xueshengyuyuejiaoshi/formAdd'
import xueshengkongbaiyemianList from '@/views/pages/xueshengkongbaiyemian/list'
import xueshengkongbaiyemianDetail from '@/views/pages/xueshengkongbaiyemian/formModel'
import xueshengkongbaiyemianAdd from '@/views/pages/xueshengkongbaiyemian/formAdd'
import jiaoshikongbaiyemianList from '@/views/pages/jiaoshikongbaiyemian/list'
import jiaoshikongbaiyemianDetail from '@/views/pages/jiaoshikongbaiyemian/formModel'
import jiaoshikongbaiyemianAdd from '@/views/pages/jiaoshikongbaiyemian/formAdd'
import jiazhangkongbaiyemianList from '@/views/pages/jiazhangkongbaiyemian/list'
import jiazhangkongbaiyemianDetail from '@/views/pages/jiazhangkongbaiyemian/formModel'
import jiazhangkongbaiyemianAdd from '@/views/pages/jiazhangkongbaiyemian/formAdd'
import ChatAssistant from '@/views/pages/ChatAssistant.vue';

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'jiaoshiList',
			component: jiaoshiList
		},	{
				path: 'showChat',

				component: ChatAssistant
			}, {
			path: 'jiaoshiDetail',
			component: jiaoshiDetail
		}, {
			path: 'jiaoshiAdd',
			component: jiaoshiAdd
		}
		, {
			path: 'zuoyexinxiList',
			component: zuoyexinxiList
		}, {
			path: 'zuoyexinxiDetail',
			component: zuoyexinxiDetail
		}, {
			path: 'zuoyexinxiAdd',
			component: zuoyexinxiAdd
		}
		, {
			path: 'tijiaozuoyeList',
			component: tijiaozuoyeList
		}, {
			path: 'tijiaozuoyeDetail',
			component: tijiaozuoyeDetail
		}, {
			path: 'tijiaozuoyeAdd',
			component: tijiaozuoyeAdd
		}
		, {
			path: 'xueshengList',
			component: xueshengList
		}, {
			path: 'xueshengDetail',
			component: xueshengDetail
		}, {
			path: 'xueshengAdd',
			component: xueshengAdd
		}
		, {
			path: 'xueshengCenter',
			component: xueshengCenter
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'messagesList',
			component: messagesList
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'zuoyepigaiList',
			component: zuoyepigaiList
		}, {
			path: 'zuoyepigaiDetail',
			component: zuoyepigaiDetail
		}, {
			path: 'zuoyepigaiAdd',
			component: zuoyepigaiAdd
		}
		, {
			path: 'ziyuanleixingList',
			component: ziyuanleixingList
		}, {
			path: 'ziyuanleixingDetail',
			component: ziyuanleixingDetail
		}, {
			path: 'ziyuanleixingAdd',
			component: ziyuanleixingAdd
		}
		, {
			path: 'jiaoxueziyuanList',
			component: jiaoxueziyuanList
		}, {
			path: 'jiaoxueziyuanDetail',
			component: jiaoxueziyuanDetail
		}, {
			path: 'jiaoxueziyuanAdd',
			component: jiaoxueziyuanAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'jiazhangList',
			component: jiazhangList
		}, {
			path: 'jiazhangDetail',
			component: jiazhangDetail
		}, {
			path: 'jiazhangAdd',
			component: jiazhangAdd
		}
		, {
			path: 'jiazhangCenter',
			component: jiazhangCenter
		}
		, {
			path: 'jiazhangyuyuejiaoshiList',
			component: jiazhangyuyuejiaoshiList
		}, {
			path: 'jiazhangyuyuejiaoshiDetail',
			component: jiazhangyuyuejiaoshiDetail
		}, {
			path: 'jiazhangyuyuejiaoshiAdd',
			component: jiazhangyuyuejiaoshiAdd
		}
		, {
			path: 'xueshengyuyuejiaoshiList',
			component: xueshengyuyuejiaoshiList
		}, {
			path: 'xueshengyuyuejiaoshiDetail',
			component: xueshengyuyuejiaoshiDetail
		}, {
			path: 'xueshengyuyuejiaoshiAdd',
			component: xueshengyuyuejiaoshiAdd
		}
		, {
			path: 'xueshengkongbaiyemianList',
			component: xueshengkongbaiyemianList
		}, {
			path: 'xueshengkongbaiyemianDetail',
			component: xueshengkongbaiyemianDetail
		}, {
			path: 'xueshengkongbaiyemianAdd',
			component: xueshengkongbaiyemianAdd
		}
		, {
			path: 'jiaoshikongbaiyemianList',
			component: jiaoshikongbaiyemianList
		}, {
			path: 'jiaoshikongbaiyemianDetail',
			component: jiaoshikongbaiyemianDetail
		}, {
			path: 'jiaoshikongbaiyemianAdd',
			component: jiaoshikongbaiyemianAdd
		}
		, {
			path: 'jiazhangkongbaiyemianList',
			component: jiazhangkongbaiyemianList
		}, {
			path: 'jiazhangkongbaiyemianDetail',
			component: jiazhangkongbaiyemianDetail
		}, {
			path: 'jiazhangkongbaiyemianAdd',
			component: jiazhangkongbaiyemianAdd
		},{
				path: '/chat',
				name: 'ChatAssistant',
				component: ChatAssistant,
			},


		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/xueshengRegister',
		component: xueshengRegister
	}
	,{
		path: '/jiazhangRegister',
		component: jiazhangRegister
	},

]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
