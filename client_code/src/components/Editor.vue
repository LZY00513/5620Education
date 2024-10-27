<template>
  <div>
    <!-- Image upload component helper-->
    <div style="border: 1px solid #ccc">
      <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig"
               :mode="mode" />
      <Editor style="height: 310px; overflow-y: hidden;" v-model="valueHtml" :defaultConfig="editorConfig"
              :mode="mode" @onCreated="handleCreated" @onChange="handleChange" />
    </div>
  </div>
</template>
<script setup>
import '@wangeditor/editor/dist/css/style.css' // Import css

import {
  onBeforeUnmount,
  ref,
  shallowRef,
  onMounted,
  getCurrentInstance,
  defineEmits,
  defineProps,
  watch
} from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties;
import {
  Editor,
  Toolbar
} from '@wangeditor/editor-for-vue'
let props = defineProps({
  value:String
})
const emit = defineEmits(['change'])
// Editor instance, must use shallowRef
const editorRef = shallowRef()

// Content HTML
const valueHtml = ref('')
watch(()=>props.value,(e)=>{
  if(e!=valueHtml.value){
    valueHtml.value = e
  }
})

onMounted(() => {
  setTimeout(() => {
    valueHtml.value = props.value||''
  }, 0)
})
const mode = 'default' // or 'simple'
const toolbarConfig = {
  excludeKeys: ['fullScreen', 'group-video', 'insertLink', 'insertImage']
}
const editorConfig = {
  placeholder: 'Please enter content...',
  fontSize:['12px','14px','16px'],
  MENU_CONF: {
    uploadImage: {
      server: context?.$config.name + '/file/upload',
      fieldName: 'file',
      // Max file size limit, default is 2M
      maximgSize: 10 * 1024 * 1024, // 10M
      // Max number of files to upload, default is 100
      maxNumberOfimgs: 10,
      // File type restrictions when selecting files, default is ['image/*']. Set to [] for no restrictions
      allowedimgTypes: ['image/*'],
      // Custom upload parameters, such as passing validation tokens, etc. These parameters will be added to formData and uploaded to the server.
      meta: {
        // token: 'xxx',
        // otherKey: 'yyy'
        // img:''
      },
      // Append meta to the URL parameters, default is false
      metaWithUrl: false,

      // Custom http headers
      headers: {
        'Token':context?.$toolUtil.storageGet('Token')
      },

      // Whether to pass cookies when cross-domain, default is false
      withCredentials: true,

      // Timeout period, default is 10 seconds
      timeout: 10 * 1000, // 10 seconds

      // Before upload
      onBeforeUpload(imgs) {
        context?.$toolUtil.message('Image is being uploaded, please wait patiently','warning')
        return imgs;
      },
      // Custom insert image
      customInsert(res, insertFn) {
        // Since custom insert disables onSuccess and onFailed callbacks, handle manually

        if (res.code === 0) {
          context?.$toolUtil.message('Image uploaded successfully','success')
        } else {
          context?.$toolUtil.message('Image upload failed, please try again','error')
        }
        // Extract url alt href from res and insert image
        insertFn(context?.$config.url + 'file/' + res.file);
        // console.log(res, "res.data")
      },

      // After a single file upload success
      onSuccess(img, res) {
      },

      // After a single file upload failure
      onFailed(img, res) {
      },

      // Upload progress callback function
      onProgress(progress) {
        // console.log('progress', progress);
        // progress is a number from 0-100
      },

      // Upload error or timeout
      onError(img, err, res) {
        // console.log(`${img.name} upload error`, err, res);
      }
    }
  }
}

// Destroy the editor when the component is destroyed
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})
const handleChange=(e)=>{
  emit('change',editorRef.value.getHtml())
}
const handleCreated = (editor) => {
  editorRef.value = editor // Save editor instance, important!
}
</script>

<style>
.editor {
  line-height: normal !important;
}

.ql-snow .ql-tooltip[data-mode="link"]::before {
  content: "Please enter link address:";
}

.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
  border-right: 0px;
  content: "Save";
  padding-right: 0px;
}

.ql-snow .ql-tooltip[data-mode="video"]::before {
  content: "Please enter video address:";
}

.ql-container {
  height: 400px;
}

.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: "14px";
}

.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
  content: "10px";
}

.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
  content: "18px";
}

.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
  content: "32px";
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: "Text";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "Heading 1";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "Heading 2";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "Heading 3";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "Heading 4";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "Heading 5";
}

.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "Heading 6";
}

.ql-snow .ql-picker.ql-font .ql-picker-label::before,
.ql-snow .ql-picker.ql-font .ql-picker-item::before {
  content: "Standard font";
}

.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
  content: "Serif font";
}

.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
  content: "Monospace font";
}
</style>
