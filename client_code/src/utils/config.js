const config = {
	get() {
		return {
			url: process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
			name: process.env.VUE_APP_BASE_API,
			menuList: [
				{
					name: 'Assignment Information Management',
					icon: '',
					child: [
						{
							name: 'Assignment',
							url: '/index/zuoyexinxiList'
						},
					]
				},
				{
					name: 'News Information Management',
					icon: '',
					child: [
						{
							name: 'Announcement',
							url: '/index/newsList'
						},
					]
				},
				{
					name: 'Forum Communication',
					icon: 'icon-common4',
					child: [
						{
							name: 'Forum',
							url: '/index/forumList'
						},
					]
				},
				{
					name: 'Message Board Management',
					icon: '',
					child: [
						{
							name: 'Message',
							url: '/index/messagesList'
						},
					]
				},
				{
					name: 'Educational Resources Management',
					icon: '',
					child: [
						{
							name: 'Educational ',
							url: '/index/jiaoxueziyuanList'
						},
					]
				},
				{
					name: 'Teacher Management',
					icon: '',
					child: [
						{
							name: 'Teacher',
							url: '/index/jiaoshiList'
						},
					]
				},
				{
					name: 'Chat',
					icon: '',
					child: [
						{
							name: 'Chat',
							url: '/index/showChat',
							 // 使用自定义事件或方法代替 `url`
						}
					]
				}
			]
		}
	},
	getProjectName() {
		return {
			projectName: "Intelligent Education Platform"
		}
	}
}
export default config
