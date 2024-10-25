const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '作业信息管理',
					icon: '',
					child:[

						{
							name:'作业信息',
							url:'/index/zuoyexinxiList'
						},
					]
				},
				{
					name: '新闻资讯管理',
					icon: '',
					child:[

						{
							name:'公告信息',
							url:'/index/newsList'
						},
					]
				},
				{
					name: '论坛交流',
					icon: 'icon-common4',
					child:[

						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
				{
					name: '留言板管理',
					icon: '',
					child:[

						{
							name:'留言板',
							url:'/index/messagesList'
						},
					]
				},
				{
					name: '教学资源管理',
					icon: '',
					child:[

						{
							name:'教学资源',
							url:'/index/jiaoxueziyuanList'
						},
					]
				},
				{
					name: '教师管理',
					icon: '',
					child:[

						{
							name:'教师',
							url:'/index/jiaoshiList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "在线学习教育平台"
        } 
    }
}
export default config
