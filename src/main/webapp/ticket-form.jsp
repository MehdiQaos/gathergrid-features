<%@ page language="java" contentType="text/html; ISO-8859-1; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Ticket</title>
</head>
<body>
    <h1>Create Event ticket :</h1>
    <form action="create-ticket" method="post">
        <label for="price">Price :</label>
        <input type="text" id="price" name="ticketPrice"><br>

        <label for="quantity">Quantity :</label>
        <input type="text" id="quantity" name="ticketQuantity"><br>

        <label for="ticketType">Ticket type:</label>
        <select name="ticketType" id="ticketType">
            <option value="vip">VIP</option>
            <option value="student">STUDENT</option>
            <option value="senior_citizen">SENIOR CITIZEN</option>
            <option value="child">CHILD</option>
        </select>
        <br>
        <input type="submit" value="Create Ticket">
    </form>
</body>
</html>