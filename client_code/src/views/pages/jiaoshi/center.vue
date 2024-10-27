<template>
  <div class="center_view" :style='{}'>
    <div class="section_title">
      <span>{{formName}}</span>
    </div>
    <div class="usersView">
      <div class="usersTabView">
        <div class="usersTab" :class="tabIndex=='center'?'usersTabActive':''" @click="tabClick({tableName:'center'})">Personal Center</div>
        <div class="usersTab " :class="tabIndex=='updatePassword'?'usersTabActive':''" @click="tabClick({tableName:'updatePassword'})">Change Password</div>
        <template v-for="(item,index) in menuList">
          <div v-if="item.child.length>1" class="usersTab" @mouseenter="usersTabHover(index)"
               @mouseleave="usersTabLeave">
            {{item.menu}}
            <el-collapse-transition>
              <div class="usersTabHoverView" v-if="usersTabIndex==index">
                <div class="usersTabHoverTab" v-for="(items,indexs) in item.child" @click="tabClick(items)">
                  {{items.menu}}
                </div>
              </div>
            </el-collapse-transition>
          </div>
          <div v-else-if="hasBack(item.child[0])" class="usersTab" @click="tabClick(item.child[0])">
            {{item.child[0].menu}}
          </div>
        </template>
        <div class="usersTab" @click="tabClick({tableName:'storeup',type:1})">My Favorites</div>
      </div>
      <div class="usersBox updateInfo" v-if="tabIndex=='center'">
        <el-form class="usersForm" ref="userFormRef" :model="userForm" label-width="120px" :rules="rules">
          <el-row>
            <el-col :span="24">
              <el-form-item prop="jiaoshigonghao" label="Teacher ID">
                <el-input class="list_inp" v-model="userForm.jiaoshigonghao" placeholder="Teacher ID" readonly></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="mima" label="Password">
                <el-input class="list_inp" v-model="userForm.mima" placeholder="Password" type="password"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="jiaoshixingming" label="Teacher Name">
                <el-input class="list_inp" v-model="userForm.jiaoshixingming" placeholder="Teacher Name" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="touxiang" label="Avatar">
                <uploads
                    action="file/upload"
                    tip="Please upload an avatar"
                    :limit="1"
                    style="width: 100%;text-align: left;"
                    :fileUrls="userForm.touxiang?userForm.touxiang:''"
                    @change="touxiangUploadSuccess">
                </uploads>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="lianxidianhua" label="Contact Number">
                <el-input class="list_inp" v-model="userForm.lianxidianhua" placeholder="Contact Number" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="Gender" prop="xingbie">
                <el-select
                    class="list_sel"
                    v-model="userForm.xingbie"
                    placeholder="Please select gender"
                    style="width:100%;"
                >
                  <el-option v-for="(item,index) in xingbieLists" :label="item" :value="item">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="zhuanye" label="Major">
                <el-input class="list_inp" v-model="userForm.zhuanye" placeholder="Major" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="suoshoukemu" label="Courses Taught">
                <el-input class="list_inp" v-model="userForm.suoshoukemu" placeholder="Courses Taught" ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="keyueshijian" label="Available Time">
                <el-input class="list_inp" v-model="userForm.keyueshijian" placeholder="Available Time" ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <div class="formModel_btn_box">
            <el-button class="formModel_confirm" @click="updateSession">Update Information</el-button>
            <el-button class="formModel_cancel" @click="loginout" type="danger">Log Out</el-button>
          </div>
        </el-form>
      </div>
      <div class="usersBox updatePassword" v-if="tabIndex=='updatePassword'">
        <el-form class="usersForm" ref="passwordFormRef" :model="passwordForm" label-width="120px"
                 :rules="passwordRules">
          <el-row>
            <el-col :span="24">
              <el-form-item label="Original Password" prop="mima">
                <el-input class="list_inp" v-model="passwordForm.mima" placeholder="Original Password"
                          type="password"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="New Password" prop="newmima">
                <el-input class="list_inp" v-model="passwordForm.newmima" placeholder="New Password"
                          type="password"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="Confirm Password" prop="newmima2">
                <el-input class="list_inp" v-model="passwordForm.newmima2" placeholder="Confirm Password"
                          type="password"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <div class="formModel_btn_box">
            <el-button class="formModel_confirm" @click="updatePassword">Change Password</el-button>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  ElMessageBox
} from 'element-plus'
import moment from 'moment'
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
} from 'vue';
import {
  useRoute,
  useRouter
} from 'vue-router';
import { useStore } from 'vuex'
const store = useStore()
import menu from '@/utils/menu'
const context = getCurrentInstance()?.appContext.config.globalProperties;
const route = useRoute()
const router = useRouter()
// Basic information
const tableName = 'jiaoshi'
const formName = 'Personal Center'
// Basic information
const uploadUrl = context.$config.url + 'file/upload'
// Personal information
const userFormRef = ref(null)
const userForm = ref({})
// Change password
const passwordFormRef = ref(null)
const passwordForm = ref({
  mima: '',
  newmima: '',
  newmima2: ''
})
const passwordRules = ref({
  mima: [{
    required: true,
    message: 'Please enter',
    trigger: 'blur'
  }, ],
  newmima: [{
    required: true,
    message: 'Please enter',
    trigger: 'blur'
  }, ],
  newmima2: [{
    required: true,
    message: 'Please enter',
    trigger: 'blur'
  }, ],
})
// Validation rules
// Match integer
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback();
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error("Please enter an integer"));
  } else {
    callback();
  }
}
// Match number
const validateNumber = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error("Please enter a number"));
  } else {
    callback();
  }
}
// Match mobile number
const validateMobile = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error("Please enter a valid mobile number"));
  } else {
    callback();
  }
}
// Match phone number
const validatePhone = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error("Please enter a valid phone number"));
  } else {
    callback();
  }
}
// Match email
const validateEmail = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error("Please enter a valid email address"));
  } else {
    callback();
  }
}
// Match ID card
const validateIdCard = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.checkIdCard(value)) {
    callback(new Error("Please enter a valid ID card number"));
  } else {
    callback();
  }
}
// Match website address
const validateUrl = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isURL(value)) {
    callback(new Error("Please enter a valid URL address"));
  } else {
    callback();
  }
}
const rules = ref({
  jiaoshigonghao: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  mima: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  jiaoshixingming: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  touxiang: [
  ],
  lianxidianhua: [
    { validator: validateMobile, trigger: 'blur' },
  ],
  xingbie: [
  ],
  zhuanye: [
  ],
  sfsh: [
  ],
  shhf: [
  ],
  suoshoukemu: [
  ],
  keyueshijian: [
  ],
})
const getSession = () =>{
  context?.$http({
    url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
    method:'get'
  }).then(res=>{
    context?.$toolUtil.storageSet('userid',res.data.data.id)
    context?.$toolUtil.storageSet("frontName", res.data.data.jiaoshigonghao)
    context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang)
    userForm.value = res.data.data
  })
}
// Menu navigation
const tabIndex = ref('center')
const tabClick = (item) => {
  if (item.tableName == 'center') {
    tabIndex.value = 'center'
    return
  }
  if (item.tableName == 'updatePassword') {
    passwordForm.value = {
      mima: '',
      newmima: '',
      newmima2: ''
    }
    tabIndex.value = 'updatePassword'
    return
  }
  if(item.tableName=='examrecord'&&item.menuJump=='22'){
    router.push(`/index/examfailrecord?centerType=1`)
    return
  }
  if(item.tableName=='forum'&&item.menuJump=='14'){
    router.push(`/index/forumList?centerType=1&&myType=1`)
    return
  }
  if(item.tableName=='storeup'){
    router.push(`/index/storeupList?centerType=1&&type=${item.type}`)
    return;
  }
  router.push(`/index/${item.classname||item.tableName}List?centerType=1${item.menuJump?'&menuJump='+item.menuJump:''}`)
}
const hasBack = (menu)=>{
  if(menu.tableName=='storeup'){
    return false
  }
  return true
}
// Change password
const updatePassword = async ()=>{
  passwordFormRef.value.validate(async (valid) => {
    if (valid) {
      if(passwordForm.value.mima != userForm.value.mima){
        context?.$toolUtil.message('Original password is incorrect', 'error')
        return false
      }
      if(passwordForm.value.newmima != passwordForm.value.newmima2){
        context?.$toolUtil.message('The two passwords entered are incorrect', 'error')
        return false
      }
      userForm.value.mima = passwordForm.value.newmima
      store.dispatch('user/update',userForm.value).then(res=>{
        if(res?.data&&res.data.code==0){
          context?.$toolUtil.message('Update successful','success')
          passwordForm.value = {
            mima: '',
            newmima: '',
            newmima2: ''
          }
          getSession()
        }
      })
    }
  })
}
// Menu
const menuList = ref([])
const role = ref('')
// Avatar upload callback
const touxiangUploadSuccess=(e)=>{
  userForm.value.touxiang = e
}
// Gender list
const xingbieLists = ref([])
// Initialization
const init = () => {
  const menus = menu.list()
  let arr = []
  if (menus) {
    menuList.value = menus
  }
  role.value = context?.$toolUtil.storageGet('frontRole')
  for (let i = 0; i < menuList.value.length; i++) {
    if (menuList.value[i].roleName == role.value) {
      arr = menuList.value[i].backMenu
      break;
    }
  }
  menuList.value = arr
  xingbieLists.value = "Male,Female".split(',')
  getSession()
}
// Show and hide menu hover
const usersTabIndex = ref(-1)
const usersTabHover = (index) => {
  usersTabIndex.value = index
}
const usersTabLeave = () => {
  usersTabIndex.value = -1
}
// Rich text editor
const editorChange = (e,name) =>{
  userForm.value[name] = e
}
// Save
const updateSession = () => {
  userFormRef.value.validate((valid)=>{
    if(valid){
      if(userForm.value.touxiang!=null){
        userForm.value.touxiang = userForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
      }
      store.dispatch('user/update',userForm.value).then(res=>{
        if(res?.data&&res.data.code==0){
          context?.$toolUtil.message('Update successful','success')
          getSession()
        }
      })
    }
  })
}
// Log out
const loginout = () => {
  context?.$toolUtil.message('Logout successful', 'success')
  context?.$toolUtil.storageClear()
  router.replace('/index/home')
}
init()
</script>

<style lang="scss" scoped>
.usersView {

  .usersTabView {

    .usersTab {

      .usersTabHoverView {
        padding: 20px 0;
        z-index: 999;
        background: #eee;
        display: block;
        width: 200px;
        .usersTabHoverTab {
          color: #333;
          font-size: 15px;
          line-height: 40px;
        }
        .usersTabHoverTab:hover {
        }
      }
    }
    .usersTab:hover {
    }
    .usersTabActive {
    }
  }

  .usersBox {
    .usersForm {
      // form item
      :deep(.el-form-item) {
        // label
        .el-form-item__label {
        }
        // Content box
        .el-form-item__content {
          // Input box
          .list_inp {
          }
          // Dropdown box
          .list_sel {
            // Remove default style
            .select-trigger{
              height: 100%;
              .el-input{
                height: 100%;
              }
            }
          }
          // Image upload style
          .el-upload-list  {
            // Hint
            .el-upload__tip {
            }
            // Outer box
            .el-upload--picture-card {
              // Icon
              .el-icon{
              }
            }
            .el-upload-list__item {
            }
          }
        }
      }
    }
  }
  // Button box
  .formModel_btn_box {
    .formModel_cancel {
    }
    .formModel_cancel:hover {
    }

    .formModel_confirm {
    }
    .formModel_confirm:hover {
    }
  }
}
</style>

<style>
/** Total box **/
.center_view {
  margin: 20px auto;
  width: 1200px;
  position: relative;
  background: rgb(255, 255, 255);
  font-size:16px;
  color:#666;
}
/** Title **/
.center_view .section_title{
  width: 100%;
  margin: 20px 0 0;
  font-size: 22px;
  color: #7282a4;
  font-weight:600;
  text-align: center;
  padding:10px 0;
  background:#fff;
  border: 1px solid #7282a450;
  box-shadow: inset 0px 0px 30px 0px #7282a420;
}
/** Content area **/
.center_view .usersView{
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}

/** Tab total box **/
.center_view .usersView .usersTabView{
  width: 200px;
  background: none;
  padding: 0px;
  box-sizing: border-box;
  order:2;
}
/** Item **/
.center_view .usersView .usersTabView .usersTab{
  width: 100%;
  font-size: 16px;
  padding: 0px;
  margin: 0px 0px 6px;
  position: relative;
  cursor: pointer;
  text-align: center;
  background: #fff;
  color: rgb(51, 51, 51);
  line-height:50px;
}
.center_view .usersView .usersTabView .usersTab:hover{
  background: #7282a4;
  color: #fff;
}
.center_view .usersView .usersTabView .usersTab.usersTabActive{
  width: 100%;
  font-size: 16px;
  padding: 0px;
  margin: 0px 0px 6px;
  position: relative;
  cursor: pointer;
  text-align: center;
  background: #7282a4;
  color: #fff;
}

/** Right part total box **/
.center_view .usersView .usersBox{
  width: calc(100% - 220px);
  background: #fff;
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
  box-shadow: none;
  border: 1px solid #eee;
}
/** Form box **/
.center_view .usersView .usersBox .usersForm{
  width: 100%;
  padding: 30px;
}
.center_view .usersView .usersBox .usersForm .el-form-item{
  margin: 0px 0px 20px;
  display: flex;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__label{
  width: 150px;
  background: none;
  text-align: right;
  display: block;
  font-size: 16px;
  color: rgb(51, 51, 51);
  font-weight: 500;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content{
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  width: calc(100% - 120px);
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_inp{
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: 100%;
  box-sizing: border-box;
  background: rgb(255, 255, 255);
  font-size: 16px;
}
/** Member **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .vip_item{
  display: flex;
  align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .vip_item .vip_inp{
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: 80%;
  box-sizing: border-box;
  background: rgb(255, 255, 255);
  font-size: 16px;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .vip_item .vip_btn{
  background: #7282a4;
  color: rgb(255, 255, 255);
  height: 36px;
  line-height: 36px;
  padding: 0px 20px;
  border-radius: 0px;
  margin: 0px 0px 0px 5px;
  border-color: #7282a4;
}
/** End **/

/** Dropdown selection **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_sel{
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 0px);
  padding: 0px 10px;
  border-radius: 0px;
  background: rgb(255, 255, 255);
  font-size: 16px;
}
/** End **/

/** Date selection **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_date{
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: 100%;
  border-radius: 0px;
  background: rgb(255, 255, 255);
}
/** End **/

/** Radio **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio{
  display: flex;
  width: calc(100% - 0px);
  align-items: center;
  flex-wrap: wrap;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio{
  width: auto;
  margin: 0 20px 0 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio .el-radio__inner{
  border-color: #999;
  background: #fff;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio .el-radio__label{
  color: #999;
  font-size: 16px;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio.is-checked .el-radio__inner{
  border-color: #7282a4;
  background: #7282a4;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio.is-checked .el-radio__label{
  color: #7282a4;
  font-size: 16px;
}
/** End **/

/** Checkbox **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox{
  display: flex;
  width: calc(100% - 0px);
  flex-wrap: wrap;
  align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox{
  width: auto;
  margin: 0 20px 0 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox .el-checkbox__inner{
  border-color: #999;
  background: #fff;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox .el-checkbox__label{
  color: #999;
  font-size: 16px;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox.is-checked .el-checkbox__inner{
  border-color: #7282a4;
  background: #7282a4;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox.is-checked .el-checkbox__label{
  color: #7282a4;
  font-size: 16px;
}
/** End **/

/** Textarea **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_textarea{
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_textarea .el-textarea__inner{
  width: 100%;
  min-height: 150px;
  padding: 12px;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 0px;
  color: #666;
  font-size: 16px;
}
/** End **/

/** Image upload **/
/* Box */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--picture-card{
  background-color: rgb(255, 255, 255);
  width: 100px;
  height: 90px;
  line-height: 100px;
  text-align: center;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 0px;
  cursor: pointer;
}
/* Icon */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--picture-card .el-icon{
  font-size: 32px;
  color: #999;
}
/* Hint */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .img-uploader .el-upload__tip{
  font-size: 15px;
  color: #666;
  margin: 0;
}
/** End **/

/** File upload **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
  border-radius: 0px;
  box-sizing: border-box;
  width: 360px;
  height: 180px;
  text-align: center;
  cursor: pointer;
  overflow: hidden;
}
/* Icon */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger .el-icon-upload{
  font-size: 67px;
  color: #7282a4;
  line-height: 50px;
}
/* Hint */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .upload-demo .el-upload__tip{
  font-size: 15px;
  color: #666;
  margin: 0px 0 0;
  line-height:1;
}
/* Click to upload */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger em{
  color: #7282a4;
  font-size: 15px;
}
/** End **/

/** Rich text **/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_editor{
  width: 100%;
  height: auto;
  margin: 0px;
  padding: 0px;
  border-radius: 0px;
  border-width: 0px;
  border-style: solid;
  border-color: rgb(204, 204, 204);
  background-color: rgb(255, 255, 255);
}
/** End **/

/** Button **/
.center_view .formModel_btn_box{
  display: flex;
  align-items: center;
  width: 100%;
  padding: 10px 0px 10px 150px;
  margin: 40px 0px 0px;
}
/** Update information **/
.center_view .formModel_btn_box .formModel_confirm{
  margin: 0px 20px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 36px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 0px;
  border: 0px;
  background: #7282a4;
  cursor: pointer;
}
.center_view .formModel_btn_box .formModel_confirm:hover{
  background: #7282a4;
}
/** Logout **/
.center_view .formModel_btn_box .formModel_cancel{
  margin: 0px 20px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 36px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 0px;
  border: 0px;
  background: #666;
  cursor: pointer;
}
.center_view .formModel_btn_box .formModel_cancel:hover{
  background: rgba(66, 66, 66,1);
}
</style>
