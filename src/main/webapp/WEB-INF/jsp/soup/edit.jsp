<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>


<html>
<head>

    <title>添加新内容</title>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.form.min.js"></script>

</head>
<body>

<div class="col-xs-12 col-md-8 col-md-offset-2" style="margin-top: 20px;">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">添加新内容</h3>
        </div>
        <div class="panel-body">
            <form id="uploadForm" role="form" enctype="multipart/form-data">

                <div class="form-group">
                    <label>内容</label> <input required="required" name="content"
                                             class="form-control" id="content" placeholder="请添加内容"/>
                </div>

                <div class="form-group">
                    <label>添加图片</label> <input type="file" name="pic" id="pic"
                                               onchange="javascript:previewImage(this)"/>
                </div>
                <button type="submit" class="btn btn-default btn-primary"
                        id="sendButton">提交
                </button>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        var options = {
            type: 'POST',
            url: '${ctx}/api2/updatesoup.json',
            success: showResponse,
            dataType: 'json',
            error: function (xhr, status, err) {
                alert("操作失败");
            }
        };
        $("#uploadForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false; //防止表单自动提交
        });

    });
</script>
<script type="text/javascript">
    function showResponse(responseText, statusText, xhr, $form) {
        if (responseText.code == 1) {
            /**
             * 请求成功后的操作
             */
            alert("发表成功！");
        } else {
            alert("抱歉，发表失败！");
        }
    }
</script>
<script type="text/javascript">
    function previewImage(obj) {

        var len = obj.files.length;
        //alert("len="+len);
        var file = obj.files;

        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file[0])
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file[0])
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file[0])
        }

        var html = "";
        if (file[0].name.indexOf(".mp4") >= 0) {
            html = '<video src="' + url + '" controls="controls" ></video>';
        } else {
            $("#teamLogo").remove();
            html = '<img src="' + url + '" " width="100px" id = "teamLogo"/>';
        }
        $(html).insertBefore(obj);

    };
</script>

</body>
</html>