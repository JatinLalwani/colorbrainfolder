<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table,tr,td{
border: 1px solid black;
}
.Sparkle {
	
	font-size:100px;
	font-family:impact;
	
 -webkit-text-fill-color: transparent;
 background: -webkit-linear-gradient(transparent, transparent),
             url(http://www.myspacehippo.com/files/glitterbacks/bg345.gif) repeat;
 background: -o-linear-gradient(transparent, transparent);
 -webkit-background-clip: text;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">

function addTheComment(){
	var commentFromUser = $("#comment").val();
	$.ajax({
		url:'comment.htm',
        data:{comment:commentFromUser},
        success:function(data){
        	$("#commentor").text(data+" says : ");
        	$("#theComment").text(commentFromUser);
        }
	});
}

var nHist = window.history.length;
if(window.history[nHist] != window.location)
  window.history.forward();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hall of fame</title>
</head>
<%@ page import="java.util.*" %>
<%@ page import= "com.play.entity.Players"%>
<%@ page import= "com.play.entity.Comments"%>
<%List<Players> playerList = (List)session.getAttribute("playerList");%>
<%List<Comments> commentList = (List)session.getAttribute("commentList");%>
<body>

<center>

<h2><a href = "playAgain.htm" style = "text-decoration:none;">Play Again!</a></h2>
<table cellspacing="5" cellpadding="10">

<tr>
<th colspan="4" class="Sparkle"> HALL OF FAME </th>
</tr>

<tr>
<th>Rank</th>
<th>Player Name</th>
<th>Score</th>
<th>Date and Time </th>
</tr>

<%
String playerName;
int score;
String date;
for(int i=0;i<playerList.size();i++){
	playerName = playerList.get(i).getUsername();
	score = playerList.get(i).getScore();
	date = playerList.get(i).getDate();
%>

<tr>
<td><%=i+1 %></td>
<td><%=playerName %></td>
<td><%=score %></td>
<td><%=date %></td>
</tr>

<%
}
%>

</table>

</center>
<br>
<h1>Comments:</h1>
<textarea id="comment" rows="4" cols="100"></textarea><br><br>
<button id="addComment" Onclick="addTheComment()">Add Comment</button>
<br>

<div>
<b><h3 id="commentor"></h3></b>
<i><h4 id="theComment"></h4></i>
<hr>
</div>


<%
String username="";
String comment="";
for(int i=0;i<commentList.size();i++){
	username = commentList.get(i).getUsername()+" says : ";
	comment = commentList.get(i).getComments();

%>

<h3><b><%=username %></b></h3>
<h4><i><%=comment %></i></h4>
<hr>
<%
}
%>


</body>
</html>