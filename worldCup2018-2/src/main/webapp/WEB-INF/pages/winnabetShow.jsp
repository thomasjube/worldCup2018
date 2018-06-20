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
            	<c:set var="indexLines" value ="1"/>
				<c:set var="previousPoints" value ="0"/>
				<c:set var="previousScore" value ="0"/>
				<c:set var="previousResult" value ="0"/>
				<c:set var="classement" value ="premier"/>
                <div class="col-md-12">
                  <table class="kode-table kode-table-v2">
                    <thead>
                      <tr>
                        <th>Parieur</th>
                        <th>Nb. paris</th>
                        <th>Participations</th>
                        <th>% résultats</th>
                        <th>Bons scores</th>
                        <th>Bons résultats</th>
                        <th>Points</th>
                      </tr>
                    </thead>
                    <tbody>
                      	<c:forEach items="${lines}" var="line" varStatus="status">
                      		<c:if test="${status.index == 0 }">
                      			<c:set var="previousPoints" value ="${line.points }"/>
								<c:set var="previousScore" value ="${line.nbScore }"/>
								<c:set var="previousResult" value ="${line.nbResults }"/>
                      		</c:if>
                        	<c:choose>
                      			<c:when test="${status.index > 0 && previousPoints != line.points }">
                      				<c:if test="${classement == 'troisieme' }">
                      					<c:set var="classement" value =""/>
                      				</c:if>
                      				
                      				<c:if test="${classement == 'deuxieme'}">
                      					<c:set var="classement" value ="troisieme"/>
                      				</c:if>
                      				
                      				<c:if test="${classement == 'premier'}">
                      					<c:set var="classement" value ="deuxieme"/>
                      				</c:if>
                      			</c:when>
                      			<c:otherwise>
                      				<c:if test="${classement != ''}">
                      					<c:choose>
                      						<c:when test="${previousScore != line.nbScore}">
                      							<c:if test="${classement == 'troisieme' }">
			                      					<c:set var="classement" value =""/>
			                      				</c:if>
			                      				
			                      				<c:if test="${classement == 'deuxieme'}">
			                      					<c:set var="classement" value ="troisieme"/>
			                      				</c:if>
			                      				
			                      				<c:if test="${classement == 'premier'}">
			                      					<c:set var="classement" value ="deuxieme"/>
			                      				</c:if>
                      						</c:when>
                      						<c:otherwise>
                      							<c:if test="${previousResult != line.nbResults}">
                      								<c:if test="${classement == 'troisieme' }">
				                      					<c:set var="classement" value =""/>
				                      				</c:if>
				                      				
				                      				<c:if test="${classement == 'deuxieme'}">
				                      					<c:set var="classement" value ="troisieme"/>
				                      				</c:if>
				                      				
				                      				<c:if test="${classement == 'premier'}">
				                      					<c:set var="classement" value ="deuxieme"/>
				                      				</c:if>
                      							</c:if>
                      						</c:otherwise>
                      					</c:choose>
                      				</c:if>
                      			</c:otherwise>
                      		</c:choose>
                        	<c:set var="previousPoints" value ="${line.points}"/>
                        	<c:set var="previousScore" value ="${line.nbScore}"/>
                        	<c:set var="previousResult" value ="${line.nbResults}"/>
                        	
                        	<tr class="${classement}">
		                        <td>${line.name}</td>
		                        <td>${line.nbWinaBet}</td>
		                        <td>${line.percentParticipation} %</td>
		                        <td>${line.percentGood} %</td>
		                        <td>${line.nbScore}</td>
		                        <td>${line.nbResults}</td>
		                        <td style="font-weight:bold;font_size:3em">${line.points}</td>
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