<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加文档课件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

<form class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">课件名</label>
        <div class="layui-input-inline">
            <input type="text" name="coursewareName" lay-verify="required" placeholder="请输入课件名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">课件地址</label>
        <div class="layui-input-inline">
            <input type="text" name="coursewareUrl" placeholder="请上传文档课件" autocomplete="off" class="layui-input" >
        </div>
        <button style="float: left;" type="button" class="layui-btn" id="layuiadmin-upload-useradmin">上传课件</button>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <button class="layui-btn" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit">确认</button>
        </div>
    </div>
</form>

<script src="../layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '../layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'upload'], function(){
        var $ = layui.$
            ,form = layui.form
            ,upload = layui.upload ;

        upload.render({
            elem: '#layuiadmin-upload-useradmin'
            ,url: '${pageContext.request.contextPath}/uploadDocument.do'
            ,accept: 'file'
            ,method: 'get'
            ,acceptMime: 'pdf'
            ,done: function(res){
                $(this.item).prev("div").children("input").val(res.data.src)
            }
        });

        //监听提交
        form.on('submit(LAY-user-front-submit)', function(data){
            $.ajax({
                url: "${pageContext.request.contextPath}/insertDocument.do",
                datatype: "json",
                type: 'post',
                data: $("#layuiadmin-form-useradmin").serialize(),
                parseData: function(res){ //res 即为原始返回的数据
                    return {
                        "count": res.count, //解析数据长度
                        "data":res.data,
                    };
                },
                success: function (da) {
                    if (da.data > 0) {
                        layer.msg("添加成功");
                    } else {
                        layer.msg("添加失败");
                    }
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

                    parent.layer.close(index); //再执行关闭
                }
            });
            return false;
        });
    })
</script>
</body>
</html>
