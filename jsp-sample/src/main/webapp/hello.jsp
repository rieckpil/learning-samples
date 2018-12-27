<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hello World JSP</title>
</head>
<body>
	<%
		String name = request.getParameter("name");
		if (name != null && name.length() > 0) {
			out.println(name);
		} else {
			out.println("World");
		}

		out.println(session.getId());
		out.println("</br>");
		out.println(session.getId());
	%>
	<h1>Hello World from a JSP!</h1>
</body>
</html>