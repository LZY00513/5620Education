const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/cl26103485/client/index.html'
        }
    },
    getProjectName(){
        return {
            projectName: "在线学习教育平台"
        } 
    }
}
export default config
