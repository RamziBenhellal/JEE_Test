<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
	

<title>Teachers Table</title>
</head>
<body>
	<div class="container">
		<%@ include file="../layouts/header.jsp"%>
		<c:choose>
			<c:when test="${!empty error }">
				<div class="alert alert-danger" role="alert">${ error }</div>
			</c:when>
			<c:when test="${empty error }">
			<c:if test="${!empty success }">
				<div class="alert alert-success" role="alert">${ sessionScope.success }<span class="glyphicon glyphicon-ok"></span></div>
			</c:if>
				<h2>Teachers Table</h2>
				<a class="btn btn-primary float-right" href="/test/teacher/create"
					role="button"><span class="glyphicon glyphicon-plus"></span> &nbsp;Add New</a>
				<table class="table">
					<thead>
						<tr>
						    <th>Teacher Code</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Module</th>
							<th>Class</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${teachers }" var="teacher">
							<tr>
							    <td>${teacher.codeTeacher }</td>
								<td>${teacher.firstname }</td>
								<td>${teacher.lastname }</td>
								<td>${teacher.module.toString() }</td>
								<td>${teacher.tClass.toString() }</td>
								<td><a
									href="/test/teacher/details?id=${teacher.codeTeacher }">Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
		</c:choose>
		<%@ include file="../layouts/footer.jsp"%>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>

</body>
</html>