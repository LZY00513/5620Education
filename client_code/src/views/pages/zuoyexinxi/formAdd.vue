
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
          <el-form-item label="Assignment Name" prop="zuoyemingcheng">
            <el-input class="list_inp" v-model="form.zuoyemingcheng" placeholder="Assignment Name"
                      type="text" 							:readonly="!isAdd||disabledForm.zuoyemingcheng?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Cover" prop="fengmian">
            <uploads
                :disabled="!isAdd||disabledForm.fengmian?true:false"
                action="file/upload"
                tip="Please upload a cover"
                :limit="3"
                style="width: 100%;text-align: left;"
                :fileUrls="form.fengmian?form.fengmian:''"
                @change="fengmianUploadSuccess">
            </uploads>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Assignment Content" prop="zuoyeneirong">
            <uploads
                :disabled="!isAdd||disabledForm.zuoyeneirong?true:false"
                type="file"
                action="file/upload"
                tip="Please upload the assignment content"
                :limit="1"
                style="width: 100%;text-align: left;"
                :fileUrls="form.zuoyeneirong?form.zuoyeneirong:''"
                @change="zuoyeneirongUploadSuccess">
            </uploads>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Release Time" prop="fabushijian">
            <el-date-picker
                class="list_date"
                v-model="form.fabushijian"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="datetime"
                style="width:100%;"
                :readonly="!isAdd||disabledForm.fabushijian?true:false"
                placeholder="Please select the release time" />
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
          <el-form-item label="Last Click Time" prop="reversetime">
            <el-date-picker
                class="list_date"
                v-model="form.reversetime"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="datetime"
                style="width:100%;"
                :readonly="!isAdd||disabledForm.reversetime?true:false"
                placeholder="Please select the last click time" />
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
const tableName = 'zuoyexinxi'
const formName = 'Assignment Information'
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
  zuoyemingcheng: '',
  fengmian: '',
  zuoyeneirong: '',
  fabushijian: '',
  jiaoshigonghao: '',
  jiaoshixingming: '',
  userid: '',
  reversetime: '',
})
const formRef = ref(null)
const id = ref(0)
const type = ref('')
const disabledForm = ref({
  zuoyemingcheng : false,
  fengmian : false,
  zuoyeneirong : false,
  fabushijian : false,
  jiaoshigonghao : false,
  jiaoshixingming : false,
  userid : false,
  reversetime : false,
})
const isAdd = ref(false)
// Form Validation
// Validate Integer
const validateIntNumber = (rule, value, callback) => {
  if (!value) {
    callback();
  } else if (!context?.$toolUtil.isIntNumer(value)) {
    callback(new Error("Please enter an integer"));
  } else {
    callback();
  }
}
// Validate Number
const validateNumber = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isNumber(value)) {
    callback(new Error("Please enter a number"));
  } else {
    callback();
  }
}
// Validate Mobile Number
const validateMobile = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error("Please enter a valid mobile number"));
  } else {
    callback();
  }
}
// Validate Phone Number
const validatePhone = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isPhone(value)) {
    callback(new Error("Please enter a valid phone number"));
  } else {
    callback();
  }
}
// Validate Email
const validateEmail = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isEmail(value)) {
    callback(new Error("Please enter a valid email address"));
  } else {
    callback();
  }
}
// Validate ID Card
const validateIdCard = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.checkIdCard(value)) {
    callback(new Error("Please enter a valid ID card number"));
  } else {
    callback();
  }
}
// Validate URL
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
  zuoyemingcheng: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  fengmian: [
  ],
  zuoyeneirong: [
  ],
  fabushijian: [
  ],
  jiaoshigonghao: [
  ],
  jiaoshixingming: [
  ],
  userid: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  reversetime: [
  ],
})
// Cover Upload Callback
const fengmianUploadSuccess=(e)=>{
  form.value.fengmian = e
}
// Assignment Content Upload Callback
const zuoyeneirongUploadSuccess=(e)=>{
  form.value.zuoyeneirong = e
}
// Methods
// Get Info
const getInfo = ()=>{
  context?.$http({
    url: `${tableName}/info/${id.value}`,
    method: 'get'
  }).then(res => {
    let reg=new RegExp('../../../file','g')
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
  form.value.fabushijian = context?.$toolUtil.getCurDateTime()
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
      if(x=='zuoyemingcheng'){
        form.value.zuoyemingcheng = row[x];
        disabledForm.value.zuoyemingcheng = true;
        continue;
      }
      if(x=='fengmian'){
        form.value.fengmian = row[x];
        disabledForm.value.fengmian = true;
        continue;
      }
      if(x=='zuoyeneirong'){
        form.value.zuoyeneirong = row[x];
        disabledForm.value.zuoyeneirong = true;
        continue;
      }
      if(x=='fabushijian'){
        form.value.fabushijian = row[x];
        disabledForm.value.fabushijian = true;
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
      if(x=='userid'){
        form.value.userid = row[x];
        disabledForm.value.userid = true;
        continue;
      }
      if(x=='reversetime'){
        form.value.reversetime = row[x];
        disabledForm.value.reversetime = true;
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
    if(json.hasOwnProperty('jiaoshixingming') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
      form.value.jiaoshixingming = json.jiaoshixingming
      disabledForm.value.jiaoshixingming = true;
    }
    if (localStorage.getItem('autoSave')) {
      localStorage.removeItem('autoSave')
      save()
    }
  })
}
// Initialize
// Cancel
const backClick = () => {
  history.back()
}
// Submit
const save=()=>{
  if(form.value.fengmian!=null) {
    form.value.fengmian = form.value.fengmian.replace(new RegExp(context?.$config.url,"g"),"");
  }
  if(form.value.zuoyeneirong!=null) {
    form.value.zuoyeneirong = form.value.zuoyeneirong.replace(new RegExp(context?.$config.url,"g"),"");
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
	// 面包屑盒子
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
	// 表单
	.add_form{
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 输入框
				.list_inp {

				}
				//日期选择器
				.list_date {
				}
				//图片上传样式
				.el-upload-list  {
					//提示语
					.el-upload__tip {
					}
					//外部盒子
					.el-upload--picture-card {
						//图标
						.el-icon{
						}
					}
					.el-upload-list__item {
					}
				}
				//文件上传样式
				.upload-demo {
					width: 100%;
					//外部盒子
					.el-upload-dragger {
					}
					//图标
					.el-icon--upload {
					}
					//提示文字
					.el-upload__text {
						em {
						}
					}
					//提示文字
					.el-upload__tip {
					}
				}
			}
		}
	}
	// 按钮盒子
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


.edit_view .add_form .el-form-item .el-form-item__content .list_date{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: 100%;
    border-radius: 0px;
    background: rgb(255, 255, 255);
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


</style>