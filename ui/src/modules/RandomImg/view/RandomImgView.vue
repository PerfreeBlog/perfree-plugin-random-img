<template>
  <div class="page">
    <div class="search-box">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline" ref="searchFormRef">
        <el-form-item label="名称">
          <el-input v-model="searchForm.name" placeholder="请输入名称" clearable/>
        </el-form-item>
        <el-form-item label="是否开启">
          <el-select v-model="searchForm.status" placeholder="请选择是否开启" clearable  style="width: 200px">
            <el-option :key="0" :label="'开启'" :value="0" />
            <el-option :key="1" :label="'关闭'" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="initList" :icon="Search" v-hasPermission="['admin:randomImg:query']">查询</el-button>
          <el-button :icon="Refresh" @click="resetSearchForm">重置</el-button>
          <el-button :icon="Download" @click="exportExcel" v-hasPermission="['admin:randomImg:export']">导出</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button :icon="Plus" type="primary" plain @click="handleAdd" v-hasPermission="['admin:randomImg:create']">新增</el-button>
      </el-col>
      <div class="right-tool">
        <el-button :icon="Refresh" circle @click="initList"/>
      </div>
    </el-row>

    <div class="table-box">

      <el-table :data="tableData" style="width: 100%;height:100%;" row-key="id" v-loading="loading" >
        <el-table-column prop="name" label="名称" min-width="150"/>
        <el-table-column prop="remark" label="备注" min-width="150"/>
        <el-table-column prop="slug" label="访问标识" min-width="150"/>
        <el-table-column prop="slug" label="访问地址" min-width="200">
          <template v-slot="scope">
            <el-link :href="'/randomImg/' + scope.row.slug" target="_blank">{{'/randomImg/' + scope.row.slug}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="attachLibraryName" label="附件库" min-width="150"/>
        <el-table-column prop="status" label="是否开启" min-width="120">
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.status === 0">开启</el-tag>
            <el-tag type="danger" v-else>关闭</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="visitCount" label="访问次数" min-width="100"/>
        <el-table-column prop="createTime" label="创建时间" min-width="150">
          <template v-slot="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="240" fixed="right">
          <template v-slot="scope">
            <el-button size="small" type="primary" link :icon="Edit" @click="handleUpdate(scope.row)" v-hasPermission="['admin:randomImg:update']">修改</el-button>
            <el-button size="small" type="primary" link :icon="Delete" @click="handleDelete(scope.row)" v-hasPermission="['admin:randomImg:delete']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
          v-model:current-page="searchForm.pageNo"
          v-model:page-size="searchForm.pageSize"
          :page-sizes="[10, 20, 30, 50]"
          layout="total,sizes,prev, pager, next, jumper"
          background
          small
          @change="initList"
          :total="searchForm.total"
      />
    </div>

    <el-dialog v-model="open" :title="title" width="600px" draggable>
      <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="80px"
          status-icon
          :rules="addRule"
          v-loading="addFormLoading"
      >
        <el-form-item label="名称" prop="name">
          <el-input v-model="addForm.name" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="访问标识" prop="slug">
          <el-input v-model="addForm.slug" placeholder="请输入访问标识"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="addForm.remark" placeholder="请输入备注"/>
        </el-form-item>
        <el-form-item label="附件库" prop="attachLibraryId">
          <el-select v-model="addForm.attachLibraryId" placeholder="请选择附件库" clearable  style="width: 200px">
            <el-option :key="attachLibrary.id" :label="attachLibrary.name" :value="attachLibrary.id" v-for="attachLibrary in attachLibraryList" />
          </el-select>
        </el-form-item>
          <el-form-item label="是否开启" prop="status">
            <el-select v-model="addForm.status" placeholder="请选择是否开启" clearable  style="width: 200px">
              <el-option :key="0" :label="'开启'" :value="0" />
              <el-option :key="1" :label="'关闭'" :value="1" />
            </el-select>
          </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
              <el-button type="primary" @click="submitAddForm">确 定</el-button>
              <el-button @click="open = false; resetAddForm()">取 消</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import {ElMessage, ElMessageBox} from "element-plus";
import {handleTree, parseTime} from "@/core/utils/perfree.js";
import {
  randomImgAddApi,
  randomImgDelApi,
  randomImgGetApi,
  randomImgPageApi,
  randomImgUpdateApi,
  randomImgExportExcelApi,
  initAttachLibraryListApi
} from "../api/randomImg.js";
import {Delete, Edit, Filter, Plus, Refresh, Search, Download} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {getDictByParentDictType, getDictByParentDictTypeAndValue} from "@/core/utils/dictUtils.js";

let attachLibraryList = ref([]);
const searchForm = ref({
  pageNo: 1,
  pageSize: 10,
  total: 0,
  name:  null ,
  status:  null ,
})
const addForm = ref({
  id: null,
  name: null,
  remark: null,
  attachLibraryId: null,
  status: 0,
  slug: null
});
const addRule = reactive({
  name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
  slug: [{required: true, message: '访问标识不能为空', trigger: 'blur'}],
  attachLibraryId: [{required: true, message: '附件库不能为空', trigger: 'blur'}],
  status: [{required: true, message: '是否开启不能为空', trigger: 'blur'}],
});

const searchFormRef = ref();
const addFormRef = ref();
let open = ref(false);
let title = ref('');
let tableData = ref([]);
let loading = ref(false);
let addFormLoading = ref(false);

/**
 * 添加提交
 */
function submitAddForm() {
  addFormRef.value.validate(valid => {
    if (valid) {
      if (addForm.value.id) {
        randomImgUpdateApi(addForm.value).then((res) => {
          if (res.code === 200) {
            ElMessage.success('操作成功');
            open.value = false;
            resetAddForm();
            initList();
          } else {
            ElMessage.error(res.msg);
          }
        })
      } else {
        randomImgAddApi(addForm.value).then((res) => {
          if (res.code === 200) {
            ElMessage.success('操作成功');
            open.value = false;
            resetAddForm();
            initList();
          } else {
            ElMessage.error(res.msg);
          }
        })
      }
    }
  })
}

function initAttachLibraryList() {
  initAttachLibraryListApi().then(res => {
    attachLibraryList.value = res.data;
    addFormLoading.value = false;
  })
}

/**
 * 新增
 */
function handleAdd() {
  addFormLoading.value = true;
  resetAddForm();
  title.value = '添加随机图';
  open.value = true;
  initAttachLibraryList();
}

/**
 * 修改
 */
function handleUpdate(row) {
  addFormLoading.value = true;
  resetAddForm();
  title.value = '修改随机图';
  open.value = true;
  initAttachLibraryList();
  randomImgGetApi(row.id).then((res) => {
    addForm.value = res.data;
    addFormLoading.value = false;
  })
}

/**
 * 删除
 * @param row
 */
function handleDelete(row) {
  let keys = Object.keys(row);
  ElMessageBox.confirm('确定要删除[' + row[keys[0]] + ']吗？', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    randomImgDelApi(row.id).then((res) => {
      if (res.code === 200 && res.data) {
        ElMessage.success('删除成功');
        initList();
      } else {
        ElMessage.error(res.msg);
      }
    });
  }).catch(() => {
  })
}

/**
 * 加载列表
 */
function initList() {
  loading.value = true;
  randomImgPageApi(searchForm.value).then((res) => {
    tableData.value = res.data.list;
    searchForm.value.total = res.data.total;
    loading.value = false;
  })
}

/**
 * 重置搜索表单
 */
function resetSearchForm() {
  searchForm.value = {
    pageNo: 1,
    pageSize: 10,
    total: 0,
    name:   null ,
    status:   null ,
  }
  searchFormRef.value.resetFields();
  initList();
}

/**
 * 重置添加表单
 */
function resetAddForm() {
  addForm.value = {
    id: null,
    name: null,
    remark: null,
    attachLibraryId: null,
    status: 0,
    slug: null
  }
  if (addFormRef.value) {
    addFormRef.value.resetFields();
  }
}

/**
 * 导出excel
 */
function exportExcel() {
  loading.value = true;
  randomImgExportExcelApi(searchForm.value).then(res => {
    window.download.excel(res,  '随机图数据.xlsx');
    loading.value = false;
  }).catch(e => {
    ElMessage.error('导出失败');
    loading.value = false;
  })
}

initList();
</script>
<style scoped></style>