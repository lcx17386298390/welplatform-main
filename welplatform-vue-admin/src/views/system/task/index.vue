<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务标题" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务描述" prop="taskDescription">
        <el-input
          v-model="queryParams.taskDescription"
          placeholder="请输入任务描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务积分" prop="points">
        <el-input
          v-model="queryParams.points"
          placeholder="请输入任务积分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务过期时间" prop="expireTime">
        <el-date-picker clearable
          v-model="queryParams.expireTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择任务过期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="任务点击数" prop="clickCount">
        <el-input
          v-model="queryParams.clickCount"
          placeholder="请输入任务点击数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员id" prop="adminUid">
        <el-input
          v-model="queryParams.adminUid"
          placeholder="请输入管理员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入发布者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐等级(0:正常)" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入推荐等级(0:正常)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否发布:0:否：1：是" prop="isPublish">
        <el-input
          v-model="queryParams.isPublish"
          placeholder="请输入是否发布:0:否：1：是"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务图片uid" prop="fileUid">
        <el-input
          v-model="queryParams.fileUid"
          placeholder="请输入任务图片uid"
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
          v-hasPermi="['welplatform:task:add']"
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
          v-hasPermi="['welplatform:task:edit']"
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
          v-hasPermi="['welplatform:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['welplatform:task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务id" align="center" prop="uid" />
      <el-table-column label="任务标题" align="center" prop="taskName" />
      <el-table-column label="任务描述" align="center" prop="taskDescription" />
      <el-table-column label="任务积分" align="center" prop="points" />
      <el-table-column label="任务内容" align="center" prop="content" />
      <el-table-column label="任务过期时间" align="center" prop="expireTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任务类型" align="center" prop="type" />
      <el-table-column label="任务点击数" align="center" prop="clickCount" />
      <el-table-column label="管理员id" align="center" prop="adminUid" />
      <el-table-column label="发布者" align="center" prop="author" />
      <el-table-column label="推荐等级(0:正常)" align="center" prop="level" />
      <el-table-column label="是否发布:0:否：1：是" align="center" prop="isPublish" />
      <el-table-column label="任务图片uid" align="center" prop="fileUid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['welplatform:task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['welplatform:task:remove']"
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

    <!-- 添加或修改任务管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务标题" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务标题" />
        </el-form-item>
        <el-form-item label="任务描述" prop="taskDescription">
          <el-input v-model="form.taskDescription" placeholder="请输入任务描述" />
        </el-form-item>
        <el-form-item label="任务积分" prop="points">
          <el-input v-model="form.points" placeholder="请输入任务积分" />
        </el-form-item>
        <el-form-item label="任务内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="任务过期时间" prop="expireTime">
          <el-date-picker clearable
            v-model="form.expireTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择任务过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="任务点击数" prop="clickCount">
          <el-input v-model="form.clickCount" placeholder="请输入任务点击数" />
        </el-form-item>
        <el-form-item label="管理员id" prop="adminUid">
          <el-input v-model="form.adminUid" placeholder="请输入管理员id" />
        </el-form-item>
        <el-form-item label="发布者" prop="author">
          <el-input v-model="form.author" placeholder="请输入发布者" />
        </el-form-item>
        <el-form-item label="推荐等级(0:正常)" prop="level">
          <el-input v-model="form.level" placeholder="请输入推荐等级(0:正常)" />
        </el-form-item>
        <el-form-item label="是否发布:0:否：1：是" prop="isPublish">
          <el-input v-model="form.isPublish" placeholder="请输入是否发布:0:否：1：是" />
        </el-form-item>
        <el-form-item label="任务图片uid" prop="fileUid">
          <el-input v-model="form.fileUid" placeholder="请输入任务图片uid" />
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
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/system/task";

export default {
  name: "Task",
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
      // 任务管理表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        taskDescription: null,
        points: null,
        content: null,
        expireTime: null,
        type: null,
        clickCount: null,
        adminUid: null,
        author: null,
        level: null,
        isPublish: null,
        fileUid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "任务类型不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询任务管理列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
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
        taskName: null,
        taskDescription: null,
        points: null,
        createTime: null,
        updateTime: null,
        content: null,
        expireTime: null,
        type: null,
        clickCount: null,
        adminUid: null,
        author: null,
        level: null,
        isPublish: null,
        fileUid: null
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
      this.title = "添加任务管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const uid = row.uid || this.ids
      getTask(uid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.uid != null) {
            updateTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除任务管理编号为"' + uids + '"的数据项？').then(function() {
        return delTask(uids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('welplatform/task/export', {
        ...this.queryParams
      }, `task_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
