<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style type="text/css">
        video{width: auto;max-height: 100%}
    </style>
</head>
<body>
<!--第一种 引入视频链接 -->
<<%--div id="app" style="width: 500px;height: 400px" align="center">
    <video controls>
        <source src="courseware/video/test.mp4" type="video/mp4"></source>
    </video>
</div>--%>
<!--第二种 嵌入iframe 播放外部视频 -->
<div align="center">
    <iframe src='courseware/video/test.mp4' allowfullscreen></iframe>
</div>

<script>

</script>
</body>
</html>
