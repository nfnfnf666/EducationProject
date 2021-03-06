<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改教师信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

<form class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;">
    <input type="text" name="teacherId" class="layui-input layui-hide" value="${teacher.teacherId}">
    <div class="layui-form-item">
        <label class="layui-form-label">教师姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="teacherName" lay-verify="required" value="${teacher.teacherName}" placeholder="请输入教师姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录密码</label>
        <div class="layui-input-inline">
            <input type="text" name="teacherPwd" lay-verify="required" value="${teacher.teacherPwd}" placeholder="请输入登录密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号码</label>
        <div class="layui-input-inline">
            <input type="text" name="teacherPhone" lay-verify="phone" value="${teacher.teacherPhone}" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱地址</label>
        <div class="layui-input-inline">
            <input type="text" name="teacherEmail" lay-verify="email" value="${teacher.teacherEmail}" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">教师头像</label>
        <div class="layui-input-inline">
            <input type="text" name="teacherImg" value="${teacher.teacherImg}" placeholder="请上传图片" autocomplete="off" class="layui-input" >
        </div>
        <button style="float: left;" type="button" class="layui-btn" id="layuiadmin-upload-useradmin">上传图片</button>
    </div>
    <div class="layui-form-item" lay-filter="sex">
        <label class="layui-form-label">选择性别</label>
        <div class="layui-input-block">
            <input type="radio" name="teacherSex" value="男" title="男" <c:if test="${teacher.teacherSex == '男'}"> checked </c:if>>
            <input type="radio" name="teacherSex" value="女" title="女" <c:if test="${teacher.teacherSex == '女'}"> checked </c:if>>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <button class="layui-btn" lay-submit lay-filter="LAY-user-front-submit" id="LAY-user-front-submit">确认</button>
        </div>
    </div>
</form>

<script src="layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'upload'], function(){
        var $ = layui.$
            ,form = layui.form
            ,upload = layui.upload ;

        upload.render({
            elem: '#layuiadmin-upload-useradmin'
            ,url: '${pageContext.request.contextPath}/teacherUploadImage.do'
            ,accept: 'images'
            ,method: 'get'
            ,acceptMime: 'image/*'
            ,done: function(res){
                $(this.item).prev("div").children("input").val(res.data.src)
            }
        });

        //监听提交
        form.on('submit(LAY-user-front-submit)', function(data){
            $.ajax({
                url: "${pageContext.request.contextPath}/updateTeacher.do",
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
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
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
