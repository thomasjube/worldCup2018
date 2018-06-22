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
						<li><a href="../winabet/edit">Saisie pari</a></li>
						<li><a href="../winabet/game">Résultat par match</a></li>
						<li><a href="../winabet">Classement général</a></li>
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
            		<div class="kode-section-title"> <h2 ${not empty goalsStats ? 'style="color:white !important"' : ''} >Classement des buteurs</h2></div>
            		<c:choose>
						<c:when test="${not empty goalsStats }">
							<c:set var="previousGoals" value ="0"/>
							<c:set var="indexGoals" value ="1"/>
							<ul class="table-body"><li><span>Position</span><span>Joueur</span><span>Buts</span><span>Match</span><span style="width:2em">Ratio</span></li></ul>
							<c:forEach items="${goalsStats}" var="goalsStat" varStatus="status">
			                    <c:if test="${not empty goalsStat.player}">
				                    <ul class="table-body">
				                      <li>
				                      	<span>
				                      		<c:choose>
				                      			<c:when test="${previousGoals == goalsStat.goals }"> - </c:when>
				                      			<c:otherwise>
				                      				${indexGoals }
				                      				<c:set var="previousGoals" value ="${goalsStat.goals }"/>
				                      				<c:set var="indexGoals" value ="${indexGoals + 1 }"/>
				                      			</c:otherwise>
				                      		</c:choose>
				                      	</span>
				                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${goalsStat.player.team.name}.png" alt="" width="30" height="20">&nbsp;
				                        <span><c:if test="${not empty goalsStat.player.firstName}">${goalsStat.player.firstName.charAt(0)}.&nbsp;</c:if>${goalsStat.player.name}</span>
				                        <span>${goalsStat.goals}<c:if test="${goalsStat.penalties > 0 }">&nbsp;(${goalsStat.penalties} sp)</c:if></span>
				                        <span>${goalsStat.totalGames}</span>
				                        <span style="width:2em">${goalsStat.ratio}</span>
				                      </li>
				                    </ul>
			                    </c:if>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun buteurs</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2 ${not empty passesStats ? 'style="color:white !important"' : ''} >Classement des passeurs</h2></div>
            		<c:choose>
						<c:when test="${not empty passesStats }">
							<c:set var="previousPasses" value ="0"/>
							<c:set var="indexPasses" value ="1"/>
							<ul class="table-body"><li><span>Position</span><span>Joueur</span><span>Passes</span><span>Match</span><span style="width:2em">Ratio</span></li></ul>
							<c:forEach items="${passesStats}" var="passesStat" varStatus="status">
			                    <c:if test="${not empty passesStat.player}">
				                    <ul class="table-body">
				                      <li>
										<span>
				                      		<c:choose>
				                      			<c:when test="${previousPasses == passesStat.passes }"> - </c:when>
				                      			<c:otherwise>
				                      				${indexPasses }
				                      				<c:set var="previousPasses" value ="${passesStat.passes }"/>
				                      				<c:set var="indexPasses" value ="${indexPasses + 1 }"/>
				                      			</c:otherwise>
				                      		</c:choose>
				                      	</span>
				                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${passesStat.player.team.name}.png" alt="" width="30" height="20">&nbsp;
				                        <span><c:if test="${not empty passesStat.player.firstName}">${passesStat.player.firstName.charAt(0)}.&nbsp;</c:if>${passesStat.player.name}</span>
				                        <span>${passesStat.passes}</span>
				                        <span>${passesStat.totalGames}</span>
				                        <span style="width:2em">${passesStat.ratio}</span>
				                      </li>
				                    </ul>
			                    </c:if>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<span>Aucun buteurs</span>
						</c:otherwise>            		
            		</c:choose>
            		<br/><br/><br/>
            		<div class="kode-section-title"> <h2 ${not empty yellowCardsStats ? 'style="color:white !important"' : ''} >Classement des cartons jaunes</h2></div>
            		<c:choose>
						<c:when test="${not empty yellowCardsStats }">
							<c:set var="previousYellow" value ="0"/>
							<c:set var="indexYellow" value ="1"/>
							<c:forEach items="${yellowCardsStats}" var="yellowCardStat" varStatus="status">
			                    <ul class="table-body">
			                      <li>
			                      	<span>
			                      		<c:choose>
			                      			<c:when test="${previousYellow == yellowCardStat.yellowCards }"> - </c:when>
			                      			<c:otherwise>
			                      				${indexYellow }
			                      				<c:set var="previousYellow" value ="${yellowCardStat.yellowCards }"/>
			                      				<c:set var="indexYellow" value ="${indexYellow + 1 }"/>
			                      			</c:otherwise>
			                      		</c:choose>
			                      	</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${yellowCardStat.player.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span><c:if test="${not empty yellowCardStat.player.firstName}">${yellowCardStat.player.firstName.charAt(0)}.&nbsp;</c:if>${yellowCardStat.player.name}</span>
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
            		<div class="kode-section-title"><h2 ${not empty redCardsStats ? 'style="color:white !important"' : ''} >Classement des cartons rouges</h2></div>
            		<c:choose>
						<c:when test="${not empty redCardsStats }">
							<c:set var="previousReds" value ="0"/>
							<c:set var="indexReds" value ="1"/>
							<c:forEach items="${redCardsStats}" var="redCardStats" varStatus="status">
			                    <ul class="table-body">
			                      <li>
		                      		<span>
			                      		<c:choose>
			                      			<c:when test="${previousReds == redCardStats.redCards }"> - </c:when>
			                      			<c:otherwise>
			                      				${indexReds }
			                      				<c:set var="previousReds" value ="${redCardStats.redCards }"/>
			                      				<c:set var="indexReds" value ="${indexReds + 1 }"/>
			                      			</c:otherwise>
			                      		</c:choose>
			                      	</span>
			                        <img style="top:1em;" src="<%=request.getContextPath()%>/resources/images/flag/${redCardStats.player.team.name}.png" alt="" width="30" height="20">&nbsp;
			                        <span><c:if test="${not empty redCardStats.player.firstName}">${redCardStats.player.firstName.charAt(0)}.&nbsp;</c:if>${redCardStats.player.name}</span>
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
            		<div class="kode-section-title"> <h2 ${not empty bestAttackTeams ? 'style="color:white !important"' : ''} >Meilleure Attaque</h2></div>
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
            		<div class="kode-section-title"> <h2 ${not empty worstDefenseTeams ? 'style="color:white !important"' : ''} >Pire défense</h2></div>
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
            	<br/><br/><br/>
				<div class="kode-section-title"><h2 style="color:white !important">Stats général</h2></div>
			    	<ul class="table-body">
			        	<li><span>Nombre de pénalty : </span><span>${countPenalties}</span></li>
			        </ul>
			    	<ul class="table-body">
			        	<li><span>Nombre de CSC : </span><span>${countCSC}</span></li>
			        </ul>
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