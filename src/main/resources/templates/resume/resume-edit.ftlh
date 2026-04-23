<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Редактирование резюме</h2>
        <div class="card p-4 shadow-sm">
            <form method="post" action="/resume/edit/${resumeDto.id}">
                <#if _csrf??>
                    <input

                            type="hidden"

                            name="${(_csrf.parameterName)!'csrf-param-name'}"

                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>


                <div class="mb-3">
                    <label class="form-label fw-bold">Название</label>
                    <input type="text" name="name" class="form-control" value="${resumeDto.name!}">
                    <#if errors?? && errors.hasFieldErrors("name")>
                        <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Категория</label>
                    <select name="categoryId" class="form-select">
                        <#list categories as cat>
                            <option value="${cat.id}"
                                    <#if resumeDto.categoryId?? && resumeDto.categoryId == cat.id>selected</#if>>
                                ${cat.name}
                            </option>
                        </#list>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Зарплата</label>
                    <input type="number" name="salary" class="form-control" value="${resumeDto.salary!}" min="0">
                </div>

                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" name="isActive" id="isActive"
                           <#if resumeDto.isActive?? && resumeDto.isActive>checked</#if>>
                    <label class="form-check-label" for="isActive">Активно искать работу</label>
                </div>

                <div class="d-flex gap-2 justify-content-end">
                    <a href="/resume/${resumeDto.id}" class="btn btn-outline-secondary">Отмена</a>
                    <button type="submit" class="btn btn-success">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>