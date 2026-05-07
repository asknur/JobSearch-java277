<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4"><@spring.message 'profile.edit.title'/></h2>
        <div class="card p-4 shadow-sm">

            <form method="post" action="/profile/avatar" enctype="multipart/form-data" class="mb-4">
                <#if _csrf??>
                    <input
                            type="hidden"
                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <label class="form-label fw-bold"><@spring.message 'profile.edit.changeAvatar'/></label>
                <div class="d-flex align-items-center gap-3">
                    <img src="/avatars/${userDto.avatar!'default.png'}" class="rounded-circle"
                         width="80" height="80" alt="avatar">
                    <input type="file" name="avatar" class="form-control" accept="image/*">
                    <button type="submit" class="btn btn-outline-primary"><@spring.message 'profile.edit.upload'/></button>
                </div>
            </form>

            <hr>

            <form method="post" action="/profile/edit">

                <#if _csrf??>
                    <input
                            type="hidden"
                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'profile.edit.field.name'/></label>
                    <input type="text" name="name" class="form-control" value="${userDto.name!}">
                    <#if errors?? && errors.hasFieldErrors("name")>
                        <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'profile.edit.field.surname'/></label>
                    <input type="text" name="surname" class="form-control" value="${userDto.surname!}">
                    <#if errors?? && errors.hasFieldErrors("surname")>
                        <div class="text-danger">${errors.getFieldError("surname").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.age'/></label>
                    <input type="number" name="age" class="form-control" value="${userDto.age!}" min="14" max="100">
                    <#if errors?? && errors.hasFieldErrors("age")>
                        <div class="text-danger">${errors.getFieldError("age").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.phone'/></label>
                    <input type="tel" name="phoneNumber" class="form-control" value="${userDto.phoneNumber!}">
                </div>

                <div class="d-flex gap-2 justify-content-end">
                    <a href="/profile" class="btn btn-outline-secondary"><@spring.message 'common.cancel'/></a>
                    <button type="submit" class="btn btn-primary"><@spring.message 'common.save'/></button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>