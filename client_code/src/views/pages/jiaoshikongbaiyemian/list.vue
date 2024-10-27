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
          Teacher ID:
        </div>
        <div class="search_box">
          <el-input class="search_inp" v-model="searchQuery.jiaoshigonghao" placeholder="Teacher ID"
                    clearable>
          </el-input>
        </div>
      </div>
      <div class="search_btn_view">
        <el-button class="search_btn" type="primary" @click="searchClick">Search</el-button>
        <el-button class="add_btn" type="success" v-if="btnAuth('jiaoshikongbaiyemian','新增')" @click="addClick">Add</el-button>
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
            prev-text="Previous Page"
            next-text="Next Page"
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
// Basic information
const tableName = 'jiaoshikongbaiyemian'
const formName = 'Teacher Blank Page'
// Basic information
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
// Permission verification
const btnAuth = (e,a)=>{
  if(centerType.value){
    return context?.$toolUtil.isBackAuth(e,a)
  }else{
    return context?.$toolUtil.isAuth(e,a)
  }
}
const addClick = () => {
  router.push('/index/jiaoshikongbaiyemianAdd')
}
// Determine whether to jump from personal center
const centerType = ref(false)
// Back
const backClick = () => {
  router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
}
// Search
const searchQuery = ref({})
// Drop-down list
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
  if(searchQuery.value.jiaoshigonghao&&searchQuery.value.jiaoshigonghao!=''){
    params.jiaoshigonghao = '%' + searchQuery.value.jiaoshigonghao + '%'
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
// Back box
.back_view {
  border-radius: 4px;
  padding: 10px 0px;
  margin: 10px auto;
  background: none;
  width: 100%;
  text-align: right;
  // Back button
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
  // Back button - hover
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
// Category box
.category_view {
  // Category item
  .category {
  }
  // Item - hover
  .category:hover {
  }
  // Item - selected
  .categoryActive {
  }
}

// Search
.list_search {
  .search_view {
    .search_label {
    }
    .search_box {
      // Input box
      :deep(.search_inp) {
        .is-focus {
          box-shadow: none !important;
        }
      }
    }
  }
  .search_btn_view {
    // Search button
    .search_btn {
    }
    // Search button - hover
    .search_btn:hover {
    }
    // Add button
    .add_btn {
    }
    // Add button - hover
    .add_btn:hover {
    }
  }
}

// Data box
.page_list {
  // List
  .data_box {
    .data_view {
      .data_item:nth-of-type(2n - 1) {
        // Image box
        .data_img_box {
          width: 100%;
          // Image
          .data_img {
            object-fit: cover;
            width: 100%;
            height: 100%;
          }
        }
        // Content box
        .data_content {
          // Price
          .data_price {
            color: #f00;
          }
          // Title 1
          .data_title1 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 2
          .data_title2 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 3
          .data_title3 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 4
          .data_title4 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 5
          .data_title5 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
        }
      }
      .data_item:nth-of-type(2n) {
        // Image box
        .data_img_box {
          width: 100%;
          // Image
          .data_img {
            object-fit: cover;
            width: 100%;
            height: 100%;
          }
        }
        // Content box
        .data_content {
          // Price
          .data_price {
            color: #f00;
          }
          // Title 1
          .data_title1 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 2
          .data_title2 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 3
          .data_title3 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 4
          .data_title4 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
          // Title 5
          .data_title5 {
            margin: 0 0 10px;
            width: 100%;
            text-align: center;
          }
        }
      }
      .data_item:nth-of-type(2n - 1):hover {
        // Image box
        .data_img_box {
          // Image
          .data_img {
          }
        }
        // Content box
        .data_content {
          // Price
          .data_price {
          }
          // Title 1
          .data_title1 {
          }
          // Title 2
          .data_title2 {
          }
          // Title 3
          .data_title3 {
          }
          // Title 4
          .data_title4 {
          }
          // Title 5
          .data_title5 {
          }
        }
      }
      .data_item:nth-of-type(2n):hover {
        // Image box
        .data_img_box {
          // Image
          .data_img {
          }
        }
        // Content box
        .data_content {
          // Price
          .data_price {
          }
          // Title 1
          .data_title1 {
          }
          // Title 2
          .data_title2 {
          }
          // Title 3
          .data_title3 {
          }
          // Title 4
          .data_title4 {
          }
          // Title 5
          .data_title5 {
          }
        }
      }
    }
  }
}

// Pagination
.el-pagination {
  // Total pages
  :deep(.el-pagination__total) {
  }
  // Previous page
  :deep(.btn-prev) {
  }
  // Next page
  :deep(.btn-next) {
  }
  // Previous page disabled
  :deep(.btn-prev:disabled) {
  }
  // Next page disabled
  :deep(.btn-next:disabled) {
  }
  // Page number
  :deep(.el-pager) {
    // Number
    .number {
    }
    // Number hover
    .number:hover {
    }
    // Selected
    .number.is-active {
    }
  }
  // Sizes
  :deep(.el-pagination__sizes) {
    .el-select {
      // Remove default style
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
  // Jump page
  :deep(.el-pagination__jump) {
    // Input box
    .el-input {
      .is-focus {
        box-shadow: none !important;
      }
    }
  }
}

// Hot information box
.hot_view {
  // Title
  .hot_title {
  }

  .hot_list {
    // Item
    .hot {
      // Image box
      .hot_img_view {
        // Image
        .hot_img {
        }
      }
      // Content box
      .hot_content {
        // Name
        .hot_text {
        }
      }
    }
  }
}
</style>
