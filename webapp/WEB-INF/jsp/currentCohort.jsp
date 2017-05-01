<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="container">



	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<h1>Current Cohort Roster</h1>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">








			<table style="width: 100%"
				class="table-responsive table-bordered table-striped">
				<tr>
					<th>Cohort</th>
					<th colspan="2">Name</th>
					<th colspan="2">Email</th>
					<th colspan="2">Phone Number</th>
					<th>Student Id</th>
				</tr>
				<c:forEach items="${studentList}" var="student">

					<tr>
						<td><c:out value="${student.cohort}" /></td>
						<c:choose>
							<c:when test="${empty currentUser}">

								<td colspan="2"><c:out value="${student.firstName}" /> <c:out
										value="${student.lastName}" /></td>
							</c:when>
							<c:otherwise>
								<c:url value="/studentProfileById" var="studentProfileByIdURL">
									<c:param name="studentId" value="${student.studentId}" />
								</c:url>


								<td id="name-cohort-table" colspan="2"><a
									href="${studentProfileByIdURL}"><c:out
											value="${student.firstName}" /> <c:out
											value="${student.lastName}" /></a></td>
							</c:otherwise>
						</c:choose>
						<td colspan="2"><c:out value="${student.emailId}" /></td>
						<td colspan="2"><c:out value="${student.phoneNumber}" /></td>
						<c:choose>
							<c:when test="${empty currentUser}">
								<td></td>
							</c:when>
							<c:otherwise>
								<td><c:out value="${student.studentId}" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>

		</div>
		<div class="col-md-1"></div>
	</div>

</div>





<c:import url="/WEB-INF/jsp/footer.jsp" />





