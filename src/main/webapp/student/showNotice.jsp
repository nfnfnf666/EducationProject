<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>留言板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="layuiadmin/style/template.css" media="all">
</head>
<body>

<div class="layui-fluid layadmin-message-fluid">
    <div class="layui-row">
        <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
            <c:forEach items="${noticeList}" var="notice">
                <div class="layui-card">
                    <div class="layui-card-header"><b>${notice.noticeTitle}</b></div>
                    <div class="layui-card-body">${notice.noticeContent}</div>
                    <p class="layadmin-homepage-min-font">${notice.noticePublishedTime}</p>
                </div>
            </c:forEach>
        </div>

    </div>
</div>
</div>


<script src="layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index']);
    layui.use(['layer','form'],function(){
        var form = layui.form;
        form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            return false;
        });
    })
</script>
</body>
</html>
