<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group">
        <label class="col-sm-2 col-form-label">User Name: </label>
        <div class="col-sm-6">
            <input type="text" class="form-control" name="username" placeholder="Username"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" name="password" placeholder="Password"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
    <button type="submit" class="btn btn-primary ml-3">
        <#if isRegisterForm>
            Create
        <#else>
            Sign in
        </#if>
    </button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary">Sing out</button>
</form>
</#macro>