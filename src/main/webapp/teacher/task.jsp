<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>作业管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <table class="layui-hide" id="tableDemo" lay-filter="test"></table>
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
                    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
                    <button type="button" onclick="insertTask()" class="layui-btn layui-btn-sm">新增</button>
                </div>
            </script>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="edit">查看作业详情</a>
            </script>
        </div>
    </div>
</div>

<script src="../layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'contlist', 'table'], function(){

        var table = layui.table;
        var form=layui.form;
        var layer=layui.layer;
        $ = layui.jquery;
        var d=table.render({
            elem: '#tableDemo'
            ,url: "../taskPage.do"
            ,datatype:"json"
            ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data//解析数据列表
                };
            }
            ,toolbar: '#toolbarDemo'
            ,title: '作业数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'taskId', title:'作业ID', fixed: 'left', sort: true}
                ,{field:'taskTitle', title:'作业标题', edit: 'text', fixed: 'left'}
                ,{field:'taskContent', title:'作业内容', edit: 'text', fixed: 'left'}
                ,{field:'taskPublishedTime', title:'发布时间', fixed: 'left'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: [ 'prev', 'page', 'next', 'skip','limit', 'count'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 3 //只显示 1 个连续页码
                ,limit:5    //每页显示记录数
                ,first: false //不显示首页
                ,last: false //不显示尾页
            }

            ,limits:[5,10,20,50,100]
            ,id:"testReload"
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                parent.layer.open({   //打开一个弹出层
                    title: '作业完成详情',   //弹出层的标题
                    maxmin: true,
                    type: 2,
                    content: 'teacher/completionDetail.jsp?taskId='+data.taskId, //要访问的地址
                    area: ['820px', '600px'],


                    end: function () { //表示弹出层关闭的时候的代码块
                        location.reload();
                    }
                });
            }
        });


        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>

<script>
    //新增数据
    function insertTask() {
        parent.layer.open({   //打开一个弹出层
            title: '新增作业',   //弹出层的标题
            maxmin: true,
            type: 2,
            content: 'teacher/insertTask.jsp', //要访问的地址
            area: ['500px', '450px'],


            end: function () { //表示弹出层关闭的时候的代码块
                location.reload();
            }
        });
    }
</script>

</body>
</html>

