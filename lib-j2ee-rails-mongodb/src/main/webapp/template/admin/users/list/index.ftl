<!DOCTYPE html>
<html lang="zh-cn">
<body>
<h1>users list!</h1>
<#if users??&&users?size gt 0>
    <#list users?keys as key>
    <h2>${key}</h2>
    <h2>${users[key]["name"]}</h2>
    </#list>
</#if>

</body>
</html>