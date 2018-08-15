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

<title>New Student</title>
</head>
<body>
	<div class="container ">

		<%@include file="../layouts/header.jsp"%>

		<div class="row">
			<div class="col-md-4 col-md-offset-3">

				<c:if test="${ !empty error }">
					<div class="alert alert-danger" role="alert">${error }</div>
				</c:if>
				<form action="/test/teacher/create" method="post">
					<h3 class="col-md-offset-3">Create New Teacher</h3>
					<h4 class="col-md-offset-1">Set User Informations</h4>
					<div class="form-group ">
						<label for="username">Username : </label> <input type="text"
							class="form-control" id="username" name="username"
							placeholder="Your Username">
					</div>

					<div class="form-group">
						<label for="password">Password : </label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Password">
					</div>

					<div class="form-group">
						<label for="confirm_password">Confirm Password : </label> <input type="password"
							class="form-control" id="confirm_password" name="confirm_password"
							placeholder="Confirm Password">
					</div>
					<h4 class="col-md-offset-1">Set Teacher Informations</h4>
					<div class="form-group ">
						<label for="firstname">First Name : </label> <input type="text"
							class="form-control" id="firstname" name="firstname"
							placeholder="Your First Name">
					</div>

					<div class="form-group">
						<label for="lastname">Last Name : </label> <input type="text"
							class="form-control" id="lastname" name="lastname"
							placeholder="Your Last Name">
					</div>
					
					<div class="form-group">
						<label for="module">Module : </label> <select
							class="form-control" id="module" name="module">
							<c:forEach items="${modules }" var="module">
								<option value="${module.moduleCode }">${module.moduleCode }</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="tclass">Class : </label> <select
							class="form-control" id="tclass" name="tclass">
							<c:forEach items="${classes }" var="c">
								<option value="${c.classCode }">${c.classCode }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
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