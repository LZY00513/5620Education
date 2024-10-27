
<template>
  <div class="edit_view" :style='{}'>
    <div class="bread_view">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
        <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-form ref="formRef" :model="form" class="add_form" label-width="120px" :rules="rules">
      <el-row>
        <el-col :span="24">
          <el-form-item label="Teacher ID" prop="jiaoshigonghao">
            <el-input class="list_inp" v-model="form.jiaoshigonghao" placeholder="Teacher ID"
                      type="text" 							:readonly="!isAdd||disabledForm.jiaoshigonghao?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Content" prop="neirong">
            <editor class="list_editor" :value="form.neirong" placeholder="Please enter content" :readonly="!isAdd||disabledForm.neirong?true:false"
                    @change="(e)=>editorChange(e,'neirong')"></editor>
          </el-form-item>
        </el-col>
      </el-row>
      <div class="formModel_btn_box">
        <el-button class="formModel_cancel" @click="backClick">Cancel</el-button>
        <el-button class="formModel_confirm"
                   @click="save"
                   type="success"
        >
          Save
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed
} from 'vue';
import {
  useRoute,
  useRouter
} from 'vue-router';
const context = getCurrentInstance()?.appContext.config.globalProperties;
const route = useRoute()
const router = useRouter()
// Basic information
const tableName = 'jiaoshikongbaiyemian'
const formName = 'Teacher Blank Page'
// Basic information
const breadList = ref([{
  name: formName
}])
// Get unique identifier
const getUUID =()=> {
  return new Date().getTime();
}
// Form data
const form = ref({
  neirong: '',
  jiaoshigonghao: '',
})
const formRef = ref(null)
const id = ref(0)
const type = ref('')
const disabledForm = ref({
  neirong : false,
  jiaoshigonghao : false,
})
const isAdd = ref(false)
// Form validation
// Match integers
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback();
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error("Please enter an integer"));
  } else {
    callback();
  }
}
// Match numbers
const validateNumber = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error("Please enter a number"));
  } else {
    callback();
  }
}
// Match mobile numbers
const validateMobile = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error("Please enter a valid mobile number"));
  } else {
    callback();
  }
}
// Match telephone numbers
const validatePhone = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error("Please enter a valid telephone number"));
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
// Match URL
const validateUrl = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isURL(value)) {
    callback(new Error("Please enter a valid URL"));
  } else {
    callback();
  }
}
const rules = ref({
  neirong: [
  ],
  jiaoshigonghao: [
  ],
})
// Methods

// Methods
// Get info
const getInfo = ()=>{
  context?.$http({
    url: `${tableName}/info/${id.value}`,
    method: 'get'
  }).then(res => {
    let reg=new RegExp('../../../file','g')
    res.data.data.neirong = res.data.data.neirong.replace(reg,'../../../cl26103485/file');
    form.value = res.data.data
  })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
// Initialization
const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
  if(formId){
    id.value = formId
    type.value = formType
  }
  if(formType == 'add'){
    isAdd.value = true
  }else if(formType == 'info'){
    isAdd.value = false
    getInfo()
  }else if(formType == 'edit'){
    isAdd.value = true
    getInfo()
  }
  else if(formType == 'cross'){
    isAdd.value = true
    // getInfo()
    for(let x in row){
      if(x=='neirong'){
        form.value.neirong = row[x];
        disabledForm.value.neirong = true;
        continue;
      }
      if(x=='jiaoshigonghao'){
        form.value.jiaoshigonghao = row[x];
        disabledForm.value.jiaoshigonghao = true;
        continue;
      }
    }
    if(row){
      crossRow.value = row
    }
    if(table){
      crossTable.value = table
    }
    if(tips){
      crossTips.value = tips
    }
    if(statusColumnName){
      crossColumnName.value = statusColumnName
    }
    if(statusColumnValue){
      crossColumnValue.value = statusColumnValue
    }
  }
  context?.$http({
    url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
    method: 'get'
  }).then(res => {
    var json = res.data.data
    if(json.hasOwnProperty('jiaoshigonghao') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
      form.value.jiaoshigonghao = json.jiaoshigonghao
      disabledForm.value.jiaoshigonghao = true;
    }
    if (localStorage.getItem('autoSave')) {
      localStorage.removeItem('autoSave')
      save()
    }
  })
}
// Initialization
// Cancel
const backClick = () => {
  history.back()
}
// Rich text data callback
const editorChange = (e,name) =>{
  form.value[name] = e
}
// Submit
const save=()=>{
  var table = crossTable.value
  var objcross = JSON.parse(JSON.stringify(crossRow.value))
  let crossUserId = ''
  let crossRefId = ''
  let crossOptNum = ''
  if(type.value == 'cross'){
    if(crossColumnName.value!=''){
      if(!crossColumnName.value.startsWith('[')){
        for(let o in objcross){
          if(o == crossColumnName.value){
            objcross[o] = crossColumnValue.value
          }
        }
        // Modify cross-table data
        changeCrossData(objcross)
      }else{
        crossUserId = context?.$toolUtil.storageGet('userid')
        crossRefId = objcross['id']
        crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
      }
    }
  }
  formRef.value.validate((valid)=>{
    if(valid){
      if(crossUserId&&crossRefId){
        form.value.crossuserid = crossUserId
        form.value.crossrefid = crossRefId
        let params = {
          page: 1,
          limit: 1000,
          crossuserid:form.value.crossuserid,
          crossrefid:form.value.crossrefid,
        }
        context?.$http({
          url: `${tableName}/page`,
          method: 'get',
          params: params
        }).then(res=>{
          if(res.data.data.total>=crossOptNum){
            context?.$toolUtil.message(`${crossTips.value}`,'error')
            return false
          }else{
            context?.$http({
              url: `${tableName}/${!form.value.id ? "save" : "update"}`,
              method: 'post',
              data: form.value
            }).then(res=>{
              context?.$toolUtil.message(`Operation successful`,'success')
              history.back()
            })
          }
        })
      }else{
        context?.$http({
          url: `${tableName}/${!form.value.id ? "save" : "update"}`,
          method: 'post',
          data: form.value
        }).then(res=>{
          context?.$toolUtil.message(`Operation successful`,'success')
          history.back()
        })
      }
    }
  })
}
// Modify cross-table data
const changeCrossData=(row)=>{
  context?.$http({
    url: `${crossTable.value}/update`,
    method: 'post',
    data: row
  }).then(res=>{})
}
onMounted(()=>{
  type.value = route.query.type?route.query.type:'add'
  let row = null
  let table = null
  let statusColumnName = null
  let tips = null
  let statusColumnValue = null
  if(type.value == 'cross'){
    row = context?.$toolUtil.storageGet('crossObj')?JSON.parse(context?.$toolUtil.storageGet('crossObj')):{}
    table = context?.$toolUtil.storageGet('crossTable')
    statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
    tips = context?.$toolUtil.storageGet('crossTips')
    statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
  }
  init(route.query.id?route.query.id:null, type.value,'', row, table, statusColumnName, tips, statusColumnValue)
})
onUnmounted(()=>{
  Object.keys(localStorage).map(item=>{
    if(item.startsWith('cross')){
      localStorage.removeItem(item)
    }
  })
})
</script>

<style lang="scss" scoped>
// Breadcrumb box
.bread_view {
  :deep(.breadcrumb) {
    .el-breadcrumb__separator {
    }
    .first_breadcrumb {
      .el-breadcrumb__inner {
      }
    }
    .second_breadcrumb {
      .el-breadcrumb__inner {
      }
    }
  }
}
// Form
.add_form{
  // Form item
  :deep(.el-form-item) {
    // Label
    .el-form-item__label {
    }
    // Content box
    .el-form-item__content {
      // Input box
      .list_inp {

      }
      // Rich text editor
      .list_editor {
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
</style>

<style>
.edit_view {
  width: 1200px;
  margin: 20px auto;
  padding: 0px ;
  background: #fff;
  overflow: hidden;
  font-size: 16px;
  color:#666;
}
.edit_view .add_form{
  width: 100%;
  padding: 30px;
  border:0px solid #eee
}
.edit_view .add_form .el-form-item{
  margin: 0px 0px 20px;
  display: flex;
}
.edit_view .add_form .el-form-item .el-form-item__label{
  width: 150px;
  background: none;
  text-align: right;
  display: block;
  font-size: 16px;
  color: rgb(51, 51, 51);
  font-weight: 500;
}
.edit_view .add_form .el-form-item .el-form-item__content{
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  width: calc(100% - 120px);
}
.edit_view .add_form .el-form-item .el-form-item__content .list_inp{
  height: 36px;
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: 100%;
  box-sizing: border-box;
  background: rgb(255, 255, 255);
  font-size: 16px;
}
.edit_view .add_form .el-form-item .el-form-item__content .list_editor{
  width: 100%;
  height: auto;
  margin: 0px;
  padding: 0px;
  border-radius: 0px;
  background: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
  min-height: 300px;
}
</style>
