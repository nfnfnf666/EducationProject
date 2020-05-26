<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>视频列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="layuiadmin/style/template.css" media="all">
</head>
<body>


<div class="layui-fluid layadmin-cmdlist-fluid">
    <div class="layui-row layui-col-space30">
        <c:forEach var="videoCourseware" items="${videoCoursewareList}">
            <div class="layui-col-md2 layui-col-sm4">
                <div class="cmdlist-container">
                    <button id="layer">
                        <img src="image/video.png">
                    </button>
                    <a href="javascript:;">
                        <div class="cmdlist-text">
                            <p class="info">${videoCourseware.coursewareName}</p>
                        </div>
                    </a>
                </div>
            </div>
        </c:forEach>

    </div>
</div>


<script src="layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index']);
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer
            ,$ = layui.$;
        var loadstr = '<video width="100%" height="100%"  controls="controls" autobuffer="autobuffer"  loop="loop"><source src="courseware/video/test.mp4" type="video/mp4"></source>您的浏览器不支持此种视频格式</video>';
        $('#layer').click(function () {
            layer.open({
                type: 1,
                title: '播放视频',
                content: loadstr,
            });
        })

    });

</script>
</body>
</html>