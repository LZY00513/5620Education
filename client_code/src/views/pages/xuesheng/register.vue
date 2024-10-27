<template>
  <div>
    <div class="register_view">
      <el-form :model="registerForm" class="register_form">
        <div class="title_view">{{projectName}} Registration</div>
        <div class="list_item">
          <div class="list_label">Student ID:</div>
          <input class="list_inp"
                 v-model="registerForm.xuehao"
                 placeholder="Please enter Student ID"
                 type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">Password:</div>
          <input class="list_inp"
                 v-model="registerForm.mima"
                 placeholder="Please enter password"
                 type="password"
          />
        </div>
        <div class="list_item">
          <div class="list_label">Confirm Password:</div>
          <input class="list_inp" v-model="registerForm.mima2" type="password" placeholder="Please enter confirm password" />
        </div>
        <div class="list_item">
          <div class="list_label">Student Name:</div>
          <input class="list_inp"
                 v-model="registerForm.xueshengxingming"
                 placeholder="Please enter student name"
                 type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">Avatar:</div>
          <div class="list_file_list">
            <uploads
                action="file/upload"
                tip="Please upload an avatar"
                :limit="3"
                style="width: 100%;text-align: left;"
                :fileUrls="registerForm.touxiang?registerForm.touxiang:''"
                @change="touxiangUploadSuccess">
            </uploads>
          </div>
        </div>
        <div class="list_item">
          <div class="list_label">Gender:</div>
          <el-select
              class="list_sel"
              v-model="registerForm.xingbie"
              placeholder="Please select gender"
          >
            <el-option v-for="item in xueshengxingbieLists" :label="item" :value="item"></el-option>
          </el-select>
        </div>
        <div class="list_item">
          <div class="list_label">Mobile Number:</div>
          <input class="list_inp"
                 v-model="registerForm.shoujihaoma"
                 placeholder="Please enter mobile number"
                 type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">Parent Account:</div>
          <el-select
              class="list_sel"
              v-model="registerForm.jiazhangzhanghao"
              placeholder="Please select parent account"
          >
            <el-option v-for="item in xueshengjiazhangzhanghaoLists" :label="item" :value="item"></el-option>
          </el-select>
        </div>
        <div class="list_btn">
          <el-button class="register" type="success" @click="handleRegister">Register</el-button>
          <div class="r-login" @click="close">Already have an account? Log in directly</div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import {
  ref,
  getCurrentInstance,
  nextTick,
  onMounted,
} from 'vue';
const context = getCurrentInstance()?.appContext.config.globalProperties;
const projectName = context?.$project.projectName
// Get registration type
import { useRoute } from 'vue-router';
const route = useRoute()
const tableName = ref('xuesheng')

// Common method
const getUUID=()=> {
  return new Date().getTime();
}

const registerForm = ref({
  xingbie: '',
  jiazhangzhanghao: '',
})
const xueshengxingbieLists = ref([])
const xueshengjiazhangzhanghaoLists = ref([])
const init=()=>{
  xueshengxingbieLists.value = "Male,Female".split(',')
  context?.$http({
    url:`option/jiazhang/jiazhangzhanghao`,
    method:'get'
  }).then(res=>{
    xueshengjiazhangzhanghaoLists.value = res.data.data
  })
}
const touxiangUploadSuccess=(fileUrls)=> {
  registerForm.value.touxiang = fileUrls;
}
// Multi-level linkage parameters
// Register button
const handleRegister = () => {
  let url = tableName.value +"/register";
  if((!registerForm.value.xuehao)){
    context?.$toolUtil.message(`Student ID cannot be empty`,'error')
    return false
  }
  if((!registerForm.value.mima)){
    context?.$toolUtil.message(`Password cannot be empty`,'error')
    return false
  }
  if(registerForm.value.mima!=registerForm.value.mima2){
    context?.$toolUtil.message('The two password entries do not match','error')
    return false
  }
  if((!registerForm.value.xueshengxingming)){
    context?.$toolUtil.message(`Student name cannot be empty`,'error')
    return false
  }
  if(registerForm.value.touxiang!=null){
    registerForm.value.touxiang = registerForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
  }
  if(registerForm.value.shoujihaoma&&(!context?.$toolUtil.isMobile(registerForm.value.shoujihaoma))){
    context?.$toolUtil.message(`Mobile number should be in mobile format`,'error')
    return false
  }
  context?.$http({
    url:url,
    method:'post',
    data:registerForm.value
  }).then(res=>{
    context?.$toolUtil.message('Registration successful','success', obj=>{
      context?.$router.push({
        path: "/login"
      });
    })
  })
}
// Return to login
const close = () => {
  context?.$router.push({
    path: "/login"
  });
}
init()
onMounted(()=>{

})
</script>

<style lang="scss" scoped>
	.register_view {
        background-image: url("http://clfile.zggen.cn/20241024/479ba3ebda544acbba7fcbdd6353644a.png");
		// 表单盒子
		.register_form {
		}
		// 标题样式
		.title_view {
			padding: 0 0 30px;
			color: #333;
			width: 100%;
			font-size: 22px;
			text-align: center;
		}
		// item盒子
		.list_item {
			// label
			.list_label {
			}
			// 输入框
			:deep(.list_inp) {

			}
		}
		//下拉框样式
		:deep(.list_sel) {
			//去掉默认样式
			.select-trigger{
				height: 100%;
				.el-input{
					height: 100%;
				}
			}
		}
		//图片上传样式
		.list_file_list  {
			//提示语
			:deep(.el-upload__tip){
			}
			//外部盒子
			:deep(.el-upload--picture-card){
				//图标
				.el-icon{
				}
			}
			:deep(.el-upload-list__item) {
			}
		}
		//按钮盒子
		.list_btn {
			//注册按钮
			.register {
			}
			//注册按钮悬浮样式
			.register:hover {
			}
			//已有账号
			.r-login {
			}
		}
	}
</style>
<style>

.register_view {
    min-height: 100vh;
    position: relative;
    background: url(http://clfile.zggen.cn/20241024/479ba3ebda544acbba7fcbdd6353644a.png) no-repeat center center / 100% 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.register_view .register_form{
    width: 600px;
    box-shadow: rgb(187, 187, 187) 0px 4px 9px;
    padding: 30px 60px 30px 40px;
    margin:0 40% 0 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    border-radius: 10px;
    background: rgba(255, 255, 255,1);
    box-shadow: none;
    font-size: 24px;
}


.register_view .outTitle_view{
    display: flex;
    align-items: center;
    padding: 0px 0px 60px;
    position:absolute;
    left:40px;
    top:20px;
}
.register_view .outTitle_view .outTilte{
    color: #7282a4;
    font-size: 24px;
    font-weight: 600;
}


.register_view .register_form .list_item{
    display: flex;
    align-items: center;
    width: 100%;
    justify-content: center;
    margin: 0px 10px 20px 0px;
}
.register_view .register_form .list_item .list_label{
    width: 120px;
    text-align: right;
    font-size: 16px;
}
.register_view .register_form .list_item .list_inp{
    height: 40px;
    line-height: 40px;
    border:none;
    border: 1px solid #ddd;
    padding: 0px 10px;
    width: calc(100% - 120px);
    color:#999;
    font-size: 16px;
    background:#fff;
}
.register_view .register_form .list_item .list_date{
    width: calc(100% - 120px)!important;
    height: 44px;
    line-height: 44px;
    border:none;
    border: 1px solid #ddd;
    box-sizing: border-box;
    border-radius: 0px;
    color:#999;
    font-size: 16px;
    background:#fff;
}
.register_view .register_form .list_item .list_sel{
    line-height: 36px;
    border:none;
    border: 1px solid #ddd;
    box-sizing: border-box;
    width: calc(100% - 120px);
    padding: 0px 10px;
    border-radius: 0px;
    color:#999;
    font-size: 16px;
    background:#fff;
}


.register_view .register_form .list_item .list_file_list{
    width: calc(100% - 120px);
}

.register_view .register_form .list_item .el-upload--picture-card{
    background-color: rgb(255, 255, 255);
    width: 90px;
    height: 60px;
    line-height: 60px;
    text-align: center;
    border: 1px solid #ddd;
    border-radius: 0px;
    cursor: pointer;
}

.register_view .register_form .list_item .el-upload--picture-card .el-icon{
    font-size: 22px;
    color: rgb(153, 153, 153);
}

.register_view .register_form .list_item .el-upload__tip{
    font-size: 16px;
    color: rgb(153, 153, 153);
    margin: 7px 0px 0px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}


.register_view .register_form .list_item .el-upload-dragger{
    background-color: rgb(255, 255, 255);
    border: 1px solid #ddd;
    border-radius: 0px;
    box-sizing: border-box;
    width: 100%;
    height: auto;
    text-align: center;
    cursor: pointer;
    overflow: hidden;
    padding: 10px;
}

.register_view .register_form .list_item .el-upload-dragger .el-icon--upload{
    font-size: 60px;
    color: #7282a4;
    line-height: 50px;
    margin: 0px;
}

.register_view .register_form .list_item .el-upload-dragger .el-upload__text{
    font-size: 16px;
    color: rgb(153, 153, 153);
    margin: 5px 0px 0px;
}

.register_view .register_form .list_item .el-upload-dragger .el-upload__text em{
    color: #7282a4;
}


.register_view .register_form .list_item .list_radio{
    display: flex;
    width: calc(100% - 120px);
    align-items: center;
    flex-wrap: wrap;
}

.register_view .register_form .list_item .list_radio .el-radio{
    width: auto;
    margin: 0px 20px 0px 0px;
    display: flex;
    align-items: center;
}

.register_view .register_form .list_item .list_radio .el-radio .el-radio__inner{
    border-color: rgb(153, 153, 153);
    background: rgb(255, 255, 255);
}

.register_view .register_form .list_item .list_radio .el-radio.is-checked .el-radio__inner{
    border-color: #7282a4;
    background: #7282a4;
}

.register_view .register_form .list_item .list_radio .el-radio .el-radio__label{
    color: #999;
    font-size: 16px;
}

.register_view .register_form .list_item .list_radio .el-radio.is-checked .el-radio__label{
    color: #7282a4;
}


.register_view .register_form .list_item .list_checkbox{
    display: flex;
    width: calc(100% - 120px);
    flex-wrap: wrap;
    align-items: center;
}

.register_view .register_form .list_item .list_checkbox .el-checkbox{
    width: auto;
    margin: 0px 15px 0px 0px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.register_view .register_form .list_item .list_checkbox .el-checkbox .el-checkbox__inner{
    border-color: #999;
    background: rgb(255, 255, 255);
}

.register_view .register_form .list_item .list_checkbox .el-checkbox.is-checked .el-checkbox__inner{
    border-color: #7282a4;
    background: #7282a4;
}

.register_view .register_form .list_item .list_checkbox .el-checkbox .el-checkbox__label{
    color: #999;
    font-size: 16px;
}

.register_view .register_form .list_item .list_checkbox .el-checkbox.is-checked .el-checkbox__label{
    color: #999;
}


.register_view .register_form .list_code{
    display: flex;
    align-items: center;
    width: 100%;
    justify-content: center;
    margin: 0px 10px 10px 0px;
}
.register_view .register_form .list_code .list_code_label{
    width: 120px;
    text-align: right;
    font-size: 16px;
}
.register_view .register_form .list_code .list_code_item{
    width: calc(100% - 120px);
    display: flex;
    align-items: center;
}
.register_view .register_form .list_code .list_code_item .list_code_inp{
    height: 36px;
    line-height: 36px;
    border:none;
    border: 1px solid #ddd;
    padding: 0px 10px;
    width: calc(100% - 110px);
    font-size: 16px;
}
.register_view .register_form .list_code .list_code_btn{
    padding:0px;
    width: 100px;
    height: 36px;
    line-height: 36px;
    text-align: center;
    border-radius: 0;
    border: none;
    background: #7282a4;
    color: rgb(255, 255, 255);
    font-size: 16px;
    margin-left:10px;
}


.register_view .register_form .list_btn{
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;
    margin: 20px 0px 0px;
    padding: 0 0 0 120px;
}

.register_view .register_form .list_btn .register{
    margin: 0px 0px 12px 0px;
    padding: 0px 10px;
    width: auto;
    height: 40px;
    font-size: 18px;
    color: rgb(255, 255, 255);
    border-radius: 0;
    border: 0px;
    cursor: pointer;
    background: #7282a4;
    min-width:100%;
    letter-spacing:4px;
}

.register_view .register_form .list_btn .r-login{
    width: 100%;
    text-align: right;
    cursor: pointer;
    padding: 10px 0px 0px;
    color: #666;
    font-size: 16px;
}
.register_view .register_form .list_btn .r-login:hover{
    text-decoration:underline;
}

</style>