<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4"><@spring.message 'resume.edit.title'/></h2>
        <div class="card p-4 shadow-sm">
            <form method="post" action="/resume/edit/${resumeDto.id}">
                <#if _csrf??>
                    <input
                            type="hidden"
                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                            value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.name'/></label>
                    <input type="text" name="name" class="form-control" value="${resumeDto.name!}">
                    <#if errors?? && errors.hasFieldErrors("name")>
                        <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.category'/></label>
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
                    <label class="form-label fw-bold"><@spring.message 'common.salary'/></label>
                    <input type="number" name="salary" class="form-control"  value="<#if resumeDto.salary??>${resumeDto.salary?string["0.##"]}</#if>">
                </div>

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.work.title'/></label>
                    <#list resumeDto.workExperiences as w>
                        <div class="border rounded p-3 mb-2">
                            <div class="mb-2">
                                <label class="form-label">Название компании</label>
                                <input type="text" name="workExperiences[${w?index}].companyName" class="form-control" value="${w.companyName!}">
                            </div>
                            <div class="mb-2">
                                <label class="form-label">Должность</label>
                                <input type="text" name="workExperiences[${w?index}].position" class="form-control" value="${w.position!}">
                            </div>
                            <div class="mb-2">
                                <label class="form-label">Лет опыта</label>
                                <input type="number" name="workExperiences[${w?index}].years" class="form-control" value="${w.years!}" min="0">
                            </div>
                            <div class="mb-2">
                                <label class="form-label">Обязанности</label>
                                <textarea name="workExperiences[${w?index}].responsibilities" class="form-control" rows="2">${w.responsibilities!}</textarea>
                            </div>
                        </div>
                    </#list>
                </div>

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.education.title'/></label>
                    <#list resumeDto.educations as e>
                        <div class="border rounded p-3 mb-2">
                            <div class="mb-2">
                                <label class="form-label">Учебное заведение</label>
                                <input type="text" name="educations[${e?index}].institution" class="form-control" value="${e.institution!}">
                            </div>
                            <div class="mb-2">
                                <label class="form-label">Программа</label>
                                <input type="text" name="educations[${e?index}].program" class="form-control" value="${e.program!}">
                            </div>
                            <div class="row mb-2">
                                <div class="col">
                                    <label class="form-label">Дата начала</label>
                                    <input type="date" name="educations[${e?index}].startDate" class="form-control" value="${e.startDate!}">
                                </div>
                                <div class="col">
                                    <label class="form-label">Дата окончания</label>
                                    <input type="date" name="educations[${e?index}].endDate" class="form-control" value="${e.endDate!}">
                                </div>
                            </div>
                            <div class="mb-2">
                                <label class="form-label">Степень</label>
                                <input type="text" name="educations[${e?index}].degree" class="form-control" value="${e.degree!}">
                            </div>
                        </div>
                    </#list>
                </div>

                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" name="isActive" id="isActive"
                           <#if resumeDto.isActive?? && resumeDto.isActive>checked</#if>>
                    <label class="form-check-label" for="isActive"><@spring.message 'resume.field.isActive'/></label>
                </div>

                <div class="d-flex gap-2 justify-content-end">
                    <a href="/resume/${resumeDto.id}" class="btn btn-outline-secondary"><@spring.message 'common.cancel'/></a>
                    <button type="submit" class="btn btn-success"><@spring.message 'common.save'/></button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>