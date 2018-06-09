<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>KickOff - Sports Html Template</title>

    <!-- Css Files -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/themetypo.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/widget.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/color.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/flexslider.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/owl.carousel.css" rel="stylesheet">
      
    <link href="<%=request.getContextPath()%>/resources/css/responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    
     <!--// Wrapper //-->
    <div class="kode-wrapper">
             <header id="mainheader" class="kode-header-absolute">
		<div class="header-8">
			<div class="container">
				<!--NAVIGATION START-->
				<div class="logo">
					<a href="../" class="logo"><img src="<%=request.getContextPath()%>/resources/images/logo.png" alt=""></a>
				</div>
				<div class="kode-navigation pull-left">
					<ul>
						<li><a href="../team">Equipes</a></li>
						<li><a href="../poule">Poules</a></li>
						<li><a href="../game">Matchs</a></li>
						<li><a href="../stats">Statistiques</a></li>
					</ul>
				</div>
				<!--NAVIGATION END-->
			</div>
		</div>
      </header>

		<br /><br /><br /><br />
      <!--// Main Content //-->
      <div class="kode-content">
		<br /><br /><br /><br />
        <!--// Page Content //-->
        <c:set var="previous" value=""/>
        <c:forEach items="${mapGames}" var="mapGame">
	        <section class="kode-pagesection margin-bottom-40">
	          <div class="container">
	            <div class="row">
					<div class="col-md-12">
						<div class="heading heading-12 margin-top30-bottom10">
							<h2><span class="left"></span>Matchs du <tags:localDate date="${mapGame.key}"/><span class="right"></span></h2>
						</div>
					</div>	
	                <div class="col-md-8">
	                  
	                  <table class="kode-table">
	                    <thead>
	                      <tr>
	                        <th colspan=7 style="text-align:center;">Match</th>
	                        <th>Heure</th>
<!-- 	                        <th>Stade</th> -->
	                      </tr>
	                    </thead>
	                    <tbody>
	                    <c:forEach items="${mapGame.value}" var="game">
	                      <tr id="${game.id}">
	                        <td style="text-align: center;width:10%;">
	                        	<img style="top:0.6em;" src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="30" height="20">&nbsp;
	                        </td>
	                        <td style="width:27%;"><div style="width:100%;">${game.team1.name}</div></td>
	                        <td style="width:5%;">${game.score1}</td>
	                        <td style="width:5%;">VS</td>
	                      	<td style="width:5%;">${game.score2}</td>
	                      	<td style="width:27%;"><div style="width:100%;">${game.team2.name}</div></td>
	                        <td style="width:10%">
	                        	<img style="top:0.6em;" src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="30" height="20">
	                        </td>
	                        <td>${game.time}</td>
<%-- 	                     	<td>${game.stade}</td> --%>
	                      </tr>
	                    </c:forEach>
	                    </tbody>
	                 </table>
	                </div>
	              </div>
	            </div>
	        </section>
        </c:forEach>
        <!--// Page Content //-->

      </div>
      <!--// Main Content //-->

      <!--// Contact Footer //-->

      <div class="kode-bottom-footer">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <p>©2018 Project by Thomas</p>
            </div>
            <div class="col-md-6">
              <a href="#" id="kode-topbtn" class="thbg-colortwo"><i class="fa fa-angle-up"></i></a>
            </div>
          </div>
        </div>      </div>
<div class="clearfix clear"></div>
</div>
    <!--// Wrapper //-->

    <!-- jQuery (necessary for JavaScript plugins) -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.flexslider.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/owl.carousel.min.js"></script>
      
    <script src="<%=request.getContextPath()%>/resources/js/jquery.countdown.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.bxslider.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap-progressbar.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.prettyphoto.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/kode_pp.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/functions.js"></script>

	<script>
		$("tbody tr").click(function(e){
			$(window.location).attr('href',"editGame?id="+$(this).attr('id'));
		});
	</script>

  </body>
</html>