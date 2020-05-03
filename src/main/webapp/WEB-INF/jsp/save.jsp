<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script language ="javascript">

</script>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
.row > .col {
  flex: 0 0 34px;
  background: #eee;
}
</style>
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


<div class="control-group <c:out value='${errorClass}' />"
		<div class="starter-template">
			<h1>New Trade</h1>
			<form:form class="form-horizontal" method="post"
                             modelAttribute="trade">


                    <input type="hidden" name="id" value="${trade.id}" />
                    <div class="container">
                        <div class="form-group">
                        <label class="col-sm-2 control-label" id="tradeId"><spring:message code="trade.id"/>
                        </label>
                         <div class="col-md-6 col-sm-4 col-lg">
                                <input
                                    class="form-control"
                                    placeholder='trade id '
                                    type="text"
                                    name="tradeId"
                                    id="tradeId"
                                    value="${trade.tradeId}"
                                    oninvalid="setCustomValidity('<spring:message code="NotEmpty.trade.tradeId"/>');"
                                    oninput="setCustomValidity('');"
                                    path="tradeId"
                                    required
                                />
                            </div>
                                <form:errors path="tradeId" cssClass="error" />
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" id="version"><spring:message code="trade.Version"/>
                            </label>
                            <div class="col-md-6 col-sm-4 col-lg">
                                <input
                                    class="form-control"
                                    placeholder="<spring:message code="trade.Version"/>"
                                    type="number"
                                    name="version"
                                    id="version"
                                    min="1"
                                    value="${trade.version}"
                                    oninvalid="setCustomValidity('<spring:message code="NotNull.trade.version"/>');"
                                    oninput="setCustomValidity('');"
                                    required
                                />

                            </div>
                            <form:errors path="version" cssClass="error" />
                        </div>
                        <div class="form-group">
                                <label class="col-sm-2 control-label" id="counterPartyID"><spring:message
                                    code="trade.CounterPartyId"/>
                                </label>
                            <div class="col-md-6 col-sm-4 col-lg">
                                <input
                                    class="form-control"
                                    placeholder="<spring:message code="trade.CounterPartyId"/>"
                                    type="text"
                                    name="counterPartyID"
                                    id="counterPartyID"
                                    value="${trade.counterPartyID}"
                                    oninvalid="setCustomValidity('<spring:message code="NotEmpty.trade.counterPartyID"/>');"
                                    oninput="setCustomValidity('');"
                                    required
                                />
                            </div>
                                <form:errors path="counterPartyID" cssClass="error" />
                        </div>
                        <div class="form-group">
                                <label class="col-sm-2 control-label" id="bookid"><spring:message code="trade.BookId"/>
                                </label>
                            <div class="col-md-6 col-sm-4 col-lg">
                                <select
                                    name="bookid"
                                    id="bookid"
                                    class="form-control"
                                    oninvalid="setCustomValidity('<spring:message code="NotEmpty.trade.bookid"/>');"
                                    oninput="setCustomValidity('');"
                                    required
                                >

                                    <option value=""><spring:message code="trade.BookId.select"/></option>
                                    <c:forEach items="${bookList}" var="book">
                                        <option value="${book.id}"
                                            ${book.id == trade.bookid ? 'selected="selected"' : ''}>${book.id}
                                        </option>
                                    </c:forEach>

                                </select>
                            </div>
                               <form:errors path="bookid" cssClass="error" />
                        </div>

                        <div class="form-group">
                                <label class="col-sm-2 control-label" id="maturityDate">
                                    <spring:message code="trade.MaturityDate"/>
                                </label>
                            <div class="col-md-6 col-sm-4 col-lg">
                                <input
                                    class="form-control"
                                    path="maturityDate"
                                    placeholder="<spring:message code="trade.MaturityDate"/>"
                                    type="date"
                                    name="maturityDate"
                                    id="maturityDate"
                                    value="<fmt:formatDate value="${trade.maturityDate}"
                                    type="date" pattern="yyyy-MM-dd"/>"
                                    oninvalid="setCustomValidity('<spring:message code="NotNull.trade.maturityDate"/>');"
                                    oninput="setCustomValidity('');"
                                    required
                                />
                            </div>
                                <form:errors path="maturityDate" cssClass="error" />
                        </div>

<!--required-->
                        <div class="form-group">
                        <label class="col-sm-2 control-label" id="maturityDate">

                        </label>
                        <div class="col-md-6 col-sm-4 col-lg">
                            <input type="submit" value="<spring:message code="trade.Save"/>" class="btn btn-default"/>

                             <input type="cancel" value="<spring:message code="trade.Cancel"/>" class="btn btn-default"/>
                         </div>
                         </div>
                </div>
			</form:form>
		</div>

	</div>


</body>

</html>