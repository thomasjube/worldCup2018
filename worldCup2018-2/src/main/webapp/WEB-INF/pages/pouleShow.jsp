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
        <section>
	        <div class="container">
	            <div class="row">
	            	<div class="kode-pagecontent col-md-8" style="text-align: center;">
	            		<a id="lastPoule" href="#"><img alt="" src="<%=request.getContextPath()%>/resources/images/gauche.png" width="30" height="30"></a>
		            	<select class="poule-selector">
		            		<c:forEach items="${poules}" var="p">
		            			<option value="${p.id}" ${p.id == poule.id ? 'selected' : '' }>Poule ${p.name}</option>
		            		</c:forEach>
		            	</select>
		            	<a id="nextPoule" href="#"><img alt="" src="<%=request.getContextPath()%>/resources/images/droite.png" width="30" height="30"></a>
	            	</div>
	            </div>
	            <br /><br /><br />
	        </div>
        </section>
        
        <section class="kode-pagesection margin-bottom-40">
          <div class="container">
            <div class="row">
            
                <div class="kode-pagecontent col-md-8">
                  
                  <div class="kode-inner-fixer margin-none padding-none">
                    <div class="kode-fixer-counter">
                      <h2 class="thbg-color">Prochain match de la poule</h2>
                      <div id="kodeCountdown"></div>
                    </div>
                    <div class="kode-team-match">
                      <ul>
                      <li><a href="#"><img src="<%=request.getContextPath()%>/resources/images/flag/${nextGame.team1.name}.png" alt="" width="120" height="100"></a></li>
                      <li class="home-kode-vs"><a href="#" class="kode-modren-btn thbg-colortwo">vs</a></li>
                      <li><a href="#"><img src="<%=request.getContextPath()%>/resources/images/flag/${nextGame.team2.name}.png" alt="" width="120" height="100"></a></li>
                    </ul>
                      <div class="clearfix"></div>
                      <span class="kode-subtitle"><tags:localDate date="${nextGame.date}"/>&nbsp;${nextGame.time} ${nextGame.stade }</span>
                    </div>
                  </div>

                </div>
                <div class="col-md-12">
                  <table class="kode-table kode-table-v2">
                    <thead>
                      <tr>
                        <th>Team</th>
                        <th>G</th>
                        <th>N</th>
                        <th>P</th>
<!--                         <th>Buts mis</th> -->
<!--                         <th>Buts pris</th> -->
                        <th>pts</th>
                        <th>Diff</th>
                      </tr>
                    </thead>
                    <tbody>
                      
                      	<c:forEach items="${orderTeams}" var="team">
                        	<tr class="team" data-teamId="${team.id}">
	                        	<td><img src="<%=request.getContextPath()%>/resources/images/flag/${team.name}.png" alt="" width="30" height="20">&nbsp;${team.name}</td>
		                        <td>${team.gameWin}</td>
		                        <td>${team.gameDraw}</td>
		                        <td>${team.gameLost}</td>
		                        <td>${team.point}</td>
		                        <td><c:if test="${team.diff > 0}">+</c:if>${team.diff}</td>
	                        </tr>
                        </c:forEach>
                      
                    </tbody>
                 </table>
                </div>
              </div>
            </div>
        </section>
        <!--// Page Content //-->

        <section class="kode-pagesection kode-pagecontent kode-result-list shape-view margin-bottom-40">
          <div class="container">
            <div class="row">
				<div class="col-md-12">
					<div class="heading heading-12 margin-top10-bottom-80">
						<h2><span class="left"></span>Résultats<span class="right"></span></h2>
						
						<br /><br /><br /><br /><br /><br />
					</div>
				</div>
				<c:forEach items="${games}" var="game">
	                 <div class="col-md-6">
	                    <article>
	                      <span class="kode-result-count thbg-colortwo">${not empty game.score1 ? game.score1 : '-'}</span>
	                      <div class="kode-result-thumb">
	                        <a href="#"><img src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="110" height="110"></a>
	                      </div>
	                      <div class="kode-result-info">
	                        <h2><a href="#">${game.team1.name}</a> <span><c:choose><c:when test="${empty game.score1 && empty game.score2 }"> </c:when><c:when test="${game.score1 > game.score2}">VIC</c:when><c:when test="${game.score1 < game.score2}">DEF</c:when><c:otherwise>NUL</c:otherwise></c:choose></span></h2>
	                        <ul>
	                          <c:forEach var="goal" items="${game.getGoalsTeam1()}">
		                          <li>${goal.player.name}<span>(${goal.minute}')</span></li>
	                          </c:forEach>
                        	</ul>
	                      </div>
	                    </article>
	                </div>
	                <div class="col-md-6">
	                  <article class="kode-even">
	                    <span class="kode-result-count thbg-colortwo">${not empty game.score2 ? game.score2 : '-'}</span>
	                    <div class="kode-result-thumb-2">
	                      <a href="#"><img src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="110" height="110"></a>
	                    </div>
	                    <div class="kode-result-info">
	                      <h2><a href="#">${game.team2.name}</a> <span><c:choose><c:when test="${empty game.score1 && empty game.score2 }"></c:when> <c:when test="${game.score1 < game.score2}">VIC</c:when><c:when test="${game.score1 > game.score2}">DEF</c:when><c:otherwise>NUL</c:otherwise></c:choose></span></h2>
	                      <ul>
	                          <c:forEach var="goal" items="${game.getGoalsTeam2()}">
		                          <li>${goal.player.name}<span>(${goal.minute}')</span></li>
	                          </c:forEach>
                       	</ul>
	                    </div>
	                  </article>
	                </div>
              </c:forEach>
              </div>
            </div>
        </section>

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
    var poule = "${poule.name}";
    $(document).ready(function(){
    	
    });
    
    $(".poule-selector").change(function(e){
    	console.log($(this).val());
    	$(location).attr('href','showPoule?id='+$(this).val());
    });
    
    $("#lastPoule").click(function(e){
    	if(poule != 'A')
    		$(location).attr('href','lastPoule?id=${poule.id}');
    });
    
    $("#nextPoule").click(function(e){
    	if(poule != 'H')
    		$(location).attr('href','nextPoule?id=${poule.id}');
    });
    
    $(".team").click(function(e){
    	$(location).attr('href','../team/showTeam?id='+$(this).data("teamid"));
    });
    
    </script>

  </body>
</html>