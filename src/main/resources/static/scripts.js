/**
 * 
 */

       function getURLParameter(name) 
       {
	      return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
	   }
       
       function fillId_MovieData(ID)
       {
    	      var title = getURLParameter('title');
    	  	  if (title == null) { return; }
    	  	  
    	         var xmlhttp = new XMLHttpRequest();
    	             xmlhttp.onreadystatechange = function() 
    	             {
    	            	
    	                if (this.readyState == 4 && this.status == 200)
    	                   {
    	                      myObj = JSON.parse(this.responseText);
    	                      
    	                      
    	                      var txt = "<table border='10'><tr><th colspan='2'><h3>" 
    	                              + myObj.title
    	                              + "</h3></th></tr>"
    	                              + "<tr><th colspan='2'>Directed by: "
    	                              + myObj.directors
    	                              + "</th></tr>";
    	                              
    	                      var keys = [];
    	                      for (var key in myObj) 
    	                          {
    	                    	     txt += "<tr><td>" + key        + "</td>";
    	                    	     txt += "<td>"     + myObj[key] + "</td></tr>";
    	                    	  }
    	                      txt += "</table>";
    	                      
    	                      document.getElementById(ID).innerHTML = txt;
    	                   }
    	             };


    	        xmlhttp.open("GET", "http://localhost:8080/movies/title/" + title, true);
    	        xmlhttp.send();
       }