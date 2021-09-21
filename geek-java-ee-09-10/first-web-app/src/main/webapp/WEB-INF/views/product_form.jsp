<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<%--<%@ include file="head.jsp"%>--%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Product Form"/>
</jsp:include>

<body>

<%@include file="navbar.jsp" %>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/product" var="productSubmitUrl"/>
            <form action="${productSubmitUrl}" method="post" accept-charset="utf-8" onsubmit="return CheckSubmit()">
                <input value="${requestScope.product.id}" type="hidden" id="id" name="id">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input value="${requestScope.product.name}" type="text" class="form-control" id="name" name="name"
                           placeholder="Enter name" required onchange="allowSubmit=true">
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input value="${requestScope.product.price}" type="number" class="form-control" id="price"
                                                      name="price" placeholder="Enter price" required onchange="allowSubmit=true">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="${productSubmitUrl}" type="button" class="btn btn-primary">Back</a>
            </form>
        </div>
    </div>
</div>

<%@include file="scripts.jsp" %>
<script>
    allowSubmit = false;
    function CheckSubmit(){
        if(!allowSubmit)alert('Nothing has been changed');
        return allowSubmit
    }
</script>

</body>

</html>