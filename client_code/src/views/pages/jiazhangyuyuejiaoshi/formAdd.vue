
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
          <el-form-item label="Teacher Name" prop="jiaoshixingming">
            <el-input class="list_inp" v-model="form.jiaoshixingming" placeholder="Teacher Name"
                      type="text" 							:readonly="!isAdd||disabledForm.jiaoshixingming?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Parent Account" prop="jiazhangzhanghao">
            <el-input class="list_inp" v-model="form.jiazhangzhanghao" placeholder="Parent Account"
                      type="text" 							:readonly="!isAdd||disabledForm.jiazhangzhanghao?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Parent Name" prop="jiazhangxingming">
            <el-input class="list_inp" v-model="form.jiazhangxingming" placeholder="Parent Name"
                      type="text" 							:readonly="!isAdd||disabledForm.jiazhangxingming?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Student ID" prop="xuehao">
            <el-input class="list_inp" v-model="form.xuehao" placeholder="Student ID"
                      type="text" 							:readonly="!isAdd||disabledForm.xuehao?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Appointment Time" prop="yuyueshijian">
            <el-date-picker
                class="list_date"
                v-model="form.yuyueshijian"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="datetime"
                style="width:100%;"
                :readonly="!isAdd||disabledForm.yuyueshijian?true:false"
                placeholder="Please select appointment time" />
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
// Base information
const tableName = 'jiazhangyuyuejiaoshi'
const formName = 'Parent Appointment Teacher'
// Base information
const breadList = ref([{
  name: formName
}])
// Get unique identifier
const getUUID =()=> {
  return new Date().getTime();
}
// Form data
const form = ref({
  jiaoshigonghao: '',
  jiaoshixingming: '',
  jiazhangzhanghao: '',
  jiazhangxingming: '',
  xuehao: '',
  yuyueshijian: '',
  shhf: '',
})
const formRef = ref(null)
const id = ref(0)
const type = ref('')
const disabledForm = ref({
  jiaoshigonghao : false,
  jiaoshixingming : false,
  jiazhangzhanghao : false,
  jiazhangxingming : false,
  xuehao : false,
  yuyueshijian : false,
  shhf : false,
})
const isAdd = ref(false)
// Form validation
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
// Match mobile phone number
const validateMobile = (rule, value, callback) => {
  if(!value){
    callback();
  } else if (!context?.$toolUtil.isMobile(value)) {
    callback(new Error("Please enter a valid mobile phone number"));
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
    callback(new Error("Please enter a valid URL"));
  } else {
    callback();
  }
}
const rules = ref({
  jiaoshigonghao: [
  ],
  jiaoshixingming: [
  ],
  jiazhangzhanghao: [
  ],
  jiazhangxingming: [
  ],
  xuehao: [
  ],
  yuyueshijian: [
  ],
  shhf: [
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
  form.value.yuyueshijian = context?.$toolUtil.getCurDateTime()
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
      if(x=='jiazhangzhanghao'){
        form.value.jiazhangzhanghao = row[x];
        disabledForm.value.jiazhangzhanghao = true;
        continue;
      }
      if(x=='jiazhangxingming'){
        form.value.jiazhangxingming = row[x];
        disabledForm.value.jiazhangxingming = true;
        continue;
      }
      if(x=='xuehao'){
        form.value.xuehao = row[x];
        disabledForm.value.xuehao = true;
        continue;
      }
      if(x=='yuyueshijian'){
        form.value.yuyueshijian = row[x];
        disabledForm.value.yuyueshijian = true;
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
    if(json.hasOwnProperty('jiazhangzhanghao') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
      form.value.jiazhangzhanghao = json.jiazhangzhanghao
      disabledForm.value.jiazhangzhanghao = true;
    }
    if(json.hasOwnProperty('jiazhangxingming') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
      form.value.jiazhangxingming = json.jiazhangxingming
      disabledForm.value.jiazhangxingming = true;
    }
    if(json.hasOwnProperty('xuehao') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
      form.value.xuehao = json.xuehao
      disabledForm.value.xuehao = true;
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








</style>