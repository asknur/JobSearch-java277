<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
        <title>Job Search 2.0</title>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Job Search 2.0</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/vacancy">Вакансии</a></li>

                    <#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
                        <#assign auth = SPRING_SECURITY_CONTEXT.authentication>
                        <#assign principal = auth.principal>
                        <#if principal?has_content && principal != "anonymousUser">

                            <#assign isEmployer = false>
                            <#list auth.authorities as a>
                                <#if a.authority == "ROLE_EMPLOYER">
                                    <#assign isEmployer = true>
                                </#if>
                            </#list>

                            <#if isEmployer>
                            <#-- Работодатель видит резюме -->
                                <li class="nav-item"><a class="nav-link" href="/resume">Резюме</a></li>
                            <#else>
                            <#-- Соискатель видит компании -->
                                <li class="nav-item"><a class="nav-link" href="/company">Компании</a></li>
                            </#if>

                            <li class="nav-item"><a class="nav-link" href="/profile">Профиль</a></li>
                        </#if>
                    </#if>
                </ul>

                <div class="d-flex align-items-center gap-2">
                    <#if SPRING_SECURITY_CONTEXT?? && SPRING_SECURITY_CONTEXT.authentication??>
                        <#assign auth = SPRING_SECURITY_CONTEXT.authentication>
                        <#assign principal = auth.principal>
                        <#if principal?has_content && principal != "anonymousUser">
                            <a href="/profile" class="text-light">Hello ${principal.username!"user"}</a>
                            <form action="/auth/logout" method="post" class="d-inline">
                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf'}"
                                           value="${(_csrf.token)!''}"/>
                                </#if>
                                <button type="submit" class="btn btn-danger">Выйти</button>
                            </form>
                        <#else>
                            <a href="/auth/login" class="btn btn-success">Войти</a>
                            <a href="/auth/register" class="btn btn-info">Регистрация</a>
                        </#if>
                    <#else>
                        <a href="/auth/login" class="btn btn-success">Войти</a>
                        <a href="/auth/register" class="btn btn-info">Регистрация</a>
                    </#if>
                </div>
            </div>
        </div>
    </nav>

    <div class="container mt-3">
        <#nested>
    </div>
    </body>
    </html>
</#macro>