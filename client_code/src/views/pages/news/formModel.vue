<template>
  <div>
    <el-dialog v-model="formVisible" :title="formTitle" width="60%" custom-class="news-detail-dialog" destroy-on-close>
      <div class="news_detail">
        <div class="news_detail_title" style="text-align: center;">{{detail.title}}</div>
        <div class="news_detail_time">Publish Time: {{detail.addtime}}</div>
        <div class="news_detail_content" v-html="detail.content"></div>
      </div>
      <template #footer>
				<span class="dialog-footer">
					<el-button @click="formVisible=false">Close</el-button>
				</span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import {
  ref,
  nextTick,
  getCurrentInstance,
  defineEmits
} from 'vue';
const context = getCurrentInstance()?.appContext.config.globalProperties;
const id = ref(0)
const detail = ref({})
const formRef = ref(null)
const formVisible = ref(false)
const formTitle = ref('')
// Initialize
const init = (refid=null) => {
  detail.value = {}
  if(refid){
    id.value = refid
    getInfo()
  }
  formTitle.value = 'View Announcement Information'
  formVisible.value = true
}
// Declare parent call
defineExpose({
  init
})
// Callback parent method
const getInfo = () => {
  context?.$http({
    url: `news/detail/${id.value}`,
    method: 'get'
  }).then(res => {
    detail.value = res.data.data
    formVisible.value = true
  })
}
</script>

<style>
	.news_detail {
		.news_detail_title {
		}
	
		.news_detail_time {
		}
	
		.news_detail_content {
		}
	}

</style>