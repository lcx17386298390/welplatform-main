<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务类型名字" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入任务类型名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务关键词" prop="typeDesc">
        <el-input
          v-model="queryParams.typeDesc"
          placeholder="请输入任务关键词"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务id" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纬度,当任务类型为2时需要" prop="latitude">
        <el-input
          v-model="queryParams.latitude"
          placeholder="请输入纬度,当任务类型为2时需要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经度，当任务类型为2时需要" prop="longitude">
        <el-input
          v-model="queryParams.longitude"
          placeholder="请输入经度，当任务类型为2时需要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['welplatform:type:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['welplatform:type:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['welplatform:type:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['welplatform:type:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务类型id" align="center" prop="uid" />
      <el-table-column label="任务类型名字" align="center" prop="typeName" />
      <el-table-column label="任务类型" align="center" prop="type" />
      <el-table-column label="任务关键词" align="center" prop="typeDesc" />
      <el-table-column label="任务id" align="center" prop="taskId" />
      <el-table-column label="纬度,当任务类型为2时需要" align="center" prop="latitude" />
      <el-table-column label="经度，当任务类型为2时需要" align="center" prop="longitude" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['welplatform:type:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['welplatform:type:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改任务种类管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务类型名字" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入任务类型名字" />
        </el-form-item>
        <el-form-item label="任务关键词" prop="typeDesc">
          <el-input v-model="form.typeDesc" placeholder="请输入任务关键词" />
        </el-form-item>
        <el-form-item label="任务id" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入任务id" />
        </el-form-item>
        <el-form-item label="纬度,当任务类型为2时需要" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度,当任务类型为2时需要" />
        </el-form-item>
        <el-form-item label="经度，当任务类型为2时需要" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度，当任务类型为2时需要" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listType, getType, delType, addType, updateType } from "@/api/system/type";

export default {
  name: "Type",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 任务种类管理表格数据
      typeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: null,
        type: null,
        typeDesc: null,
        taskId: null,
        latitude: null,
        longitude: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "任务类型不能为空", trigger: "change" }
        ],
        typeDesc: [
          { required: true, message: "任务关键词不能为空", trigger: "blur" }
        ],
        taskId: [
          { required: true, message: "任务id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询任务种类管理列表 */
    getList() {
      this.loading = true;
      listType(this.queryParams).then(response => {
        this.typeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        uid: null,
        typeName: null,
        type: null,
        typeDesc: null,
        taskId: null,
        latitude: null,
        longitude: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.uid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务种类管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const uid = row.uid || this.ids
      getType(uid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务种类管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.uid != null) {
            updateType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addType(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const uids = row.uid || this.ids;
      this.$modal.confirm('是否确认删除任务种类管理编号为"' + uids + '"的数据项？').then(function() {
        return delType(uids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('welplatform/type/export', {
        ...this.queryParams
      }, `type_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
