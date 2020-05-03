<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><spring:message code="trade.system.name"/></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/save">New Trade</a></li>
					<li><a href="/1">1</a></li>
					<li><a href="/2">2</a></li>
					<li><a href="/3">3</a></li>
					<li><a href="/4">4</a></li>
					<li><a href="/5">5</a></li>
					<li><a href="/6">6</a></li>
					<li><a href="/7">7</a></li>
					<li><a href="/8">8</a></li>
					<li><a href="/9">9</a></li>
					<li><a href="/10">10</a></li>
					<li><a href="/11">11</a></li>
					<li><a href="/12">12</a></li>
					<li><a href="/13">13</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1><spring:message code="trade.plural"/></h1>
			<div class="panel panel-default">
			  
		  	<!-- Table -->
		  	<table class="table">
		    	<thead>
		    		<tr>
		    			<td><spring:message code="trade.id"/></td>
		    			<td><spring:message code="trade.Version"/></td>
		    			<td><spring:message code="trade.CounterPartyId"/></td>
		    			<td><spring:message code="trade.BookId"/></td>
		    			<td><spring:message code="trade.MaturityDate"/></td>
		    			<td><spring:message code="trade.Expiry"/></td>
		    			<td><spring:message code="trade.CreatedDate"/></td>
		    			<td><spring:message code="trade.ModifiedDate"/> </td>
		    			<td><spring:message code="trade.Action"/></td>
		    		</tr>
		    	</thead>
		    	<tbody>
		    		
		    		<c:forEach items="${trades}" var="trade">
		    			<tr>
							<th>${trade.tradeId}</th>
							<th>${trade.version}</th>
							<th>${trade.counterPartyID}</th>
							<th>${trade.bookid}</th>
							<th><fmt:formatDate value="${trade.maturityDate}" type="date" pattern="${config.dateformat}"/></th>
							<th>${trade.expired}</th>
							<th><fmt:formatDate value="${trade.createdDate}" type="date" pattern="${config.dateformat}"/></th>
							<th><fmt:formatDate value="${trade.modifiedDate}" type="date" pattern="${config.dateformat}"/></th>
							<th>
								<div class="btn-group">
								  <button
								  	id="${trade.id}"
								  	style="width: 70px" 
								  	type="button" 
								  	class="btn btn-success"
								  	onclick="window.location='/save?q=${trade.id}'"><spring:message code="trade.Action.Edit"/></button>
								  <form action="/delete" onsubmit="return confirm('<spring:message code="trade.Action.Delete.Confirm"/>')">
								  	<input type="hidden" name="id" value="${trade.id}" />
								  	<button style="width: 70px" type="submit" class="btn btn-danger"><spring:message code="trade.Action.Delete"/></button>
								  </form>
								</div>
							</th>
						</tr>
					</c:forEach>
		    		
		    	</tbody>
		  	</table>
			</div>
		</div>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>