<template>
  <div class="forum_view" :style='{}'>
    <div class="back_view" v-if="centerType">
      <el-button class="back_btn" @click="backClick" type="primary">Back</el-button>
    </div>
    <div class="section_title">
      <span>{{myType ? 'My Posts' : formName}}</span>
    </div>
    <el-form :model="searchQuery" class="list_search">
      <div class="search_view">
        <div class="search_label">
          Title:
        </div>
        <div class="search_box">
          <el-input class="search_inp" v-model="searchQuery.title" placeholder="Title" size="small" clearable>
          </el-input>
        </div>
      </div>
      <div class="search_btn_view">
        <el-button class="search_btn" type="primary" @click="searchClick">Search</el-button>
        <el-button class="add_btn" @click="addClick" v-if="!myType && btnAuth('forum', 'Add')" type="success">Post New</el-button>
      </div>
    </el-form>
    <div class="forum_list">
      <div class="forum_item" v-for="(item, index) in list" :key="index" @mouseenter="forumEnter(index)"
           @mouseleave="forumLeave" @click.stop="detailClick(item.id)">
        <div class="forum_item_left">
          <span class="forum_item_title">{{item.title}}</span>
          <span class="forum_item_name">(Posted by: {{item.username}})</span>
        </div>
        <div class="forum_item_btn_box" v-if="item.userid == userid && forumShowIndex == index && (btnAuth('forum', 'Edit') || btnAuth('forum', 'Delete'))">
          <el-button class="forum_edit_btn" v-if="btnAuth('forum', 'Edit')" type="primary" @click.stop="editClick(item.id)">Edit</el-button>
          <el-button class="forum_del_btn" v-if="btnAuth('forum', 'Delete')" type="danger" @click.stop="delClick(item.id)">Delete</el-button>
        </div>
        <div class="forum_time" v-else>{{item.addtime}}</div>
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
          @next-click="nextClick" />
    </div>

    <el-dialog v-model="formVisible" :title="formTitle" width="70%" custom-class="edit_view" destroy-on-close>
      <el-form class="add_form" :model="form" label-width="120px" :rules="rules" ref="formRef">
        <el-row>
          <el-col :span="24">
            <el-form-item label="Title" prop="title">
              <el-input class="list_inp" v-model="form.title" placeholder="Enter Title"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="Type">
              <el-radio-group class="list_radio" v-model="form.isdone">
                <el-radio label="Open">Public</el-radio>
                <el-radio label="Close">Private</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="Content" prop="content">
              <editor :value="form.content" placeholder="Enter Content"
                      class="list_editor" @change="contentChange"></editor>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
				<span class="formModel_btn_box">
					<el-button class="formModel_cancel" @click="formVisible=false">Close</el-button>
					<el-button class="formModel_confirm" type="primary" @click="save">{{form.id ? 'Save Changes' : 'Post'}}</el-button>
				</span>
      </template>
    </el-dialog>
    <el-dialog v-model="detailVisible" :title="''" width="70%" destroy-on-close>
      <div class="forum_detail">
        <div class="forum_title">
          <span>{{detailForm.title}}</span>
        </div>
        <div class="forum_info">
          <span class="forum_name">Posted by: {{detailForm.username}}</span>
          <span class="forum_time">Post Time: {{detailForm.addtime}}</span>
        </div>
        <el-divider />
        <div class="forum_content" v-html="detailForm.content"></div>
      </div>
      <el-card class="forum_comment_box">
        <el-divider class="comment_divider" content-position="left">Comment List</el-divider>
        <div class="comment_add_box">
          <el-button class="comment_add" @click="commentClick" type="success">Post Comment</el-button>
        </div>
        <div class="forum_comment_list">
          <div class="forum_comment" v-for="(item, index) in detailForm.childs" :key="index"
               @mouseenter="commentEnter(index)" @mouseleave="commentLeave">
            <div class="forum_comment_user">
              <img v-if="item.avatarurl" class="forum_comment_user_avatar"
                   :src="$config.url + item.avatarurl.split(',')[0]" alt="">
              <img v-else class="forum_comment_user_avatar" src="@/assets/avatar.png" alt="">
              <span class="forum_comment_username">User: {{item.username}}</span>
            </div>
            <div class="forum_comment_content" v-html="item.content"></div>
            <div class="forum_comment_reply_view">
              <div class="forum_comment_del" v-if="commentShowIndex == index && item.userid == userid"
                   @click="commentDelClick(item.id)">Delete</div>
              <div class="forum_comment_reply" v-if="commentShowIndex == index"
                   @click="commentReplyClick(item.id)">Reply</div>
            </div>
            <div class="forum_comment_second_list" v-if="item.childs">
              <el-divider class="forum_comment_second_divider" content-position="left">Replies</el-divider>
              <div class="forum_comment_second" v-for="(items, indexs) in item.childs" :key="indexs"
                   @mouseenter="commentEnter1(indexs)" @mouseleave="commentLeave1">
                <div class="forum_comment_second_user">
                  <img v-if="items.avatarurl" class="forum_comment_second_user_avatar"
                       :src="$config.url + items.avatarurl.split(',')[0]" alt="">
                  <img v-else class="forum_comment_second_user_avatar" src="@/assets/avatar.png" alt="">
                  <span class="forum_comment_second_username">User: {{items.username}}</span>
                </div>
                <div class="forum_comment_second_content" v-html="items.content"></div>
                <div class="forum_comment_second_reply_view">
                  <div class="forum_comment_second_del"
                       v-if="commentShowIndex == index && items.userid == userid && commentShowIndex1 == indexs"
                       @click="commentDelClick(items.id)">Delete</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-card>
    </el-dialog>
    <el-dialog v-model="commentVisible" :title="'Post Comment'" width="50%" destroy-on-close>
      <el-form class="add_form" :model="commentForm" label-width="120px">
        <el-row>
          <el-col :span="24">
            <el-form-item prop="content" label="Content">
              <editor :value="commentForm.content" placeholder="Enter Comment"
                      class="list_editor" @change="commentChange"></editor>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <template #footer>
				<span class="formModel_btn_box">
					<el-button class="formModel_cancel" @click="commentVisible=false">Close</el-button>
					<el-button class="formModel_confirm" type="primary" @click="commentSave">Submit</el-button>
				</span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {
  ref,
  nextTick,
  getCurrentInstance
} from 'vue';
import {
  ElMessageBox
} from 'element-plus'
import {
  useRoute,
  useRouter
} from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties;
// Basic information
const tableName = 'forum'
const formName = 'Forum Discussion'
const router = useRouter()
const route = useRoute()
// Permission validation
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
const list = ref([])
const listLoading = ref(false)
const listQuery = ref({
  page: 1,
  limit: 20,
  isdone: 'Open',
  sort: 'addtime,is_top,top_time',
  order: 'desc,desc,desc'
})
const myListQuery = ref({
  page: 1,
  limit: 20,
  parentid: 0,
  sort: 'addtime',
  order: 'desc'
})
const searchQuery = ref({})
// Pagination
const layouts = ref(["total","prev","pager","next","sizes","jumper"])
const total = ref(0)
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
const searchClick = () => {
  listQuery.value.page = 1
  getList()
}
const getList = () => {
  listLoading.value = true
  let params = myType.value ? JSON.parse(JSON.stringify(myListQuery.value)) : JSON.parse(JSON.stringify(listQuery
      .value))
  if (searchQuery.value.title && searchQuery.value.title != '') {
    params['title'] = `%${searchQuery.value.title}%`
  }
  context?.$http({
    url: `forum/${myType.value?'page':'flist'}`,
    method: 'get',
    params: params
  }).then(res => {
    listLoading.value = false
    list.value = res.data.data.list
    total.value = res.data.data.total
  })
}
const userid = ref(0)
const form = ref({
  title: '',
  isdone: 'Open',
  content: '',
  parentid: 0,
  userid: userid.value,
  username: context?.$toolUtil.storageGet('frontName')
})
const rules = ref({
  title: [{
    required: true,
    message: 'Please enter the title',
    trigger: 'blur'
  }, ],
  content: [{
    required: true,
    message: 'Please enter the content',
    trigger: 'blur'
  }, ],
})
const formRef = ref(null)
const formVisible = ref(false)
const formTitle = ref('')
const forumShowIndex = ref(-1)
// Determine button display
const forumEnter = (index) => {
  forumShowIndex.value = index
}
const forumLeave = () => {
  forumShowIndex.value = -1
}
const resetForm = () => {
  form.value = {
    title: '',
    isdone: 'Open',
    content: '',
    parentid: 0,
    userid: userid.value,
    username: context?.$toolUtil.storageGet('frontName')
  }
}
// Publish new post
const addClick = () => {
  resetForm()
  formTitle.value = 'Post New'
  formVisible.value = true
}
// Edit post
const editClick = (id = null) => {
  if (id) {
    context?.$http({
      url: `forum/detail/${id}`,
      method: 'get'
    }).then(res => {
      form.value = res.data.data
      formTitle.value = 'Edit Post'
      formVisible.value = true
    })
  }
}
// Post content callback
const contentChange = (e)=>{
  form.value.content = e
}
// Check if navigating to "My Posts"
const myType = ref(false)
// Check if redirected from profile center
const centerType = ref(false)
// Go back
const backClick = () => {
  router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
}
// Initialize
const init = () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  if (route.query.myType) {
    myType.value = true
  }
  userid.value = context?.$toolUtil.storageGet('userid')
  getList()
}
const detailVisible = ref(false)
const detailForm = ref({})
// View details
const detailClick = (id = null) => {
  if (id) {
    context?.$http({
      url: `forum/list/${id}`,
      method: 'get'
    }).then(res => {
      detailForm.value = res.data.data
      detailVisible.value = true
    })
  }
}
// Delete post
const delClick = (id = null) => {
  if (id) {
    ElMessageBox.confirm(`Do you want to delete this post?`, 'Notice', {
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
      type: 'warning',
    }).then(() => {
      context?.$http({
        url: `forum/delete`,
        method: 'post',
        data: [id]
      }).then(res => {
        context?.$toolUtil.message('Deleted successfully', 'success', () => {
          getList()
        })
      })
    })
  }
}
// Publish post
const save = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      context?.$http({
        url: `forum/${form.value.id?'update':'add'}`,
        method: 'post',
        data: form.value
      }).then(res => {
        context?.$toolUtil.message(`${form.value.id?'Edited':'Posted'} successfully`, 'success', () => {
          getList()
          formVisible.value = false
        })
      })
    }
  })
}
// Comment
const commentForm = ref({
  content: '',
  parentid: '',
  userid: '',
  username: '',
  avatarurl: '',
})
const commentVisible = ref(false)
// Reset comment
const resetCommentForm = () => {
  commentForm.value = {
    content: '',
    parentid: '',
    userid: context?.$toolUtil.storageGet('userid'),
    username: context?.$toolUtil.storageGet('frontName'),
    avatarurl: context?.$toolUtil.storageGet('headportrait') ? context?.$toolUtil.storageGet('headportrait') : '',
  }
}
// Add comment
const commentClick = () => {
  resetCommentForm()
  commentForm.value.parentid = detailForm.value.id
  commentVisible.value = true
}
// Show reply button
const commentShowIndex = ref(-1)
const commentShowIndex1 = ref(-1)
const commentEnter = (index) => {
  commentShowIndex.value = index
}
const commentLeave = () => {
  commentShowIndex.value = -1
}
const commentEnter1 = (index) => {
  commentShowIndex1.value = index
}
const commentLeave1 = () => {
  commentShowIndex1.value = -1
}
// Comment content callback
const commentChange = (e)=>{
  commentForm.value.content = e
}
// Save comment
const commentSave = () => {
  if(commentForm.value.content==''){
    context?.$toolUtil.message(`Please enter content`, 'error')
    return false
  }
  let sensitiveWords = "";
  let sensitiveWordsArr = [];
  if(sensitiveWords) {
    sensitiveWordsArr = sensitiveWords.split(",");
  }
  for(var i=0; i<sensitiveWordsArr.length; i++){
    // Global replacement
    var reg = new RegExp(sensitiveWordsArr[i],"g");
    // Check if the content includes sensitive words
    if (commentForm.value.content.indexOf(sensitiveWordsArr[i]) > -1) {
      // Replace sensitive words with **
      commentForm.value.content = commentForm.value.content.replace(reg,"**");
    }
  }
  context?.$http({
    url: 'forum/add',
    method: 'post',
    data: commentForm.value
  }).then(res => {
    context?.$toolUtil.message(`Posted successfully`, 'success', () => {
      detailClick(detailForm.value.id)
      commentVisible.value = false
    })
  })
}
// Delete comment
const commentDelClick = (id = null) => {
  if (id) {
    ElMessageBox.confirm(`Do you want to delete this comment?`, 'Notice', {
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
      type: 'warning',
    }).then(() => {
      context?.$http({
        url: `forum/delete`,
        method: 'post',
        data: [id]
      }).then(res => {
        context?.$toolUtil.message('Deleted successfully', 'success', () => {
          detailClick(detailForm.value.id)
        })
      })
    })
  }
}
// Reply to comment
const commentReplyClick = (id = null) => {
  if (id) {
    resetCommentForm()
    commentForm.value.parentid = id
    commentVisible.value = true
  }
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
// Search
.list_search {
  .search_view {
    .search_label {
    }
    .search_box {
      // Input box
      :deep(.search_inp) {

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
// List box
.forum_list {
  // Item
  .forum_item {
    // Item left part
    .forum_item_left {
      // Title
      .forum_item_title {
      }
      // Publisher
      .forum_item_name {
      }
    }
    // Button box
    .forum_item_btn_box {
      // Edit button
      .forum_edit_btn {
      }
      // Edit button - hover
      .forum_edit_btn:hover {
      }
      // Delete button
      .forum_del_btn {
      }
      // Delete button - hover
      .forum_del_btn:hover {
      }
    }
    // Time
    .forum_time {
    }
  }

  .forum_item:hover {
  }
}

// Post details
.forum_detail {

  .forum_title {
  }

  .forum_info {
    .forum_name {
    }
    .forum_time {
    }
  }

  .forum_content {
  }
}
// Comment box
.forum_comment_box {
  // Divider
  :deep(.comment_divider) {
    border: none;
    background: none;
    // Divider text style
    .el-divider__text {
      font-size: 16px;
    }
  }
  // Add comment box
  .comment_add_box {
    margin: 0 0 20px;
    text-align: right;
    // Button
    .comment_add {
    }
    // Button - hover
    .comment_add:hover {
    }
  }
  // Post comments
  .forum_comment_list {
    // Comment item
    .forum_comment {
      // Comment user box
      .forum_comment_user {
        // User avatar
        .forum_comment_user_avatar {
        }
        // Username
        .forum_comment_username {
        }
      }
      // Comment content
      .forum_comment_content {
      }
      // Reply button box
      .forum_comment_reply_view {
        // Delete button
        .forum_comment_del {
        }
        // Delete button - hover
        .forum_comment_del:hover {
        }
        // Reply button
        .forum_comment_reply {
        }
        // Reply button - hover
        .forum_comment_reply:hover {
        }
      }
      // Second-level comment box
      .forum_comment_second_list {
        // Divider
        :deep(.forum_comment_second_divider) {
          border: none;
          background: #dcdfe6;
          // Divider text style
          .el-divider__text {
            font-size: 16px;
          }
        }
        // Second-level comment item
        .forum_comment_second {
          // User box
          .forum_comment_second_user {
            // User avatar
            .forum_comment_second_user_avatar {
            }
            // Username
            .forum_comment_second_username {
            }
          }
          // Second-level content
          .forum_comment_second_content {
          }
          // Second-level button box
          .forum_comment_second_reply_view {
            // Second-level delete
            .forum_comment_second_del {
            }
            // Second-level delete - hover
            .forum_comment_second_del:hover {
            }
          }
        }
      }
    }
  }
}

// Pagination
.el-pagination {
  // Total page number
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

        }
      }
    }
  }
  // Jump page
  :deep(.el-pagination__jump) {
    // Input box
    .el-input {

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
      // Rich text
      .list_editor {
      }
      // Radio button style
      .list_radio {
        // Unselected style
        .el-radio {
          // Radio button
          .el-radio__inner {
          }
          // Hint text
          .el-radio__label{
          }
        }
        // Selected style
        .is-checked {
          // Radio button
          .el-radio__inner {
          }
          // Hint text
          .el-radio__label{
          }
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
/* Total box */
.forum_view {
  width: 1200px;
  margin: 20px auto;
  padding: 0px ;
  background: #fff;
  overflow: hidden;
}
.forum_view .title{
  width: 100%;
  margin: 20px 0 0;
  font-size: 22px;
  color: #7282a4;
  font-weight:600;
  text-align: center;
  padding:10px 0;
  background:#fff;
  border: 1px solid #7282a450;
  box-shadow: inset 0px 0px 30px 0px #7282a420;
}
/* Search box */
.forum_view .list_search{
  width: 100%;
  margin: 30px auto;
  display:flex;
  align-items:center;
  justify-content:center;
}
.forum_view .list_search .search_view{
  display:flex;
  align-items:center;
  margin-right:10px;
}
.forum_view .list_search .search_view .search_label{
  font-size:16px;
  white-space: nowrap;
}
.forum_view .list_search .search_view .search_box{
}
.forum_view .list_search .search_view .search_box .search_inp{
  padding:0 10px;
  border:1px solid #ddd;
  font-size:15px;
  height:38px;
  border-radius:4px;
  min-width:300px;
}
.forum_view .list_search .search_btn_view .search_btn{
  background: #7282a4;
  color: rgb(255, 255, 255);
  border:0;
  border-radius:4px;
  height:38px;
  font-size:15px;
  min-width:100px;
}
.forum_view .list_search .search_btn_view .search_btn:hover{
}
.forum_view .list_search .search_btn_view .add_btn{
  background: #666;
  color: rgb(255, 255, 255);
  border:0;
  border-radius:4px;
  height:38px;
  font-size:15px;
}
.forum_view .list_search .search_btn_view .add_btn:hover{
}

/* List box */
.forum_view .forum_list{
  width: 100%;
  margin: 20px auto;
  padding: 0px;
  display:flex;
  flex-wrap:wrap;
  align-items:center;
  justify-content: space-between;
}
/* Item */
.forum_view .forum_list .forum_item{
  width: 49%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px 16px;
  cursor: pointer;
  color: rgb(102, 102, 102);
  height: 60px;
  border-image: initial;
  font-size: 16px;
  border: 1px solid #eee;
  border-radius: 4px;
  margin: 0px 0px 20px;
}
.forum_view .forum_list .forum_item:last-child{
}
.forum_view .forum_list .forum_item:hover{
  background:rgba(0, 0, 0, .5);
}
/* Left content box */
.forum_view .forum_list .forum_item .forum_item_left{
  display: flex;
  align-items: center;
}
/* Title */
.forum_view .forum_list .forum_item .forum_item_left .forum_item_title{
  font-size: 16px;
  color: #333;
  font-weight: 600;
}
.forum_view .forum_list .forum_item:hover .forum_item_left .forum_item_title{
  color: #fff;
}

/* Publisher */
.forum_view .forum_list .forum_item .forum_item_left .forum_item_name{
  color: rgb(153, 153, 153);
}
.forum_view .forum_list .forum_item:hover .forum_item_left .forum_item_name{
  color: #fff;
}
/* Time */
.forum_view .forum_list .forum_item .forum_time{
  font-size: 15px;
  color: rgb(153, 153, 153);
}
/* Button box */
.forum_view .forum_list .forum_item .forum_item_btn_box{
  width:auto;
}
/* Edit */
.forum_view .forum_list .forum_item .forum_item_btn_box .forum_edit_btn{
  margin: 0px 10px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 34px;
  font-size: 14px;
  color: rgb(255, 255, 255);
  border-radius: 30px;
  border: 0px;
  background: #6393eb;
  cursor: pointer;
}
/* Delete */
.forum_view .forum_list .forum_item .forum_item_btn_box .forum_del_btn{
  margin: 0px 10px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 34px;
  font-size: 14px;
  color: rgb(255, 255, 255);
  border-radius: 30px;
  border: 0px;
  background: #eb6363;
  cursor: pointer;
}

/* Detail box */
.forum_view .forum_detail{
  width: 100%;
  margin: 20px 0px;
}
/* Title */
.forum_view .forum_detail .forum_title{
  width: 100%;
  text-align: center;
  font-size: 22px;
  font-weight: bold;
  color: rgb(0, 0, 0);
}
/* Info box */
.forum_view .forum_detail .forum_info{
  width: 100%;
  text-align: center;
  color: rgb(153, 153, 153);
  font-size: 16px;
  margin: 20px 0px 10px;
}
/* Publisher */
.forum_view .forum_detail .forum_info .forum_name{
  margin: 0px 10px 0px 0px;
}
/* Time */
.forum_view .forum_detail .forum_info .forum_time{
  margin: 0px 10px 0px 0px;
}
/* Divider */
.forum_view .forum_detail .el-divider--horizontal{
  display: block;
  height: 0px;
  width: 100%;
  margin: 24px 0;
}
/* Content area */
.forum_view .forum_detail .forum_content{
  width: 100%;
  text-align: left;
  font-size: 16px;
  color: rgb(102, 102, 102);
  text-indent: 2rem;
}

/* First-level comment box */
.forum_view .forum_comment_box{
  margin: 40px 0px 0px;
  width: 100%;
  padding: 0px;
  border: 0px solid rgb(238, 238, 238);
  box-shadow: none;
  border-radius: 0px;
}
.forum_view .forum_comment_box .el-card__body{
  padding: 0px;
}
/* List box */
.forum_view .forum_comment_box .forum_comment_list{
  width: 100%;
  text-align: left;
}
/* Item */
.forum_view .forum_comment_box .forum_comment_list .forum_comment{
  width: 100%;
  border-radius: 4px;
  margin: 0px 0px 40px;
  border: 1px solid #eee;
}
/* User box */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_user{
  display: flex;
  align-items: center;
  justify-content:center;
  background: #7282a410;
  padding: 10px 10px;
  border-bottom: 1px solid #eee;
}
/* Avatar */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_user .forum_comment_user_avatar{
  width: 40px;
  height: 40px;
  border-radius: 4px;
  margin: 0px 10px 0px 0px;
}
/* Username */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_user .forum_comment_username{
  font-size: 16px;
  color: #333;
}
/* Content */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_content{
  width: calc(100% - 40px);
  margin: 0px 0px 0px 40px;
  padding: 10px 0px 0px;
  font-size: 16px;
}
/* Button box */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_reply_view{
  width: 100%;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  padding: 0px 20px 0px 0px;
  box-sizing: border-box;
}
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_reply_view .forum_comment_del{
  font-size: inherit;
  color: rgb(255, 255, 255);
  cursor: pointer;
  margin: 0px 0px 0px 10px;
  background: #fb6665;
  padding: 5px 10px;
  border-radius: 4px;
}
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_reply_view .forum_comment_reply{
  font-size: inherit;
  color: rgb(255, 255, 255);
  cursor: pointer;
  margin: 0px 0px 0px 10px;
  background: #666;
  padding: 5px 10px;
  border-radius: 4px;
}

/* Add comment button */
.forum_view .forum_comment_box  .comment_add{
  margin: 0px 10px 0px 0px;
  padding: 0px 24px;
  width: auto;
  height: 40px;
  font-size: 16px;
  color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 0px;
  background: #7282a4;
  cursor: pointer;
}

/* Second-level comment box */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list{
  width: calc(100% - 60px);
  margin: 0px 0px 0px 60px;
  padding: 20px 0px 0px;
}
/* Line */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second_divider{
  display: block;
  height: 1px;
  width: 100%;
  margin: 24px 0;
}
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second_divider .el-divider__text{
  font-size: 16px;
}
/* Item */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second{
  width: 100%;
  padding: 10px 0px;
  border-width: 0px 0px 1px;
  border-style: solid;
  border-color: rgb(238, 238, 238);
  border-image: initial;
}
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second:last-child{
  border-width: 0px 0px 0px;
}

/* User box */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second .forum_comment_second_user{
  display: flex;
  align-items: center;
}
/* Avatar */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second .forum_comment_second_user .forum_comment_second_user_avatar{
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin: 0px 10px 0px 0px;
}
/* Username */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second .forum_comment_second_user .forum_comment_second_username{
  font-size: 16px;
}
/* Content */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second .forum_comment_second_content{
  width: calc(100% - 60px);
  margin: 0px 0px 0px 60px;
  padding: 10px 0px 0px;
  color:#999;
  font-size: 16px;
}
/* Button box */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second .forum_comment_second_reply_view{
  width: 100%;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  padding: 0px 20px 0px 0px;
  box-sizing: border-box;
}
/* Delete */
.forum_view .forum_comment_box .forum_comment_list .forum_comment .forum_comment_second_list .forum_comment_second .forum_comment_second_reply_view .forum_comment_second_del{
  font-size: inherit;
  color: rgb(255, 255, 255);
  cursor: pointer;
  margin: 0px 0px 0px 10px;
  background: #fb9365;
  padding: 5px 10px;
  border-radius: 4px;
}

</style>
