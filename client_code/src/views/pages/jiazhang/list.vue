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
        <el-button class="add_btn" type="success" v-if="btnAuth('jiazhang','Add')" @click="addClick">Add</el-button>
      </div>
    </el-form>
    <div class="page_list">
      <div class="data_box">
        <div class="data_view">
          <div class="data_item" v-for="(item,index) in list" :key="index" @click.stop="detailClick(item.id)" >
            <div class="data_content">
            </div>
          </div>
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
// Basic Information
const tableName = 'jiazhang'
const formName = 'Parent'
// Basic Information
const breadList = ref([{
  name: formName
}])
const list = ref([])
const listQuery = ref({
  page: 1,
  limit: Number(12)
})
const total = ref(0)
const listLoading = ref(false)
// Permission Validation
const btnAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isAuth(e,a)
  }
}
const addClick = () => {
  router.push('/index/jiazhangAdd')
}
// Determine if jumping from Personal Center
const centerType = ref(false)
// Back
const backClick = () => {
  router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
}
// Search
const searchQuery = ref({})
// Dropdown List
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
const detailClick = (id) => {
  router.push(`${tableName}Detail?id=` + id + (centerType.value?'&&centerType=1':''))
}
// Download File
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
// View Large Image
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
				.data_item:nth-of-type(2n - 1) {
					// 图片盒子
					.data_img_box {
						width: 100%;
						// 图片
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// 内容盒子
					.data_content {
						// 价格
						.data_price {
							color: #f00;
						}
						// 标题1
						.data_title1 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题2
						.data_title2 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题3
						.data_title3 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题4
						.data_title4 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题5
						.data_title5 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
					}
				}
				.data_item:nth-of-type(2n) {
					// 图片盒子
					.data_img_box {
						width: 100%;
						// 图片
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// 内容盒子
					.data_content {
						// 价格
						.data_price {
							color: #f00;
						}
						// 标题1
						.data_title1 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题2
						.data_title2 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题3
						.data_title3 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题4
						.data_title4 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// 标题5
						.data_title5 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
					}
				}
				.data_item:nth-of-type(2n - 1):hover {
					// 图片盒子
					.data_img_box {
						// 图片
						.data_img {
						}
					}
					// 内容盒子
					.data_content {
						// 价格
						.data_price {
						}
						// 标题1
						.data_title1 {
						}
						// 标题2
						.data_title2 {
						}
						// 标题3
						.data_title3 {
						}
						// 标题4
						.data_title4 {
						}
						// 标题5
						.data_title5 {
						}
					}
				}
				.data_item:nth-of-type(2n):hover {
					// 图片盒子
					.data_img_box {
						// 图片
						.data_img {
						}
					}
					// 内容盒子
					.data_content {
						// 价格
						.data_price {
						}
						// 标题1
						.data_title1 {
						}
						// 标题2
						.data_title2 {
						}
						// 标题3
						.data_title3 {
						}
						// 标题4
						.data_title4 {
						}
						// 标题5
						.data_title5 {
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