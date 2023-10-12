<%@ include file="common/header.jspf" %>
<%@ include file="common/navbar.jspf" %>

<div class="container shadow-lg p-lg-5 w-50">
    <h1>Todo Details</h1>
    <form:form method="post" modelAttribute="todo">
        <div class="form-group mb-3 mt-3">
            <form:label path="description">Description</form:label>
            <form:input path="description" class="form-control" type="text" required="required"/>
        </div>
        <div class="form-group mb-3">
            <form:label path="targetDate">Target Date</form:label>
            <form:input path="targetDate" class="form-control" type="text" required="required"/>
        </div>
        <div class="form-group mb-3">
            <form:label path="done">Done?</form:label>
            <div class="form-check">
                <form:radiobutton path="done" id="done-yes" value="true" />
                <label for="done-yes" class="form-check-label">Yes</label>
            </div>
            <div class="form-check">
                <form:radiobutton path="done" id="done-no" value="false" />
                <label for="done-no" class="form-check-label">No</label>
            </div>
        </div>
        <input type="submit" class="btn btn-success">
    </form:form>
</div>

<%@ include file="common/footer.jspf" %>