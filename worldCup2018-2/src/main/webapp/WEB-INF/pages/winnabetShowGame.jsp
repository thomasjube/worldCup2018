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
						<li><a href="../winabet/edit?id=${game.id}">Saisie pari</a></li>
						<li><a href="../winabet/game">Résultat par match</a></li>
						<li><a href="../winabet">Classement général</a></li>
					</ul>
				</div>
				<!--NAVIGATION END-->
			</div>
		</div>
      </header>
	
		<c:set var="totalGoals" value="0"/>
		<c:set var="totalPasses" value="0"/>
		<c:set var="totalYellowCards" value="0"/>
		<c:set var="totalRedCards" value="0"/>
	
		<br /><br /><br /><br />
      <!--// Main Content //-->
      <div class="kode-content">
		<br /><br /><br /><br />
        <!--// Page Content //-->
                <section>
	        <div class="container">
	            <div class="row">
	            	<div class="kode-pagecontent col-md-8" style="text-align: center;">
	            		<a id="lastGame" href="#"><img alt="" src="<%=request.getContextPath()%>/resources/images/gauche.png" width="30" height="30"></a>
		            	<select class="game-selector">
		            		<c:forEach items="${games}" var="value">
		            			<option value="${value.id}" ${value.id == game.id ? 'selected' : '' }>Match :  ${value.team1.name} vs ${value.team2.name }</option>
		            		</c:forEach>
		            	</select>
		            	<a id="nextGame" href="#"><img alt="" src="<%=request.getContextPath()%>/resources/images/droite.png" width="30" height="30"></a>
	            	</div>
	            </div>
	            <br/>
	        </div>
        </section>
        
        <section class="kode-pagesection kode-pagecontent kode-result-list shape-view margin-bottom-40">
          <div class="container">
            <div class="row">
				<div class="col-md-12">
					<div class="heading heading-12 margin-top10-bottom-80">
						<h2><span class="left"></span>Résultats<span class="right"></span></h2>
						
						<br /><br /><br /><br /><br /><br />
					</div>
				</div>
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
		                           <li>${not empty goal.player.name ? goal.player.name : 'CSC'}<c:if test="${goal.penalty }"> (sp)</c:if><span>(${goal.minute}')</span></li>
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
		                           <li>${not empty goal.player.name ? goal.player.name : 'CSC'}<c:if test="${goal.penalty }"> (sp)</c:if><span>(${goal.minute}')</span></li>
	                          </c:forEach>
                       	</ul>
	                    </div>
	                  </article>
	                </div>
              </div>
            </div>
        </section>
        
        <section class="kode-pagesection margin-bottom-40">
          <div class="container">
            <div class="row">
                <div class="col-md-12">
                  <table class="kode-table kode-table-v2">
                    <thead>
                      <tr>
                        <th>Parieur</th>
                        <th>Score1</th>
                        <th>Score2</th>
                        <th>Points</th>
                      </tr>
                    </thead>
                    <tbody>
                      	<c:forEach items="${winnabets}" var="winnabet" varStatus="status">
                        	<tr>
		                        <td>${winnabet.name}</td>
		                        <td>${winnabet.score1}</td>
		                        <td>${winnabet.score2}</td>
		                        <td>
		                        <c:choose>
		                        	<c:when test="${empty game.score1 }">-</c:when>
		                        	<c:otherwise>${winnabet.goodScore ? 5 : (winnabet.goodResult ? 3 : 0) }</c:otherwise>
		                        </c:choose>
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
    var gameId = ${game.id};
    $(document).ready(function(){
    });
    
    $(".game-selector").change(function(e){
    	$(location).attr('href','game?id='+$(this).val());
    });
    
    $("#lastGame").click(function(e){
    	if(gameId != 1 ){
    		var value = gameId - 1;
    		$(location).attr('href','game?id=' + value);
    	}
    });
    
    $("#nextGame").click(function(e){
    	if(gameId != 64){
    		var value = gameId + 1;
    		$(location).attr('href','game?id=' + value);
    	}
    });
    
    
    </script>

  </body>
</html>