<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>

<script type="text/javascript">
function validateForm()
{
	var username=document.forms["nameform"]["username"].value;
 	if (username==null || username=="")
  	{
  		alert("Please provide your name");
  		return false;
  	} 
}


var nHist = window.history.length;
if(window.history[nHist] != window.location)
  window.history.forward();
</script>

</head>
<body>

<center>
<!-- <span>Write the rules of the game here.</span> -->
<h1>How to Play?</h1>
<div style="border-style: groove;">
<br>
<img src="color_options.JPG" alt="image not found :("></img>
<p>In the image above for instance, you can see four color names. These are actually four options and you
   need to pick(click) the correct one. In the given image, the second option is correct because the COLOR NAME
   matches the TEXT COLOR which is not the case with rest of the options. ONE AND ONLY ONE option is correct.
   You will be awarded +10 for correct choice and -10 for the rest. After you pick one, the next set of options
   will appear. Answer as many set of options as you can correctly in 10 seconds.</p>
</div>


<br><br>
<form action = "getUsername.htm" onsubmit="return validateForm()" name = "nameform">
Enter Your Name : <input type="text" name = "username" pattern="[a-zA-Z]{4,}" title="Minimum 4 letters with no numbers/special chars"><br><br>
<input type = "submit" value = "Lets play">
<br><br>

<%@ page import="java.util.Date" %>

</form>

</center>

</body>
</html>