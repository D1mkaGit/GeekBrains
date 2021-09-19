<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ru.geekbrains.persist.ProductRepository" %>
<%@ page import="ru.geekbrains.persist.Product" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">

<%--<%@ include file="head.jsp"%>--%>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="Product List"/>
</jsp:include>

<%--<%!--%>
<%--    private ProductRepository productRepository;--%>

<%--    @Override--%>
<%--    public void jspInit() {--%>
<%--        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");--%>
<%--    }--%>
<%--%>--%>

<body>

<%@include file="navbar.jsp"%>

<div class="container">

    <div class="row py-2">
        <div class="col-md-3 col-xs-12">
            <ul class="list-group my-2">
                <li class="list-group-item active">Category 1</li>
                <li class="list-group-item">Category 2</li>
                <li class="list-group-item">Category 3</li>
                <li class="list-group-item">Category 4</li>
                <li class="list-group-item">Category 5</li>
            </ul>
        </div>

        <div class="col-md-9 col-xs-12">
            <c:url value="/product/new" var="newProductUrl"/>
            <a class="btn btn-primary my-2" href="${newProductUrl}">Add Product</a>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
<%--                <% for (Product prod : (List<Product>) request.getAttribute("products")) { %>--%>
                <c:forEach var="prod" items="${requestScope.products}">
                <tr>
                    <th scope="row">
<%--                        <%= prod.getId() %>--%>
                        <c:out value="${prod.id}"/>
                    </th>
                    <td>
<%--                        <%= prod.getName() %>--%>
                        <c:out value="${prod.name}"/>
                    </td>
                    <td>
<%--                        <%= prod.getPrice() %>--%>
                        <c:out value="${prod.price}"/>
                    </td>
                    <td>
                        <c:url value="/product/${prod.id}" var="productUrl"/>
                        <a class="btn btn-success" href="${productUrl}"><i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
                </c:forEach>
<%--                <% } %>--%>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="scripts.jsp"%>

</body>
</html>