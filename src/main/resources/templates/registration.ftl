<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<h3>Add new user</h3>
    <#if message?ifExists>
    ${message}
    </#if>
    <@l.login "/registration" true/>
</@c.page>