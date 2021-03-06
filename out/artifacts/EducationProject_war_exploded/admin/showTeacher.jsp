<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教师管理</title>
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
            <div class="demoTable">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">工号</label>
                        <div class="layui-input-block">
                            <input type="text" name="id" id="demoReload" placeholder="请输入" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="id" id="demoReload1" placeholder="请输入" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" data-type="reload">
                            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                        </button>
                    </div>
                    <div class="layui-inline">
                        <button type="button" class="layui-btn" id="test1">导入数据</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <table class="layui-hide" id="tableDemo" lay-filter="test"></table>
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
                    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
                    <button class="layui-btn layui-btn-sm" lay-event="delSum">批量删除</button>
                    <button class="layui-btn layui-btn-sm" lay-event="downloadExcel">数据导出</button>
                    <button type="button" onclick="insertTeacher()" class="layui-btn layui-btn-sm">新增</button>
                </div>
            </script>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>
        </div>
    </div>
</div>



<script src="../layuiadmin/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use(['table','jquery','layer','form'], function(){
        var table = layui.table;
        var form=layui.form;
        var layer=layui.layer;
        $ = layui.jquery;
        table.render({
            elem: '#tableDemo'
            ,url:'../teacherPage.do'
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
            ,title: '教师数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{type: 'numbers', fixed: 'left' ,title: '序列'}
                ,{field:'teacherId', title:'教师编号', fixed: 'left'}
                ,{field:'teacherName', title:'教师姓名', fixed: 'left', unresize: true}
                ,{field:'teacherImg', title:'教师头像', fixed: 'left', unresize: true,templet:'<div><img src="${pageContext.request.contextPath}/image/{{d.teacherImg}}" width="30" height="30"/></div>'}
                ,{field:'teacherSex', title:'性别', fixed: 'left', unresize: true}
                ,{field:'teacherPwd', title:'登录密码', fixed: 'left', unresize: true}
                ,{field:'teacherPhone', title:'手机号码', fixed: 'left', unresize: true}
                ,{field:'teacherEmail', title:'邮箱地址', fixed: 'left', unresize: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: [ 'prev', 'page', 'next', 'skip','limit', 'count'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 1 //只显示 1 个连续页码
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
                //批量删除
                case 'delSum': {
                    var data = checkStatus.data;
                    if (data.length == 0) {
                        parent.layer.alert('您尚未选择数据!');
                        return;
                    }
                    var ids = "";
                    for (var i = 0; i < data.length; i++) {
                        ids += data[i].teacherId + ",";
                    }
                    layer.confirm('您确定要删除选中数据吗？', function (index) {
                        layer.close(index);
                        $.ajax({
                            url: "${pageContext.request.contextPath}/delTeachers.do",
                            dataType: "json",
                            data: {ids: ids},
                            type: "GET",
                            parseData: function(res){ //res 即为原始返回的数据
                                return {
                                    "count": res.count, //解析数据长度
                                    "data":res.data,
                                };
                            },
                            success: function (da) {
                                layer.closeAll('loading');
                                //alert(da);
                                //alert(da.data);
                                if (da.data >0) {
                                    parent.layer.alert('数据已删除！');
                                    location.reload(true);
                                } else {
                                    parent.layer.alert("删除失败！");
                                }
                            },
                            error: function () {
                                parent.layer.alert("删除失败！");
                            }
                        });
                    });
                }
                    break;
                case 'downloadExcel':
                    var data = checkStatus.data;
                    //alert(data);
                    //layer.alert(JSON.stringify(data));
                    if (data.length == 0) {
                        parent.layer.alert('您尚未选择数据!');
                        return;
                    }
                    var ids = "";
                    for (var i = 0; i < data.length; i++) {
                        ids += data[i].teacherId + ",";
                    }
                    $.ajax({
                        url: "${pageContext.request.contextPath}/teacherDownloadExcel.do",
                        dataType: "json",
                        data: {ids: ids},    //JSON.stringify(data)
                        type: "GET",
                        parseData: function(res){ //res 即为原始返回的数据
                            return {
                                "code": res.code, //解析接口状态
                                "msg": res.msg, //解析提示文本
                                "count": res.count, //解析数据长度
                            };
                        },
                        success: function (da){
                            if (da.code ==1) {
                                parent.layer.alert('数据成功导出到：'+da.excelPath);
                                //location.reload(true);
                            } else {
                                parent.layer.alert("数据导出失败！");
                            }
                        }
                    });
                    break;
            };
        });



        //数据重载
        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                var demoReload1 = $('#demoReload1');

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        teacher_id: demoReload.val(),
                        teacher_name:demoReload1.val()
                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确定要删除吗？', {  //打开一个询问框
                    btn: ['删除','取消'] //按钮
                }, function(){
                    $.ajax({
                        url:"${pageContext.request.contextPath}/delTeacher.do",
                        datatype:"json",
                        data:{teacherId:data.teacherId},
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
                    title: '修改界面',   //弹出层的标题
                    maxmin: true,
                    type: 2,
                    content: '${pageContext.request.contextPath}/uploadTeacher.do?teacherId='+data.teacherId, //要访问的地址
                    area: ['550px', '450px'],


                    end: function () { //表示弹出层关闭的时候的代码块
                        location.reload();
                    }
                });
            }
        });
    });
</script>

<script>
    //新增数据
    function insertTeacher() {
        parent.layer.open({   //打开一个弹出层
            title: '新增教师界面',   //弹出层的标题
            maxmin: true,
            type: 2,
            content: 'admin/insertTeacher.jsp', //要访问的地址
            area: ['550px', '500px'],


            end: function () { //表示弹出层关闭的时候的代码块
                location.reload();
            }
        });
    }

    //导入表格数据
    layui.use(['element','upload'], function(){
        var upload = layui.upload;
        var element = layui.element;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: "${pageContext.request.contextPath}/teacherUploadExcel.do"
            ,accept: 'file' //普通文件
            ,exts:'xls|xlsx'
            ,method:"POST"
            ,parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                };
            }
            ,done: function(index,res){
                //alert(res);
                location.reload(true);

                //console.log(res);
            }


        });
    });

</script>


</body>
</html>
