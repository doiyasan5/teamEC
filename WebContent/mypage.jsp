<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
<link rel="shortcut icon" href="./images/favicon.ico">
<link rel="stylesheet" type="text/css" href="./css/hibiscus.css">
<link rel="stylesheet" href="./css/form.css" />

</head>
<body>
		<jsp:include page="header.jsp" />
	<div class="top">
		<h1>マイページ画面</h1>
		<table>
			<tr>
				<td id="title">姓</td>
				<td id="body"><s:property value="#session.familyName" /></td>
			</tr>
			<tr>
				<td id="title">名</td>
				<td id="body"><s:property value="#session.firstName" /></td>
			</tr>
			<tr>
				<td id="title">ふりがな</td>
				<td id="body"><s:property value="#session.familyNameKana" /><s:property value="#session.firstNameKana" /></td>
			</tr>
			<tr>
				<td id="title">性別</td>
				<td id="body"><s:if test="#session.sex==0">男性</s:if>
					<s:if test="#session.sex==1">女性</s:if></td>
			</tr>
			<tr>
				<td id="title">メールアドレス</td>
				<td id="body"><s:property value="#session.email" /></td>
			</tr>
		</table>
		<div id="form">
			<s:form action="PurchaseHistoryAction">
				<s:submit id="submit" value="購入履歴" />
			</s:form>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>