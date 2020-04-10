<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>回帖列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline layuiadmin-input-useradmin">
                    <label class="layui-form-label">回帖ID</label>
                    <div class="layui-input-block">
                        <input type="text" id="demoReload" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">回复人编号</label>
                    <div class="layui-input-block">
                        <input type="text" id="demoReload1" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">回复内容</label>
                    <div class="layui-input-block">
                        <input type="text" id="demoReload2" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" data-type="reload">搜索</button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-btn-group test-table-operate-btn" style="margin-bottom: 10px;">
                <button class="layui-btn layui-btn-sm" data-type="getCheckData">获取选中行数据</button>
                <button class="layui-btn layui-btn-sm" data-type="getCheckLength">获取选中数目</button>
                <button class="layui-btn layui-btn-sm" data-type="isAll">验证是否全选</button>
            </div>

            <table class="layui-hide" id="test-table-operate" lay-filter="test-table-operate"></table>

            <script type="text/html" id="test-table-operate-barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
    }).use(['index', 'forum', 'table'], function(){
        var $ = layui.$
            ,form = layui.form
            ,table = layui.table;

        table.render({
            elem: '#test-table-operate'
            ,url: '../replyList.do'
            ,cols: [[
                {type:'checkbox', fixed: 'left'}
                ,{field:'replyId', title: '回帖编号', sort: true, fixed: 'left'}
                ,{field:'topicId', title: '帖子编号', sort: true}
                ,{field:'replyerId', title: '回复者编号', sort: true}
                ,{field:'replyContent', title: '回复内容'}
                ,{field:'replyTime', title: '回复时间'}
                ,{width:160, align:'center', fixed: 'right', toolbar: '#test-table-operate-barDemo'}
            ]]
            ,page: true
        });

        //监听工具条
        table.on('tool(test-table-operate)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定要删除吗？', {  //打开一个询问框
                    btn: ['删除','取消'] //按钮
                }, function(){
                    $.ajax({
                        url:"../delReply.do",
                        datatype:"json",
                        data:{replyId:data.replyId},
                        parseData: function(res){ //res 即为原始返回的jsom字符串数据
                            return {
                                "count": res.count, //解析数据长度
                            };
                        },
                        type:"post",
                        success:function (da) {
                            var da=JSON.parse(da);
                            if(da.count>0){
                                layer.msg('删除成功', {icon: 1});
                            }else {
                                layer.msg('删除失败', {icon: 1});
                            }
                        }
                    })
                    //删除数据成功，怎么刷新页面
                    location.reload();
                }, function(){
                    layer.msg('已取消', {
                        //layer.close(),
                        time: 2000, //2s后自动关闭
                        //btn: ['明白了', '知道了']
                    });
                })

            } else if(obj.event === 'edit'){
                parent.layer.open({   //打开一个弹出层
                    title: '修改回复',   //弹出层的标题
                    maxmin: true,
                    type: 2,
                    content: 'uploadReply.do?replyId='+data.replyId, //要访问的地址
                    area: ['800px', '500px'],

                    end: function () { //表示弹出层关闭的时候的代码块
                        location.reload();
                    }
                });
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                var demoReload1 = $('#demoReload1');
                var demoReload2 = $('#demoReload2');

                //执行重载
                table.reload('test-table-operate', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        reply_id: demoReload.val()
                        ,replyer_id: demoReload1.val()
                        ,reply_content: demoReload2.val()
                    }
                });
            }
            ,getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('test-table-operate')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('test-table-operate')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('test-table-operate');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.test-table-operate-btn .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>

