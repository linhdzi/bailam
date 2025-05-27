<%@ page import="java.util.List, com.example.entity.Product" %>
<%
    List<Product> list = (List<Product>) request.getAttribute("productList");
    for(Product p : list){
%>
<tr>
    <td><%= p.getId() %></td>
    <td><%= p.getName() %></td>
    <td><%= p.getPrice() %></td>
    <td><%= p.getQuantity() %></td>
    <td><form action="deleteProduct" method="post">
        <input type="hidden" name="id" value="<%= p.getId() %>">
        <input type="submit" value="Delete">
    </form></td>
</tr>
<% } %>
