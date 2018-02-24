<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sytle>
<style>
body,html{
	height:100%;
	width:100%;
}

.left{
	height:20%;
	width:50%;
	float:left;
	/* background-color:blue; */
}

.right{
	height:20%;
	/* width:50%; */
	float:right;
	/* background-color:yellow; */
	color:red;
}

.bottom{
	height:80%;
	width:100%;
	 left: 0;
    bottom : 0;
    position: absolute;
	/* float:"down"; */
	/* background-color:orange;  */
}
</style>
</sytle>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play!!</title>
</head>
<body>
<%@ page import="com.play.controller.PlayController" %>
<% boolean timeUp = (Boolean)session.getAttribute("timeUp"); %>
<%PlayController playController = (PlayController)session.getAttribute("playController");%>


<%-- <div style="overflow: hidden;">
<h2 style = "float:left;"><span> Your Score : </span><span><%=playController.score %></span></h2>
<%String remTime = '<script> sessionStorage.getItem("+"distance"+")</script>'; %>
<h2  style = "float:right;color:red;" ><span>Time Remaining: </span> <span id="demo" ><script> sessionStorage.getItem("distance")</script></span></h2> 
</div> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

$(document).ready(function(){
	var i = 10;
	var x = setInterval(function(){
		$(".remTime").text(i);
		i = i - 1;
		if(i<0){
			clearInterval(x);
		}
	},1000);
});

$(document).ready(function(){
	var i = 10;
	var x = setInterval(function(){
		i = i - 1;
		if(i<0){
			window.location.replace("hallOfFame.htm?a='a'");
			clearInterval(x);
			
		}
	},1000);
});


function linkClicked(nameOfLink){
	$.ajax({
		url:'link.htm',
        data:{name:nameOfLink},
        success:function(data){
        	$(".score").text(data);
        }
	});
	MyFunction();
}
$(document).ready(function(){
	$("#link0").click(function(){
		linkClicked($(this).attr("name"));
	});
});

$(document).ready(function(){
	$("#link1").click(function(){
		linkClicked($(this).attr("name"));
	});
});

$(document).ready(function(){
	$("#link2").click(function(){
		linkClicked($(this).attr("name"));
	});
});

$(document).ready(function(){
	$("#link3").click(function(){
		linkClicked($(this).attr("name"));
	});
});

function MyFunction(){
	$(document).ready(function(){
		$.ajax({
			url:'playServlet.htm',
            data:{name:'abc'},
            success:function(data){
            	var obj = jQuery.parseJSON(data);
            	/* $("#w3s").attr("href", "https://www.w3schools.com/jquery/"); */
            	$("#link0").attr("name",obj.name0);
            	$("#link1").attr("name",obj.name1);
            	$("#link2").attr("name",obj.name2);
            	$("#link3").attr("name",obj.name3);

            	$("#link0").attr("style","text-decoration:none;color:"+obj.colorOfText0+";");
            	$("#link1").attr("style","text-decoration:none;color:"+obj.colorOfText1+";");
            	$("#link2").attr("style","text-decoration:none;color:"+obj.colorOfText2+";");
            	$("#link3").attr("style","text-decoration:none;color:"+obj.colorOfText3+";");

            	$("#link0").text(obj.colorName0);
            	$("#link1").text(obj.colorName1);
            	$("#link2").text(obj.colorName2);
            	$("#link3").text(obj.colorName3);
            	
            }
		});

});
}

$(document).ready(function(){

		$.ajax({
			url:'playServlet.htm',
            data:{name:'abc'},
            success:function(data){
            	var obj = jQuery.parseJSON(data);
            	/* $("#w3s").attr("href", "https://www.w3schools.com/jquery/"); */
            	$("#link0").attr("name",obj.name0);
            	$("#link1").attr("name",obj.name1);
            	$("#link2").attr("name",obj.name2);
            	$("#link3").attr("name",obj.name3);

            	$("#link0").attr("style","text-decoration:none;color:"+obj.colorOfText0+";");
            	$("#link1").attr("style","text-decoration:none;color:"+obj.colorOfText1+";");
            	$("#link2").attr("style","text-decoration:none;color:"+obj.colorOfText2+";");
            	$("#link3").attr("style","text-decoration:none;color:"+obj.colorOfText3+";");

            	$("#link0").text(obj.colorName0);
            	$("#link1").text(obj.colorName1);
            	$("#link2").text(obj.colorName2);
            	$("#link3").text(obj.colorName3);
            	
            }
		});

});

var nHist = window.history.length;
if(window.history[nHist] != window.location)
  window.history.forward();
</script>
<div class="left"><h1 style="float:left">Your Score : </h1><h1 class = "score" style="float:left">0</h1></div>
<div class="right"><h1 style="float:left">Time Remaining : </h1>&nbsp<h1 class="remTime" style="float:left"></h1>&nbsp<h1 style="float:right">Seconds!</h1></div>
<div class="bottom">
<center>
<!-- <h1>Select the correct option!</h1> -->

<table cellspacing="10" >
<tr><th colspan="4"><h1>Select the correct option!</h1></th></tr>
<tr >


<td><h2><a id = "link0" href = "#" onclick="MyFunction();"></a></h2></td>
<td><h2><a id = "link1" href = "#" onclick="MyFunction();"></a></h2></td>
<td><h2><a id = "link2" href = "#" onclick="MyFunction();"></a></h2></td>
<td><h2><a id = "link3" href = "#" onclick="MyFunction();"></a></h2></td>

</tr>
</table>


</center>
</div>

</body>
</html>