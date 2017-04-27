<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="resource/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#page").val(1);
			getData($("#page").val());
		});
		function getData(page){
			$.ajax({
				type: "get",
				url: "call/search?page="+page,
				success: function (data) {
					var list=data.resultList;
					var str="";
					for(var i=0;i<list.length;i++){
						str+="<tr>"
							 +"<td>"+list[i].songName+"</td>"
							 +"<td>"+list[i].songOwner+"</td>"
							 +"<td><img src='"+list[i].songCover+"'></img></td>"
							 +"<td><button onclick=detail('"+list[i].songId+"')>查看call表</button></td>"
							 +"</tr>";
					}
					$("#body").html(str);
					$("#max").val(data.totalPage);
				}
			});
		}
		function nextpage(){
			var page=parseInt($("#page").val());
			var max=parseInt($("#max").val());
			if(page+1>max){
				alert("已到最后一页!");
				return;
			}
			$("#page").val(page+1);
			getData($("#page").val());
		}
		function prepage(){
			var page=parseInt($("#page").val());
			if(page-1<=0){
				alert("已经是第一页!");
				return;
			}
			$("#page").val($("#page").val()-1);
			getData($("#page").val());
		}
		function detail(songId){
			$.ajax({
				type: "get",
				url: "call/detail?song.songId="+songId,
				success: function (data) {
					var str="<center>"
							+"<iframe src='resource/html"+data.result.callSource+"' width=1200px height=1000px></iframe>"
							+"</center>";
					$("#all").html(str);
				}
			});
		}
	</script>
  </head>
  
  <body id="all">
  	<input id="page" type="hidden" value="" />
  	<input id="max" type="hidden" value="" />
  	<center>
  		<h1>当你看到这条消息时则证明cae已经成功启动</h1>
  		<a href="javascript:void(0)" onclick="prepage()">上一页</a>&nbsp;&nbsp;
  		<a href="javascript:void(0)" onclick="nextpage()">下一页</a>
  		<table border="1">
  			<thead>
  				<tr>
  					<th>歌名</th>
  					<th>歌手</th>
  					<th>封面</th>
  					<th>操作</th>
  				</tr>
  			</tread>
  			<tbody id="body">
  			</tbody>
  		</table>
  		<a href="javascript:void(0)" onclick="prepage()">上一页</a>&nbsp;&nbsp;
  		<a href="javascript:void(0)" onclick="nextpage()">下一页</a>
  	</center>
  </body>
</html>
