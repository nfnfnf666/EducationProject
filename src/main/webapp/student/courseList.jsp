<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教学辅助系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="layuiadmin/style/template.css" media="all">
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <li class="layui-nav-item" lay-unselect>
                    <a lay-href="app/message/index.html" layadmin-event="message" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>
                        <!-- 如果有新消息，则显示小圆点 -->
                        <%--<span class="layui-badge-dot"></span>--%>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite><img src="${pageContext.request.contextPath}/image/${studentList.get(0).studentImg}" class="layui-nav-img">${studentList.get(0).studentName}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="user/info.jsp">基本资料</a></dd>
                        <dd><a lay-href="user/password.jsp">修改密码</a></dd>
                        <hr>
                        <dd layadmin-event="logout" style="text-align: center;"><a>退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
            </ul>
        </div>

        <br>
        <br>
        <br>

        <div class="layui-fluid layadmin-maillist-fluid">
            <div class="layui-row layui-col-space15">
                <c:forEach items="${courseList}" var="course">
                    <div class="layui-col-md4 layui-col-sm6">
                        <div class="layadmin-contact-box" >
                            <div class="layui-col-md4 layui-col-sm6">
                                <a href="courseHome.do?studentId=${studentList.get(0).studentId}&courseId=${course.courseId}">
                                    <div class="layadmin-text-center">
                                        <img src="${pageContext.request.contextPath}/image/course.jpg">
                                    </div>
                                </a>
                            </div>

                            <div class="layui-col-md8 layadmin-padding-left20 layui-col-sm6">
                                <a href="courseHome.do?studentId=${studentList.get(0).studentId}&courseId=${course.courseId}">
                                    <h3 class="layadmin-title">
                                        <strong>${course.courseName}</strong>
                                    </h3>
                                </a>
                                <div class="layadmin-address">
                                    <a href="courseHome.do?studentId=${studentList.get(0).studentId}&courseId=${course.courseId}">
                                        ${course.courseDesc}
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>


        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');

</script>
</body>
</html>



