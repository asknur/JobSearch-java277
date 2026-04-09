<#import "./layout.ftlh" as main>
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
                        <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                             class="rounded-circle mb-3" width="120" height="120" alt="Avatar">
                        <h4>${user.surname} ${user.name}</h4>
                        <p class="text-muted">${user.email}</p>
                        <p><strong>Возраст:</strong> ${user.age}</p>
                        <p><strong>Телефон:</strong> ${user.phone_number!"Не указан"}</p>

                        <span class="badge bg-primary">${user.accountType}</span>

                        <div class="d-flex justify-content-center mt-3 gap-2">
                            <a href="/profile/edit" class="btn btn-outline-primary rounded-pill px-4">Редактировать</a>
                            <form action="/logout" method="post">
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
                            <#if user.accountType== "applicant">
                                Мои резюме и отклики
                            <#else>
                                Мои вакансии и отклики
                            </#if>
                        </h5>
                    </div>
                    <div class="card-body">
                        <#if user.accountType == "applicant">
                            <h6 class="mb-3">Мои резюме</h6>
                            <#if resumes?has_content>
                                <ul class="list-group mb-4">
                                    <#list resumes as resume>
                                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                            ${resume.name}
                                            <span class="badge bg-success">${resume.name}</span>
                                        </li>
                                    </#list>
                                </ul>
                            <#else>
                                <p class="text-muted">Резюме пока нет.</p>
                            </#if>
                            <#if user.accountType == "applicant">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h6 class="mb-0">Мои резюме</h6>
                                    <a href="/resume/create" class="btn btn-sm btn-success rounded-pill">+ Создать резюме</a>
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

                        <!-- Для работодателя -->
                        <#if user.accountType == "employer">
                            <h6 class="mb-3">Мои вакансии</h6>
                            <#if vacancies?has_content>
                                <ul class="list-group mb-4">
                                    <#list vacancies as vacancy>
                                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                            ${vacancy.name}
                                            <span class="badge bg-secondary">${vacancy.respondedCount} откликов</span>
                                        </li>
                                    </#list>
                                </ul>
                            <#else>
                                <p class="text-muted">Вакансий пока нет.</p>
                            </#if>
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h6 class="mb-0">Мои резюме</h6>
                                <a href="/vacancy/create" class="btn btn-sm btn-success rounded-pill">+ Создать вакансию</a>
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
