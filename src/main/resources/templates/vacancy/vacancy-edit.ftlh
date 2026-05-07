<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4"><@spring.message 'vacancy.edit.title'/></h2>
        <div class="card p-4 shadow-sm">
            <form method="post" action="/vacancy/edit/${vacancyDto.id}">
                <#if _csrf??>
                    <input
                            type="hidden"
                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'vacancy.field.name'/></label>
                    <input type="text" name="name" class="form-control" value="${vacancyDto.name!}">
                    <#if errors?? && errors.hasFieldErrors("name")>
                        <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.category'/></label>
                    <select name="categoryId" class="form-select">
                        <#list categories as cat>
                            <option value="${cat.id}"
                                    <#if vacancyDto.categoryId?? && vacancyDto.categoryId == cat.id>selected</#if>>
                                ${cat.name}
                            </option>
                        </#list>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.description'/></label>
                    <textarea name="description" class="form-control" rows="4">${vacancyDto.description!}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.salary'/></label>
                    <input type="number" name="salary" class="form-control"
                           value="<#if vacancyDto.salary??>${vacancyDto.salary?string["0.##"]}</#if>" min="0">
                    <#if errors?? && errors.hasFieldErrors("salary")>
                        <div class="text-danger">${errors.getFieldError("salary").defaultMessage}</div>
                    </#if>
                </div>

                <div class="col-md-4">
                    <label class="form-label fw-bold"><@spring.message 'vacancy.field.expFrom.short'/></label>
                    <input type="number" name="expFrom" class="form-control" value="${vacancyDto.expFrom!}" min="0">
                    <#if errors?? && errors.hasFieldErrors("expFrom")>
                        <div class="text-danger">${errors.getFieldError("expFrom").defaultMessage}</div>
                    </#if>
                </div>
                    <div class="col-md-4">
                        <label class="form-label fw-bold"><@spring.message 'vacancy.field.expTo'/></label>
                        <input type="number" name="expTo" class="form-control" value="${vacancyDto.expTo!}" min="0">
                    </div>
                </div>

                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" name="isActive" id="isActive"
                           <#if vacancyDto.isActive?? && vacancyDto.isActive>checked</#if>>
                    <label class="form-check-label" for="isActive"><@spring.message 'vacancy.field.isActive'/></label>
                </div>

                <div class="d-flex gap-2 justify-content-end">
                    <a href="/profile" class="btn btn-outline-secondary"><@spring.message 'common.cancel'/></a>
                    <button type="submit" class="btn btn-success"><@spring.message 'common.save'/></button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>