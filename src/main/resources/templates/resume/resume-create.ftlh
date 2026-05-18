<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4"><@spring.message 'resume.create.title'/></h2>
        <div class="card p-4 shadow-sm">
            <form method="post" action="/resume/create">
                <#if _csrf??>
                    <input type="hidden"
                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.field.name'/></label>
                    <input type="text" name="name" class="form-control" value="${resumeDto.name!}">
                    <#if errors?? && errors.hasFieldErrors("name")>
                        <div class="text-danger">${errors.getFieldError("name").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'common.category'/></label>
                    <select name="categoryId" class="form-select">
                        <option value=""><@spring.message 'common.category.choose'/></option>
                        <#list categories as cat>
                            <option value="${cat.id}">${cat.name}</option>
                        </#list>
                    </select>
                    <#if errors?? && errors.hasFieldErrors("categoryId")>
                        <div class="text-danger">${errors.getFieldError("categoryId").defaultMessage}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.field.salary.expected'/></label>
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
                    <label class="form-check-label" for="isActive">
                        <@spring.message 'resume.field.isActive'/>
                    </label>
                </div>

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.work.title'/></label>
                    <div id="work-container"></div>
                    <button type="button" class="btn btn-outline-secondary btn-sm mt-2" onclick="addWork()">
                         <@spring.message 'resume.work.addButton'/>
                    </button>
                </div>

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.education.title'/></label>
                    <div id="edu-container"></div>
                    <button type="button" class="btn btn-outline-secondary btn-sm mt-2" onclick="addEdu()">
                         <@spring.message 'resume.education.addButton'/>
                    </button>
                </div>

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.contacts.title'/></label>
                    <div class="row">
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><@spring.message 'resume.contacts.phone'/></label>
                            <input type="tel" name="contacts[0].contactValue" class="form-control" placeholder="+996 XXX XXX XXX">
                            <input type="hidden" name="contacts[0].typeId" value="2">
                        </div>
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><@spring.message 'common.email'/></label>
                            <input type="email" name="contacts[1].contactValue" class="form-control" placeholder="example@mail.com">
                            <input type="hidden" name="contacts[1].typeId" value="1">
                        </div>
                        <div class="col-md-6 mb-2">
                            <label class="form-label"><@spring.message 'resume.contacts.telegram'/></label>
                            <input type="text" name="contacts[2].contactValue" class="form-control" placeholder="@username">
                            <input type="hidden" name="contacts[2].typeId" value="3">
                        </div>
                    </div>
                </div>

                <div class="d-flex gap-2 justify-content-end">
                    <a href="/profile" class="btn btn-outline-secondary">
                        <@spring.message 'common.cancel'/>
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <@spring.message 'resume.button.publish'/>
                    </button>
                </div>
            </form>
        </div>
    </div>

    <script>
        let workCount = 0;
        let eduCount = 0;

        function addWork() {
            const container = document.getElementById('work-container');
            const i = workCount++;
            const div = document.createElement('div');
            div.className = 'border rounded p-3 mb-2';
            div.innerHTML =
                '<div class="mb-2">' +
                '<label class="form-label">Название компании</label>' +
                '<input type="text" name="workExperiences[' + i + '].companyName" class="form-control">' +
                '</div>' +
                '<div class="mb-2">' +
                '<label class="form-label">Должность</label>' +
                '<input type="text" name="workExperiences[' + i + '].position" class="form-control">' +
                '</div>' +
                '<div class="mb-2">' +
                '<label class="form-label">Лет опыта</label>' +
                '<input type="number" name="workExperiences[' + i + '].years" class="form-control" min="0">' +
                '</div>' +
                '<div class="mb-2">' +
                '<label class="form-label">Обязанности</label>' +
                '<textarea name="workExperiences[' + i + '].responsibilities" class="form-control" rows="2"></textarea>' +
                '</div>' +
                '<button type="button" class="btn btn-outline-danger btn-sm mt-1" onclick="this.closest(\'.border\').remove()">Удалить</button>';
            container.appendChild(div);
        }

        function addEdu() {
            const container = document.getElementById('edu-container');
            const i = eduCount++;
            const div = document.createElement('div');
            div.className = 'border rounded p-3 mb-2';
            div.innerHTML =
                '<div class="mb-2">' +
                '<label class="form-label">Учебное заведение</label>' +
                '<input type="text" name="educations[' + i + '].institution" class="form-control">' +
                '</div>' +
                '<div class="mb-2">' +
                '<label class="form-label">Программа</label>' +
                '<input type="text" name="educations[' + i + '].program" class="form-control">' +
                '</div>' +
                '<div class="row mb-2">' +
                '<div class="col">' +
                '<label class="form-label">Дата начала</label>' +
                '<input type="date" name="educations[' + i + '].startDate" class="form-control">' +
                '</div>' +
                '<div class="col">' +
                '<label class="form-label">Дата окончания</label>' +
                '<input type="date" name="educations[' + i + '].endDate" class="form-control">' +
                '</div>' +
                '</div>' +
                '<div class="mb-2">' +
                '<label class="form-label">Степень</label>' +
                '<input type="text" name="educations[' + i + '].degree" class="form-control">' +
                '</div>' +
                '<button type="button" class="btn btn-outline-danger btn-sm mt-1" onclick="this.closest(\'.border\').remove()">Удалить</button>';
            container.appendChild(div);
        }
    </script>
</@main.layout>о