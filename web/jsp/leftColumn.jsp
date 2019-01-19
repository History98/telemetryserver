<%@ page import="com.telemetryserver.model.TypeCategory" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: kemedi
  Date: 20/11/2018
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head></head>

<body>


   <li><div>
    <a class="link1" href="/nodes
    ?action=allNodes">

        <span style="margin-left: 15px;" class="label">All Nodes</span>

    </a>
   </div></li>




<li>
    <div> <span class="label" style = "margin-left : 15px;">Types</span> </div>
     <ul>
         <%
            List<TypeCategory> categoryList = (List<TypeCategory>)
             application.getAttribute("categoryList");

             Iterator<TypeCategory> it = categoryList.iterator();

             while(it.hasNext())
             {
                 TypeCategory typeCat = (TypeCategory) it.next();
                 %>




         <li>
             <a class = "label" href="/nodes
              ?action=category
              &categoryId = <%=typeCat.get_id()%>%
              &category=<%=typeCat.get_name()%>"
             >
                 <span class = "label" style = "margin-left : 30px;">
                     <%=typeCat.get_name()%>
                 </span>
             </a>
         </li>




         <% } %>

     </ul>
</li>



</body>


</html>
