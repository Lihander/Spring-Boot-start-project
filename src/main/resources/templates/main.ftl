<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <@l.logout/>
</div>
<span><a href="/user">User list</a></span>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="Введите сообщение">
        <input type="text" name="tag" placeholder="Тэг">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Добавить</button>
    </form>
</div>
<div><h1>Список сообщений</h1></div>
<div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>
</div>
<#list messages as message>
<div>
    <b>${message.id}</b>
    ${message.text}
    <i>${message.tag}</i>
    <b>${message.authorName}</b>
    <div>
        <#if message.filename??>
            <img src="/img/${message.filename}"
        </#if>
    </div>
</div>
<#else>
No message
</#list>
</@c.page>