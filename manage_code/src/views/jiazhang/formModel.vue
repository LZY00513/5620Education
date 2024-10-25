<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row>
					<el-col :span="12">
						<el-form-item label="家长账号" prop="jiazhangzhanghao">
							<el-input class="list_inp" v-model="form.jiazhangzhanghao" placeholder="家长账号"
								type="text" :readonly="!isAdd||disabledForm.jiazhangzhanghao?true:false" />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="密码" prop="mima">
							<el-input class="list_inp" v-model="form.mima" placeholder="密码"
								type="password" :readonly="!isAdd||disabledForm.mima?true:false" />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="家长姓名" prop="jiazhangxingming">
							<el-input class="list_inp" v-model="form.jiazhangxingming" placeholder="家长姓名"
								 type="text" 								:readonly="!isAdd||disabledForm.jiazhangxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="性别" prop="xingbie">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.xingbie?true:false"
								v-model="form.xingbie" 
								placeholder="请选择性别"
								>
								<el-option v-for="(item,index) in xingbieLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="手机号码" prop="shoujihaoma">
							<el-input class="list_inp" v-model="form.shoujihaoma" placeholder="手机号码"
								 type="text" 								:readonly="!isAdd||disabledForm.shoujihaoma?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="学号" prop="xuehao">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.xuehao?true:false"
								v-model="form.xuehao" 
								placeholder="请选择学号"
								>
								<el-option v-for="(item,index) in xuehaoLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'jiazhang'
	const formName = '家长'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
		jiazhangzhanghao : false,
		mima : false,
		jiazhangxingming : false,
		xingbie : false,
		shoujihaoma : false,
		xuehao : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	//表单验证
	//匹配整数
	const validateIntNumber = (rule, value, callback) => {
		if (!value) {
			callback();
		} else if (!context?.$toolUtil.isIntNumer(value)) {
			callback(new Error("请输入整数"));
		} else {
			callback();
		}
	}
	//匹配数字
	const validateNumber = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isNumber(value)) {
			callback(new Error("请输入数字"));
		} else {
			callback();
		}
	}
	//匹配手机号码
	const validateMobile = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isMobile(value)) {
			callback(new Error("请输入正确的手机号码"));
		} else {
			callback();
		}
	}
	//匹配电话号码
	const validatePhone = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isPhone(value)) {
			callback(new Error("请输入正确的电话号码"));
		} else {
			callback();
		}
	}
	//匹配邮箱
	const validateEmail = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isEmail(value)) {
			callback(new Error("请输入正确的邮箱地址"));
		} else {
			callback();
		}
	}
	//匹配身份证
	const validateIdCard = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.checkIdCard(value)) {
			callback(new Error("请输入正确的身份证号码"));
		} else {
			callback();
		}
	}
	//匹配网站地址
	const validateUrl = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isURL(value)) {
			callback(new Error("请输入正确的URL地址"));
		} else {
			callback();
		}
	}
	const rules = ref({
		jiazhangzhanghao: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		mima: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		jiazhangxingming: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		xingbie: [
		],
		shoujihaoma: [
			{ validator: validateMobile, trigger: 'blur' },
		],
		xuehao: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//性别列表
	const xingbieLists = ref([])
	//学号列表
	const xuehaoLists = ref([])
	//methods

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			jiazhangzhanghao: '',
			mima: '',
			jiazhangxingming: '',
			xingbie: '',
			shoujihaoma: '',
			xuehao: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
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
			formVisible.value = true
		}

		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
		})
		xingbieLists.value = "男,女".split(',')
		context?.$http({
			url: `option/xuesheng/xuehao`,
			method: 'get'
		}).then(res=>{
			xuehaoLists.value = res.data.data
		})
	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
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
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = user.value.id
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
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
		await context?.$http({
			url: `${crossTable.value}/update`,
			method: 'post',
			data: row
		}).then(res=>{})
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
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
		.cancel_btn {
		}
		.cancel_btn:hover {
		}
		
		.confirm_btn {
		}
		.confirm_btn:hover {
		}
	}
</style>
