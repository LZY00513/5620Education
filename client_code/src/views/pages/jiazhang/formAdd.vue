
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
          <el-form-item label="Parent Account" prop="jiazhangzhanghao">
            <el-input class="list_inp" v-model="form.jiazhangzhanghao" placeholder="Parent Account"
                      type="text" :readonly="!isAdd||disabledForm.jiazhangzhanghao?true:false" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Password" prop="mima">
            <el-input class="list_inp" v-model="form.mima" placeholder="Password"
                      type="password" :readonly="!isAdd||disabledForm.mima?true:false" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Parent Name" prop="jiazhangxingming">
            <el-input class="list_inp" v-model="form.jiazhangxingming" placeholder="Parent Name"
                      type="text" :readonly="!isAdd||disabledForm.jiazhangxingming?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Gender" prop="xingbie">
            <el-select
                class="list_sel"
                :disabled="!isAdd||disabledForm.xingbie?true:false"
                v-model="form.xingbie"
                placeholder="Select Gender"
                style="width:100%;"
            >
              <el-option v-for="(item,index) in xingbieLists" :label="item"
                         :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Mobile Number" prop="shoujihaoma">
            <el-input class="list_inp" v-model="form.shoujihaoma" placeholder="Mobile Number"
                      type="text" :readonly="!isAdd||disabledForm.shoujihaoma?true:false" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="Student ID" prop="xuehao">
            <el-select
                class="list_sel"
                :disabled="!isAdd||disabledForm.xuehao?true:false"
                v-model="form.xuehao"
                placeholder="Select Student ID"
                style="width:100%;"
            >
              <el-option v-for="(item,index) in xuehaoLists" :label="item"
                         :value="item"
              >
              </el-option>
            </el-select>
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
const tableName = 'jiazhang'
const formName = 'Parent'
// Basic Information
const breadList = ref([{
  name: formName
}])
// Get Unique Identifier
const getUUID =()=> {
  return new Date().getTime();
}
// Form Data
const form = ref({
  jiazhangzhanghao: '',
  mima: '',
  jiazhangxingming: '',
  xingbie: '',
  shoujihaoma: '',
  xuehao: '',
})
const formRef = ref(null)
const id = ref(0)
const type = ref('')
const disabledForm = ref({
  jiazhangzhanghao : false,
  mima : false,
  jiazhangxingming : false,
  xingbie : false,
  shoujihaoma : false,
  xuehao : false,
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
  jiazhangzhanghao: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  mima: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  jiazhangxingming: [
    {required: true,message: 'Please enter',trigger: 'blur'},
  ],
  xingbie: [
  ],
  shoujihaoma: [
    { validator: validateMobile, trigger: 'blur' },
  ],
  xuehao: [
  ],
})
// Gender List
const xingbieLists = ref([])
// Student ID List
const xuehaoLists = ref([])
// Methods

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
      if(x=='jiazhangzhanghao'){
        form.value.jiazhangzhanghao = row[x];
        disabledForm.value.jiazhangzhanghao = true;
        continue;
      }
      if(x=='mima'){
        form.value.mima = row[x];
        disabledForm.value.mima = true;
        continue;
      }
      if(x=='jiazhangxingming'){
        form.value.jiazhangxingming = row[x];
        disabledForm.value.jiazhangxingming = true;
        continue;
      }
      if(x=='xingbie'){
        form.value.xingbie = row[x];
        disabledForm.value.xingbie = true;
        continue;
      }
      if(x=='shoujihaoma'){
        form.value.shoujihaoma = row[x];
        disabledForm.value.shoujihaoma = true;
        continue;
      }
      if(x=='xuehao'){
        form.value.xuehao = row[x];
        disabledForm.value.xuehao = true;
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
    if (localStorage.getItem('autoSave')) {
      localStorage.removeItem('autoSave')
      save()
    }
  })
  xingbieLists.value = "Male,Female".split(',')
  context?.$http({
    url: `option/xuesheng/xuehao`,
    method: 'get'
  }).then(res=>{
    xuehaoLists.value = res.data.data
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
        // Modify Cross Table Data
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
              context?.$toolUtil.message(`Operation Successful`,'success')
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
          context?.$toolUtil.message(`Operation Successful`,'success')
          history.back()
        })
      }
    }
  })
}
// Modify Cross Table Data
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
				// 下拉框
				.list_sel {
					//去掉默认样式
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
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









</style>