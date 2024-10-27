<template>
  <div>
    <el-dialog v-model="approvalVisible" :title="'Approval'" width="70%" destroy-on-close>
      <el-form ref="ruleFormRef" :model="approvalForm" label-width="120px" :rules="rules">
        <el-row>
          <el-col :span="24">
            <el-form-item label="Approval Reply" prop="shhf">
              <el-input v-model="approvalForm.shhf" type="textarea" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <span class="dialog-footer" v-if="sfshType == 1">
          <el-button type="danger" @click="approvalSave('No')">Reject</el-button>
          <el-button type="primary" @click="approvalSave('Yes')">Approve</el-button>
        </span>
        <span class="dialog-footer" v-if="sfshType == 2">
          <el-button type="danger" @click="approvalVisible = false">Cancel</el-button>
          <el-button type="primary" @click="approvalSave()">Reply</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {
  ref,
  toRefs,
  getCurrentInstance,
  defineEmits,
} from 'vue';

const emit = defineEmits(['shChange']);
const context = getCurrentInstance()?.appContext.config.globalProperties;

// Props
const props = defineProps({
  tableName: String,
});
const { tableName } = toRefs(props);

// Validation rules
const rules = ref({
  shhf: [
    {
      required: true,
      message: 'Please enter an approval reply',
      trigger: 'blur',
    },
  ],
});

// Data references
const approvalForm = ref({});
const approvalVisible = ref(false);
const ruleFormRef = ref(null);
const sfshType = ref(1);

// Function to handle opening the approval dialog
const approvalClick = (row, type = 1) => {
  sfshType.value = type;
  approvalForm.value = JSON.parse(JSON.stringify(row));
  approvalVisible.value = true;
};

// Exposing the approval click method to the parent component
defineExpose({
  approvalClick,
});

// Function to handle saving the approval
const approvalSave = (type) => {
  if (sfshType.value == 1) {
    approvalForm.value.sfsh = type;
  }

  ruleFormRef.value.validate((valid) => {
    if (valid) {
      let url = `${tableName.value}/update`;
      context?.$http({
        url: url,
        method: 'post',
        data: approvalForm.value,
      }).then((res) => {
        context?.$toolUtil.message('Approval successful', 'success', (obj) => {
          approvalVisible.value = false;
          emit('shChange');
        });
      });
    }
  });
};
</script>

<style></style>
