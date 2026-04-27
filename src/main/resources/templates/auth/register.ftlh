<#import "../layout.ftlh" as layout>
<@layout.layout>

    <div class="d-flex justify-content-center align-items-center" style="min-height: 100vh; background: #f9fafb;">
        <div class="card shadow-lg rounded-4 p-4" style="max-width: 450px; width: 100%; border: none;">
            <div class="card-body py-5 px-md-4">
                <h2 class="fw-bold mb-4 text-center">Регистрация</h2>
                <form id="register-form" action="/auth/register" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!''}"/>
                    </#if>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" class="form-control <#if errors?? && errors.hasFieldErrors('email')>is-invalid</#if>"
                               value="${userDto.email!}" placeholder="you@example.com">
                        <#if errors?? && errors.hasFieldErrors("email")>
                            <div class="text-danger">${errors.getFieldError("email").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Пароль</label>
                        <input type="password" name="password" class="form-control <#if errors?? && errors.hasFieldErrors('password')>is-invalid</#if>"
                               placeholder="Введите пароль">
                        <#if errors?? && errors.hasFieldErrors("password")>
                            <div class="text-danger">${errors.getFieldError("password").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Имя пользователя</label>
                        <input type="text" name="name" class="form-control" value="${userDto.name!}" placeholder="Введите имя">
                        <#if errors?? && errors.hasFieldErrors("name")>
                            <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Номер телефона</label>
                        <input type="tel" name="phoneNumber" class="form-control" value="${userDto.phoneNumber!}" placeholder="+996 XXX XXX XXX">
                        <#if errors?? && errors.hasFieldErrors("phoneNumber")>
                            <div class="text-danger">${errors.getFieldError("phoneNumber").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3 text-center">
                        <label class="form-label d-block mb-2">Тип аккаунта</label>
                        <div class="btn-group" role="group">
                            <input type="radio" class="btn-check" name="accountType" id="applicant" value="APPLICANT" checked>
                            <label class="btn btn-outline-primary" for="applicant">Соискатель</label>
                            <input type="radio" class="btn-check" name="accountType" id="employer" value="EMPLOYER">
                            <label class="btn btn-outline-success" for="employer">Компания</label>
                        </div>
                    </div>

                    <div class="d-grid mt-4">
                        <button type="submit" class="btn btn-primary btn-lg rounded-3">Зарегистрироваться</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@layout.layout>
