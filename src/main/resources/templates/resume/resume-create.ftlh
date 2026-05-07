<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4"><@spring.message 'resume.edit.title'/></h2>
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

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.work.title'/></label>
                    <div id="work-experience-container">
                        <div class="border rounded p-3 mb-2">
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.work.companyName'/></label>
                                <input type="text" name="workExperiences[0].companyName" class="form-control">
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.work.position'/></label>
                                <input type="text" name="workExperiences[0].position" class="form-control">
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.work.years'/></label>
                                <input type="number" name="workExperiences[0].years" class="form-control" min="0">
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.work.responsibilities'/></label>
                                <textarea name="workExperiences[0].responsibilities" class="form-control" rows="2"></textarea>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-outline-secondary btn-sm" onclick="addWorkExperience()">
                        <@spring.message 'resume.work.addButton'/>
                    </button>
                </div>

                <hr>
                <div class="mb-3">
                    <label class="form-label fw-bold"><@spring.message 'resume.education.title'/></label>
                    <div id="education-container">
                        <div class="border rounded p-3 mb-2">
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.education.institution'/></label>
                                <input type="text" name="educations[0].institution" class="form-control">
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.education.program'/></label>
                                <input type="text" name="educations[0].program" class="form-control">
                            </div>
                            <div class="row mb-2">
                                <div class="col">
                                    <label class="form-label"><@spring.message 'resume.education.startDate'/></label>
                                    <input type="date" name="educations[0].startDate" class="form-control">
                                </div>
                                <div class="col">
                                    <label class="form-label"><@spring.message 'resume.education.endDate'/></label>
                                    <input type="date" name="educations[0].endDate" class="form-control">
                                </div>
                            </div>
                            <div class="mb-2">
                                <label class="form-label"><@spring.message 'resume.education.degree'/></label>
                                <input type="text" name="educations[0].degree" class="form-control">
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-outline-secondary btn-sm" onclick="addEducation()">
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

                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" name="isActive" id="isActive" checked>
                    <label class="form-check-label" for="isActive">
                        <@spring.message 'resume.field.isActive'/>
                    </label>
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
        const i18n = {
            companyName: '<@spring.message "resume.work.companyName"/>',
            position: '<@spring.message "resume.work.position"/>',
            years: '<@spring.message "resume.work.years"/>',
            responsibilities: '<@spring.message "resume.work.responsibilities"/>',
            deleteButton: '<@spring.message "resume.work.deleteButton"/>',
            institution: '<@spring.message "resume.education.institution"/>',
            program: '<@spring.message "resume.education.program"/>',
            startDate: '<@spring.message "resume.education.startDate"/>',
            endDate: '<@spring.message "resume.education.endDate"/>',
            degree: '<@spring.message "resume.education.degree"/>'
        };

        let workCount = 1;
        let eduCount = 1;

        function addWorkExperience() {
            const container = document.getElementById('work-experience-container');
            const block = document.createElement('div');
            block.className = 'border rounded p-3 mb-2';
            block.innerHTML = `
                <div class="mb-2">
                    <label class="form-label">${i18n.companyName}</label>
                    <input type="text" name="workExperiences[${workCount}].companyName" class="form-control">
                </div>
                <div class="mb-2">
                    <label class="form-label">${i18n.position}</label>
                    <input type="text" name="workExperiences[${workCount}].position" class="form-control">
                </div>
                <div class="mb-2">
                    <label class="form-label">${i18n.years}</label>
                    <input type="number" name="workExperiences[${workCount}].years" class="form-control" min="0">
                </div>
                <div class="mb-2">
                    <label class="form-label">${i18n.responsibilities}</label>
                    <textarea name="workExperiences[${workCount}].responsibilities" class="form-control" rows="2"></textarea>
                </div>
                <button type="button" class="btn btn-outline-danger btn-sm mt-1"
                        onclick="this.parentElement.remove()">${i18n.deleteButton}</button>
            `;
            container.appendChild(block);
            workCount++;
        }

        function addEducation() {
            const container = document.getElementById('education-container');
            const block = document.createElement('div');
            block.className = 'border rounded p-3 mb-2';
            block.innerHTML = `
                <div class="mb-2">
                    <label class="form-label">${i18n.institution}</label>
                    <input type="text" name="educations[${eduCount}].institution" class="form-control">
                </div>
                <div class="mb-2">
                    <label class="form-label">${i18n.program}</label>
                    <input type="text" name="educations[${eduCount}].program" class="form-control">
                </div>
                <div class="row mb-2">
                    <div class="col">
                        <label class="form-label">${i18n.startDate}</label>
                        <input type="date" name="educations[${eduCount}].startDate" class="form-control">
                    </div>
                    <div class="col">
                        <label class="form-label">${i18n.endDate}</label>
                        <input type="date" name="educations[${eduCount}].endDate" class="form-control">
                    </div>
                </div>
                <div class="mb-2">
                    <label class="form-label">${i18n.degree}</label>
                    <input type="text" name="educations[${eduCount}].degree" class="form-control">
                </div>
                <button type="button" class="btn btn-outline-danger btn-sm mt-1"
                        onclick="this.parentElement.remove()">${i18n.deleteButton}</button>
            `;
            container.appendChild(block);
            eduCount++;
        }
    </script>
</@main.layout>