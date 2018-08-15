<!DOCTYPE html>
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
<title>Edit Student</title>
</head>
<body>
	<div class="container ">

		<%@include file="../layouts/header.jsp"%>
		<div class="row">
			<div class="col-md-4 col-md-offset-3">


				<c:if test="${ !empty error }">
					<div class="alert alert-danger" role="alert">${error }</div>
				</c:if>
				<form action="/test/student/edit?id=${ student.serialNumber }"
					method="post">
					<h3 class="col-md-offset-3">Edit Student Informations</h3>
					<div class="form-group ">
						<label for="firstname">First Name : </label> <input type="text"
							class="form-control" value="${ student.firstname }"
							id="firstname" name="firstname" placeholder="Your First Name">
					</div>
					<div class="form-group ">
						<label for="lastname">Last Name : </label> <input type="text"
							class="form-control" value="${ student.lastname }" id="lastname"
							name="lastname" placeholder="Your Last Name">
					</div>

					<input type="hidden" value="${ student.serialNumber }" id="serial"
						name="serial" placeholder="Your Serial Number">
					<div class="form-group ">
						<label for="birthdate">Birth Date : </label> <input type="date"
							class="form-control" id="birthdate" name="birthdate"
							value="${ student.birthDate }" placeholder="Birth Date">
					</div>
					<label for="specialty">Speciality : </label>
					<div class="form-group ">
						<select class="form-control" id="specialty" name="specialty">
							<option value="${student.specialty }">${student.specialty }</option>
							<option value="science">Science</option>
							<option value="litheratur">Litheratur</option>
						</select>
					</div>
					<div class="form-group">
						<label for="sclass">Class : </label> <select
							class="form-control" id="sclass" name="sclass">
							<option value="${student.sClass.toString() }">${student.sClass.toString() }</option>
							<c:forEach items="${classes }" var="c">
								<option value="${c.classCode }">${c.classCode }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group ">
						<input type="submit" class="form-control" value="Save">
					</div>
				</form>
			</div>
		</div>

		<%@include file="../layouts/footer.jsp"%>
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