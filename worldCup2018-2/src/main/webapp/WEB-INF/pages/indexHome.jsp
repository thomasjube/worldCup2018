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
    <title>Coupe du monde 2018</title>

    <!-- Css Files -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/themetypo.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/widget.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/color.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/flexslider.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/owl.carousel.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/jquery.bxslider.css" rel="stylesheet">    
	<link href="<%=request.getContextPath()%>/resources/css/prettyphoto.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/responsive.css" rel="stylesheet">
    
        <!-- jQuery (necessary for JavaScript plugins) -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.flexslider.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.countdown.js"></script>  
    <script src="<%=request.getContextPath()%>/resources/js/waypoints-min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.bxslider.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap-progressbar.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.accordion.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.circlechart.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.prettyphoto.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/kode_pp.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/functions.js"></script>

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
					<a href="" class="logo"><img src="<%=request.getContextPath()%>/resources/images/logo.png" alt=""></a>
				</div>
				<div class="kode-navigation pull-left">
					<ul>
						<li><a href="<%=request.getContextPath()%>/team">Equipes</a></li>
						<li><a href="<%=request.getContextPath()%>/poule">Poules</a></li>
						<li><a href="<%=request.getContextPath()%>/game">Matchs</a></li>
						<li><a href="<%=request.getContextPath()%>/stats">Statistiques</a></li>
					</ul>
				</div>
				<!--NAVIGATION END-->
			</div>
		</div>
      </header>

      <!--// Main Banner //-->
      <div id="mainbanner">
        <div class="flexslider">
          <ul class="slides">
            <li>
              <img src="<%=request.getContextPath()%>/resources/images/les_bleus.jpg" alt="" />
              <div class="container">
                <div class="kode-caption">       
                	<h2>Tous derrière les bleus</h2>
                    <div class="clearfix"></div>        
                  <div class="clearfix"></div>
                  <a class="kode-modren-btn thbg-colortwo" href="team/showTeam?id=132">Voir l'équipe</a>
                </div>
              </div>
            </li>
            <li>
              <img src="<%=request.getContextPath()%>/resources/images/coupe.jpg" alt="" />
              <div class="container">
                <div class="kode-caption">       
                	<h2>Le dernier vainqueur</h2> 
                    <div class="clearfix"></div>          
                  <div class="clearfix"></div>
                  <a class="kode-modren-btn thbg-colortwo" href="team/showTeam?id=165">Voir l'équipe</a>
                </div>
              </div>
            </li>
            <li>
              <img src="<%=request.getContextPath()%>/resources/images/organisateur.jpg" alt="" />
              <div class="container">
                <div class="kode-caption">       
                	<h2>Pays organisateur</h2>
                    <div class="clearfix"></div>        
                  <div class="clearfix"></div>
                  <a class="kode-modren-btn thbg-colortwo" href="team/showTeam?id=2">Voir l'équipe</a>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <!--// Main Banner //-->

      <!--// Main Content //-->
      <div class="kode-content">
        <br/>
        <!--// Page Content //-->
        <c:if test="${not empty lastGame}">
        <section class="kode-pagesection padding-30-topbottom bg-white">
          <div class="container">
            <div class="row">
              <div class="kode-result-list shape-view col-md-12">
				<div class="heading heading-12 margin-top30-bottom80">
					<p>Dernier match</p>
					<h2><span class="left"></span><c:out value="Résultat du dernier match"/><span class="right"></span></h2>
				</div>
				<div class="clear clearfix"></div>
                <div class="row">
                  <div class="col-md-6">
                    <article>
                      <span class="kode-result-count thbg-colortwo"><c:out value="${lastGame.score1}"/></span>
                      <div class="kode-result-thumb">
                        <a href="#"><img src="<%=request.getContextPath()%>/resources/images/flag/${lastGame.team1}.png" alt=""></a>
                      </div>
                      <div class="kode-result-info">
                        <h2><a href="#"><c:out value="${lastGame.team1.name}"/></a> <span>Win</span></h2>
                        <ul>
                          <c:forEach var="goal" items="${lastGame.getGoalsTeam1()}">
	                          <li>${goal.scorer.name}<span>(${goal.minute}')</span></li>
                          </c:forEach>
                        </ul>
                      </div>
                    </article>
                  </div>
                  <div class="col-md-6">
                    <article class="kode-even">
                      <span class="kode-result-count thbg-colortwo"><c:out value="${lastGame.score2}"/></span>
                      <div class="kode-result-thumb">
                        <a href="#"><img src="<%=request.getContextPath()%>/resources/images/flag/${lastGame.team2.name}.png" alt=""></a>
                      </div>
                      <div class="kode-result-info">
                        <h2><a href="#"><c:out value="${lastGame.team2.name}"/></a> <span>Los</span></h2>
                        <ul>
                          <c:forEach var="goal" items="${lastGame.getGoalsTeam2()}">
	                          <li>${goal.scorer.name}<span>(${goal.minute}')</span></li>
                          </c:forEach>
                        </ul>
                      </div>
                    </article>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
        </section>
        </c:if>
        <!--// Page Content //-->
		
		 <!--// Page Content //-->
        <section class="kode-pagesection kode-sport-section kode-parallax">
          <div class="container">
            <div class="row">
                <div class="col-md-12">
					<div class="heading heading-12 margin-top5-bottom10-flat">
						<p>Is Your Team Ready For Next Match!</p>
						<h2><span class="left"></span>Next Match Started In<span class="right"></span></h2>
					</div>
                  <div class="kode-fixer-counter">
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
            </div>
        </section>
        <!--// Page Content //-->
		
        <!--// Page Content //-->
<!--         <section class="kode-pagesection top_player_section" > -->
<!--           <div class="container"> -->
<!--             <div class="row"> -->

<!--               <div class="col-md-12"> -->
<!--                 <div class="kode-section-title"> <h2>Top Player</h2> </div> -->

<!--                   <div class="owl-carousel-team owl-theme kode-team-list next-prev-style"> -->
<!--                     <div class="item"> -->
<%--                       <figure><a href="#" class="kode-team-thumb"><img src="<%=request.getContextPath()%>/resources/extra-images/player-1.jpg" alt=""></a> --%>
<!--                         <figcaption> -->
<!--                           <ul class="kode-team-network"> -->
<!--                             <li><a href="#" class="fa fa-facebook"></a></li> -->
<!--                             <li><a href="#" class="fa fa-twitter"></a></li> -->
<!--                             <li><a href="#" class="fa fa-linkedin"></a></li> -->
<!--                           </ul> -->
<!--                           <div class="clearfix"></div> -->
<!--                           <h2><a href="#">Sergio Ramos</a></h2> -->
<!--                           <a href="#" class="kode-modren-btn thbg-colortwo">View Detail</a> -->
<!--                         </figcaption> -->
<!--                       </figure> -->
<!--                     </div> -->
<!--                     <div class="item"> -->
<%--                       <figure><a href="#" class="kode-team-thumb"><img src="<%=request.getContextPath()%>/resources/extra-images/player-2.jpg" alt=""></a> --%>
<!--                         <figcaption> -->
<!--                           <ul class="kode-team-network"> -->
<!--                             <li><a href="#" class="fa fa-facebook"></a></li> -->
<!--                             <li><a href="#" class="fa fa-twitter"></a></li> -->
<!--                             <li><a href="#" class="fa fa-linkedin"></a></li> -->
<!--                           </ul> -->
<!--                           <div class="clearfix"></div> -->
<!--                           <h2><a href="#">Wayne Rooney</a></h2> -->
<!--                           <a href="#" class="kode-modren-btn thbg-colortwo">Vew Detail</a> -->
<!--                         </figcaption> -->
<!--                       </figure> -->
<!--                     </div> -->
<!--                     <div class="item"> -->
<%--                       <figure><a href="#" class="kode-team-thumb"><img src="<%=request.getContextPath()%>/resources/extra-images/player-3.jpg" alt=""></a> --%>
<!--                         <figcaption> -->
<!--                           <ul class="kode-team-network"> -->
<!--                             <li><a href="#" class="fa fa-facebook"></a></li> -->
<!--                             <li><a href="#" class="fa fa-twitter"></a></li> -->
<!--                             <li><a href="#" class="fa fa-linkedin"></a></li> -->
<!--                           </ul> -->
<!--                           <div class="clearfix"></div> -->
<!--                           <h2><a href="#">Iker Casillas</a></h2> -->
<!--                           <a href="#" class="kode-modren-btn thbg-colortwo">Vew Detail</a> -->
<!--                         </figcaption> -->
<!--                       </figure> -->
<!--                     </div> -->
<!--                     <div class="item"> -->
<%--                       <figure><a href="#" class="kode-team-thumb"><img src="<%=request.getContextPath()%>/resources/extra-images/player-4.jpg" alt=""></a> --%>
<!--                         <figcaption> -->
<!--                           <ul class="kode-team-network"> -->
<!--                             <li><a href="#" class="fa fa-facebook"></a></li> -->
<!--                             <li><a href="#" class="fa fa-twitter"></a></li> -->
<!--                             <li><a href="#" class="fa fa-linkedin"></a></li> -->
<!--                           </ul> -->
<!--                           <div class="clearfix"></div> -->
<!--                           <h2><a href="#">Sergio Ramos</a></h2> -->
<!--                           <a href="#" class="kode-modren-btn thbg-colortwo">Vew Detail</a> -->
<!--                         </figcaption> -->
<!--                       </figure> -->
<!--                     </div> -->
<!--                   </div> -->

<!--               </div> -->
              
<!--             </div> -->
<!--           </div> -->
<!--         </section> -->
        <!--// Page Content //-->
		
		<div class="kd-pagesection project_fact_inline">
        <div class="container">
          <div class="row">

            <div class="col-md-12">

              <!--// Counter Section //-->
              <div class="kd-counter">
                <ul class="row">
                  <li class="col-md-3">
                    <i class="fa fa-soccer-ball-o fa-3x"></i>
                    <span class="word-count">${totalGoals}</span>
                    <small>Buts inscrits</small>
                  </li>
                  <li class="col-md-3">
                    <i class="fa fa-users fa-3x"></i>
                    <span class="word-count">${totalPlayers }</span>
                    <small>Participants</small>
                  </li>
                  <li class="col-md-3">
                    <i class="fa fa-flag fa-3x"></i>
                    <span class="word-count">${totalTeams}</span>
                    <small>Equipes</small>
                  </li>
                  <li class="col-md-3">
                    <i class="fa fa-trophy fa-3x"></i>
                    <span class="word-count">${totalGames}</span>
                    <small>Matchs</small>
                  </li>
                </ul>
              </div>
              <!--// Counter Section //-->

            </div>

          </div>
        </div>
      </div>
		
		   <!--// Page Content //-->

		 <!--// Page Content //-->
        <section class="kode-pagesection margin-bottom-40">
          <div class="container">
            <div class="row">
				<div class="col-md-12">
					<div class="heading heading-12 margin-top30-bottom10">
						<p>Is Your Team Ready For Next Match!</p>
						<h2><span class="left"></span>Matchs de la France<span class="right"></span></h2>
					</div>
				</div>	
                <div class="col-md-8">
                  
                  <table class="kode-table">
                    <thead>
                      <tr>
                        <th>Match</th>
                        <th>Date</th>
                        <th>Stade</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${franceGames}" var="game">
                      <tr id="${game.id }">
                        <td style="text-align: center;width:100%">
	                        <img style="position:relative;float:left;top:0.6em;" src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="30" height="20">&nbsp;
	                        <span class="name-team">${game.team1.name}</span> 
	                        <span class="score-team">${game.score1}</span>
	                        <img style="position:relative;float:right;margin-left:0.8em;top:0.6em;" src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="30" height="20">
	                        <span class="name-team2">${game.team2.name}</span>
	                        <span class="span-vs">VS</span>
	                        <span class="score-team2">${game.score2}</span>
	                        </td>
	                        <td><tags:localDate date="${game.date}"/><br/>${game.time}</td>
	                        <td>${game.stade}</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                 </table>
                </div>
              </div>
            </div>
        </section>
      </div>
      <div class="kode-bottom-footer">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <p>©2018  Project by Thomas</p>
            </div>
            <div class="col-md-6">
              <a href="#" id="kode-topbtn" class="thbg-colortwo"><i class="fa fa-angle-up"></i></a>
            </div>
          </div>
        </div>      </div>
<div class="clearfix clear"></div>
    </div>
    <!--// Wrapper //-->
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header thbg-color">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Login To Your Account</h4>
          </div>
          <div class="modal-body">
            <form class="kode-loginform">
              <p><span>User Name</span> <input type="text" placeholder="User Name"></p>
              <p><span>Password</span> <input type="password" placeholder="Password"></p>
              <p><label><input type="checkbox"><span>Remember Me</span></label></p>
              <p class="kode-submit"><a href="#">Lost Your Password</a> <input class="thbg-colortwo" type="submit" value="Sign in"></p>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModalTwo" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header thbg-color">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Register</h4>
          </div>
          <div class="modal-body">
            <form class="kode-loginform">
              <p><span>Email</span> <input type="text" placeholder="Email"></p>
              <p><span>Password</span> <input type="password" placeholder="Password"></p>
              <p><span>Retype Password</span> <input type="password" placeholder="Retype Password"></p>
              <p><label><input type="checkbox"><span>Remember Me</span></label></p>
              <p class="kode-submit"><a href="#">Lost Your Password</a> <input class="thbg-colortwo" type="submit" value="Sign Up"></p>
            </form>
          </div>
        </div>
      </div>
    </div>

  </body>
  
<script>
jQuery(document).ready(function(){
	if($('#kodeCountdown').length){
		var nextGame = new Date("${nextGame.dateTime}");
		jQuery('#kodeCountdown').countdown({until: nextGame});
		jQuery('#year').text(nextGame.getFullYear());
	}
});

$("tbody tr").click(function(e){
	$(window.location).attr('href',"/worldCup2018-2/game/editGame?id="+$(this).attr('id'));
});
</script>
</html>