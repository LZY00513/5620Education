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
							name: 'Assignment Information',
							url: '/index/zuoyexinxiList'
						},
					]
				},
				{
					name: 'News Information Management',
					icon: '',
					child: [
						{
							name: 'Announcement Information',
							url: '/index/newsList'
						},
					]
				},
				{
					name: 'Forum Communication',
					icon: 'icon-common4',
					child: [
						{
							name: 'Forum Communication',
							url: '/index/forumList'
						},
					]
				},
				{
					name: 'Message Board Management',
					icon: '',
					child: [
						{
							name: 'Message Board',
							url: '/index/messagesList'
						},
					]
				},
				{
					name: 'Educational Resources Management',
					icon: '',
					child: [
						{
							name: 'Educational Resources',
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
