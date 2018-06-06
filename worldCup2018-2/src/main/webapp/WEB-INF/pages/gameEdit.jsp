<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
						</div>
					</div>	
	                <div class="col-md-8">
	                  <form:form action="" method="post" modelAttribute="editForm">
	                 	 <form:hidden path="id"/>
		                  <table class="kode-table">
		                    <thead>
		                      <tr>
		                        <th>Match</th>
		                        <th class="game-date">Heure</th>
		                        <th class="game-stade">Stade</th>
		                      </tr>
		                    </thead>
		                    <tbody>
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
		                    </tbody>
		                 </table>
		                 <table class="kode-table">
		                 	<thead>
		                 	<th>But</th>
		                 	<th>Minute</th>
		                 	<th>But</th>
		                 	<th style='width:4em;'>Minute</th>
		                 	</thead>
			                 <tbody id="playerBody">
			                 </tbody>
		                 </table>
		                 <input type="submit" value="Save">
		                 <input type="button" value="Reset" onclick="window.location='reset?id=${game.id}'">
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
		
	});
	
		$("#score1").change(function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-"+i).length)
				{
					$("#tr-player-"+i).find(".player-team1").html("<select ><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".minute-team1").html("<select ><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else
				{
					$("#playerBody").append("<tr id='tr-player-"+i+"'><td class='player-team1'><select><c:forEach var='player' items='${game.team1.players}'><option value='${player.id}' label='${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team1'><select ><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td><td class='player-team2'></td><td class='minute-team2'></td></tr>");
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
						$("#tr-player-"+i).find(".minute-team1").html("");
					}
					else
					{
						console.log("test3 : "+$('tr[id^="tr-player-"]').length);
						$("#tr-player-"+i).remove();
					}
				}
			}
			//enlever les td en trop // et/ou les lignes si plus rien sur la ligne
		});
		
		$("#score2").change(function(e){
			for(i=0;i < parseInt($(this).val());i++)
			{
				if($("#tr-player-"+i).length)
				{
					$("#tr-player-"+i).find(".player-team2").html("<select><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.name}' /></c:forEach></select>");
					$("#tr-player-"+i).find(".minute-team2").html("<select><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select>");
				}
				else
				{
					$("#playerBody").append("<tr id='tr-player-"+i+"'><td class='player-team1'></td><td class='minute-team1'></td><td class='player-team2'><select ><c:forEach var='player' items='${game.team2.players}'><option value='${player.id}' label='${player.firstName} ${player.name}' /></c:forEach></select></td><td class='minute-team2'><select ><c:forEach begin='1' end='90' var='minute'><option value='${minute}' label='${minute}'/></c:forEach></select></td></tr>");
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
