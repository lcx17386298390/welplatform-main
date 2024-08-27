<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="缓存的FormId" prop="formId">
        <el-input
          v-model="queryParams.formId"
          placeholder="请输入缓存的FormId"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是FormId还是prepayId" prop="isprepay">
        <el-input
          v-model="queryParams.isprepay"
          placeholder="请输入是FormId还是prepayId"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="可用次数，fromId为1，prepay为3，用1次减1" prop="useAmount">
        <el-input
          v-model="queryParams.useAmount"
          placeholder="请输入可用次数，fromId为1，prepay为3，用1次减1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="过期时间，腾讯规定为7天" prop="expireTime">
        <el-date-picker clearable
          v-model="queryParams.expireTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择过期时间，腾讯规定为7天">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="微信登录openid" prop="openId">
        <el-input
          v-model="queryParams.openId"
          placeholder="请输入微信登录openid"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="addTime">
        <el-date-picker clearable
          v-model="queryParams.addTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="逻辑删除" prop="deleted">
        <el-input
          v-model="queryParams.deleted"
          placeholder="请输入逻辑删除"
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
          v-hasPermi="['welplatform:formid:add']"
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
          v-hasPermi="['welplatform:formid:edit']"
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
          v-hasPermi="['welplatform:formid:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['welplatform:formid:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="formidList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="缓存的FormId" align="center" prop="formId" />
      <el-table-column label="是FormId还是prepayId" align="center" prop="isprepay" />
      <el-table-column label="可用次数，fromId为1，prepay为3，用1次减1" align="center" prop="useAmount" />
      <el-table-column label="过期时间，腾讯规定为7天" align="center" prop="expireTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="微信登录openid" align="center" prop="openId" />
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除" align="center" prop="deleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['welplatform:formid:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['welplatform:formid:remove']"
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

    <!-- 添加或修改用户微信数据管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="缓存的FormId" prop="formId">
          <el-input v-model="form.formId" placeholder="请输入缓存的FormId" />
        </el-form-item>
        <el-form-item label="是FormId还是prepayId" prop="isprepay">
          <el-input v-model="form.isprepay" placeholder="请输入是FormId还是prepayId" />
        </el-form-item>
        <el-form-item label="可用次数，fromId为1，prepay为3，用1次减1" prop="useAmount">
          <el-input v-model="form.useAmount" placeholder="请输入可用次数，fromId为1，prepay为3，用1次减1" />
        </el-form-item>
        <el-form-item label="过期时间，腾讯规定为7天" prop="expireTime">
          <el-date-picker clearable
            v-model="form.expireTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择过期时间，腾讯规定为7天">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="微信登录openid" prop="openId">
          <el-input v-model="form.openId" placeholder="请输入微信登录openid" />
        </el-form-item>
        <el-form-item label="创建时间" prop="addTime">
          <el-date-picker clearable
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="逻辑删除" prop="deleted">
          <el-input v-model="form.deleted" placeholder="请输入逻辑删除" />
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
import { listFormid, getFormid, delFormid, addFormid, updateFormid } from "@/api/system/formid";

export default {
  name: "Formid",
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
      // 用户微信数据管理表格数据
      formidList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        formId: null,
        isprepay: null,
        useAmount: null,
        expireTime: null,
        openId: null,
        addTime: null,
        deleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        formId: [
          { required: true, message: "缓存的FormId不能为空", trigger: "blur" }
        ],
        isprepay: [
          { required: true, message: "是FormId还是prepayId不能为空", trigger: "blur" }
        ],
        useAmount: [
          { required: true, message: "可用次数，fromId为1，prepay为3，用1次减1不能为空", trigger: "blur" }
        ],
        expireTime: [
          { required: true, message: "过期时间，腾讯规定为7天不能为空", trigger: "blur" }
        ],
        openId: [
          { required: true, message: "微信登录openid不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户微信数据管理列表 */
    getList() {
      this.loading = true;
      listFormid(this.queryParams).then(response => {
        this.formidList = response.rows;
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
        id: null,
        formId: null,
        isprepay: null,
        useAmount: null,
        expireTime: null,
        openId: null,
        addTime: null,
        updateTime: null,
        deleted: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户微信数据管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFormid(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户微信数据管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFormid(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFormid(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用户微信数据管理编号为"' + ids + '"的数据项？').then(function() {
        return delFormid(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('welplatform/formid/export', {
        ...this.queryParams
      }, `formid_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
