<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<script language ="javascript">

</script>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>


<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Trade Finance System</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/save">New Trade</a></li>
                    <li><a href="/1">1</a></li>
					<li><a href="/2">2</a></li>
					<li><a href="/3">3</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">

		<div class="starter-template">
			<h1>New Trade</h1>
			<form method="post" name="tradeform">
			<spring:errors path = "*" cssClass = "errorblock" element = "div" />
				<input type="hidden" name="id" value="${trade.id}" />


                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                <div class="input-group">
                                    <span class="input-group-addon" id="tradeId">Trade Id</span>
                                </div>
                            </div>
                            <div class="col-sm">
                                <input
                                    class="form-control"
                                    placeholder="Trade Id"
                                    type="text"
                                    name="tradeId"
                                    id="tradeId"
                                    value="${trade.tradeId}"
                                    oninvalid="setCustomValidity('Please Enter Trade Id');"
                                    oninput="setCustomValidity('');"

                                    />
                                    <spring:errors path="tradeId" cssClass="error" />
                           </div>
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <div class="input-group">
                                    <span class="input-group-addon" id="version">Version</span>
                                <input
                                    class="form-control"
                                    placeholder="Version"
                                    type="number"
                                    name="version"
                                    id="version"
                                    min="1"
                                    value="${trade.version}"
                                    oninvalid="setCustomValidity('Please Enter Version in number format');"
                                    oninput="setCustomValidity('');"
                                    required/>
                                    <spring:errors path="version" cssClass="error" />
                            </div>

                        </div>
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <div class="input-group">
                                    <span class="input-group-addon" id="counterPartyID">Counter-Party Id</span>

                                    <input
                                        class="form-control"
                                        placeholder="Counter-Party Id"
                                        type="text"
                                        name="counterPartyID"
                                        id="counterPartyID"
                                        value="${trade.counterPartyID}"
                                        oninvalid="setCustomValidity('Please Enter Counter-Party Id');"
                                        oninput="setCustomValidity('');"
                                        required/>
                                    <spring:errors path="counterPartyID" cssClass="error" />
                                </div>
                                </div>
                                </div>
                                </div>
                        <div class="row">
                            <div class="col-sm">
                                <div class="input-group">
                                    <span class="input-group-addon" id="bookid">Book Id</span>
                                    <select
                                        name="bookid"
                                        id="bookid"
                                        class="form-control"
                                        oninvalid="setCustomValidity('Please Enter Book Id');"
                                        oninput="setCustomValidity('');"
                                        required >

                                        <c:forEach items="${bookList}" var="book">
                                             <option value="${book.id}">${book.id}</option>
                                        </c:forEach>

                                    </select>
                                    <spring:errors path="bookid" cssClass="error" />
                                </div>

                                <div class="input-group">
                                    <span class="input-group-addon" id="maturityDate">Maturity Date</span>
                                    <input
                                        class="form-control"
                                        path="maturityDate"
                                        placeholder="Maturity Date"
                                        type="date"
                                        name="maturityDate"
                                        id="maturityDate"
                                        value="<fmt:formatDate value="${trade.maturityDate}" type="date" pattern="yyyy-MM-dd"/>"
                                        oninvalid="setCustomValidity('Please Enter Maturity Date');"
                                        oninput="setCustomValidity('');"
                                        required/>
                                    <spring:errors path="maturityDate" cssClass="error" />

                                </div>

				<input type="submit" value="save" class="btn btn-default"/>
				
			</form>
		</div>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>