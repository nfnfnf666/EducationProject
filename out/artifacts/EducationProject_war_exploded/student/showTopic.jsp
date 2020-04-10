<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>讨论区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="res/layui/css/layui.css">
    <link rel="stylesheet" href="res/css/global.css">
</head>
<body>

<div class="layui-container">
    <div class="layui-row layui-col-space5">
        <div class="layui-col-md12">
            <div class="fly-panel" style="margin-bottom: 0;">

                <ul class="fly-list">
                    <c:forEach items="${topicVoList}" var="topicVo">
                        <li>
                            <a href="topicDetail.do?topicId=${topicVo.topic.topicId}" class="fly-avatar">
                                <img src="image/${topicVo.student.studentImg}" width="42" height="42" alt="${topicVo.student.studentName}">
                            </a>
                            <h2>
                                <a class="layui-badge">动态</a>
                                <a href="topicDetail.do?topicId=${topicVo.topic.topicId}">${topicVo.topic.topicTitle}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="topicDetail.do?topicId=${topicVo.topic.topicId}" link>
                                    <cite>${topicVo.student.studentName}</cite>
                                </a>
                                <span>${topicVo.topic.topicPublishedTime}</span>
                                <span class="fly-list-nums">
                                    <i class="iconfont icon-pinglun1" title="回答"></i> ${topicVo.topic.replyNumber}
                                </span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <!-- <div class="fly-none">没有相关数据</div> -->


            </div>
        </div>
    </div>
</div>


<script src="res/layui/layui.js"></script>
<script>
    layui.cache.page = 'jie';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '../../res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: 'res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>