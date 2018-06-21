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
        <c:set var="previous" value=""/>
	        <section class="kode-pagesection margin-bottom-40">
	          <div class="container">
	            <div class="row">
					<div class="col-md-12">
						<div class="heading heading-12 margin-top30-bottom10">
							<h2><span class="left"></span>Composition ${game.name}<span class="right"></span></h2>
						</div>
					</div>	
	                <div class="col-md-8">
	                  <form:form action="" method="post" modelAttribute="editCompoForm">
	                 	 <form:hidden path="gameId"/>
	                 	 <form:hidden path="teamId1"/>
	                 	 <form:hidden path="teamId2"/>
							<table class="kode-table">
			                    <thead>
			                    	<tr>
			                        	<th colspan="5">
			                        		<img class="flag" style="position:relative;float:left" src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="5%">&nbsp;
		              						${game.team1.name}
		              					</th>
			                    	</tr>
			                    </thead>
			                    <c:set var="indexTeam1" value="0"/>
			                    <c:set var="titularsTeam1" value="${titulars[game.team1.id] }"/>
			                    
			                    <tbody>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Gardiens</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${goals1}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team1-titular" type="checkbox" name="titular1[${indexTeam1}]" ${ titularsTeam1.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId1[${indexTeam1}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId1[${indexTeam1}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team1.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute1[${indexTeam1}]">
													<c:forEach begin="1" end="90" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team1-captain" type="checkbox" name="captain1[${indexTeam1}]" ${ not empty game.captainTeam1 and game.captainTeam1.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam1" value="${indexTeam1 + 1}"/>
			                    	</c:forEach>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Défenseurs</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${defensers1}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team1-titular" type="checkbox" name="titular1[${indexTeam1}]" ${ titularsTeam1.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId1[${indexTeam1}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId1[${indexTeam1}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team1.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute1[${indexTeam1}]">
													<c:forEach begin="1" end="93" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team1-captain" type="checkbox" name="captain1[${indexTeam1}]" ${ not empty game.captainTeam1 and game.captainTeam1.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam1" value="${indexTeam1 + 1}"/>
			                    	</c:forEach>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Milieux</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${middles1}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team1-titular" type="checkbox" name="titular1[${indexTeam1}]" ${ titularsTeam1.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId1[${indexTeam1}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId1[${indexTeam1}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team1.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute1[${indexTeam1}]">
													<c:forEach begin="1" end="90" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team1-captain" type="checkbox" name="captain1[${indexTeam1}]" ${ not empty game.captainTeam1 and game.captainTeam1.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam1" value="${indexTeam1 + 1}"/>
			                    	</c:forEach>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="5" style="width:100%;color:white"><h4 style="color:white">Attaquants</h4></td>
			                    	</tr>
			                    	<c:forEach items="${strikers1}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team1-titular" type="checkbox" name="titular1[${indexTeam1}]" ${ titularsTeam1.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId1[${indexTeam1}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId1[${indexTeam1}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team1.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute1[${indexTeam1}]">
													<c:forEach begin="1" end="90" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team1-captain" type="checkbox" name="captain1[${indexTeam1}]" ${ not empty game.captainTeam1 and game.captainTeam1.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam1" value="${indexTeam1 + 1}"/>
			                    	</c:forEach>
			                    </tbody>
		                 	</table>
		                 	<br/><br/>
		                 	<table class="kode-table">
			                    <thead>
			                    	<tr>
			                        	<th colspan="5">
			                        		<img class="flag" style="position:relative;float:left" src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="5%">&nbsp;
		              						${game.team2.name}
		              					</th>
			                    	</tr>
			                    </thead>
			                    <c:set var="indexTeam2" value="0"/>
			                    <c:set var="titularsTeam2" value="${titulars[game.team2.id] }"/>
			                    <tbody>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Gardiens</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${goals2}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team2-titular" type="checkbox" name="titular2[${indexTeam2}]" ${ titularsTeam2.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId2[${indexTeam2}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
		                    				<td>
			                    				<select name="substituteId2[${indexTeam2}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team2.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
												<select name="minute2[${indexTeam2}]">
													<c:forEach begin="1" end="90" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team2-captain" type="checkbox" name="captain2[${indexTeam2}]" ${ not empty game.captainTeam2 and game.captainTeam2.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam2" value="${indexTeam2 + 1}"/>
			                    	</c:forEach>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Défenseurs</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${defensers2}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team2-titular" type="checkbox" name="titular2[${indexTeam2}]" ${ titularsTeam2.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId2[${indexTeam2}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId2[${indexTeam2}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team2.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute2[${indexTeam2}]">
													<c:forEach begin="1" end="93" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team2-captain" type="checkbox" name="captain2[${indexTeam2}]" ${ not empty game.captainTeam2 and game.captainTeam2.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam2" value="${indexTeam2 + 1}"/>
			                    	</c:forEach>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Milieux</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${middles2}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team2-titular" type="checkbox" name="titular2[${indexTeam2}]" ${ titularsTeam2.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId2[${indexTeam2}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId2[${indexTeam2}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team2.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute2[${indexTeam2}]">
													<c:forEach begin="1" end="90" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team2-captain" type="checkbox" name="captain2[${indexTeam2}]" ${ not empty game.captainTeam2 and game.captainTeam2.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam2" value="${indexTeam2 + 1}"/>
			                    	</c:forEach>
			                    	<tr class="table-head goal-color">
			                    		<td colspan="4" style="width:100%;color:white"><h4 style="color:white">Attaquants</h4></td>
			                    		<td style="width:100%;color:white"><h4 style="color:white">Capitaine</h4></td>
			                    	</tr>
			                    	<c:forEach items="${strikers2}" var="player">
			                    		<c:set var="substitutePlayer" value="${substitutes[player.id]}"/>
			                    		<tr>
			                    			<td style='width:7em;'><input class="team2-titular" type="checkbox" name="titular2[${indexTeam2}]" ${ titularsTeam2.contains(player.id) ? 'checked="checked"' : ''}/></td>
			                    			<td><input type="hidden" value="${player.id }" name="titularId2[${indexTeam2}]"/> ${player.number} - <c:if test="${not empty player.firstName}">${player.firstName.charAt(0)}.&nbsp;</c:if>${player.name}</td>
			                    			<td>
			                    				<select name="substituteId2[${indexTeam2}]">
													<option value="" label="Non remplacer"/>
													<c:forEach items="${game.team2.players}" var="playerTeam">
														<option value="${playerTeam.id}" label="${playerTeam.number} - ${playerTeam.firstName} ${playerTeam.name}" ${not empty substitutePlayer and substitutePlayer.player.id == playerTeam.id ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td>
				                    			<select name="minute2[${indexTeam2}]">
													<c:forEach begin="1" end="93" var="minute">
														<option value="${minute}" label="${minute}" ${not empty substitutePlayer and substitutePlayer.minute == minute ? 'selected="selected"' : ''}/>
													</c:forEach>
												</select>
			                    			</td>
			                    			<td style='width:7em;'><input class="team2-captain" type="checkbox" name="captain2[${indexTeam2}]" ${ not empty game.captainTeam2 and game.captainTeam2.id == player.id ? 'checked="checked"' : ''}/></td>
			                    		<tr>
			                    		<c:set var="indexTeam2" value="${indexTeam2 + 1}"/>
			                    	</c:forEach>
			                    </tbody>
		                 	</table>
		                 	
		                 <input class="form-button" type="submit" value="Enregistrer">
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
	
	$(document).ready(function(){
		var value = $( "input.team1-titular:checked" ).length;
		if(value == 11){
			$( "input.team1-titular:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		}
		
		var value = $( "input.team2-titular:checked" ).length;
		if(value == 11){
			$( "input.team2-titular:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		}
		
		var value = $( "input.team1-captain:checked" ).length;
		if(value == 1){
			$( "input.team1-captain:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		}
		
		var value = $( "input.team2-captain:checked" ).length;
		if(value == 1){
			$( "input.team2-captain:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
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
	
	$("input.team1-titular").click(function(e){
		var value = $( "input.team1-titular:checked" ).length;
		if(value == 11){
			$( "input.team1-titular:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		} else {
			$( "input.team1-titular:not(:checked)" ).each(function( index ) {
				$(this).removeAttr('disabled');
			});
		}
	});
	
	$("input.team2-titular").click(function(e){
		var value = $( "input.team2-titular:checked" ).length;
		if(value == 11){
			$( "input.team2-titular:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		} else {
			$( "input.team2-titular:not(:checked)" ).each(function( index ) {
				$(this).removeAttr('disabled');
			});
		}
	});
	
	$("input.team1-captain").click(function(e){
		var value = $( "input.team1-captain:checked" ).length;
		if(value == 1){
			$( "input.team1-captain:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		} else {
			$( "input.team1-captain:not(:checked)" ).each(function( index ) {
				$(this).removeAttr('disabled');
			});
		}
	});
	
	$("input.team2-captain").click(function(e){
		var value = $( "input.team2-captain:checked" ).length;
		if(value == 1){
			$( "input.team2-captain:not(:checked)" ).each(function( index ) {
				$(this).attr('disabled', 'disabled');
			});
		} else {
			$( "input.team2-captain:not(:checked)" ).each(function( index ) {
				$(this).removeAttr('disabled');
			});
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
	
	$("#add-carton").click(function(e){

		var indexMax = $(".carton-team").length;
		var index = indexMax-1;
		
		var tr = "<tr id='tr-carton-player-"+indexMax+"'>";
		tr += "<td class='teamCarton'><select id='cartonTeam"+indexMax+"' name='cartonTeam["+indexMax+"]' class='carton-team' ><option data-index='1' value='${game.team1.id}'>${game.team1.name}</option><option data-index='2' value='${game.team2.id}'>${game.team2.name}</option></select></td>";
		tr += "<td class='playerCarton'><select id='select-carton-player"+indexMax+"' name='cartonPlayers["+indexMax+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td>";
		tr += "<td class='typeCarton'><select name='cartonType["+indexMax+"]'><option value='YELLOW_CARD' selected='selected'>Carton jaune</option><option value='RED_CARD'>Carton rouge</option></select></td>";
		tr += "<td style='width:7em;' class='minuteCarton'><select name='cartonMinute["+indexMax+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td>";
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
					$("#tr-player-"+i).find(".player-team1").html("<select  id='select-player"+i+"' name='scorerPlayers1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".passer-team1").html("<select  id='select-passer"+i+"' name='passerPlayers1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".minute-team1").html("<select id='select-minute"+i+"' name='scorerPlayersMinute1["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else if(!$("#tr-player-"+i).length)
				{
					$("#playerBody").append("<tr id='tr-player-"+i+"'><td class='player-team1'><select id='select-player"+i+"' name='scorerPlayers1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='player-team1'><select id='select-passer"+i+"' name='passerPlayers1["+i+"]'><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team1'><select id='select-minute"+i+"' name='scorerPlayersMinute1["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td><td class='player-team2'></td><td class='passer-team2'></td><td class='minute-team2'></td></tr>");
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
						$("#tr-player-"+i).find(".passer-team1").html("");
						$("#tr-player-"+i).find(".minute-team1").html("");
					}
					else
					{
						$("#tr-player-"+i).remove();
					}
				}
			}
		});
		
		$("#score2").change(function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-"+i).length && $("select[name='scorerPlayers2["+i+"]']").length == 0)
				{
					$("#tr-player-"+i).find(".player-team2").html("<select id='select-player"+i+"' name='scorerPlayers2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".passer-team2").html("<select  id='select-passer"+i+"' name='passerPlayers2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".minute-team2").html("<select id='select-minute"+i+"' name='scorerPlayersMinute2["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else if(!$("#tr-player-"+i).length)
				{
					$("#playerBody").append("<tr id='tr-player-"+i+"'><td class='player-team1'></td><td class='passer-team1'></td><td class='minute-team1'></td><td class='player-team2'><select id='select-player"+i+"' name='scorerPlayers2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='player-team2'><select id='select-passer"+i+"' name='passerPlayers2["+i+"]'><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.number} - ${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team2'><select id='select-minute"+i+"' name='scorerPlayersMinute2["+i+"]'><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td></tr>");
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
						$("#tr-player-"+i).find(".passer-team2").html("");
						$("#tr-player-"+i).find(".minute-team2").html("");
					}
					else
					{
						$("#tr-player-"+i).remove();
					}
				}
			}
		});
	</script>

  </body>
</html>
