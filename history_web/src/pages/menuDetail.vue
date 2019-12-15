<template>
  <div style="width:95%">
    <div class="button" style="width:3%;float:right;">
      <P><el-button class="el-icon-plus" @click.prevent="addRow()"></el-button></P>
      <p><el-button class="el-icon-minus" @click.prevent="delData()"></el-button></p>
      <p><el-button class="el-icon-edit" @click.prevent="updateData()"></el-button></p>
    </div>
    <div class="table">
      <el-table
        :data="tableData"
        ref="table"
        tooltip-effect="dark"
        border
        stripe
        style="width: 95%"
        @selection-change='selectRow'>
        <el-table-column type="selection" width="45" align="center"></el-table-column>
        <el-table-column label="序号"  type="index" width="60" align="center">
            <template slot-scope="scope">
                <el-input v-model="scope.row.id" readonly></el-input>
            </template>
        </el-table-column>
        <el-table-column  label="名称" align="center">
          <template slot-scope="scope">
              <el-input v-model="scope.row.name"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="图标">
          <template slot-scope="scope">
            <el-input v-model="scope.row.icon"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="url">
          <template slot-scope="scope">
            <el-input v-model="scope.row.url" v-show="scope.row.url"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="父菜单">
          <template slot-scope="scope">
            <el-input v-model="scope.row.parentid"></el-input>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
 
<script> 
import axios from 'axios'
export default {
    data () {
      return {
        tableData: [],
        selectlistRow: []
      }
    },
    mounted() {
        this.getData()
    },
    methods: {
        // 获取menu的详细信息
        getData() {
            var url = "/menu/getMenus"
            this.$ajax.get(url).then(res => {
                this.tableData = res.data
            }).catch(error => {
                console.log(error.message)
            })
        },
        // 获取表格选中时的数据
        selectRow (val) {
            this.selectlistRow = val
        },
        // 增加行
        addRow () {
            var list = {
                id: this.id,
                name: this.name,
                icon: this.icon,
                url: this.url,
                parentid: this.parentid
            }
            this.tableData.unshift(list)
            
        },
        // 删除方法
        // 删除选中行
        delData () {
            for (let i = 0; i < this.selectlistRow.length; i++) {
            let val = this.selectlistRow
            // 获取选中行的索引的方法
            // 遍历表格中tableData数据和选中的val数据，比较它们的rowNum,相等则输出选中行的索引
            // rowNum的作用主要是为了让每一行有一个唯一的数据，方便比较，可以根据个人的开发需求从后台传入特定的数据
            val.forEach((val, index) => {
                this.tableData.forEach((v, i) => {
                if (val.id === v.id) {
                    // i 为选中的索引
                    this.tableData.splice(i, 1)
                }
                })
            })
            }
            // 删除完数据之后清除勾选框
            //this.$refs.tableData.clearSelection()
        },
        updateData() {
            let arr = []
            let val = this.selectlistRow
            val.forEach((select, index) => {
                this.tableData.forEach((v, i) => {
                if (select.id === v.id) {
                    // i 为选中的索引
                    arr.push(v)
                }
                })
            })           
            if (arr.length == 0) {
                return
            }
            var api = "/menu/updateData"
            this.$ajax({
                method: 'post',
                url: api,
                data: this.$qs.stringify({
                    menu: JSON.stringify(arr)
                })
            })
            .then(res => {
                alert(res.data)
            }).catch(error => {
                console.log(error.message)
            })
            console.log(arr)
        }
    }
}
</script>