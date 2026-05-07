<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>
<@layout.layout>

    <div class="d-flex justify-content-center align-items-center" style="min-height: 100vh; background: #f9fafb;">
        <div class="card shadow-lg rounded-4 p-4" style="max-width: 450px; width: 100%; border: none;">
            <div class="card-body py-5 px-md-4">
                <h2 class="fw-bold mb-4 text-center"><@spring.message 'auth.register.title'/></h2>
                <form id="register-form" action="/auth/register" method="post">
                    <#if _csrf??>
                        <input type="hidden"
                               name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!''}"/>
                    </#if>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message 'auth.register.email'/></label>
                        <input type="email" name="email" class="form-control <#if errors?? && errors.hasFieldErrors('email')>is-invalid</#if>"
                               value="${userDto.email!}" placeholder="<@spring.message 'auth.register.email.placeholder'/>">
                        <#if errors?? && errors.hasFieldErrors("email")>
                            <div class="text-danger">${errors.getFieldError("email").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message 'auth.register.password'/></label>
                        <input type="password" name="password" class="form-control <#if errors?? && errors.hasFieldErrors('password')>is-invalid</#if>"
                               placeholder="<@spring.message 'auth.register.password.placeholder'/>">
                        <#if errors?? && errors.hasFieldErrors("password")>
                            <div class="text-danger">${errors.getFieldError("password").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message 'auth.register.username'/></label>
                        <input type="text" name="name" class="form-control" value="${userDto.name!}" placeholder="<@spring.message 'auth.register.username.placeholder'/>">
                        <#if errors?? && errors.hasFieldErrors("name")>
                            <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3">
                        <label class="form-label"><@spring.message 'auth.register.phone'/></label>
                        <input type="tel" name="phoneNumber" class="form-control" value="${userDto.phoneNumber!}" placeholder="<@spring.message 'auth.register.phone.placeholder'/>">
                        <#if errors?? && errors.hasFieldErrors("phoneNumber")>
                            <div class="text-danger">${errors.getFieldError("phoneNumber").defaultMessage}</div>
                        </#if>
                    </div>

                    <div class="mb-3 text-center">
                        <label class="form-label d-block mb-2"><@spring.message 'auth.register.accountType'/></label>
                        <div class="btn-group" role="group">
                            <input type="radio" class="btn-check" name="accountType" id="applicant" value="APPLICANT" checked>
                            <label class="btn btn-outline-primary" for="applicant"><@spring.message 'auth.register.applicant'/></label>
                            <input type="radio" class="btn-check" name="accountType" id="employer" value="EMPLOYER">
                            <label class="btn btn-outline-success" for="employer"><@spring.message 'auth.register.employer'/></label>
                        </div>
                    </div>

                    <div class="d-grid mt-4">
                        <button type="submit" class="btn btn-primary btn-lg rounded-3"><@spring.message 'auth.register.submit'/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@layout.layout>
