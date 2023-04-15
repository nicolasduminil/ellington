<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Item Management</title>
  </head>
  <body>
    <h1>Insert an Item:</h1>
    <form name="form1" action="api/items" method="POST">
      Key
      <input type="text" name="key">
      Value &nbsp;
      <input type="text" name="value">
      <input type="submit" value="Insert">
    </form>
    <br/>
    <table BORDER="1" WIDTH="50%">
      <tr>
        <th COLSPAN="2">
          <BR>
          <H3>Item List</H3>
        </th>
      </tr>
      <c:forEach var="item" items="${itemFacade.itemList}" varStatus="status">
        <c:set var="url" value="api/items/${status.index}"/>
        <tr>
          <td><a href="${url}">${item.key}</a></td>
        </tr>
      </c:forEach>
    </table>
    <br/>
    <a href="api/items/list">Get Full List</a>
  </body>
</html>