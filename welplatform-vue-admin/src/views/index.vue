<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <blockquote class="text-warning" style="font-size: 14px">
          
        </blockquote>

        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>大学生智慧迎新平台后台管理</h2>
        <p>
          大学生智慧迎新平台后台管理
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://gitee.com/violet27/welplatform')"
            >访问码云</el-button
          >
          
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>学生注册情况</span>
          </div>

          <div class="body">
            <div id="registerChart" style="width: 600px;height:400px;"></div>
          </div>

        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8" class="task-status">
        <el-card class="update-log">

          <div slot="header" class="clearfix">
            <span>任务完成情况</span>
          </div>

          <div class="body">
            <div id="taskChart" style="width: 1000px;height:400px;"></div>
          </div>
        
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8" class="studentMap">
        <el-card class="update-log">

           <div slot="header" class="clearfix">
            <span>新生分布地图</span>
          </div>

          <div class="body">
            <div id="mapChart" style="width: 1000px;height:400px;"></div>
          </div>

        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from "echarts";
import china from "../plugins/china.json"
import { getAuthCnt,getTaskCnt } from "@/api/login";
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "1.0.0",
      //已认证人数
      authCnt: 0,
      //未认证人数
      unauthCnt: 0,
      //任务名字数组
      taskNameArr: [],
      //各任务已完成人数数组
      finishTaskArr: [],
      //各任务未完成人数数组
      notfinishTaskArr: [],
    };
  },
 
  
  mounted(){
    this.initChartsData();
    
  },
  methods: {
      goTarget(href) {
        window.open(href, "_blank");
      },
      
      // 绘制中国地图
      canvasMap() {
      let that = this
      // 基于准备好的dom，初始化echarts实例
      var myChartContainer = document.getElementById('mapChart')
      var resizeMyChartContainer = function() {
        myChartContainer.style.width =
          document.body.offsetWidth / 2 - 100 + 'px' //页面一半的大小
      }
      resizeMyChartContainer()
      var myChartChina1 = that.$echarts.init(myChartContainer)

      function randomData() {
        return Math.round(Math.random() * 500)
      }
      // 绘制图表
      var optionMap = {
        tooltip: {},
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['']
        },
        visualMap: {
          min: 0,
          max: 1000,
          left: '10%',
          top: 'bottom',
          text: ['高', '低'],
          calculable: true,
          color: ['#158BFE', '#ffffff']
        },
        selectedMode: 'single',
        series: [
          {
            name: '',
            type: 'map',
            mapType: 'china',
            itemStyle: {
              normal: {
                borderColor: 'rgba(0, 0, 0, 0.2)'
              },
              emphasis: {
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            showLegendSymbol: true,
            label: {
              normal: {
                show: true
              },
              emphasis: {
                show: true
              }
            },
            data: [
              { name: '北京', value: randomData() },
              { name: '天津', value: randomData() },
              { name: '上海', value: randomData() },
              { name: '重庆', value: randomData() },
              { name: '河北', value: randomData() },
              { name: '河南', value: randomData() },
              { name: '云南', value: randomData() },
              { name: '辽宁', value: randomData() },
              { name: '黑龙江', value: randomData() },
              { name: '湖南', value: randomData() },
              { name: '安徽', value: randomData() },
              { name: '山东', value: randomData() },
              { name: '新疆', value: randomData() },
              { name: '江苏', value: randomData() },
              { name: '浙江', value: randomData() },
              { name: '江西', value: randomData() },
              { name: '湖北', value: randomData() },
              { name: '广西', value: randomData() },
              { name: '甘肃', value: randomData() },
              { name: '山西', value: randomData() },
              { name: '内蒙古', value: randomData() },
              { name: '陕西', value: randomData() },
              { name: '吉林', value: randomData() },
              { name: '福建', value: randomData() },
              { name: '贵州', value: randomData() },
              { name: '广东', value: randomData() },
              { name: '青海', value: randomData() },
              { name: '西藏', value: randomData() },
              { name: '四川', value: randomData() },
              { name: '宁夏', value: randomData() },
              { name: '海南', value: randomData() },
              { name: '台湾', value: randomData() },
              { name: '香港', value: randomData() },
              { name: '澳门', value: randomData() }
            ]
          }
        ]
      }

      myChartChina1.setOption(optionMap)
      window.onresize = function() {
        resizeMyChartContainer()
        myChartChina1.resize()
      }
    },
    initEcharts(){
      //学生注册情况
      var registerChart = echarts.init(document.getElementById('registerChart'));

      //任务完成情况
      var taskChart = echarts.init(document.getElementById('taskChart'));

      //广告点击情况
      //var advertiseChart = echarts.init(document.getElementById('advertiseChart'));
      registerChart.setOption({
          title: {
              text: '学生注册情况',
          },
          series : [
              {
                  name: '学生注册情况',
                  type: 'pie',    
                  radius: '55%',  
                  data:[          
                      {value:this.authCnt, name:'已认证'},
                      {value:this.unauthCnt, name:'未认证'}
                  ]
              }
          ]
      })
      taskChart.setOption({
          title: {
              text: '任务完成情况',
          },
          legend: {
            data: ['已完成','未完成']
          },
          //任务名字
          xAxis: {
            //任务名字数组
            data: this.taskNameArr
          },
          yAxis: {},
          series : [
              {
                  name: '已完成',
                  type: 'bar',    
                  //已完成任务人数数组
                  data: this.finishTaskArr
              },
              {
                  name: '未完成',
                  type: 'bar',    
                  //未完成任务人数数组
                  data: this.notfinishTaskArr
              }
          ]
      })
      this.canvasMap();
    },
    initChartsData(){
      // 使用 Promise.all 等待两个初始化方法都完成
      return Promise.all([
        this.initRegisterChartsData(),
        this.initTaskChartsData(),
      ]).then(() => {
        // 这里会在 initRegisterChartsData 和 initTaskChartsData 都成功完成后执行
        // 可以放心地初始化 ECharts
        this.initEcharts();
      }).catch(error => {
        // 如果有任何一个 Promise 被拒绝，这里会捕获到错误
        console.error("初始化图表数据失败:", error);
      });
    
    },
    initRegisterChartsData(){
      return getAuthCnt().then(res => {
        //console.log(res.data[0]);
        this.authCnt = res.data[0];
        this.unauthCnt = res.data[1];
      })
    },
    initTaskChartsData(){
      return getTaskCnt().then(res => {
        console.log(res);
        this.taskNameArr = res.data.taskNameArr;
        this.finishTaskArr = res.data.finishTaskArr;
        this.notfinishTaskArr = res.data.notfinishTaskArr;
      })
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  .task-status {
    // 增加任务状态区域的宽度和高度
    padding: 20px;
    // 如果需要，还可以在这里调整其他样式
    width: 1000px; // 例如，增加宽度
    height: 400px; // 增加高度
}
.studentMap{
   // 增加任务状态区域的宽度和高度
   padding: 100px;
    // 如果需要，还可以在这里调整其他样式
    width: 1000px; // 例如，增加宽度
    height: 400px; // 增加高度
}

}
</style>

