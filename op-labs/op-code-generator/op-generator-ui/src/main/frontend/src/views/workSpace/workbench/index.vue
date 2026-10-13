<template>
  <lay-container fluid="true" style="padding: 10px">
    <lay-row space="10">
      <lay-col md="24" sm="24" xs="24">
        <lay-row :space="10">
          <lay-col :md="24">
            <lay-table id="tableName" :columns="columns" :data-source="tableModelList" :default-toolbar="true" v-model:selectedKeys="selectedKeys3">
              <template v-slot:expand="{ data }">
                <div class="expand-content">
                  <lay-table :columns="tableColumns" :data-source="data.columnGenModelList">
                    <template #comment="{ row }">
                      <lay-input v-model="row.comment" />
                    </template>

                    <template #ignoreVo="{ row }">
                      <lay-switch v-model="row.ignoreVo"></lay-switch>
                    </template>

                    <template #ignoreCreator="{ row }">
                      <lay-switch v-model="row.ignoreCreator"></lay-switch>
                    </template>

                    <template #ignoreUpdater="{ row }">
                      <lay-switch v-model="row.ignoreUpdater"></lay-switch>
                    </template>
                    <template #queryItem="{ row }">
                      <lay-switch v-model="row.queryItem"></lay-switch>
                    </template>
                    <template #customStr="{ row }">
                      <lay-textarea v-model="row.customStr">
                      </lay-textarea>
                    </template>
                  </lay-table>
                </div>
              </template>
            </lay-table>
          </lay-col>
        </lay-row>
      </lay-col>
<!--      <lay-col md="6" sm="6" xs="24">-->
<!--        <lay-row :space="10">-->
<!--          <lay-col :md="24">-->
<!--            <lay-card>-->
<!--              <template v-slot:title>-->
<!--                了解更多-->
<!--              </template>-->
<!--              <div class="links">-->
<!--                <a>编程思想</a>-->
<!--                <a>设计模式</a>-->
<!--                <a>框架学习</a>-->
<!--                <a>源码操作</a>-->
<!--                <a>IDEA 插件</a>-->
<!--                <a>云效玩法</a>-->
<!--                <a>编程规范</a>-->
<!--                <a>文档阅读</a>-->
<!--              </div>-->
<!--            </lay-card>-->
<!--          </lay-col>-->
<!--          <lay-col :md="24">-->
<!--            <lay-card>-->
<!--              <template v-slot:title>-->
<!--                更新说明-->
<!--              </template>-->
<!--              <div id="main" ref="mainRef">-->
<!--                1.添加自动生成逻辑，配置转化模型-->
<!--              </div>-->
<!--            </lay-card>-->
<!--          </lay-col>-->
<!--          <lay-col :md="24">-->
<!--            <lay-card>-->
<!--              <template #title>-->
<!--                我想进步-->
<!--              </template>-->
<!--              <a class="news">直达B站</a>-->
<!--            </lay-card>-->
<!--          </lay-col>-->
<!--        </lay-row>-->
<!--      </lay-col>-->
    </lay-row>
    <lay-row>
      <lay-col md="4" sm="12" xs="24" md-offset="8"><div class="btn-div">
        <lay-button type="primary" @click="codegen">代码生成</lay-button>
      </div></lay-col>
    </lay-row>
  </lay-container>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import { getTableList ,doCodeGen} from '../../../api/module/table';
import {layer} from "@layui/layer-vue";

export default defineComponent({
  setup() {

    const tableModelList = ref([]);

    const columns = [
      { title:"选项", width:"30px", key:"tableName" ,type: "checkbox",fixed: "left"},
      { title:"表名", width:"40px", key:"tableName"},
      { title:"表注释", width:"80px", key:"tableComment" },
      { title:"表编码", width:"80px", key:"collation" },
      { title:"表引擎", width:"80px", key:"engine" },
      { title:"数据库", width:"80px", key:"database" }
    ]

    const tableColumns = [
      { title:"列名", width:"20px", key:"name"},
      { title:"数据类型", width:"20px", key:"dataType" },
      { title:"注释", width:"80px", key:"comment",customSlot:"comment" },
      { title:"VO忽略", width:"40px", key:"ignoreVo",customSlot:"ignoreVo"},
      { title:"Creator忽略", width:"40px", key:"ignoreCreator" ,customSlot:"ignoreCreator"},
      { title:"Updater忽略", width:"40px", key:"ignoreUpdater" ,customSlot:"ignoreUpdater"},
      { title:"查询项", width:"40px", key:"queryItem" ,customSlot:"queryItem"},
      { title:"自定义字符", width:"120px", key:"customStr" ,customSlot:"customStr"}
    ]

    onMounted(async () => {
      const res = await getTableList();
      tableModelList.value = res.result
    })

    const codegen = async () => {
      let res = await doCodeGen({"modelList":tableModelList.value});
      layer.msg(res.msg);
    }
    const expandKeys2 = ref(['tableName'])
    const defaultExpandAll2 = ref(false)
    const selectedKeys3 = ref([]);

    return {
      columns,
      tableColumns,
      expandKeys2,
      tableModelList,
      defaultExpandAll2,
      selectedKeys3,
      codegen
    }
  },
});

</script>

<style lang="less" scoped>

.expand-content {
  width: 100%;
  padding: 0px 20px 20px 0px;
  .layui-progress {
    margin-top: 24px;
  }
}

.btn-div {
  margin-top:12px;
}
.project-grids {
  :deep(.layui-card-body) {
    padding: 0;
  }
}

.project-grid {
  padding: 24px;
  background-color: #f8f8f8;
  margin: 10px;
  color: #777;

  .project-grid-title {
    padding-bottom: 10px;

    i {
      margin-right: 10px;
      font-size: 24px;
      color: #009688;
    }

    a {
      line-height: 24px;
      font-size: 16px;
      vertical-align: top;
    }
  }

  .project-grid-center {
    height: 44px;
    line-height: 22px;
    margin-bottom: 10px;
    overflow: hidden;
  }

  .project-grid-footer {
    position: relative;

    a {
      color: #777;
      font-size: 12px;
      text-overflow: ellipsis;
      word-break: break-all;
    }

    span {
      color: #ccc;
      font-size: 12px;
      position: absolute;
      right: 0;
    }
  }
}


.dynamic {
  padding: 15px 0;
  border-bottom: 1px solid #eee;
  display: -webkit-flex;
  display: flex;

  .layui-status-img {
    width: 32px;
    height: 32px;
    border-radius: 50%;
    background-color: #009688;
    margin-right: 15px;
  }

  a {
    color: #01aaed;
  }

  span {
    color: #bbb;
  }
}

.links {
  padding: 10px
}

.links a {
  width: 25%;
  font-size: 14px;
  margin-top: 8px;
  margin-bottom: 8px;
  display: inline-block;
  color: #666;
}

.news {
  display: block;
  line-height: 60px;
  text-align: center;
  background-color: #009688 !important;
  color: #fff !important;
  margin-bottom: 10px;
}

#main {
  height: 300px;
  width: 100%;
}
</style>
