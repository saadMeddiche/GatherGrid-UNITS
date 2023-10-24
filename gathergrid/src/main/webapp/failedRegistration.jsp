<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Document</title>
   </head>
   <body>
        Failed Page

        <% 
            List<String> errors = (List<String>) request.getAttribute("errors");

            for (String error : errors) {
                
                out.println(error);
                
            }

      
        %>

    

   </body>
</html>