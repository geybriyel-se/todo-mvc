<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<div class="container">
    <h1 class="mb-md-5">Let's keep working!</h1>
    <table class="table">
        <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
            <c:choose>
                <c:when test="${todo.isDone()}">
                    <del>
                        <tr>
                            <td class="text-decoration-line-through text-black-50">${todo.description}</td>
                            <td class="text-decoration-line-through text-black-50">${todo.targetDate}</td>
                            <td class="text-decoration-line-through text-black-50">Done</td>
                            <td>
                                <a href="/todo/edit?id=${todo.id}" class="btn btn-warning">Edit</a>
                                <a href="" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </del>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>Pending</td>
                        <td>
                            <a href="/todo/edit?id=${todo.id}" class="btn btn-warning">Edit</a>
                            <a href="" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>


        </tbody>
    </table>
    <a href="add-todo" class="btn btn-success">Add New Todo</a>

</div>

<%@ include file="common/footer.jspf"%>