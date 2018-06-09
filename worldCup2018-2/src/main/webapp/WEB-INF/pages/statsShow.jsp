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
            		<div class="kode-section-title"> <h2>Classement des buteurs</h2></div>
            		<c:choose>
						<c:when test="${not empty goalsStats }">
							<c:forEach items="${goalsStats}" var="goalsStat" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>${status.index + 1 }</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${goalsStat.player.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span>${goalsStat.player.name}</span>
			                        <span>${goalsStat.goals}</span>
			                      </li>
			                    </ul>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun buteurs</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2>Classement des passeurs</h2></div>
            		<c:choose>
						<c:when test="${not empty passesStats }">
							<c:forEach items="${passesStats}" var="passesStat" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>${status.index + 1 }</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${passesStat.player.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span>${passesStat.player.name}</span>
			                        <span>${passesStat.passes}</span>
			                      </li>
			                    </ul>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun buteurs</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2>Classement des cartons jaunes</h2></div>
            		<c:choose>
						<c:when test="${not empty yellowCardsStats }">
							<c:forEach items="${yellowCardsStats}" var="yellowCardStat" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>${status.index + 1 }</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${yellowCardStat.player.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span>${yellowCardStat.player.name}</span>
			                        <span>${yellowCardStat.yellowCards}</span>
			                      </li>
			                    </ul>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun cartons jaunes</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2>Classement des cartons rouges</h2></div>
            		<c:choose>
						<c:when test="${not empty redCardsStats }">
							<c:forEach items="${redCardsStats}" var="redCardStats" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>${status.index + 1 }</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${redCardStats.player.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span>${redCardStats.player.name}</span>
			                        <span>${redCardStats.redCards}</span>
			                      </li>
			                    </ul>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun cartons rouges</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2>Meilleure Attaque</h2></div>
            		<c:choose>
						<c:when test="${not empty bestAttackTeams }">
							<c:forEach items="${bestAttackTeams}" var="bestAttackTeam" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>${status.index + 1 }</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${bestAttackTeam.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span>${bestAttackTeam.team.name}</span>
			                        <span>${bestAttackTeam.goals}</span>
			                      </li>
			                    </ul>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun résultat</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2>Pire défense</h2></div>
            		<c:choose>
						<c:when test="${not empty worstDefenseTeams }">
							<c:forEach items="${worstDefenseTeams}" var="worstDefenseTeam" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>${status.index + 1 }</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${worstDefenseTeam.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span>${worstDefenseTeam.team.name}</span>
			                        <span>${worstDefenseTeam.goals}</span>
			                      </li>
			                    </ul>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun résultat</span>
						</c:otherwise>            		
            		</c:choose>   
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