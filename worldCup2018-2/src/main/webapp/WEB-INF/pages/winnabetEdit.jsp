<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
		<c:set var="indexWinabet" value="0"/>
	
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
 		
 		<section class="kode-pagesection margin-bottom-40">
	          <div class="container">
	            <div class="row">
					<div class="col-md-12">
						<div class="heading heading-12 margin-top30-bottom10">
							<h2><span class="left"></span>${game.name} - <tags:localDate date="${game.date}"/>&nbsp;${game.time}<span class="right"></span></h2>
						</div>
					</div>	
	                <div class="col-md-8">
						<form:form action="" method="post" modelAttribute="form">
	                 	 <form:hidden path="gameId"/>
							<table class="kode-table">
		                    <thead>
		                      <tr>
		                        <th>Parieur</th>
		                        <th><img src="<%=request.getContextPath()%>/resources/images/flag/${game.team1.name}.png" alt="" width="10%"> ${game.team1.name}</th>
		                        <th><img src="<%=request.getContextPath()%>/resources/images/flag/${game.team2.name}.png" alt="" width="10%"> ${game.team2.name}</th>
								<th style='text-align:right'>
									<input id="add_winabet" value="Ajout" type="button" class="table-button">
								</th>
		                      </tr>
		                    </thead>
		                    <tbody id="winabet-lines">
		                    	<c:forEach items="${winnabets}" var="result" varStatus="status">
		                    	<tr id="winabetLine[${status.index }]" class="winabet_line_${status.index }">
			                    	<td>
			                    		<select name="betNames[${status.index }]">
			                    			<c:forEach items="${betNames}" var="betName" varStatus="statusName">
			                    				<option value="${betName}" label="${betName}" ${betName == result.name ? 'selected="selected"' : '' }/>
			                    			</c:forEach>
			                    		</select>
			                    	</td>
			                    	<td>
										<select name="resultsTeam1[${status.index }]">
			                    			<c:forEach begin="0" end="15" var="score">
			                    				<option value="${score}" label="${score}" ${score == result.score1 ? 'selected="selected"' : '' }/>
			                    			</c:forEach>
			                    		</select>
			                    	</td>
			                    	<td>
			                    		<select name="resultsTeam2[${status.index }]">
			                    			<c:forEach begin="0" end="15" var="score">
			                    				<option value="${score}" label="${score}" ${score == result.score2 ? 'selected="selected"' : '' }/>
			                    			</c:forEach>
			                    		</select>
			                    	</td>
			                    	<td>
			                    		<input name="removeWinabets[${status.index }]" id="removeWinabets${status.index }" type="hidden" value="false">
			                    		<input class="remove_winabet" id="winabetRemove${status.index }" value="Remove" type="button" class="table-button">
			                    	</td>
		                    	</tr>
		                    	
		                    	<c:set var="indexWinabet" value="${indexWinabet + 1}"/>
		                    	</c:forEach>
		                    </tbody>
		                 </table>
		                 
		                 <input class="form-button" type="submit" value="Enregistrer">
	                 </form:form>
	                </div>
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
    var numberLines = 0;
    $(".game-selector").change(function(e){
    	$(location).attr('href','edit?id='+$(this).val());
    	numberLines = ${indexWinabet};
    });
    

    $("#add_winabet").click(function(e){
    	var newIndex = 0;
    	if(numberLines != 0)
    		newIndex = numberLines;
    	
    	var newLine = "<tr id='winabetLine["+ newIndex +"]' class='winabet_line_"+ newIndex +"'>";
    	newLine += "<td><select name='betNames["+ newIndex +"]'><c:forEach items='${betNames}' var='betName' varStatus='statusName'><option value='${betName}' label='${betName}'/></c:forEach></select></td>";
    	newLine += "<td><select name='resultsTeam1["+ newIndex +"]'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></td>";
    	newLine += "<td><select name='resultsTeam2["+ newIndex +"]'><c:forEach begin='0' end='15' var='score'><option value='${score}' label='${score}'/></c:forEach></select></td>";
    	newLine += "<td><input name='removeWinabets["+ newIndex +"]' id='removeWinabets"+ newIndex +"' type='hidden' value='false'>";
    	newLine += "<input class='remove_winabet' id='winabetRemove"+ newIndex +"' value='Remove' type='button' class='table-button'></td>";
    	newLine += "</tr>";
    	
    	var nbLine = numberLines - 1;
    	if(numberLines == 0){
    		$("tbody#winabet-lines").append(newLine);	
    	} else{
    		$("tr.winabet_line_"+nbLine).after(newLine);
    	}
    	
    	numberLines ++;
    });
    
    $("#lastGame").click(function(e){
    	if(gameId != 1 ){
    		var value = gameId - 1;
    		$(location).attr('href','edit?id=' + value);
    	}
    });
    
    $("#nextGame").click(function(e){
    	if(gameId != 64){
    		var value = gameId + 1;
    		$(location).attr('href','edit?id=' + value);
    	}
    });
    
    $(document).on("click",".remove_winabet",function(e){
    	var id = $(this).attr("id");
    	var index = id.split("Remove")[1];
    	$('tr.winabet_line_'+index).css('display','none');
    	$('input#removeWinabets'+index).val(true);
    	
    });
    
    $(document).ready(function(e){
    	numberLines = ${indexWinabet};
    });
    
    </script>
  </body>
</html>