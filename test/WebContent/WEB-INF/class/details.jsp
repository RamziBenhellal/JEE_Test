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
<title>${ data.get("titel") }</title>
</head>
<body>
	<div class="container">
		<%@ include file="../layouts/header.jsp"%>

		<c:choose>
			<c:when test="${!empty error }">
				<div class="alert alert-danger" role="alert">${ error }</div>
			</c:when>

			<c:when test="${ empty error }">

				<div class="row">
					<div class="col-md-6 col-md-offset-1">
						<h2 class="col-md-offset-3">Class Informations</h2>
						<dl class="row">
							<dt class="col-sm-3">Class Code :</dt>
							<dd class="col-sm-9">${ c.classCode }</dd>

							<dt class="col-sm-3">Students Number :</dt>
							<dd class="col-sm-9">${ c.studentsNumber }</dd>

							<dt class="col-sm-3">Speciality :</dt>
							<dd class="col-sm-9">${ c.specialty }</dd>
						</dl>

						<ul class="list-inline">
							<li class="list-inline-item"><a class="btn btn-primary"
								href="/test/class/edit?id=${ c.classCode }" role="button"><span
									class="glyphicon glyphicon-edit"></span>&nbsp;Edit</a></li>
							<li class="list-inline-item">
								<form action="/test/class/details?id=${ c.classCode }"
									method="post">
									<div class="form-group">
										<input class="btn btn-danger" type="submit" value="Delete" />
									</div>
								</form>
							</li>
						</ul>
					</div>
					<c:if test="${!empty teachers }">
						<div class="col-md-6 col-md-offset-1">
							<h3 class="col-md-offset-1">Teachers :</h3>
							<ul class="list-group">
								<c:forEach items="${ teachers }" var="teacher">
									<li class="list-group-item">${teacher.firstname}&nbsp;${ teacher.lastname}
										: ${ teacher.module.module}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<c:if test="${!empty students }">
						<div class="col-md-10 col-md-offset-1">
							<h3 class="col-md-offset-1">Students :</h3>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Serial Number</th>
										<th>Birth Date</th>
										<th>Speciality</th>
										<th>Class</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${students }" var="student">
										<tr>
											<td>${student.firstname }</td>
											<td>${student.lastname }</td>
											<td>${student.serialNumber }</td>
											<td>${student.birthDate }</td>
											<td>${student.specialty }</td>
											<td>${student.sClass.toString() }</td>
											<td><a
												href="/test/student/details?id=${student.serialNumber }">Details</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</div>
			</c:when>
		</c:choose>


		<%-- <label>Serial Number</label>
<b> ${ student.serialNumber }</b>
<p><c:out value="Goood Bye"></c:out></p>
<p><c:out value="Ramzi" default="No Name"></c:out></p>
<c:set var="var1" value="new variable"></c:set>
<c:out value="${var1 }"></c:out>
<c:set target="${student }" property="firstname" value="Ramzinho"></c:set>
<c:out value="${student.firstname }"></c:out>
<c:remove var="var1"/>

<c:choose>
</c:choose> --%>


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