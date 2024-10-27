<template>
  <div class="list-page" :style='{}'>
    <div class="bread_view">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
        <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="back_view" v-if="centerType">
      <el-button class="back_btn" @click="backClick" type="primary">Back</el-button>
    </div>
    <el-form :inline="true" :model="searchQuery" class="list_search">
      <div class="search_view">
        <div class="search_label">
          Parent Account:
        </div>
        <div class="search_box">
          <el-input class="search_inp" v-model="searchQuery.jiazhangzhanghao" placeholder="Parent Account"
                    clearable>
          </el-input>
        </div>
      </div>
      <div class="search_btn_view">
        <el-button class="search_btn" type="primary" @click="searchClick">Search</el-button>
        <el-button class="add_btn" type="success" v-if="btnAuth('jiazhangkongbaiyemian','Add')" @click="addClick">Add</el-button>
      </div>
    </el-form>
    <div class="page_list">
      <div class="data_box">
        <div class="data_view">
          <el-table v-loading="listLoading" class="data_table" :data="list" border :row-style="{'cursor':'pointer'}"
                    @row-click="tableDetailClick" :stripe='true'>
            <el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
            <el-table-column label="Index" width="120" :resizable='true' align="left" header-align="left">
              <template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
            </el-table-column>
            <el-table-column label="Parent Account" :resizable='true' align="left" header-align="left">
              <template #default="scope">
                {{scope.row.jiazhangzhanghao}}
              </template>
            </el-table-column>
          </el-table>
        </div>
        <el-pagination
            background
            :layout="layouts.join(',')"
            :total="total"
            :page-size="listQuery.limit"
            prev-text="Previous"
            next-text="Next"
            :hide-on-single-page="false"
            :style='{}'
            @size-change="sizeChange"
            @current-change="currentChange"
            @prev-click="prevClick"
            @next-click="nextClick"  />
      </div>
    </div>
    <el-dialog v-model="preViewVisible" :title="'View Large Image'" width="60%" destroy-on-close>
      <img :src="preViewUrl" style="width: 100%;" alt="">
    </el-dialog>
  </div>
</template>

<script setup>
import {
  ref,
  getCurrentInstance,
  nextTick,
  computed,
} from 'vue';
import {
  useRoute,
  useRouter
} from 'vue-router';
import {
  useStore
} from 'vuex';
const store = useStore()
const user = computed(()=>store.getters['user/session'])
const context = getCurrentInstance()?.appContext.config.globalProperties;
const router = useRouter()
const route = useRoute()
// Base information
const tableName = 'jiazhangkongbaiyemian'
const formName = 'Parent Blank Page'
// Base information
const breadList = ref([{
  name: formName
}])
const list = ref([])
const listQuery = ref({
  page: 1,
  limit: 20,
})
const total = ref(0)
const listLoading = ref(false)
// Permission verification
const btnAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isAuth(e,a)
  }
}
const addClick = () => {
  router.push('/index/jiazhangkongbaiyemianAdd')
}
// Determine if it is from the personal center
const centerType = ref(false)
// Return
const backClick = () => {
  router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
}
// Search
const searchQuery = ref({})
// Dropdown list
const searchClick = () => {
  listQuery.value.page = 1
  getList()
}
// Pagination
const layouts = ref(["total","prev","pager","next","sizes","jumper"])
const sizeChange = (size) => {
  listQuery.value.limit = size
  getList()
}
const currentChange = (page) => {
  listQuery.value.page = page
  getList()
}
const prevClick = () => {
  listQuery.value.page = listQuery.value.page - 1
  getList()
}
const nextClick = () => {
  listQuery.value.page = listQuery.value.page + 1
  getList()
}
// Pagination
// List
const getList = () => {
  listLoading.value = true
  let params = JSON.parse(JSON.stringify(listQuery.value))
  if(searchQuery.value.jiazhangzhanghao&&searchQuery.value.jiazhangzhanghao!=''){
    params.jiazhangzhanghao = '%' + searchQuery.value.jiazhangzhanghao + '%'
  }
  context?.$http({
    url: `${tableName}/${centerType.value?'page':'list'}`,
    method: 'get',
    params: params
  }).then(res => {
    listLoading.value = false
    list.value = res.data.data.list
    total.value = Number(res.data.data.total)
  })
}
const tableDetailClick = (row) => {
  router.push(`${tableName}Detail?id=` + row.id + (centerType.value?'&&centerType=1':''))
}
// Download file
const download = (file) =>{
  if(!file){
    context?.$toolUtil.message('File does not exist','error')
  }
  const a = document.createElement('a');
  a.style.display = 'none';
  a.setAttribute('target', '_blank');
  file && a.setAttribute('download', file);
  a.href = context?.$config.url + file;
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
}
// View large image
const preViewUrl = ref('')
const preViewVisible = ref(false)
const preViewClick = (url) =>{
  preViewUrl.value = url
  preViewVisible.value = true
}
const init = () => {
  if(route.query.centerType){
    centerType.value = true
  }
  getList()
}
init()
</script>

<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 4px;
		padding: 10px 0px;
		margin: 10px auto;
		background: none;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid #0076ca50;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			color: #0076ca;
			background: #fff;
			width: auto;
			font-size: 14px;
			height: 34px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
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
	// 分类盒子
	.category_view {
		// 分类item
		.category {
		}
		// item-悬浮
		.category:hover {
		}
		// item-选中
		.categoryActive {
		}
	}

	//搜索
	.list_search {
		.search_view {
			.search_label {
			}
			.search_box {
				// 输入框
				:deep(.search_inp) {
					.is-focus {
						box-shadow: none !important;
					}
				}
			}
		}
		.search_btn_view {
			// 搜索按钮
			.search_btn {
			}
			// 搜索按钮-悬浮
			.search_btn:hover {
			}
			// 新增按钮
			.add_btn {
			}
			// 新增按钮-悬浮
			.add_btn:hover {
			}
		}
	}

	// 数据盒子
	.page_list {
		//列表
		.data_box {
			.data_view {
				// 表格样式
				.el-table {
					padding: 0;
					background: #fff;
					width: 100%;
					border-color: #eee;
					border-width: 1px 0 0 1px;
					border-style: solid;
					:deep(.el-table__header-wrapper) {
						thead {
							width: 100%;
							tr {
								background: #fff;
								th {
									padding: 10px 0;
									border-color: #eee;
									border-width: 0 1px 1px 0;
									border-style: solid;
									text-align: left;
									.cell {
										padding: 0 10px;
										word-wrap: normal;
										color: #666;
										white-space: normal;
										font-weight: bold;
										display: inline-block;
										vertical-align: middle;
										font-size: 15px;
										line-height: 24px;
										text-overflow: ellipsis;
										word-break: break-all;
										width: 100%;
										position: relative;
										//未选中样式
										.el-checkbox {
											//复选框
											.el-checkbox__inner {
												background: #fff;
												border-color: #999;
											}
										}
										//选中样式
										.is-checked {
											//复选框
											.el-checkbox__inner {
												background: #55ffff;
												border-color: #55ffff;
											}
										}
									}
								}
							}
						}
					}
					:deep(.el-table__body-wrapper) {
						tbody {
							width: 100%;
							tr {
								background: #fff;
								td {
									padding: 10px 0 0;
									color: #666;
									background: #fff;
									border-color: #eee;
									border-width: 0 1px 1px 0;
									border-style: solid;
									text-align: left;
									.cell {
										padding: 0 10px;
										overflow: hidden;
										word-break: break-all;
										white-space: normal;
										line-height: 24px;
										text-overflow: ellipsis;
										//未选中样式
										.el-checkbox {
											//复选框
											.el-checkbox__inner {
												background: #fff;
												border-color: #999;
											}
										}
										//选中样式
										.is-checked {
											//复选框
											.el-checkbox__inner {
												background: #55ffff;
												border-color: #55ffff;
											}
										}
									}
								}
							}
							tr.el-table__row--striped {
								td {
									background: #FAFAFA !important;
								}
							}
							tr:hover {
								td {
									padding: 10px 0 0;
									color: #333;
									background: #f6f6f6;
									border-color: #eee;
									border-width: 0 1px 1px 0;
									border-style: solid;
									text-align: left;
								}
							}
						}
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
			.el-select {
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
						.is-focus {
							box-shadow: none !important;
						}
					}
				}
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
	}
	
	// 热门信息盒子
	.hot_view {
		// 标题
		.hot_title {
		}

		.hot_list {
			// item
			.hot {
				//图片盒子
				.hot_img_view {
					// 图片
					.hot_img {
					}
				}
				// 内容盒子
				.hot_content {
					// 名称
					.hot_text {
					}
				}
			}
		}
	}
</style>