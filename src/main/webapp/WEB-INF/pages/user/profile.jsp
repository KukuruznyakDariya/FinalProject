<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <h2 class="form-signin-heading"><fmt:message key="profile" bundle="${bundle}"/></h2>
    <div class="panel-body">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger"><c:out value='${errorMessage}'/></div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success"><c:out value='${successMessage}'/></div>
        </c:if>
        <form class="form-horizontal" action="?command=editProfile" method="POST">
            <fieldset class="form-group">
                <label for="firstName"><fmt:message key="first_name" bundle="${bundle}"/></label>
                <input class="form-control" id="firstName" name="firstName" value="<c:out value='${user.firstName}'/>"
                       pattern="[A-Z]?[a-z]+)|([А-Я]?[а-я]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20"
                       placeholder="<fmt:message key="first_name" bundle="${bundle}"/>"
                       required>
            </fieldset>
            <fieldset class="form-group">
                <label for="lastName"><fmt:message key="last_name" bundle="${bundle}"/></label>
                <input id="lastName" class="form-control" name="lastName"
                       value="<c:out value='${user.lastName}'/>" placeholder="<fmt:message key="last_name" bundle="${bundle}"/>"
                       required
                       pattern="([A-Z]?[a-z]+)(-[A-Z]?[a-z]+)*)|(([А-Я]?[а-я]+)(-[А-Я]?[а-я]+)*" maxlength="50"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 50">
            </fieldset>
            <fieldset class="form-group">
                <label for="login"><fmt:message key="user_login" bundle="${bundle}"/></label>
                <input class="form-control" id="login" name="login"
                       value="<c:out value='${user.login}'/>" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="email"><fmt:message key="user_email" bundle="${bundle}"/></label>
                <input class="form-control" id="email" name="email"
                       value="<c:out value='${user.email}'/>" placeholder="<fmt:message key="user_email" bundle="${bundle}"/>"
                       required
                       pattern=".+@.+"
                       maxlength="50"
                       title="<fmt:message key="incorrect_format" bundle="${bundle}"/>">
            </fieldset>
            <fieldset class="form-group">
                <label for="dateOfRegistration"><fmt:message key="date_of_registration" bundle="${bundle}"/></label>
                <input class="form-control" id="dateOfRegistration" name="dateOfRegistration"
                       value="<c:out value='${user.dateOfRegistration}'/>" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="balance"><fmt:message key="balance" bundle="${bundle}"/></label>
                <input class="form-control" id="balance" name="balance"
                       value="<c:out value='${user.balance}'/>" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="maxBet"><fmt:message key="max_bet" bundle="${bundle}"/></label>
                <input class="form-control" id="maxBet" name="maxBet"
                       value="<c:out value='${user.maxBet}'/>" disabled>
            </fieldset>
            <fieldset class="form-group">
                <button class="btn btn-lg btn-primary btn-block" z><fmt:message key="save" bundle="${bundle}"/></button>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>