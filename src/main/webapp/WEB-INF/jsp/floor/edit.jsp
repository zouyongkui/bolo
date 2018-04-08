<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="menu" value="tags" />
<!DOCTYPE html>
<html>
<head>

<title>添加新的楼层</title>

</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加楼层</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-12">
					<form action="${ctx}/community/updateFloor" method="post"
						role="form">
						<div class="form-group">
							<label>用户id</label> <input required="required" name="userId"
								class="form-control" placeholder="请添加内容" />
						</div>
						<div class="form-group">
							<label>主题id</label> <input required="required" name="communityId"
								class="form-control" placeholder="请添加内容" />
						</div>
						<div class="form-group">
							<label>内容</label> <input required="required" name="content"
								class="form-control" placeholder="请添加内容" />
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