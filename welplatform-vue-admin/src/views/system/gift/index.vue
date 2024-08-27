<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="礼品名称" prop="giftName">
        <el-input
          v-model="queryParams.giftName"
          placeholder="请输入礼品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="兑换该礼品所需的积分数量" prop="requiredPoints">
        <el-input
          v-model="queryParams.requiredPoints"
          placeholder="请输入兑换该礼品所需的积分数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存数量，记录礼品当前的库存量" prop="stockQuantity">
        <el-input
          v-model="queryParams.stockQuantity"
          placeholder="请输入库存数量，记录礼品当前的库存量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片链接，用于展示礼品的图片" prop="imageLink">
        <el-input
          v-model="queryParams.imageLink"
          placeholder="请输入图片链接，用于展示礼品的图片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="礼品价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入礼品价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="礼品来源公司" prop="sourceCompany">
        <el-input
          v-model="queryParams.sourceCompany"
          placeholder="请输入礼品来源公司"
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
          v-hasPermi="['welplatform:gift:add']"
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
          v-hasPermi="['welplatform:gift:edit']"
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
          v-hasPermi="['welplatform:gift:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['welplatform:gift:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="giftList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="礼品编号，作为主键" align="center" prop="giftId" />
      <el-table-column label="礼品名称" align="center" prop="giftName" />
      <el-table-column label="兑换该礼品所需的积分数量" align="center" prop="requiredPoints" />
      <el-table-column label="对礼品的简要描述" align="center" prop="description" />
      <el-table-column label="库存数量，记录礼品当前的库存量" align="center" prop="stockQuantity" />
      <el-table-column label="图片链接，用于展示礼品的图片" align="center" prop="imageLink" />
      <el-table-column label="礼品价格" align="center" prop="price" />
      <el-table-column label="礼品来源公司" align="center" prop="sourceCompany" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['welplatform:gift:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['welplatform:gift:remove']"
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

    <!-- 添加或修改礼品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="礼品名称" prop="giftName">
          <el-input v-model="form.giftName" placeholder="请输入礼品名称" />
        </el-form-item>
        <el-form-item label="兑换该礼品所需的积分数量" prop="requiredPoints">
          <el-input v-model="form.requiredPoints" placeholder="请输入兑换该礼品所需的积分数量" />
        </el-form-item>
        <el-form-item label="对礼品的简要描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="库存数量，记录礼品当前的库存量" prop="stockQuantity">
          <el-input v-model="form.stockQuantity" placeholder="请输入库存数量，记录礼品当前的库存量" />
        </el-form-item>
        <el-form-item label="图片链接，用于展示礼品的图片" prop="imageLink">
          <el-input v-model="form.imageLink" placeholder="请输入图片链接，用于展示礼品的图片" />
        </el-form-item>
        <el-form-item label="礼品价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入礼品价格" />
        </el-form-item>
        <el-form-item label="礼品来源公司" prop="sourceCompany">
          <el-input v-model="form.sourceCompany" placeholder="请输入礼品来源公司" />
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
import { listGift, getGift, delGift, addGift, updateGift } from "@/api/system/gift";

export default {
  name: "Gift",
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
      // 礼品管理表格数据
      giftList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        giftName: null,
        requiredPoints: null,
        description: null,
        stockQuantity: null,
        imageLink: null,
        price: null,
        sourceCompany: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询礼品管理列表 */
    getList() {
      this.loading = true;
      listGift(this.queryParams).then(response => {
        this.giftList = response.rows;
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
        giftId: null,
        giftName: null,
        requiredPoints: null,
        description: null,
        stockQuantity: null,
        imageLink: null,
        price: null,
        sourceCompany: null
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
      this.ids = selection.map(item => item.giftId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加礼品管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const giftId = row.giftId || this.ids
      getGift(giftId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改礼品管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.giftId != null) {
            updateGift(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGift(this.form).then(response => {
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
      const giftIds = row.giftId || this.ids;
      this.$modal.confirm('是否确认删除礼品管理编号为"' + giftIds + '"的数据项？').then(function() {
        return delGift(giftIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('welplatform/gift/export', {
        ...this.queryParams
      }, `gift_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
