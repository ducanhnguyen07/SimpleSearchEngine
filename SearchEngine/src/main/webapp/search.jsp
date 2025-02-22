<%@page import = "java.util.ArrayList"%>
<%@page import = "com.engine.SearchResult"%>
<html>
<head>
    <title>Search</title>
    <link rel = "stylesheet" type = "text/css" href = "style.css">
</head>
<body>
<form action = "search">
    <input type = "text" name = "keyword">
    <button type = "submit">Search</button>
</form>
<div class = "resultTable">
    <table border = 2>
        <tr>
            <td>Title</td>
            <td>Link</td>
        </tr>
        <%
            //Get results from search servlet
            ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("listWebPage");
            //Iterate for every data present in results array
            for(SearchResult result:results){
        %>
        <tr>
            <td><%= result.getTitle() %></td>
            <td><a href="<%= result.getLink() %>" target="_blank"><%= result.getLink() %></a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>