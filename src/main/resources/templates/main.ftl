<#import "parts/common.ftl" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-3">Search</button>
        </form>
    </div>
</div>
<a class="btn btn-primary" data-toggle="collapse" href="#addMessage" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse" id="addMessage">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input class="form-control" type="text" name="text" placeholder="Введите сообщение">
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="tag" placeholder="Тэг">
            </div>
            <div class="form-group custom-file">
                <input type="file" class="custom-file-input" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>

<div><h1>Список сообщений</h1></div>
<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <#if message.filename??>
                <img class="card-img-top" src="/img/${message.filename}" alt="Card image cap">
            </#if>
            <div class="card-body m2">
                <span class="card-text">${message.text}</span>
                <i>${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <strong>${message.authorName}</strong>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>
</@c.page>