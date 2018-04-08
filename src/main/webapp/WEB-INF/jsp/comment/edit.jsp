<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

    <title>添加评论</title>

</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">添加评论</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="row">
            <div class="col-lg-12">
                <form action="${ctx }/updatecomment" method="post" role="form">
                    <div class="form-group">
                        <label>内容</label> <input required="required" name="content"
                                                 class="form-control" placeholder="请添加内容"/>
                    </div>
                    <div class="form-group">
                        <label>id</label> <input required="required" name="soupid"
                                                 class="form-control" placeholder="请添加内容id"/>
                    </div>
                    <div class="form-group">
                        <label>用户名</label> <input required="required" name="userid"
                                                  class="form-control" placeholder="请添加用户名"/>
                    </div>
                    <button type="submit" class="btn btn-default btn-primary">提交</button>
                </form>
            </div>

        </div>
        <!-- /.row (nested) -->
    </div>
    <!-- /.col-lg-12 -->
</div>


</body>
</html>