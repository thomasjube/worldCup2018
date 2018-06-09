<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <section class="kode-pagesection margin-bottom-40">
          <div class="container">
            <div class="row">
            
                <div class="kode-pagecontent col-md-8">
                  <div style="text-align:center;">
                  	<img class="flag" style="top:0.5em;position:relative;float:left;margin-left:25%" src="<%=request.getContextPath()%>/resources/images/flag/${team.name}.png" alt="" width="120" height="100">&nbsp;
	              	<h1 style="font-size:80px;position:relative;float:left;margin-left:5%">${team.name}</h1>
                  </div>
                    <div class="kode-fixer-counter">
                      <h2 class="thbg-color" style="color:white !important;">Composition de l'équipe</h2>
                    </div>
	                    <table>
	                    <tbody>
	                    <tr class="table-head goal-color">
	                    	<td><h4>Gardiens</h4></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/time.png"/></td>
	                    	<td><i class="fa fa-soccer-ball-o fa-3x"></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/pass.png"/></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/yellow.png"/></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/red.png"/></i></td>
	                    </tr>
	                    <c:forEach var="player" items="${goals}">
		                    <tr class="table-body">
		                      <td id="${player.id}">
		                        <span><c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</span>
		                      </td>
		                     <td>
		                      	<c:out value="${statsTeam[player.id].playingMinutes}"/>
		                      </td>
		                        <td>
		                      		<c:out value="${statsTeam[player.id].goals}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].passes}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].yellowCard}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].redCard}"/>
		                      </td>
		                    </tr>
	                    </c:forEach>
	                    <tr class="table-head defenser-color">
	                    	<td><h4>Défenseurs</h4></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/time.png"/></td>
	                    	<td><i class="fa fa-soccer-ball-o fa-3x"></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/pass.png"/></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/yellow.png"/></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/red.png"/></i></td>
	                    </tr>
	                    <c:forEach var="player" items="${defensers}">
		                    <tr class="table-body">
		                      <td id="${player.id}">
		                        <span><c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</span>
		                      </td>
		                      <td>
		                      	<c:out value="${statsTeam[player.id].playingMinutes}"/>
		                      </td>
		                       <td>
		                      		<c:out value="${statsTeam[player.id].goals}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].passes}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].yellowCard}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].redCard}"/>
		                      </td>
		                    </tr>
	                    </c:forEach>
	                    <tr class="table-head middle-color">
	                    	<td><h4>Milieux de terrain</h4></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/time.png"/></td>
	                    	<td><i class="fa fa-soccer-ball-o fa-3x"></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/pass.png"/></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/yellow.png"/></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/red.png"/></i></td>
	                    </tr>
	                    <c:forEach var="player" items="${middles}">
		                    <tr class="table-body">
		                      <td id="${player.id}">
		                        <span><c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</span>
		                      </td>
		                      <td>
		                      	<c:out value="${statsTeam[player.id].playingMinutes}"/>
		                      </td>
		                       <td>
		                      		<c:out value="${statsTeam[player.id].goals}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].passes}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].yellowCard}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].redCard}"/>
		                      </td>
		                    </tr>
	                    </c:forEach>
	                    <tr class="table-head striker-color">
	                    	<td><h4>Attaquants</h4></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/time.png"/></td>
	                    	<td><i class="fa fa-soccer-ball-o fa-3x"></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/pass.png"/></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/yellow.png"/></i></td>
	                    	<td><img height="30px" src="<%=request.getContextPath()%>/resources/images/icones/red.png"/></i></td>
	                    </tr>
	                    <c:forEach var="player" items="${strikers}">
		                    <tr class="table-body">
		                      <td id="${player.id}">
		                        <span><c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</span>
		                      </td>
		                      <td>
		                      	<c:out value="${statsTeam[player.id].playingMinutes}"/>
		                      </td>
		                       <td>
		                      	<c:out value="${statsTeam[player.id].goals}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].passes}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].yellowCard}"/>
		                      </td>
		                      <td>
		                      		<c:out value="${statsTeam[player.id].redCard}"/>
		                      </td>
		                    </tr>
	                    </c:forEach>
	                    </tbody>
	                    </table>
                </div>
              </div>
            </div>
        </section>
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
    var team = "${team.name}";
    $(document).ready(function(){
    });
    
    </script>

  </body>
</html>