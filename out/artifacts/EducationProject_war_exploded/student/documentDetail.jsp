<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看文档</title>
    <script type="text/javascript" src="../js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.media.js"></script>
</head>
<body>
    <div id="handout_wrap_inner"></div>
</body>

<script type="text/javascript">
    $('#handout_wrap_inner').media({
        width: '100%',
        height: '100%',
        autoplay: true,
        src:'../courseware/document/jianli.pdf',
    });
</script>

</html>
