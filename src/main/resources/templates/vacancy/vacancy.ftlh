<#import "../layout.ftlh" as main>
<@main.layout>
    <form method="get" action="/vacancy" class="d-flex gap-2 align-items-center mb-3">
        <input type="hidden" name="page" value="0">
        <select name="sort" class="form-select form-select-sm w-auto" onchange="this.form.submit()">
            <option value="newest" <#if sort == "newest">selected</#if>>Сначала новые</option>
            <option value="oldest" <#if sort == "oldest">selected</#if>>Сначала старые</option>
        </select>
    </form>
    <div class="container mt-4">
        <h2 class="mb-4">Вакансии</h2>
        <#if vacancy?size == 0>
            <div class="alert alert-info">
                Вакансий пока нет
            </div>
        <#else>
            <#list vacancy as vac>
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start">
                            <h5 class="card-title mb-2">
                                ${vac.name}
                            </h5>
                            <#if vac.isActive>
                                <span class="badge bg-success">Активная</span>
                            <#else>
                                <span class="badge bg-secondary">Неактивная</span>
                            </#if>
                        </div>
                        <p class="card-text text-muted">
                            ${vac.description}
                        </p>
                        <div class="mb-2">
                            <#if vac.salary??>
                                <div>
                                    <strong>Зарплата:</strong>
                                    $${vac.salary?string["#,##0.00"]}
                                </div>
                            </#if>
                            <#if vac.createdDate??>
                                Дата публикации: ${vac.createdDate} <br>
                            </#if>
                        </div>
                        <a href="/vacancy/${vac.id}" class="btn btn-primary btn-sm">
                            Подробнее
                        </a>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
    <nav aria-label="Навигация по страницам">
        <ul class="pagination justify-content-center mt-3">
            <li class="page-item <#if currentPage == 0>disabled</#if>">
                <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
            </li>

            <#list 0..(totalPages - 1) as p>
                <li class="page-item <#if p == currentPage>active</#if>">
                    <a class="page-link" href="?page=${p}">${p + 1}</a>
                </li>
            </#list>

            <li class="page-item <#if currentPage == totalPages - 1>disabled</#if>">
                <a class="page-link" href="?page=${currentPage + 1}">Next</a>
            </li>
        </ul>
    </nav>
</@main.layout>