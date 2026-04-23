<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Создание резюме</h2>
        <div class="card p-4 shadow-sm">
            <form method="post" action="/resume/create">
                <#if _csrf??>
                    <input
                            type="hidden"
                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label class="form-label fw-bold">Название резюме</label>
                    <input type="text" name="name" class="form-control" value="${resumeDto.name!}">
                    <#if errors?? && errors.hasFieldErrors("name")>
                        <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Категория</label>
                    <select name="categoryId" class="form-select">
                        <option value="">-- Выберите категорию --</option>
                        <#list categories as cat>
                            <option value="${cat.id}">${cat.name}</option>
                        </#list>
                    </select>
                    <#if errors?? && errors.hasFieldErrors("categoryId")>
                        <div class="text-danger">${errors.getFieldError("categoryId").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Ожидаемая зарплата</label>
                    <div class="input-group">
                        <input type="number" name="salary" class="form-control" value="${resumeDto.salary!}" min="0">
                        <span class="input-group-text">USD</span>
                    </div>
                    <#if errors?? && errors.hasFieldErrors("salary")>
                        <div class="text-danger">${errors.getFieldError("salary").defaultMessage}</div>
                    </#if>
                </div>

                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" name="isActive" id="isActive" checked>
                    <label class="form-check-label" for="isActive">Активно искать работу</label>
                </div>

                <div class="d-flex gap-2 justify-content-end">
                    <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                    <button type="submit" class="btn btn-primary">Опубликовать</button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>