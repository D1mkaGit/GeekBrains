<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<%--<%@ include file="head.jsp"%>--%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Product Form"/>
</jsp:include>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">ToDo</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">List</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/product" var="productSubmitUrl"/>
            <form action="${productSubmitUrl}" method="post">
                <input value="${requestScope.product.id}" type="hidden" id="id" name="id">
                <div class="form-group">
                    <label>Name</label>
                    <input value="${requestScope.product.name}" type="text" class="form-control" id="name" name="name" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input value="${requestScope.product.price}" type="number" class="form-control" id="price" name="price" placeholder="Enter price" >
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>

</body>

</html>