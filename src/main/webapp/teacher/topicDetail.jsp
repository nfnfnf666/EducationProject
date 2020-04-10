<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>主题详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="res/layui/css/layui.css">
    <link rel="stylesheet" href="res/css/global.css">
</head>
<body>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12 content detail">
            <div class="fly-panel detail-box">
                <h1>${topicVo.topic.topicTitle}</h1>
                <div class="detail-about">
                    <a class="fly-avatar" href="">
                        <img src="image/${topicVo.student.studentImg}" alt="${topicVo.student.studentName}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="" class="fly-link">
                            <cite>${topicVo.student.studentName}</cite>
                        </a>
                        <span>${topicVo.topic.topicPublishedTime}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                    </div>
                </div>
                <div class="detail-body photos">
                    <p>
                        ${topicVo.topic.topicContent}
                    </p>
                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <c:forEach items="${replyVoList}" var="replyVo">
                        <li data-id="111" class="jieda-daan">
                            <a name="item-1111111111"></a>
                            <div class="detail-about detail-about-reply">
                                <a class="fly-avatar" href="">
                                    <img src="image/${replyVo.student.studentImg}" alt="${replyVo.student.studentName}">
                                </a>
                                <div class="fly-detail-user">
                                    <a href="" class="fly-link">
                                        <cite>${replyVo.student.studentName}</cite>
                                    </a>
                                </div>

                                <div class="detail-hits">
                                    <span>${replyVo.reply.replyTime}</span>
                                </div>
                            </div>
                            <div class="detail-body jieda-body photos">
                              <p>${replyVo.reply.replyContent}</p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <div class="layui-form layui-form-pane">
                    <form action="addReply.do?topicId=${topicVo.topic.topicId}" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="123">
                            <button class="layui-btn" lay-submit>提交回复</button>
                        </div>
                    </form>
                </div>

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
        ,avatar: 'res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: 'res/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'face'], function(){
        var $ = layui.$
            ,fly = layui.fly;
        //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
        /*
        $('.detail-body').each(function(){
          var othis = $(this), html = othis.html();
          othis.html(fly.content(html));
        });
        */
    });
</script>

</body>
</html>
