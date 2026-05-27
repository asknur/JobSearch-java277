<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>
<@layout.layout>
    <h2><@spring.message 'auth.forgot.title'/></h2>

    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>

    <#if message??>
        <div class="alert alert-warning">
            <p>${message}</p>
            <#if token??>
                <div class="p-3 bg-light border rounded">
                    <strong>Токен:</strong>
                    <code>${token}</code>
                </div>
                <div class="mt-3">
                    <a href="/auth/reset_password?token=${token}" class="btn btn-primary">
                        Перейти к сбросу пароля
                    </a>
                </div>
            </#if>
        </div>
    </#if>

    <#if !message??>
        <div class="py-5 h-100">
            <div class="row d-flex align-items-center justify-content-center h-100">
                <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                    <form action="/auth/forgot_password" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="form-outline border border-secondary rounded p-3">
                            <p><@spring.message 'auth.forgot.message'/></p>
                            <input type="email" name="email" class="form-control"
                                   placeholder="<@spring.message 'auth.forgot.email.placeholder'/>"
                                   required autofocus/>
                            <p class="text-center mt-3">
                                <input type="submit" value="<@spring.message 'auth.forgot.submit'/>"
                                       class="btn btn-primary"/>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
</@layout.layout>