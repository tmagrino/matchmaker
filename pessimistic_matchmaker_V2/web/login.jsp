<%--
	This page was a temporary login page. It is replaced by CUWebAuth login page on the web server.
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<!--
	$Id: login.html,v 1.13 2013/04/15 14:34:01 hy93 Exp $
-->

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta http-equiv="Content-Language" content="en-us" />
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />

	<title>Cornell University Web Login</title>

	<script type="text/javascript">
	<!--
		function sf() { document.login.netid.focus(); }
	// -->
	</script>
	<style type="text/css">
 
body {
 	margin: 0;
	padding: 0;
	font-family: verdana, arial, helvetica, sans-serif;
	font-size: 63.125%;
	color: #222;
 	}

#cu-identity {
	height: 75px;
	background: #b31b1b;
	}

#cu-logo {
	margin: 0 auto;
	width: 740px;
	background: url(banner_1.jpg) no-repeat top left;
	}
	
hr.banner-separator {
	display: none;
	}

#wrap {
	float: left;
	width: 100%;
	}
 
#content {
	margin: 0 auto;
	width: 740px; 
/* unhack ie5/win */
	text-align: left;
	}
	
p.reason {
	padding-bottom: 0px;
	font-size: 1.3em;
	color: #b31b1b;
	width: 25em;
}


#footer {
	float: left;
	width: 100%;
	font-size: 1.0em;
	}
	
#fc {
	width: 740px;
	margin: 0 auto;
}
	
#footer-content {
	margin: 0 auto;
	padding: 5px 0 1em 0;
	width: 68em;
	font-size: .9em;
	color: #73736c;
	float: left;
	margin-top: .5em;
	}
	
#footer a {
	font-size: 1.0em;
	}

p {
	margin: 0 0 1em 0;
	font-size: 1.3em;
	line-height: 1.4em;
	}
	
#identity {
	padding: 25px 0;
	background: #fff;
	}

#identity h1 {
	margin: 0 auto;
	width: 740px;
/*	font-family: georgia, "times new roman", times, serif;*/
	font-family: verdana, arial, helvetica, sans-serif;
	font-size: 2.4em;
	font-weight: normal;
	color: #73736c;
	} 

.first {
	margin-top: 0;
	}
	
a {
	color: #b31b1b;
	text-decoration: none;
	font-size: .9em;
	}
	
a:visited {
	color: #b37474;
	}
	
a:hover {
	color: #f00;
	border-color: #f00;
	}
	
a:active {
	color: #b31b1b;
	border-color: #e5cfcf;	
	}

hr {
	display: none;
	}
	
form {
	margin: 0 0 15px 0;
	padding: 0;
	width: 740px;
	float: left;
	}
	
fieldset {
	float: left;
	margin: 0 auto;
	padding: 1em 0 1.5em 0;
	width: 30em;
	border: none;
	border-top: 1px solid #e7e7e7;
	}
	
.form-pair {
	display: inline;	
	float: left;
	margin: .5em .5em 0 .4em;
	width: 28em;
	}
	
.form-item {
	float: left;
	margin-top: 5px;
	width: 8em;
	font-size: 1.2em;
	line-height: 1.5em;
	text-align: right;
	}	

.form-value {
	float: right;
	margin-top: 3px;
	width: 16em;
	font-size: 1.1em;
	line-height: 1.5em;
	margin-right: 0px;
	}
			
.input-text, select, textarea {
	font-family: verdana, arial, helvetica, sans-serif;
	font-size: 1em;
	}

.form-submit {
	border: 1px solid #dbdbd2;
	}
	
.input-submit, .input-reset {
	font-family: verdana, arial, helvetica, sans-serif;
	font-size: 1.1em;
	border: 1px solid;
	background: #f0eee4;
	margin-top:0px;
}

.input-submit:hover{
	background:#dbdbd2;
}

#offsetlinks ul {
	float: left;
	margin: 0 0 0 0;
	padding: 0px 0 10px 0;
	font-size: 1.3em;
	line-height: 1.4em;
	}	

#offsetlinks ul li {
	margin: 0 0 0 25em;
	padding: 0 0 0 15px;/*was 30*/
	list-style: none;
}

#reason ul {
	font-size: 1.3em;
}
#reason ul li {
	padding: 1em 0 0 0;
	list-style: none;
}
 
</style>

</head>
<body onload="sf()" class="twocolumn">
<hr class="banner-separator" />
<div id="cu-identity">
	<div id="cu-logo">
		<img src="https://web4.login.cornell.edu/banner_1.jpg" alt="Cornell University Logo"/>
	</div>
</div>

	<div id="identity">
		<h1>CUWebLogin</h1>
	</div> 

<div id="wrap">  
  <div id="content">
<!--Comments for automated stripping of CSS - DO NOT REMOVE -->
<!--WEBLOGINCSS -->
<!-- ++++++++++ -->
<!-- cut here for form.html -->
		<FORM ACTION="select-role.jsp" METHOD="POST">
		
<!--reason text-->
<div id="reason">
<ul></ul><ul></ul>
</div>
			

		<fieldset class="form-submit">
			<div class="form-pair">
				<div class="form-item">
					<label for="netid">User ID:<!-- NetID --></label> 
				</div>
				<div class="form-value">
					<input NAME="netId" type="text" size="18"  />
				</div>
			</div>

			<div class="form-pair">
				<div class="form-item">
					<label for="password">Password:</label> 
				</div>
    			<div class="form-value">
					<input id="password" name="password" type="password" size="18" autocomplete="off" />
				</div>
			</div>

<!-- realm select if needed -->
<div class="form-pair">
<div class="form-item">
<label for="realm">ID Type: </label>
</div>
<div class="form-value">
<SELECT ID="realm" NAME="realm">
<OPTION VALUE='NetID'>NetID</OPTION>
<OPTION VALUE='WCMC-CWID'>WCMC-CWID</OPTION>
<OPTION VALUE='GuestID'>GuestID</OPTION>Apply  Hide

</SELECT>
</div></div>


			<div class="form-pair">
				<div class="form-value">
					<input  type="submit" class="input-submit" name="Submit" value="Login" alt="Login" />
				</div>
			</div>
		</fieldset>
	</form>
  

	<div id="offsetlinks">
	<ul>
	<li><a href="http://www.it.cornell.edu/services/cuweblogin/about.cfm">What is this?</a></li>
	<li><a href="https://netid.cornell.edu/NetIDManagement/idtype.html">I forgot my password!</a></li>
	<li><a href="http://www.it.cornell.edu/services/netid/faq.cfm">I don't have a NetID, now what?</a></li>
	</ul>
	</div>

<div id="footer">
	<div id="fc">
		<div id="footer-content">
     <p>   <a target="_blank" href="http://www.it.cornApply  Hide
     ell.edu/services/cuweblogin/safe.cfm#exit">To log out, you must Exit or Quit your browser.</a>
</p><p>
   <strong>Caution:</strong> Always check your browser's address bar before you enter your NetID password to make sure the address starts with https://web*.login.cornell.edu/ (where web* is either web1, web2, web3 or web4).
</p> <p>
   CUWebLogin is a component of Cornell University's central authentication service.  If you are unsure of the authenticity of any online University service, please contact <a href="http://it.cornell.edu/support/">the IT Service Desk.</a>
</p><p>
This service and the services to which it provides access are for authorized use only.  Any attempt to gain unauthorized access, or exceed authorized access, to online University resources will be pursued, as applicable, under campus codes and state or federal law.
 </p>

	&copy; 2008 Cornell University. All Rights Reserved.
  	</div>

  	
<!-- ++++++++++ -->

</div><!--content-->
</div><!--wrap-->
  </div>
  </div>

</body>
</html>
