<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							教师姓名：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.jiaoshixingming" placeholder="教师姓名"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							审核状态：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.sfsh" 
								placeholder="审核状态"
								>
								<el-option v-for="item in approvalLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('jiaoshi','新增')">
						<i class="iconfont icon-xinzeng2"></i>
						新增
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('jiaoshi','删除')">
						<i class="iconfont icon-shanchu1"></i>
						删除
					</el-button>
				</div>
			</div>
			<br>
			<el-table
				v-loading="listLoading"
				border 
				:stripe='false'
				@selection-change="handleSelectionChange" 
				ref="table"
				v-if="btnAuth('jiaoshi','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' align="left" header-align="left">
					<template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="jiaoshigonghao"
					label="教师工号">
					<template #default="scope">
						{{scope.row.jiaoshigonghao}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="jiaoshixingming"
					label="教师姓名">
					<template #default="scope">
						{{scope.row.jiaoshixingming}}
					</template>
				</el-table-column>
				<el-table-column label="头像" min-width="140" width="120" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.touxiang">
							<el-image v-if="scope.row.touxiang.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.touxiang.split(',')[0]]"
								:src="scope.row.touxiang.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.touxiang.split(',')[0]]"
								:src="$config.url+scope.row.touxiang.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>无图片</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="lianxidianhua"
					label="联系电话">
					<template #default="scope">
						{{scope.row.lianxidianhua}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="xingbie"
					label="性别">
					<template #default="scope">
						{{scope.row.xingbie}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="zhuanye"
					label="专业">
					<template #default="scope">
						{{scope.row.zhuanye}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="suoshoukemu"
					label="所授科目">
					<template #default="scope">
						{{scope.row.suoshoukemu}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="left"
					 prop="keyueshijian"
					label="可约时间">
					<template #default="scope">
						{{scope.row.keyueshijian}}
					</template>
				</el-table-column>
				<el-table-column label="审核回复" min-width="140" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						{{scope.row.shhf}}
					</template>
				</el-table-column>
				<el-table-column prop="sfsh" label="审核状态" min-width="140" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-tag type="success" v-if="scope.row.sfsh=='是'">通过</el-tag>
						<el-tag type="danger" v-else-if="scope.row.sfsh=='否'">未通过</el-tag>
						<el-tag type="warning" v-else>待审核</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="审核" v-if="btnAuth('jiaoshi','审核')" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-button v-if="scope.row.sfsh!='是'" link @click="approvalClick(scope.row)">审核</el-button>
					</template>
				</el-table-column>
				<el-table-column label="操作" width="300" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('jiaoshi','查看')" @click="infoClick(scope.row.id)">
							<i class="iconfont icon-sousuo3"></i>
							详情
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id)" v-if=" btnAuth('jiaoshi','修改')">
							<i class="iconfont icon-xiugai2"></i>
							修改						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id)"  v-if="btnAuth('jiaoshi','删除')">
							<i class="iconfont icon-shanchu4"></i>
							删除						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('jiaoshi','家长预约')" type="success" @click="jiazhangyuyuejiaoshiCrossAddOrUpdateHandler(scope.row,'cross','否','','','')">
							<i class="iconfont icon-dingdan3"></i>
							家长预约
						</el-button>
						<el-button class="cross_btn" v-if="btnAuth('jiaoshi','学生预约')" type="success" @click="xueshengyuyuejiaoshiCrossAddOrUpdateHandler(scope.row,'cross','否','','','')">
							<i class="iconfont icon-dingdan3"></i>
							学生预约
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination 
				background
				:layout="layouts.join(',')"
				:total="total" 
				:page-size="listQuery.limit"
				prev-text="上一页"
				next-text="下一页"
				:hide-on-single-page="false"
				:style='{}'
				:page-sizes="[10, 20, 30, 40, 50, 100]"
				@size-change="sizeChange"
				@current-change="currentChange"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
		<Approval ref="approvalRef" :tableName="tableName" @shChange="shChange"></Approval>
		<jiazhangyuyuejiaoshiFormModel ref="jiazhangyuyuejiaoshiFormModelRef" @formModelChange="formModelChange"></jiazhangyuyuejiaoshiFormModel>
		<xueshengyuyuejiaoshiFormModel ref="xueshengyuyuejiaoshiFormModelRef" @formModelChange="formModelChange"></xueshengyuyuejiaoshiFormModel>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
		computed,
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = ref(store.state.user.avatar)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	import formModel from './formModel.vue'
	//基础信息

	const tableName = 'jiaoshi'
	const formName = '教师'
	const route = useRoute()
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 10,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			//table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
		if(searchQuery.value.jiaoshixingming&&searchQuery.value.jiaoshixingming!=''){
			params['jiaoshixingming'] = '%' + searchQuery.value.jiaoshixingming + '%'
		}
		if(searchQuery.value.sfsh && searchQuery.value.sfsh!=''){
			params['sfsh'] = searchQuery.value.sfsh
		}
		context?.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id) => {
		let ids = ref([])
		if (id) {
			ids.value = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.value.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`是否删除选中${formName}`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(() => {
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids.value
			}).then(res => {
				context?.$toolUtil.message('删除成功', 'success',()=>{
					getList()
				})
			})
		})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
    const shChange = async (type,row)=>{
        searchClick()
    }
	//搜索
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'edit')
			return
		}
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}
	
	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		window.open(context?.$config.url + file)
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	//审核
	import Approval from '@/components/common/approval.vue'
	const approvalRef = ref(null)
	const approvalClick = (row) => {
		let params = {
			id:row.id,
			jiaoshigonghao: row.jiaoshigonghao,
			mima: row.mima,
			jiaoshixingming: row.jiaoshixingming,
			touxiang: row.touxiang,
			lianxidianhua: row.lianxidianhua,
			xingbie: row.xingbie,
			zhuanye: row.zhuanye,
			sfsh: row.sfsh,
			shhf: row.shhf,
			suoshoukemu: row.suoshoukemu,
			keyueshijian: row.keyueshijian,
		}
		nextTick(() => {
			approvalRef.value.approvalClick(params )
		})
	}
	import jiazhangyuyuejiaoshiFormModel from '@/views/jiazhangyuyuejiaoshi/formModel'
	const jiazhangyuyuejiaoshiFormModelRef = ref(null)
    const jiazhangyuyuejiaoshiCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(crossOptAudit=='是'&&row.sfsh!='是') {
			context?.$toolUtil.message('请审核通过后再操作！','error')
			return
		}
        if(crossOptPay=='是'&&row.ispay!='已支付'){
            context.$toolUtil.message("请支付完成再操作",'success');
            return
        }
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			jiazhangyuyuejiaoshiFormModelRef.value.init(row.id,'cross','家长预约',row,'jiaoshi',statusColumnName,tips,statusColumnValue)
		})
    }
	import xueshengyuyuejiaoshiFormModel from '@/views/xueshengyuyuejiaoshi/formModel'
	const xueshengyuyuejiaoshiFormModelRef = ref(null)
    const xueshengyuyuejiaoshiCrossAddOrUpdateHandler = (row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue) => {
		if(crossOptAudit=='是'&&row.sfsh!='是') {
			context?.$toolUtil.message('请审核通过后再操作！','error')
			return
		}
        if(crossOptPay=='是'&&row.ispay!='已支付'){
            context.$toolUtil.message("请支付完成再操作",'success');
            return
        }
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			xueshengyuyuejiaoshiFormModelRef.value.init(row.id,'cross','学生预约',row,'jiaoshi',statusColumnName,tips,statusColumnValue)
		})
    }
	//查询审核状态列表
	const approvalLists = ref([])
	//初始化
	const init = () => {
        approvalLists.value = "是,否,待审核".split(',');
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
	
	// 操作盒子
	.list_search_view {
		// 搜索盒子
		.search_form {
			// 子盒子
			.search_view {
				// 搜索label
				.search_label {
				}
				// 搜索item
				.search_box {
					// 输入框
					:deep(.search_inp) {
					}
					// 下拉框
					:deep(.search_sel) {
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
			// 搜索按钮盒子
			.search_btn_view {
				// 搜索按钮
				.search_btn {
				}
				// 搜索按钮-悬浮
				.search_btn:hover {
				}
			}
		}
		//头部按钮盒子
		.btn_view {
			// 其他
			:deep(.el-button--default){
			}
			// 其他-悬浮
			:deep(.el-button--default:hover){
			}
			// 新增
			:deep(.el-button--success){
			}
			// 新增-悬浮
			:deep(.el-button--success:hover){
			}
			// 删除
			:deep(.el-button--danger){
			}
			// 删除-悬浮
			:deep(.el-button--danger:hover){
			}
			// 统计
			:deep(.el-button--warning){
			}
			// 统计-悬浮
			:deep(.el-button--warning:hover){
			}
		}
	}
	// 表格样式
	.el-table {
		:deep(.el-table__header-wrapper) {
			thead {
				tr {
					th {
						.cell {
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				tr {
					td {
						.cell {
							// 编辑
							.el-button--primary {
							}
							// 编辑-悬浮
							.el-button--primary:hover {
							}
							// 详情
							.el-button--info {
							}
							// 详情-悬浮
							.el-button--info:hover {
							}
							// 删除
							.el-button--danger {
							}
							// 删除-悬浮
							.el-button--danger:hover {
							}
							// 跨表
							.el-button--success {
							}
							// 跨表-悬浮
							.el-button--success:hover {
							}
							// 操作
							.el-button--warning {
							}
							// 操作-悬浮
							.el-button--warning:hover {
							}
						}
					}
				}
				tr:hover {
					td {
					}
				}
			}
		}
	}
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
		}
		// 上一页
		:deep(.btn-prev) {
		}
		// 下一页
		:deep(.btn-next) {
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
		}
		// 页码
		:deep(.el-pager) {
			// 数字
			.number {
			}
			// 数字悬浮
			.number:hover {
			}
			// 选中
			.number.is-active {
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {
			}
		}
	}
</style>
