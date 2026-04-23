<#import "./layout.ftlh" as layout>
<@layout.layout>
    <h2 class="mb-4">Главная страница</h2>
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
    </div>
    <nav aria-label="...">
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
</@layout.layout>

