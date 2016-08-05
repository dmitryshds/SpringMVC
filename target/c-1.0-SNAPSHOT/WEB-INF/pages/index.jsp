<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 30.07.2016
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
  <head>
      <spring:url value="/resources/css/home.css" var="homeCss" />
      <link href="${homeCss}" rel="stylesheet" />

      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script type="text/javascript">
          function doAjax() {
              $.ajax({
                  url: 'checkStrength',
                  data: ({password : $('#password').val()}),
                  success: function(data) {
                      $('#strengthValue').html(data);
                  }
              });
          }
      </script>

    <title></title>
  </head>
  <body>
  <form:form method="POST" commandName="user" action="check-user"
             class="box login">


      <fieldset class="boxBody">

          <form:label path="name">Name:</form:label>
          <form:input path="name" />
          <form:errors path="name" cssClass="error"/>

          <form:label path="password">Password:</form:label>
          <form:password path="password" onkeyup="doAjax()"/>
          <form:errors path="password" cssClass="error"/>
          <span style="float: right" id="strengthValue">
			</span>

      </fieldset>

      <footer> <form:checkbox path="admin" />

          <form:label path="admin">Admin</form:label> <input type="submit" class="btnLogin" value="Login" >


      </footer>


  </form:form>
  </body>
</html>
