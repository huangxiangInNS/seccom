<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
<script src="./jquery-1.8.3.js" type="text/javascript"></script> 

<script type="text/javascript">  
function sendJson() {  
    var requestStr = {  
            reqStr:  'test parameter'  
    };  
    var request = {
    	    "operId": {
    	        "requestId": ""
    	    },
    	    "requestData": {
    	      "adminUsername":"admin","adminPassword":"123456"    
    	}
    	}
;  
    //调用了jquery.json 库  
  //  var encoded = $.toJSON( request );  
  //  var jsonStr = encoded;  
 //   var actionStr = $("#actionPath").val();  
 
 var strUser = JSON.stringify(request);
    $.ajax({  
        url : "http://localhost:8080/uc/v1/adminLogin",  
        type : 'POST',  
        data : strUser,  
        dataType : 'json',  
     //   contentType : 'text/xml',  
        success : function(data, status, xhr) {  
//         Do Anything After get Return data  
//          $.each(data.payload, function(index){  
//              $("#result").append("</br>" + data.payload[index].beanStr);  
//          });


		alert(status);
		alert(data.result.code);
		alert(data.result.message);
	//	json = eval(data);  
	//	alert(json);
        },  
        Error : function(xhr, error, exception) {  
            // handle the error.    
            alert(exception.toString());  
        }  
    });  
}  
</script>  
</head>  
<body>  
    <!-- Send JSON -->  
    <B>Action Path:   </B>
    
    <input id="actionPath"  type="text"  style="width: 300px;"/>  
    <button class="button" id="buttonUpload" style="margin-left:10px;"  
                onclick="return sendJson();">Send</button>  
                
                
    <div id="result" >result: </div>  
</body>  
</html>