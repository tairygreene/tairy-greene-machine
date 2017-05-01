<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h1>Create a Student</h1>
		</div>
		<div class="col-md-4"></div>
	</div>


	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<c:url var="formAction" value="/createStudent" />
			<form id="adminStudentAdminRegistration" method="POST" action="${formAction}">
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
				<div class="form-group">
					<label for="firstName">First Name: </label> <input type="text"
						id="firstName" name="firstName" placeHolder="First Name"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="lastName">Last Name: </label> <input type="text"
						id="lastName" name="lastName" placeHolder="Last Name"
						class="form-control" />
				</div>
				<div class="form-group">
					<label for="email">Email: </label> <input type="email" id="email"
						name="email" placeHolder="email" class="form-control" />
				</div>
				<div>
					<p>Cohort Selection:</p>
					<select name="cohortNumber" id="cohortNumber">
						<option value="1">Cohort 1</option>
						<option value="2">Cohort 2</option>
						<option value="3">Cohort 3</option>
						<option value="4">Cohort 4</option>
					</select> <br> <br>
				</div>
				<button type="submit" class="btn btn-lg btn-success">Submit</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />