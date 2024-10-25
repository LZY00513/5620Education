	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import jiazhangyuyuejiaoshi from '@/views/jiazhangyuyuejiaoshi/list'
	import ziyuanleixing from '@/views/ziyuanleixing/list'
	import jiaoshikongbaiyemian from '@/views/jiaoshikongbaiyemian/list'
	import xuesheng from '@/views/xuesheng/list'
	import zuoyepigai from '@/views/zuoyepigai/list'
	import xueshengkongbaiyemian from '@/views/xueshengkongbaiyemian/list'
	import jiaoshi from '@/views/jiaoshi/list'
    import menu_manage from '@/views/menu_manage/list'
	import jiaoxueziyuan from '@/views/jiaoxueziyuan/list'
	import storeup from '@/views/storeup/list'
	import users from '@/views/users/list'
	import zuoyexinxi from '@/views/zuoyexinxi/list'
	import forum from '@/views/forum/list'
	import jiazhang from '@/views/jiazhang/list'
	import xueshengyuyuejiaoshi from '@/views/xueshengyuyuejiaoshi/list'
	import tijiaozuoye from '@/views/tijiaozuoye/list'
	import messages from '@/views/messages/list'
	import jiazhangkongbaiyemian from '@/views/jiazhangkongbaiyemian/list'
	import config from '@/views/config/list'
	import usersCenter from '@/views/users/center'
	import jiaoshiRegister from '@/views/jiaoshi/register'
	import jiaoshiCenter from '@/views/jiaoshi/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/jiaoshiCenter',
			name: '教师个人中心',
			component: jiaoshiCenter
		}
		,{
			path: '/news',
			name: '公告信息',
			component: news
		}
		,{
			path: '/jiazhangyuyuejiaoshi',
			name: '家长预约教师',
			component: jiazhangyuyuejiaoshi
		}
		,{
			path: '/ziyuanleixing',
			name: '资源类型',
			component: ziyuanleixing
		}
		,{
			path: '/jiaoshikongbaiyemian',
			name: '教师空白页面',
			component: jiaoshikongbaiyemian
		}
		,{
			path: '/xuesheng',
			name: '学生',
			component: xuesheng
		}
		,{
			path: '/zuoyepigai',
			name: '作业批改',
			component: zuoyepigai
		}
		,{
			path: '/xueshengkongbaiyemian',
			name: '学生空白页面',
			component: xueshengkongbaiyemian
		}
		,{
			path: '/jiaoshi',
			name: '教师',
			component: jiaoshi
		}
        ,{
            path: '/menu',
            name: '菜单权限管理',
            component: menu_manage
        }
		,{
			path: '/jiaoxueziyuan',
			name: '教学资源',
			component: jiaoxueziyuan
		}
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/zuoyexinxi',
			name: '作业信息',
			component: zuoyexinxi
		}
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/jiazhang',
			name: '家长',
			component: jiazhang
		}
		,{
			path: '/xueshengyuyuejiaoshi',
			name: '学生预约教师',
			component: xueshengyuyuejiaoshi
		}
		,{
			path: '/tijiaozuoye',
			name: '提交作业',
			component: tijiaozuoye
		}
		,{
			path: '/messages',
			name: '留言板',
			component: messages
		}
		,{
			path: '/jiazhangkongbaiyemian',
			name: '家长空白页面',
			component: jiazhangkongbaiyemian
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		]
	},
	{
		path: '/jiaoshiRegister',
		name: '教师注册',
		component: jiaoshiRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
