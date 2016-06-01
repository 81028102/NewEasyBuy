<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <script type="text/javascript" src="<%=request.getContentType() %>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript">
nows = new Date(),hour = nows.getHours();
if(hour < 6){document.write("${Goodmorning}");}
else if (hour < 9){document.write("${Goodmorning1}");}
else if (hour < 12){document.write("${Goodmorning2}");}
else if (hour < 14){document.write("${Goodafternoon}");}
else if (hour < 17){document.write("${Goodafternoon1}");}
else if (hour < 19){document.write("${Goodevening}");}
else if (hour < 22){document.write("${Goodevening1}");}
else {document.write("${Goodnight}");}
</script>
