
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
          <el-form-item label="Resource Name" prop="ziyuanmingcheng">
            <el-input class="list_inp" v-model="form.ziyuanmingcheng" placeholder="Resource Name"
                      type="text" 							:readonly="!isAdd||disabledForm.ziyuanmingcheng?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Cover" prop="fengmian">
            <uploads
                :disabled="!isAdd||disabledForm.fengmian?true:false"
                action="file/upload"
                tip="Please upload cover"
                :limit="3"
                style="width: 100%;text-align: left;"
                :fileUrls="form.fengmian?form.fengmian:''"
                @change="fengmianUploadSuccess">
            </uploads>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Resource Type" prop="ziyuanleixing">
            <el-select
                class="list_sel"
                :disabled="!isAdd||disabledForm.ziyuanleixing?true:false"
                v-model="form.ziyuanleixing"
                placeholder="Please select resource type"
                style="width:100%;"
            >
              <el-option v-for="(item,index) in ziyuanleixingLists" :label="item"
                         :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Resource Attachment" prop="ziyuanfujian">
            <uploads
                :disabled="!isAdd||disabledForm.ziyuanfujian?true:false"
                type="file"
                action="file/upload"
                tip="Please upload resource attachment"
                :limit="1"
                style="width: 100%;text-align: left;"
                :fileUrls="form.ziyuanfujian?form.ziyuanfujian:''"
                @change="ziyuanfujianUploadSuccess">
            </uploads>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Teacher ID" prop="jiaoshigonghao">
            <el-input class="list_inp" v-model="form.jiaoshigonghao" placeholder="Teacher ID"
                      type="text" 							:readonly="!isAdd||disabledForm.jiaoshigonghao?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Teacher Name" prop="jiaoshixingming">
            <el-input class="list_inp" v-model="form.jiaoshixingming" placeholder="Teacher Name"
                      type="text" 							:readonly="!isAdd||disabledForm.jiaoshixingming?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Resource Brief" prop="ziyuanjianshu">
            <el-input v-model="form.ziyuanjianshu" placeholder="Resource Brief" type="textarea"
                      :readonly="!isAdd||disabledForm.ziyuanjianshu?true:false"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Resource Details" prop="ziyuanxiangqing">
            <editor class="list_editor" :value="form.ziyuanxiangqing" placeholder="Please enter resource details" :readonly="!isAdd||disabledForm.ziyuanxiangqing?true:false"
                    @change="(e)=>editorChange(e,'ziyuanxiangqing')"></editor>
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
// Basic Information
const tableName = 'jiaoxueziyuan'
const formName = 'Teaching Resources'
// Basic Information
const breadList = ref([{
  name: formName
}])
// Get Unique Identifier
const getUUID =()=> {
  return new Date().getTime();
}
// Form
const form = ref({
  ziyuanmingcheng: '',
  fengmian: '',
  ziyuanleixing: '',
  ziyuanfujian: '',
  ziyuanjianshu: '',
  ziyuanxiangqing: '',
  jiaoshigonghao: '',
  jiaoshixingming: '',
})
const formRef = ref(null)
const id = ref(0)
const type = ref('')
const disabledForm = ref({
  ziyuanmingcheng : false,
  fengmian : false,
  ziyuanleixing : false,
  ziyuanfujian : false,
  ziyuanjianshu : false,
  ziyuanxiangqing : false,
  jiaoshigonghao : false,
  jiaoshixingming : false,
  storeupNumber : false,
})
const isAdd = ref(false)
// Form Validation
// Match Integer
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback();
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error("Please enter an integer"));
  } else {
    callback();
  }
}
// Match Number
const validateNumber = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error("Please enter a number"));
  } else {
    callback();
  }
}
// Match Mobile Number
const validateMobile = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error("Please enter a valid mobile number"));
  } else {
    callback();
  }
}
// Match Phone Number
const validatePhone = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error("Please enter a valid phone number"));
  } else {
    callback();
  }
}
// Match Email
const validateEmail = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error("Please enter a valid email address"));
  } else {
    callback();
  }
}
// Match ID Card
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
  ziyuanmingcheng: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  fengmian: [
  ],
  ziyuanleixing: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  ziyuanfujian: [
  ],
  ziyuanjianshu: [
  ],
  ziyuanxiangqing: [
  ],
  jiaoshigonghao: [
  ],
  jiaoshixingming: [
  ],
  storeupNumber: [
    { validator: validateIntNumber, trigger: 'blur' },
  ],
})
// Cover Upload Callback
const fengmianUploadSuccess=(e)=>{
  form.value.fengmian = e
}
// Resource Type List
const ziyuanleixingLists = ref([])
// Resource Attachment Upload Callback
const ziyuanfujianUploadSuccess=(e)=>{
  form.value.ziyuanfujian = e
}
// Methods

// Methods
// Get Info
const getInfo = ()=>{
  context?.$http({
    url: `${tableName}/info/${id.value}`,
    method: 'get'
  }).then(res => {
    let reg=new RegExp('../../../file','g')
    res.data.data.ziyuanxiangqing = res.data.data.ziyuanxiangqing.replace(reg,'../../../cl26103485/file');
    form.value = res.data.data
  })
}
const crossRow = ref('')
const crossTable = ref('')
const crossTips = ref('')
const crossColumnName = ref('')
const crossColumnValue = ref('')
// Initialize
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
      if(x=='ziyuanmingcheng'){
        form.value.ziyuanmingcheng = row[x];
        disabledForm.value.ziyuanmingcheng = true;
        continue;
      }
      if(x=='fengmian'){
        form.value.fengmian = row[x];
        disabledForm.value.fengmian = true;
        continue;
      }
      if(x=='ziyuanleixing'){
        form.value.ziyuanleixing = row[x];
        disabledForm.value.ziyuanleixing = true;
        continue;
      }
      if(x=='ziyuanfujian'){
        form.value.ziyuanfujian = row[x];
        disabledForm.value.ziyuanfujian = true;
        continue;
      }
      if(x=='ziyuanjianshu'){
        form.value.ziyuanjianshu = row[x];
        disabledForm.value.ziyuanjianshu = true;
        continue;
      }
      if(x=='ziyuanxiangqing'){
        form.value.ziyuanxiangqing = row[x];
        disabledForm.value.ziyuanxiangqing = true;
        continue;
      }
      if(x=='jiaoshigonghao'){
        form.value.jiaoshigonghao = row[x];
        disabledForm.value.jiaoshigonghao = true;
        continue;
      }
      if(x=='jiaoshixingming'){
        form.value.jiaoshixingming = row[x];
        disabledForm.value.jiaoshixingming = true;
        continue;
      }
      if(x=='storeupNumber'){
        form.value.storeupNumber = row[x];
        disabledForm.value.storeupNumber = true;
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
    if(json.hasOwnProperty('jiaoshigonghao') && context?.$toolUtil.storageGet("frontRole")!="Administrator"){
      form.value.jiaoshigonghao = json.jiaoshigonghao
      disabledForm.value.jiaoshigonghao = true;
    }
    if(json.hasOwnProperty('jiaoshixingming') && context?.$toolUtil.storageGet("frontRole")!="Administrator"){
      form.value.jiaoshixingming = json.jiaoshixingming
      disabledForm.value.jiaoshixingming = true;
    }
    if (localStorage.getItem('autoSave')) {
      localStorage.removeItem('autoSave')
      save()
    }
  })
  context?.$http({
    url: `option/ziyuanleixing/ziyuanleixing`,
    method: 'get'
  }).then(res=>{
    ziyuanleixingLists.value = res.data.data
  })
}
// Initialize
// Cancel
const backClick = () => {
  history.back()
}
// Rich Text Data Callback
const editorChange = (e,name) =>{
  form.value[name] = e
}
// Submit
const save=()=>{
  if(form.value.fengmian!=null) {
    form.value.fengmian = form.value.fengmian.replace(new RegExp(context?.$config.url,"g"),"");
  }
  if(form.value.ziyuanfujian!=null) {
    form.value.ziyuanfujian = form.value.ziyuanfujian.replace(new RegExp(context?.$config.url,"g"),"");
  }
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
        // Modify cross table data
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
// Modify cross table data
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
  // form item
  :deep(.el-form-item) {
    //label
    .el-form-item__label {
    }
    // Content box
    .el-form-item__content {
      // Input box
      .list_inp {

      }
      // Dropdown
      .list_sel {
        // Remove default style
        .select-trigger{
          height: 100%;
          .el-input{
            height: 100%;
          }
        }
      }
      // Rich text
      .list_editor {
      }
      // Long text
      .el-textarea__inner {
      }
      // Image upload style
      .el-upload-list  {
        // Tip
        .el-upload__tip {
        }
        // External box
        .el-upload--picture-card {
          // Icon
          .el-icon{
          }
        }
        .el-upload-list__item {
        }
      }
      // File upload style
      .upload-demo {
        width: 100%;
        // External box
        .el-upload-dragger {
        }
        // Icon
        .el-icon--upload {
        }
        // Tip text
        .el-upload__text {
          em {
          }
        }
        // Tip text
        .el-upload__tip {
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

.edit_view .add_form .el-form-item .el-form-item__content .list_sel{
  line-height: 36px;
  border: 1px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: calc(100% - 0px);
  padding: 0px 10px;
  border-radius: 0px;
  background: rgb(255, 255, 255);
  font-size: 16px;
}





.edit_view .add_form .el-form-item .el-form-item__content .el-textarea{
}
.edit_view .add_form .el-form-item .el-form-item__content .el-textarea .el-textarea__inner{
  width: 100%;
  min-height: 150px;
  padding: 12px;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 0px;
  color: #666;
  font-size: 16px;
}


.edit_view .add_form .el-form-item .el-form-item__content .el-upload--picture-card{
  background-color: rgb(255, 255, 255);
  width: 100px;
  height: 90px;
  line-height: 100px;
  text-align: center;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 0px;
  cursor: pointer;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--picture-card .el-icon{
  font-size: 32px;
  color: #999;
}

.edit_view .add_form .el-form-item .el-form-item__content .img-uploader .el-upload__tip{
  font-size: 15px;
  color: #666;
  margin: 0;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger{
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(221, 221, 221);
  border-radius: 0px;
  box-sizing: border-box;
  width: 100%;
  height: auto;
  padding:0 0 10px;
  text-align: center;
  cursor: pointer;
  overflow: hidden;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger .el-icon--upload{
  font-size: 67px;
  color: #7282a4;
  line-height: 50px;
  margin: 20px 0 10px;
}

.edit_view .add_form .el-form-item .el-form-item__content .upload-demo .el-upload__tip{
  font-size: 15px;
  color: #666;
  margin: 0px 0 0;
  line-height:1;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger em{
  color: #7282a4;
  font-size: 15px;
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
