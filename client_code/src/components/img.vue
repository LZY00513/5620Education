<template>
  <el-dialog title="Take Photo Upload" v-model="visible" width="1065px">
    <div class="box">
      <video id="videoCamera" class="canvas" :width="videoWidth" :height="videoHeight" autoPlay></video>
      <canvas id="canvasCamera" class="canvas" :width="videoWidth" :height="videoHeight"></canvas>
    </div>
    <template #footer>
      <div style="display: flex;justify-content: flex-end;">
        <el-upload :action="getActionUrl" :on-success="uploadSuccess" :show-file-list="false"
                   accept=".jpg,.png,.jpge" style="margin-right:10px">
          <el-button size="small">
            Upload Image
          </el-button>
        </el-upload>
        <el-button @click="drawImage" size="small">
          Take Photo
        </el-button>
        <el-button v-if="os" @click="getCompetence" size="small">
          Open Camera
        </el-button>
        <el-button v-else @click="stopNavigator" size="small">
          Close Camera
        </el-button>
        <el-button @click="resetCanvas" size="small">
          Reset
        </el-button>
        <el-button @click="onCancel" size="small">
          Complete
        </el-button>
      </div>

    </template>
  </el-dialog>
</template>
<script setup>
import {
  nextTick,
  ref,
  getCurrentInstance,
  computed,
  defineEmits
} from 'vue';
import axios from "axios";
const context = getCurrentInstance()?.appContext.config.globalProperties;
const visible = ref(false) // Dialog
const loading = ref(false) // Upload button loading
const os = ref(false) // Control camera toggle
const thisVideo = ref(null)
const thisContext = ref(null)
const thisCancas = ref(null)
const videoWidth = ref(500)
const videoHeight = ref(400)
const postOptions = ref([])
const CertCtl = ref('')
const imgSrc = ref('')
const emit = defineEmits(['imgChange'])
const getActionUrl = computed(() => {
  return context?.$config.url + 'file/upload'
})

const uploadSuccess = (e) => {
  visible.value = false;
  emit('imgChange', e.file)
}
// Call camera permission
const getCompetence = () => {
  // Must be rendered in model to get DOM nodes; directly accessing will not fetch DOM nodes in model
  nextTick(() => {
    os.value = false; // Switch to close camera
    thisCancas.value = document.getElementById('canvasCamera');
    thisContext.value = document.getElementById('canvasCamera').getContext('2d');
    thisVideo.value = document.getElementById('videoCamera');
    // Older browsers might not support mediaDevices, so we set an empty object
    if (navigator.mediaDevices === undefined) {
      navigator.menavigatordiaDevices = {}
    }
    // Some browsers partially implement mediaDevices; we can't just assign an object to getUserMedia as it would overwrite existing properties.
    if (navigator.mediaDevices.getUserMedia === undefined) {
      navigator.mediaDevices.getUserMedia = function(constraints) {
        let getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia ||
            navigator.getUserMedia;
        if (!getUserMedia) {
          return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
        }
        return new Promise(function(resolve, reject) {
          getUserMedia.call(navigator, constraints, resolve, reject)
        })
      }
    }
    const constraints = {
      audio: false,
      video: {
        width: videoWidth.value,
        height: videoHeight.value,
        transform: 'scaleX(-1)'
      }
    };
    navigator.mediaDevices.getUserMedia(constraints).then(function(stream) {
      if ('srcObject' in thisVideo.value) {
        thisVideo.value.srcObject = stream
      } else {
        thisVideo.value.src = window.URL.createObjectURL(stream)
      }
      thisVideo.value.onloadedmetadata = function (e) {
        thisVideo.value.play()
      }
    }).catch(err => {
      context?.$toolUtil.notify('Warning', 'Camera permission is not enabled or the browser version is not compatible.', 'warning')
    });
  });
}
/* Start capturing photo from camera */
const onTake = () => {
  visible.value = true
  getCompetence()
}

// Declare parent call
defineExpose({
  onTake
})
const base64toFile = (dataurl, filename = 'file') => {
  let arr = dataurl.split(',')
  let mime = arr[0].match(/:(.*?);/)[1]
  let suffix = mime.split('/')[1]
  let bstr = atob(arr[1])
  let n = bstr.length
  let u8arr = new Uint8Array(n)
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n)
  }
  let file = new File([u8arr], `${filename}.${suffix}`, {
    type: mime
  })
  return file
}
const uploadImg = () => {
  let param = new FormData()
  param.append('file', base64toFile(imgSrc.value))
  axios.post(getActionUrl.value, param, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }).then(res => {
    emit('imgChange', res.data.file)
  })
}
const onCancel = () => {
  uploadImg()
  visible.value = false
  stopNavigator()
}
const drawImage = () => {
  // Click to draw on canvas
  thisContext.value.drawImage(thisVideo.value, 0, 0, videoWidth.value, videoHeight.value);
  imgSrc.value = thisCancas.value.toDataURL('image/png');
}
// Clear canvas
const clearCanvas = (id) => {
  let c = document.getElementById(id);
  let cxt = c.getContext("2d");
  cxt.clearRect(0, 0, c.width, c.height);
}
// Reset canvas
const resetCanvas = () => {
  imgSrc.value = "";
  clearCanvas('canvasCamera');
}
// Close camera
const stopNavigator = () => {
  if (thisVideo.value && thisVideo.value !== null) {
    thisVideo.value.srcObject.getTracks()[0].stop();
    os.value = true; // Switch to open camera
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #ccc;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border: 1px dashed #ccc;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
