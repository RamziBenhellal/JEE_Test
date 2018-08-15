<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="/test">Test Project</a>

	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-link" href="/test/">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<c:if test="${!empty  sessionScope.user }">
				<c:choose>
					<c:when test="${ sessionScope.user.type.equals(\"user\")  }">
						<li class="nav-item"><a class="nav-link"
							href="/test/teacher/index">Teachers</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/test/student/index">Students</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/test/module/index">Modules</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/test/class/index">Classes</a></li>
					</c:when>
					<c:when test="${ sessionScope.user.type.equals(\"teacher\")  }">
					   <li class="nav-item"><a class="nav-link"
							href="/test/teacher/mystudents">My Students </a></li>
					</c:when>
				</c:choose>
			</c:if>
		</ul>


		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${!empty  sessionScope.user }">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							${ sessionScope.user.username }</a></li>
					<li><a href="/test/user/logout"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</c:when>

				<c:when test="${ empty  sessionScope.user }">
					<li><a href="/test/user/login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					<li><a href="/test/user/register"><span
							class="glyphicon glyphicon-collapse-up"></span> Register</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>
</nav>


