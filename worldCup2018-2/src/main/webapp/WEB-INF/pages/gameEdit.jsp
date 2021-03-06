<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
						<li><a href="../winabet/edit?id=${game.id}">Saisie pari</a></li>
						<li><a href="../winabet/game?id=${game.id}">Résultat par match</a></li>
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
        <c:set var="previous" value=""/>
	        <section class="kode-pagesection margin-bottom-40">
	          <div class="container">
	            <div class="row">
					<div class="col-md-12">
						<div class="heading heading-12 margin-top30-bottom10">
							<h2><span class="left"></span>${game.name}<span class="right"></span></h2>
							<br/>
							<img style="border:1px black solid;position:relative;float:left;top:-3em;margin-right:0.8em" src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="200" height="150">&nbsp;
							<img style="border:1px black solid;position:relative;float:right;top:-3em;margin-left:0.8em" src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="200" height="150">&nbsp;
							<br/>
						</div>
						<span style="top:-1.5em;color:#000;font-family:'Open Sans', sans-serif;font-weight:bold;text-transform:uppercase;font-size:25px;position:relative;float:left;">${game.team1.name}</span>
		                <span style="top:-1.5em;color:#000;font-family:'Open Sans', sans-serif;font-weight:bold;text-transform:uppercase;font-size:25px;position:relative;float:right;">${game.team2.name}</span>
		                     
					</div>	
	                <div class="col-md-8">
		             	<c:set var="mapTeam1" value="${titulars[game.team1.id]}"/>
		              	<c:set var="mapTeam2" value="${titulars[game.team2.id]}"/>
	                  <form:form action="" method="post" modelAttribute="editForm">
	                 	 <form:hidden path="id"/>
	                 	 <form:hidden path="prolong"/>
	                 	 <form:hidden path="peno"/>
	                 	 
							<table class="kode-table">
		                    <thead>
		                      <tr>
		                        <th>Composition</th>
								<th style='text-align:right'>
								<input class="table-button" type="button" value="Editer" onclick="window.location='editGameCompo?id=${game.id}'">
								<input id="show-composition" ${empty mapTeam1 and empty mapTeamp2 ? 'type="hidden"' : 'type="button"' } ${not empty mapTeam1 or not empty mapTeamp2 ? 'value="Fermer"' : 'value="Ouvrir"' } class="table-button"></th>
		                      </tr>
		                    </thead>
		                    <tbody id="body-compisition">
		                    	<c:forEach begin="0" end="${11}" var="index">
		                    		<tr id="${game.id}" style="display:none">
		                    			<c:choose>
		                    				<c:when test="${not empty mapTeam1 and mapTeam1.size() > index}">
		                    					<c:set var="player" value="${mapTeam1.get(index)}"/>
		                    					 <td width="50%">
													<c:if test="${player.id == game.captainTeam1.id }"><img style="color:white" width="4%" src="<%=request.getContextPath()%>/resources/images/icones/captain.png" alt=""></c:if>
		                    					 	  <c:choose>
		                    							<c:when test="${player.poste == 'G'}">Gardien - ${player.number} - </c:when>
		                    							<c:when test="${player.poste == 'D'}">Défenseur - ${player.number} - </c:when>
		                    							<c:when test="${player.poste == 'M'}">Milieux - ${player.number} - </c:when>
		                    							<c:when test="${player.poste == 'A'}">Attaquant - ${player.number} - </c:when>
		                    						</c:choose> 
			                    					<c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}
			                    					<c:if test="${not empty game.goalsPlayerTeam1[player.id] }">
			                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam1[player.id] > 1 }"> x ${game.goalsPlayerTeam1[player.id]}</c:if>
			                    					</c:if>
			                    					<c:if test="${not empty game.goalsPlayerCSCTeam2[player.id] }">
			                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam2[player.id] > 1 }"> x ${game.goalsPlayerCSCTeam2[player.id]}</c:if>
			                    					</c:if>
			                    					<c:if test="${not empty game.cartonsTeam1[player.id] }">
			                    						<c:choose>
			                    							<c:when test="${game.cartonsTeam1[player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
			                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
			                    						</c:choose>
			                    					</c:if>
			                    					
			                    					<c:if test="${not empty substitutes[player.id]}">
			                    						<c:set var="playerSubstitute" value="${substitutes[player.id]}"/>
			                    						<img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/remplacement.png" alt="">&nbsp; ${playerSubstitute.player.number} - <c:if test="${not empty playerSubstitute.player.firstName}">${playerSubstitute.player.firstName.charAt(0)}.&nbsp;</c:if>${playerSubstitute.player.name} (${playerSubstitute.player.poste}) - ${playerSubstitute.minute }'
			                    						<c:if test="${not empty game.goalsPlayerTeam1[playerSubstitute.player.id] }">
			                    							- <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam1[playerSubstitute.player.id] > 1 }"> x ${game.goalsPlayerTeam1[playerSubstitute.player.id]}</c:if>
			                    						</c:if>
			                    						<c:if test="${not empty game.goalsPlayerCSCTeam2[playerSubstitute.player.id] }">
			                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam2[playerSubstitute.player.id] > 1 }"> x ${game.goalsPlayerCSCTeam2[playerSubstitute.player.id]}</c:if>
				                    					</c:if>
			                    						<c:if test="${not empty game.cartonsTeam1[playerSubstitute.player.id] }">
				                    						<c:choose>
				                    							<c:when test="${game.cartonsTeam1[playerSubstitute.player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
				                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
				                    						</c:choose>
				                    					</c:if>
														<c:if test="${not empty substitutes[playerSubstitute.player.id]}">
			                    							<c:set var="playerSubstitute2" value="${substitutes[playerSubstitute.player.id]}"/>
			                    							<img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/remplacement.png" alt="">&nbsp; ${playerSubstitute2.player.number} - <c:if test="${not empty playerSubstitute2.player.firstName}">${playerSubstitute2.player.firstName.charAt(0)}.&nbsp;</c:if>${playerSubstitute2.player.name} (${playerSubstitute2.player.poste}) - ${playerSubstitute2.minute }'
			                    							<c:if test="${not empty game.goalsPlayerTeam1[playerSubstitute2.player.id] }">
			                    								- <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam1[playerSubstitute2.player.id] > 1 }"> x ${game.goalsPlayerTeam1[playerSubstitute2.player.id]}</c:if>
			                    							</c:if>
			                    							<c:if test="${not empty game.goalsPlayerCSCTeam2[playerSubstitute2.player.id] }">
			                    							 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam2[playerSubstitute2.player.id] > 1 }"> x ${game.goalsPlayerCSCTeam2[playerSubstitute2.player.id]}</c:if>
				                    						</c:if>
			                    							<c:if test="${not empty game.cartonsTeam1[playerSubstitute2.player.id] }">
					                    						<c:choose>
					                    							<c:when test="${game.cartonsTeam1[playerSubstitute2.player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
					                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
					                    						</c:choose>
					                    					</c:if>
															<c:if test="${not empty substitutes[playerSubstitute2.player.id]}">
				                    							<c:set var="playerSubstitute3" value="${substitutes[playerSubstitute2.player.id]}"/>
				                    							<img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/remplacement.png" alt="">&nbsp; ${playerSubstitute3.player.number} - <c:if test="${not empty playerSubstitute3.player.firstName}">${playerSubstitute3.player.firstName.charAt(0)}.&nbsp;</c:if>${playerSubstitute3.player.name} (${playerSubstitute2.player.poste}) - ${playerSubstitute3.minute }'
				                    							<c:if test="${not empty game.goalsPlayerTeam1[playerSubstitute3.player.id] }">
				                    								- <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam1[playerSubstitute3.player.id] > 1 }"> x ${game.goalsPlayerTeam1[playerSubstitute3.player.id]}</c:if>
				                    							</c:if>
			                    								<c:if test="${not empty game.goalsPlayerCSCTeam2[playerSubstitute3.player.id] }">
				                    							 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam2[playerSubstitute3.player.id] > 1 }"> x ${game.goalsPlayerCSCTeam2[playerSubstitute3.player.id]}</c:if>
				                    							</c:if>
				                    							<c:if test="${not empty game.cartonsTeam1[playerSubstitute3.player.id] }">
						                    						<c:choose>
						                    							<c:when test="${game.cartonsTeam1[playerSubstitute3.player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
						                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
						                    						</c:choose>
						                    					</c:if>
			                    							</c:if>
			                    						</c:if>
			                    					</c:if>
		                    					 </td>
		                    				</c:when>
		                    				<c:otherwise><td width="50%"></td></c:otherwise>
		                    			</c:choose>
		                    			<c:choose>
	                    					<c:when test="${not empty mapTeam2 and mapTeam2.size() > index}">
              								<c:set var="player" value="${mapTeam2.get(index)}"/>
		                    					 <td width="50%">
		                    					 	<c:if test="${player.id == game.captainTeam2.id }"><img style="color:white" width="4%" src="<%=request.getContextPath()%>/resources/images/icones/captain.png" alt=""></c:if>
		                    					 	  <c:choose>
		                    							<c:when test="${player.poste == 'G'}">Gardien - ${player.number} - </c:when>
		                    							<c:when test="${player.poste == 'D'}">Défenseur - ${player.number} - </c:when>
		                    							<c:when test="${player.poste == 'M'}">Milieux - ${player.number} - </c:when>
		                    							<c:when test="${player.poste == 'A'}">Attaquant - ${player.number} - </c:when>
		                    						</c:choose> 
													<c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}
	                    							<c:if test="${not empty game.goalsPlayerTeam2[player.id] }">
			                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam2[player.id] > 1 }"> x ${game.goalsPlayerTeam2[player.id]}</c:if>
			                    					</c:if>
                    								<c:if test="${not empty game.goalsPlayerCSCTeam1[player.id] }">
	                    							 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam1[player.id] > 1 }"> x ${game.goalsPlayerCSCTeam1[player.id]}</c:if>
	                    							</c:if>
													<c:if test="${not empty game.cartonsTeam2[player.id] }">
			                    						<c:choose>
			                    							<c:when test="${game.cartonsTeam2[player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
			                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
			                    						</c:choose>
			                    					</c:if>
			                    					<c:if test="${not empty substitutes[player.id]}">
			                    						<c:set var="playerSubstitute" value="${substitutes[player.id]}"/>
			                    						<img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/remplacement.png" alt="">&nbsp; ${playerSubstitute.player.number} - <c:if test="${not empty playerSubstitute.player.firstName }">${playerSubstitute.player.firstName.charAt(0)}.&nbsp;</c:if>${playerSubstitute.player.name} (${playerSubstitute.player.poste}) - ${playerSubstitute.minute }'
		                    							<c:if test="${not empty game.goalsPlayerTeam2[playerSubstitute.player.id] }">
				                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam2[playerSubstitute.player.id] > 1 }"> x ${game.goalsPlayerTeam2[playerSubstitute.player.id]}</c:if>
				                    					</c:if>
                    									<c:if test="${not empty game.goalsPlayerCSCTeam1[playerSubstitute.player.id] }">
	                    								 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam1[playerSubstitute.player.id] > 1 }"> x ${game.goalsPlayerCSCTeam1[playerSubstitute.player.id]}</c:if>
	                    								</c:if>
			                    						<c:if test="${not empty game.cartonsTeam2[playerSubstitute.id] }">
			                    						<c:choose>
			                    							<c:when test="${game.cartonsTeam2[playerSubstitute.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
			                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
			                    						</c:choose>
			                    					</c:if>
														<c:if test="${not empty substitutes[playerSubstitute.player.id]}">
			                    							<c:set var="playerSubstitute2" value="${substitutes[playerSubstitute.player.id]}"/>
			                    							<img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/remplacement.png" alt="">&nbsp; ${playerSubstitute2.player.number} - <c:if test="${not empty playerSubstitute2.player.firstName }">${playerSubstitute2.player.firstName.charAt(0)}.&nbsp;</c:if>${playerSubstitute2.player.name} (${playerSubstitute.player.poste}) - ${playerSubstitute2.minute }'
			                    							<c:if test="${not empty game.goalsPlayerTeam2[playerSubstitute2.player.id] }">
					                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam2[playerSubstitute2.player.id] > 1 }"> x ${game.goalsPlayerTeam2[playerSubstitute2.player.id]}</c:if>
					                    					</c:if>
	                    									<c:if test="${not empty game.goalsPlayerCSCTeam1[playerSubstitute2.player.id] }">
		                    								 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam1[playerSubstitute2.player.id] > 1 }"> x ${game.goalsPlayerCSCTeam1[playerSubstitute2.player.id]}</c:if>
		                    								</c:if>
			                    							<c:if test="${not empty game.cartonsTeam2[playerSubstitute2.player.id] }">
					                    						<c:choose>
					                    							<c:when test="${game.cartonsTeam2[playerSubstitute2.player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
					                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
					                    						</c:choose>
					                    					</c:if>
															<c:if test="${not empty substitutes[playerSubstitute2.player.id]}">
				                    							<c:set var="playerSubstitute3" value="${substitutes[playerSubstitute2.player.id]}"/>
				                    							<img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/remplacement.png" alt="">&nbsp; ${playerSubstitute3.player.number} - <c:if test="${not empty playerSubstitute3.player.firstName }">${playerSubstitute3.player.firstName.charAt(0)}.&nbsp;</c:if>${playerSubstitute3.player.name} (${playerSubstitute.player.poste}) - ${playerSubstitute3.minute }'
				                    							<c:if test="${not empty game.goalsPlayerTeam2[playerSubstitute3.player.id] }">
						                    						 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_2.png" alt=""><c:if test="${game.goalsPlayerTeam2[playerSubstitute3.player.id] > 1 }"> x ${game.goalsPlayerTeam2[playerSubstitute3.player.id]}</c:if>
						                    					</c:if>
		                    									<c:if test="${not empty game.goalsPlayerCSCTeam1[playerSubstitute3.player.id] }">
			                    								 - <img style="color:white" width="3%" src="<%=request.getContextPath()%>/resources/images/icones/goal_csc.png" alt=""><c:if test="${game.goalsPlayerCSCTeam1[playerSubstitute3.player.id] > 1 }"> x ${game.goalsPlayerCSCTeam1[playerSubstitute3.player.id]}</c:if>
			                    								</c:if>
				                    							<c:if test="${not empty game.cartonsTeam2[playerSubstitute3.player.id] }">
						                    						<c:choose>
						                    							<c:when test="${game.cartonsTeam2[playerSubstitute3.player.id].action == 'YELLOW_CARD' }"><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_jaune.png" alt=""></c:when>
						                    							<c:otherwise><img width="5%" src="<%=request.getContextPath()%>/resources/images/icones/cartons_rouge.png" alt=""></c:otherwise>
						                    						</c:choose>
						                    					</c:if> 
			                    							</c:if>
			                    						</c:if>
			                    					</c:if>
		                    					 </td>
	                    					</c:when>
		                    				<c:otherwise><td width="50%"></td></c:otherwise>	                    			
		                    			</c:choose>
		                    		</tr>
		                    	</c:forEach>
		                    </tbody>
		                 </table>
		                  <table class="kode-table">
		                    <thead>
		                      <tr>
		                        <th>Match</th>
		                        <th class="game-date">Heure</th>
		                        <th class="game-stade">Stade</th>
		                      </tr>
		                    </thead>
		                    <tbody id="resultBody">
		                      <tr id="${game.id}">
		                        <td style="text-align: center;width:100%">
		                        <img style="position:relative;float:left;top:1em;margin-right:0.8em" src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="30" height="20">&nbsp;
		                        <span class="name-team">${game.team1.name}</span> 
		                        <span class="score-team"><form:select path="score1"><c:forEach begin="0" end="15" var="score"><form:option value="${score}" label="${score}"/></c:forEach></form:select></span>
		                        <span class="span-vs-edit">VS</span>
		                        <img style="position:relative;float:right;margin-left:0.8em;top:1em;"src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="30" height="20">
		                        <span class="name-team2">${game.team2.name}</span>&nbsp;
		                        <span class="score-team2"><form:select path="score2"><c:forEach begin="0" end="15" var="score"><form:option value="${score}" label="${score}"/></c:forEach></form:select></span>
		                        </td>
		                        <td class="game-date"><tags:localDate date="${game.date}"/><br/>${game.time}</td>
		                        <td class="game-stade">${game.stade}</td>
		                      </tr>
		                      <c:if test="${game.prolong }">
		                      	<tr id="resultProlong">
		                      		<td style="text-align: center;width:100%">
			                        <span class="name-team"></span> 
			                        <span class="score-team"><form:select path="scoreProlong1"><c:forEach begin="0" end="15" var="score"><form:option value="${score}" label="${score}"/></c:forEach></form:select></span>
			                        <span class="span-vs-edit">VS</span>
			                        <span class="name-team2"></span>&nbsp;
			                        <span class="score-team2"><form:select path="scoreProlong2"><c:forEach begin="0" end="15" var="score"><form:option value="${score}" label="${score}"/></c:forEach></form:select></span>
			                        </td>
			                        <td class="game-date"><br/></td>
			                        <td class="game-stade"></td>
		                      	</tr>
		                      </c:if>
		                      
		                      <c:if test="${game.penalti }">
		                      	<tr id="resultPeno">
		                      		<td style="text-align: center;width:100%">
			                        <span class="name-team"></span> 
			                        <span class="score-team"><form:select path="scorePeno1"><c:forEach begin="0" end="15" var="score"><form:option value="${score}" label="${score}"/></c:forEach></form:select></span>
			                        <span class="span-vs-edit">VS</span>
			                        <span class="name-team2"></span>&nbsp;
			                        <span class="score-team2"><form:select path="scorePeno2"><c:forEach begin="0" end="15" var="score"><form:option value="${score}" label="${score}"/></c:forEach></form:select></span>
			                        </td>
			                        <td class="game-date"><br/></td>
			                        <td class="game-stade"></td>
		                      	</tr>
		                      </c:if>
		                    </tbody>
		                 </table>
		                 
		                 <table class="kode-table">
		                 	<thead>
		                 	<th colspan="2">Buteur</th>
		                 	<th>Passeur</th>
		                 	<th style='width:4em;'>Minute</th>
		                 	<th colspan="2">Buteur</th>
		                 	<th>Passeur</th>
		                 	<th style='width:4em;'>Minute</th>
		                 	</thead>
			                 <tbody id="playerBody">
			                 	<c:if test="${indexMax > 0 }">
				                 	<c:forEach begin="0" end="${indexMax-1}" var="index">
				                 		<tr id="tr-player-${index}">
				                 			<c:choose>
					                 			<c:when test="${not empty game.getGoalsTeam1() && index < game.score1 && not empty game.getGoalsTeam1().get(index)}">
					                 				<td class="player-team1"><tags:players isGoal="true" isCarton="${false}" index="${index}" items="${game.team1.players }" path="scorerPlayers1[${index}]"></tags:players></td>
					                 				<td class="penalty-team1"><input type="checkbox" name="scorerPenalty1[${index}]" ${editForm.scorerPenalty1[index] ? 'checked="checked"' : ''}/></td>
					                 				<td class="passer-team1"><tags:players isCarton="${false}" index="${index}" items="${empty editForm.scorerPlayers1[index] ? game.team2.players : game.team1.players }" path="passerPlayers1[${index}]"></tags:players></td>
					                 				<td class="minute-team1"><tags:minutes path="scorerPlayersMinute1[${index}]"/></td>
					                 			</c:when>
					                 			<c:otherwise>
								                 	<td class="player-team1"></td>
								                 	<td class="penalty-team1"></td>
					                 				<td class="passer-team1"></td>
					                 				<td class="minute-team1"></td>		
					                 			</c:otherwise>
				                 			</c:choose>
				                 			<c:choose>
					                 			<c:when test="${not empty game.getGoalsTeam2() && index < game.score2  && not empty game.getGoalsTeam2().get(index)}">
					                 				<td class="player-team2"><tags:players isGoal="true" isCarton="${false}" index="${index}" items="${game.team2.players }" path="scorerPlayers2[${index}]"></tags:players></td>
					                 				<td class="penalty-team2"><input type="checkbox" name="scorerPenalty2[${index}]" ${editForm.scorerPenalty2[index] ? 'checked="checked"' : ''}/></td>
					                 				<td class="passer-team2"><tags:players isCarton="${false}" index="${index}" items="${empty editForm.scorerPlayers2[index] ? game.team1.players : game.team2.players }" path="passerPlayers2[${index}]"></tags:players></td>
					                 				<td class="minute-team2"><tags:minutes path="scorerPlayersMinute2[${index}]"/> </td>
					                 			</c:when>
					                 			<c:otherwise>
								                 	<td class="player-team2"></td>
								                 	<td class="penalty-team2"></td>
					                 				<td class="passer-team2"></td>
					                 				<td class="minute-team2"></td>		
					                 			</c:otherwise>
				                 			</c:choose>
				                 		</tr>
				                 	</c:forEach>
			                 	</c:if>
			                 	<c:if test="${indexMaxProlong > 0}">
			                 		<c:forEach begin="0" end="${indexMaxProlong-1}" var="index">
				                 		<tr id="tr-player-prolong-${index}">
				                 			<c:choose>
					                 			<c:when test="${not empty game.getGoalsTeam1() && index < game.score1_prolong && not empty game.getGoalsTeam1().get(index)}">
					                 				<td class="player-team1"><tags:players isGoal="true" isCarton="${false}" index="${index}" items="${game.team1.players }" path="scorerPlayersProlong1[${index}]"></tags:players></td>
					                 				<td class="penalty-team1"><input type="checkbox" name="scorerProlongPenalty1[${index}]" ${editForm.scorerProlongPenalty1[index] ? 'checked="checked"' : ''}/></td>
					                 				<td class="passer-team1"><tags:players isCarton="${false}" index="${index}" items="${empty editForm.scorerPlayersProlong1[index] ? game.team2.players : game.team1.players }" path="passerPlayersProlong1[${index}]"></tags:players></td>
					                 				<td class="minute-team1"><tags:minutes path="scorerPlayersMinuteProlong1[${index}]"/></td>
					                 			</c:when>
					                 			<c:otherwise>
								                 	<td class="player-team1"></td>
								                 	<td class="penalty-team1"></td>
					                 				<td class="passer-team1"></td>
					                 				<td class="minute-team1"></td>		
					                 			</c:otherwise>
				                 			</c:choose>
				                 			<c:choose>
					                 			<c:when test="${not empty game.getGoalsTeam2() && index < game.score2_prolong  && not empty game.getGoalsTeam2().get(index)}">
					                 				<td class="player-team2"><tags:players isGoal="true" isCarton="${false}" index="${index}" items="${game.team2.players }" path="scorerPlayersProlong2[${index}]"></tags:players></td>
					                 				<td class="penalty-team2"><input type="checkbox" name="scorerProlongPenalty2[${index}]" ${editForm.scorerProlongPenalty2[index] ? 'checked="checked"' : ''}/></td>
					                 				<td class="passer-team2"><tags:players isCarton="${false}" index="${index}" items="${empty editForm.scorerPlayersProlong2[index] ? game.team1.players : game.team2.players }" path="passerPlayersProlong2[${index}]"></tags:players></td>
					                 				<td class="minute-team2"><tags:minutes path="scorerPlayersMinuteProlong2[${index}]"/> </td>
					                 			</c:when>
					                 			<c:otherwise>
								                 	<td class="player-team2"></td>
								                 	<td class="penalty-team2"></td> 
					                 				<td class="passer-team2"></td>
					                 				<td class="minute-team2"></td>		
					                 			</c:otherwise>
				                 			</c:choose>
				                 		</tr>
				                 	</c:forEach>
			                 	</c:if>
			                 </tbody>
		                 </table>
		                 <table class="kode-table">
		                 	<thead>
			                 	<th>Equipe</th>
			                 	<th>Joueur</th>
			                 	<th>Carton</th>
			                 	<th style='width:7em;'>Minute</th>
			                 	<th><input id="add-carton" type="button" value="Ajouter" class="table-button"></th>
		                 	</thead>
		                 	<tbody id="playerCartonBody">
				                 	<c:forEach items="${game.getCartons()}" var="carton" varStatus="status">
				                 		<tr id="tr-carton-player-${status.index}">
					                 		<td class="teamCarton"><tags:teams path="cartonTeam[${status.index}]" game="${game}"/></td>
					                 		<td class="playerCarton"><tags:players isCarton="${true}" index="${status.index}" items="${carton.player.team.players }" path="cartonPlayers[${status.index}]"></tags:players></td>
					                 		<td class="typeCarton"><tags:carton path="cartonType[${status.index}]"></tags:carton> </td>
					                 		<td style='width:7em;' class="minuteCarton"><tags:minutes isProlong="true" path="cartonMinute[${status.index}]"/></td>
					                 		<td><input type="button" class="removeCarton table-button" value="Effacer"></td>
				                 		</tr>
				                 	</c:forEach>
			                 </tbody>
		                 </table>
		                 <br/>
		                 <input class="form-button" type="submit" value="Enregistrer">
		                 <input class="form-button"type="button" value="Reset" onclick="window.location='reset?id=${game.id}'">
	                 </form:form>
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
	
	var gameInPoule = ${game.gameInPoule};
	
	$(document).ready(function(){
		var value = $("#show-composition").val();
		if(value == 'Ouvrir'){
			$('tbody#body-compisition tr').css('display', 'none');
		}else{
			$('tbody#body-compisition tr').css('display', 'table-row');
		}
	});
	
	$("#show-composition").click(function(e){
		var value = $(this).val();
		if(value == 'Ouvrir'){
			$('tbody#body-compisition tr').css('display', 'table-row');
			$(this).val('Fermer');
		}else{
			$('tbody#body-compisition tr').css('display', 'none');
			$(this).val('Ouvrir');
		}
	});
	
	$(document).on('change','.carton-team',function(e){
		var value = $(this).val();
		var index = $(this).attr('id').split("Team")[1];
		var optionSelected = $(this).find(":selected");
		var dataIndex = optionSelected.data("index");
		
		if(dataIndex == 1)
		{
			$("#select-carton-player"+index).parent().html("<select id='select-carton-player"+index+"' name='cartonPlayers["+index+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
		}
		else
		{
			$("#select-carton-player"+index).parent().html("<select id='select-carton-player"+index+"' name='cartonPlayers["+index+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
		}
	});
	
	$(document).on('click','.removeCarton',function(e){
		$(this).parent().parent().remove();
	});
	
	$(document).on('change','.player-team2 select',function(e){
		
		if($(this).val() == null || $(this).val() == "")
		{	
			var index = $(this).attr('id').split("player")[1];
			$(this).parent().parent().find('.passer-team2').html("<select  id='select-passer"+index+"' name='passerPlayers2["+index+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
		}
	});
	
	$(document).on('change','.player-team1 select',function(e){
		
		if($(this).val() == null || $(this).val() == "")
		{	
			var index = $(this).attr('id').split("player")[1];
			$(this).parent().parent().find('.passer-team1').html("<select  id='select-passer"+index+"' name='passerPlayers1["+index+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
		}
	});
	
	$("#add-carton").click(function(e){

		var indexMax = $(".carton-team").length;
		var index = indexMax-1;
		
		var tr = "<tr id='tr-carton-player-"+indexMax+"'>";
		tr += "<td class='teamCarton'><select id='cartonTeam"+indexMax+"' name='cartonTeam["+indexMax+"]' class='carton-team' ><option data-index='1' value='${game.team1.id}'>${game.team1.name}</option><option data-index='2' value='${game.team2.id}'>${game.team2.name}</option></select></td>";
		tr += "<td class='playerCarton'><select id='select-carton-player"+indexMax+"' name='cartonPlayers["+indexMax+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td>";
		tr += "<td class='typeCarton'><select name='cartonType["+indexMax+"]'><option value='YELLOW_CARD' selected='selected'>Carton jaune</option><option value='RED_CARD'>Carton rouge</option></select></td>";
		tr += "<td style='width:7em;' class='minuteCarton'><select name='cartonMinute["+indexMax+"]'><c:forEach begin='1' end='120' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td>";
		tr += "<td><input type='button' class='removeCarton table-button' value='Effacer'></td>";
		
		if(indexMax == 0)
			$("#playerCartonBody").append(tr);	
		else
			$("#tr-carton-player-"+index).after(tr);
	});
	
		$("#score1").change(function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-"+i).length && $("select[name='scorerPlayers1["+i+"]']").length == 0)
				{
					$("#tr-player-"+i).find(".player-team1").html("<select  id='select-player"+i+"' name='scorerPlayers1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select>");
					$("#tr-player-"+i).find(".penalty-team1").html("<input type='checkbox' name='scorerPenalty1["+i+"]'/>");
					$("#tr-player-"+i).find(".passer-team1").html("<select  id='select-passer"+i+"' name='passerPlayers1["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".minute-team1").html("<select id='select-minute"+i+"' name='scorerPlayersMinute1["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else if(!$("#tr-player-"+i).length)
				{
					$("#playerBody").append("<tr id='tr-player-"+i+"'><td class='player-team1'><select id='select-player"+i+"' name='scorerPlayers1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select></td><td class='penalty-team1'><input type='checkbox' name='scorerPenalty1["+i+"]'/></td><td class='passer-team1'><select id='select-passer"+i+"' name='passerPlayers1["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team1'><select id='select-minute"+i+"' name='scorerPlayersMinute1["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td><td class='player-team2'></td><td class='penalty-team2'></td><td class='passer-team2'></td><td class='minute-team2'></td></tr>");
				}
			}
			if($('tr[id^="tr-player-"]').length >= parseInt($(this).val()))
			{
				var l = $('tr[id^="tr-player-"]').length;
				for(i=parseInt($(this).val());i<l;i++)
				{
					if(!$("#tr-player-"+i).find(".player-team2").is(':empty'))
					{
						console.log("test2 : "+$('tr[id^="tr-player-"]').length);
						$("#tr-player-"+i).find(".player-team1").html("");
						$("#tr-player-"+i).find(".penalty-team1").html("");
						$("#tr-player-"+i).find(".passer-team1").html("");
						$("#tr-player-"+i).find(".minute-team1").html("");
					}
					else
					{
						$("#tr-player-"+i).remove();
					}
				}
			}
			
			if(!gameInPoule && $(this).val() == $("#score2").val())
			{
				var result = "<tr id='resultProlong'><td style='text-align: center;width:100%'>";
				result += "<span class='score-team'><select name='scoreProlong1'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "<span class='span-vs-edit'>VS</span>";
				result += "<span class='score-team2'><select name='scoreProlong2'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "</td><td class='game-date'></td><td class='game-stade'></td></tr>";
				$("#resultBody").append(result);
				$("#prolong").val(true);
			}
			else if(!gameInPoule && $(this).val() != $("#score2").val())
			{
				$("#resultProlong").remove();
				$('*[class^="tr-player-prolong-"]').remove();
				$("#prolong").val(false);
            }
		});
		
		$("#score2").change(function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-"+i).length && $("select[name='scorerPlayers2["+i+"]']").length == 0)
				{
					$("#tr-player-"+i).find(".player-team2").html("<select id='select-player"+i+"' name='scorerPlayers2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select>");
					$("#tr-player-"+i).find(".penalty-team2").html("<input type='checkbox' name='scorerPenalty2["+i+"]'/>");
					$("#tr-player-"+i).find(".passer-team2").html("<select  id='select-passer"+i+"' name='passerPlayers2["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".minute-team2").html("<select id='select-minute"+i+"' name='scorerPlayersMinute2["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else if(!$("#tr-player-"+i).length)
				{
					$("#playerBody").append("<tr id='tr-player-"+i+"'><td class='player-team1'></td><td class='penalty-team1'></td><td class='passer-team1'></td><td class='minute-team1'></td><td class='player-team2'><select id='select-player"+i+"' name='scorerPlayers2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select></td><td class='penalty-team2'><input type='checkbox' name='scorerPenalty2["+i+"]'/></td><td class='passer-team2'><select id='select-passer"+i+"' name='passerPlayers2["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team2'><select id='select-minute"+i+"' name='scorerPlayersMinute2["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td></tr>");
				}
			}
			if($('tr[id^="tr-player-"]').length > parseInt($(this).val()))
			{
				var l = $('tr[id^="tr-player-"]').length;
				for(i=parseInt($(this).val());i<=l;i++)
				{
					if(!$("#tr-player-"+i).find(".player-team1").is(':empty'))
					{
						$("#tr-player-"+i).find(".player-team2").html("");
						$("#tr-player-"+i).find(".penalty-team2").html("");
						$("#tr-player-"+i).find(".passer-team2").html("");
						$("#tr-player-"+i).find(".minute-team2").html("");
					}
					else
					{
						$("#tr-player-"+i).remove();
					}
				}
			}
			if(!gameInPoule && $(this).val() == $("#score1").val())
			{
				var result = "<tr id='resultProlong'><td style='text-align: center;width:100%'>";
				result += "<span class='score-team'><select name='scoreProlong1'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "<span class='span-vs-edit'>VS</span>";
				result += "<span class='score-team2'><select name='scoreProlong2'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "</td><td class='game-date'></td><td class='game-stade'></td></tr>";
				$("#resultBody").append(result);
				$("#prolong").val(true);
			}
			else if(!gameInPoule && $(this).val() != $("#score1").val())
			{
				$("#resultProlong").remove();
				$('*[class^="tr-player-prolong-"]').remove();
				$("#prolong").val(false);	
			}
		});
		
		
		$("#resultBody").on("change","select[name='scoreProlong1']",function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-prolong-"+i).length && $("select[name='scorerPlayersProlong1["+i+"]']").length == 0)
				{
					$("#tr-player-prolong-"+i).find(".player-team1").html("<select  id='select-player"+i+"' name='scorerPlayersProlong1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select>");
					$("#tr-player-prolong-"+i).find(".penalty-team1").html("<input type='checkbox' name='scorerProlongPenalty1["+i+"]'/>");
					$("#tr-player-prolong-"+i).find(".passer-team1").html("<select  id='select-passer"+i+"' name='passerPlayersProlong1["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-prolong-"+i).find(".minute-team1").html("<select id='select-minute"+i+"' name='scorerPlayersMinuteProlong1["+i+"]'><c:forEach begin='91' end='120' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else if(!$("#tr-player-prolong-"+i).length)
				{
					$("#playerBody").append("<tr id='tr-player-prolong-"+i+"'><td class='player-team1'><select id='select-player"+i+"' name='scorerPlayersProlong1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select></td><td class='penalty-team1'><input type='checkbox' name='scorerProlongPenalty1["+i+"]'/></td><td class='passer-team1'><select id='select-passer"+i+"' name='passerPlayersProlong1["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team1'><select id='select-minute"+i+"' name='scorerPlayersMinuteProlong1["+i+"]'><c:forEach begin='91' end='120' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td><td class='player-team2'></td><td class='penalty-team2'></td><td class='passer-team2'></td><td class='minute-team2'></td></tr>");
				}
			}
			if($('tr[id^="tr-player-prolong-"]').length >= parseInt($(this).val()))
			{
				var l = $('tr[id^="tr-player-prolong-"]').length;
				for(i=parseInt($(this).val());i<l;i++)
				{
					if(!$("#tr-player-prolong-"+i).find(".player-team2").is(':empty'))
					{
						$("#tr-player-prolong-"+i).find(".player-team1").html("");
						$("#tr-player-prolong-"+i).find(".penalty-team1").html("");
						$("#tr-player-prolong-"+i).find(".passer-team1").html("");
						$("#tr-player-prolong-"+i).find(".minute-team1").html("");
					}
					else
					{
						$("#tr-player-prolong-"+i).remove();
					}
				}
			}
			
			if(!gameInPoule && $(this).val() == $("select[name='scoreProlong2']").val())
			{
				var result = "<tr id='resultPeno'><td style='text-align: center;width:100%'>";
				result += "<span class='score-team'><select name='scorePeno1'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "<span class='span-vs-edit'>VS</span>";
				result += "<span class='score-team2'><select name='scorePeno2'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "</td><td class='game-date'></td><td class='game-stade'></td></tr>";
				$("#resultBody").append(result);
				$("#peno").val(true);
			}
			else if(!gameInPoule && $(this).val() != $("select[name='scoreProlong2']").val())
			{
				$("#resultPeno").remove();
				$("#peno").val(false);
            }
		});
		
		$("#resultBody").on("change","select[name='scoreProlong2']",function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-prolong-"+i).length && $("select[name='scorerPlayersProlong2["+i+"]']").length == 0)
				{
					$("#tr-player-prolong-"+i).find(".player-team2").html("<select id='select-player"+i+"' name='scorerPlayersProlong2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select>");
					$("#tr-player-prolong-"+i).find(".penalty-team2").html("<input type='checkbox' name='scorerProlongPenalty2["+i+"]'/>");
					$("#tr-player-prolong-"+i).find(".passer-team2").html("<select  id='select-passer"+i+"' name='passerPlayersProlong2["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-prolong-"+i).find(".minute-team2").html("<select id='select-minute"+i+"' name='scorerPlayersMinuteProlong2["+i+"]'><c:forEach begin='91' end='120' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else if(!$("#tr-player-prolong-"+i).length)
				{
					$("#playerBody").append("<tr id='tr-player-prolong-"+i+"'><td class='player-team1'></td><td class='penalty-team1'></td><td class='passer-team1'></td><td class='minute-team1'></td><td class='player-team2'><select id='select-player"+i+"' name='scorerPlayersProlong2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach><option value='' label='CSC' /></select></td><td class='penalty-team2'><input type='checkbox' name='scorerProlongPenalty2["+i+"]'/></td><td class='player-team2'><select id='select-passer"+i+"' name='passerPlayersProlong2["+i+"]'><option value='' label='Aucun' /><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team2'><select id='select-minute"+i+"' name='scorerPlayersMinuteProlong2["+i+"]'><c:forEach begin='91' end='120' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td></tr>");
				}
			}
			if($('tr[id^="tr-player-prolong-"]').length > parseInt($(this).val()))
			{
				var l = $('tr[id^="tr-player-prolong-"]').length;
				for(i=parseInt($(this).val());i<=l;i++)
				{
					if(!$("#tr-player-prolong-"+i).find(".player-team1").is(':empty'))
					{
						$("#tr-player-prolong-"+i).find(".player-team2").html("");
						$("#tr-player-prolong-"+i).find(".penalty-team2").html("");
						$("#tr-player-prolong-"+i).find(".passer-team2").html("");
						$("#tr-player-prolong-"+i).find(".minute-team2").html("");
					}
					else
					{
						$("#tr-player-"+i).remove();
					}
				}
			}
			if(!gameInPoule && $(this).val() == $("select[name='scoreProlong1']").val())
			{
				var result = "<tr id='resultPeno'><td style='text-align: center;width:100%'>";
				result += "<span class='score-team'><select name='scorePeno1'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "<span class='span-vs-edit'>VS</span>";
				result += "<span class='score-team2'><select name='scorePeno2'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></span>";
				result += "</td><td class='game-date'></td><td class='game-stade'></td></tr>";
				$("#resultBody").append(result);
				$("#peno").val(true);
			}
			else if(!gameInPoule && $(this).val() != $("select[name='scoreProlong1']").val())
			{
				$("#resultPeno").remove();
				$("#peno").val(false);
            }
		});
		
	</script>

  </body>
</html>
