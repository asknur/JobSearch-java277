<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card shadow-sm border-0 rounded-4 mb-4">
                    <div class="card-header text-center bg-primary text-white rounded-top-4">
                        <h3>
                            <#if user.accountType == "EMPLOYER">
                                Личный кабинет Работодателя
                            <#elseif user.accountType == "APPLICANT">
                                Личный кабинет Соискателя
                            </#if>
                        </h3>
                    </div>
                    <div class="card-body text-center">
                        <img src="${(user.avatar)!"default.png"}" class="rounded-circle mb-3" width="120" height="120"
                             alt="user.name">
                        <h4>${user.surname!'Не указано'} ${user.name}</h4>
                        <p class="text-muted">${user.email}</p>
                        <p><strong>Возраст:</strong> ${user.age!}</p>
                        <p><strong>Телефон:</strong> ${user.phoneNumber!"Не указан"}</p>

                        <span class="badge bg-primary">${user.accountType}</span>

                        <div class="d-flex justify-content-center mt-3 gap-2">
                            <a href="/profile/edit" class="btn btn-outline-primary rounded-pill px-4">Редактировать</a>
                            <form action="/auth/logout" method="post">
                                <#if _csrf??>
                                    <input
                                            type="hidden"
                                            name="${(_csrf.parameterName)!'csrf-param-name'}"
                                            value="${(_csrf.token)!'csrf-token'}"/>
                                </#if>
                                <button type="submit" class="btn btn-outline-primary rounded-pill px-4" id="logout">
                                    Выйти
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-md-8">
                <div class="card shadow-sm border-0 rounded-4">
                    <div class="card-header bg-light rounded-top-4">
                        <h5 class="mb-0">
                            <#if user.accountType== "APPLICANT">
                                Мои резюме и отклики
                            <#else>
                                Мои вакансии и отклики
                            </#if>
                        </h5>
                    </div>
                    <div class="card-body">
                        <#if user.accountType == "APPLICANT">
                            <h6 class="mb-3">Мои резюме</h6>
                            <#if resumes?has_content>
                                <ul class="list-group mb-4">
                                    <#list resumes as resume>
                                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                            ${resume.name}
                                            <span class="badge bg-success">${resume.name}</span>
                                        </li>
                                        <a href="/resume/edit/${resume.id}" class="btn btn-outline-success">
                                            Редактировать</a>
                                    </#list>
                                    <nav aria-label="...">
                                        <ul class="pagination justify-content-center mt-3">
                                            <li class="page-item <#if currentPage == 0>disabled</#if>">
                                                <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                                            </li>
                                            <#list 0..(resumeTotalPages - 1) as p>
                                                <li class="page-item <#if p == currentPage>active</#if>">
                                                    <a class="page-link" href="?page=${p}">${p + 1}</a>
                                                </li>
                                            </#list>
                                            <li class="page-item <#if currentPage == resumeTotalPages - 1>disabled</#if>">
                                                <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </ul>
                            <#else>
                                <p class="text-muted">Резюме пока нет.</p>
                            </#if>
                            <#if user.accountType == "APPLICANT">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h6 class="mb-0">Мои резюме</h6>
                                    <a href="/resume/create" class="btn btn-sm btn-success rounded-pill">+ Создать
                                        резюме</a>
                                </div>
                            </#if>

                            <h6 class="mb-3">Отклики</h6>
                            <#if vacancies?has_content>
                                <ul class="list-group">
                                    <#list vacancies as vacancy>
                                        <li class="list-group-item">
                                            Вакансия: <strong>${vacancy.name}</strong> <br>
                                            Описание: <span class="badge bg-info">${vacancy.description}</span>
                                        </li>
                                    </#list>
                                </ul>
                            <#else>
                                <p class="text-muted">Вы пока не откликались.</p>
                            </#if>
                        </#if>

                        <#if user.accountType == "EMPLOYER">
                            <h6 class="mb-3">Мои вакансии</h6>
                            <#if vacancies?has_content>
                                <ul class="list-group mb-4">
                                    <#list vacancies as vacancy>
                                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                            ${vacancy.name}
                                            <span class="badge bg-secondary">${vacancy.respondedCount} откликов</span>
                                        </li>
                                        <a href="/vacancy/edit/${vacancy.id}" class="btn btn-outline-success">
                                            Редактировать</a>
                                    </#list>
                                    <nav aria-label="...">
                                        <ul class="pagination justify-content-center mt-3">
                                            <li class="page-item <#if currentPage == 0>disabled</#if>">
                                                <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                                            </li>
                                            <#list 0..(vacancyTotalPages - 1) as p>
                                                <li class="page-item <#if p == currentPage>active</#if>">
                                                    <a class="page-link" href="?page=${p}">${p + 1}</a>
                                                </li>
                                            </#list>
                                            <li class="page-item <#if currentPage == vacancyTotalPages - 1>disabled</#if>">
                                                <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </ul>
                            <#else>
                                <p class="text-muted">Вакансий пока нет.</p>
                            </#if>
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h6 class="mb-0">Мои резюме</h6>
                                <a href="/vacancy/create" class="btn btn-sm btn-success rounded-pill">+ Создать
                                    вакансию</a>
                            </div>

                            <h6 class="mb-3">Отклики на вакансии</h6>
                            <#if vacancies?has_content>
                                <ul class="list-group">
                                    <#list vacancies as vacancy>
                                        <li class="list-group-item">
                                            Соискатель: <strong>${user.name}</strong> <br>
                                            Вакансия: ${vacancy.name} <br>
                                            Статус: <span class="badge bg-info">${vacancy.description}</span>
                                        </li>
                                    </#list>
                                </ul>
                            <#else>
                                <p class="text-muted">Пока никто не откликнулся.</p>
                            </#if>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
