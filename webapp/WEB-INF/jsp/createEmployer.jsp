<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		<h1>Create New Employer</h1>
	</div>
	<div class="col-md-4"></div>
</div>


<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
		<c:url var="formAction" value="/createEmployer" />
		<form id="adminEmployerRegistration" method="POST" action="${formAction}">
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
			<div class="form-group">
				<label for="employerName">Employer Name: </label> <input type="text"
					id="employerName" name="employerName" placeHolder="Employer Name"
					class="form-control" />
			</div>
			<div class="form-group">
				<label for="email">Email: </label> <input type="email" id="email"
					name="email" placeHolder="email" class="form-control" />
			</div>
			<button type="submit" class="btn btn-lg btn-success">Submit</button>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>


<c:import url="/WEB-INF/jsp/footer.jsp" />