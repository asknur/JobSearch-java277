<#import "./layout.ftlh" as layout>
<@layout.layout>
    <h2 class="mb-4">Главная страница</h2>
    <div class="row">
        <#list vacancy as vac>
            <div class="col-md-4 mb-3">
                <div class="card p-3">
                    <h5>${vac.name}</h5>
                    <p>${vac.description}</p>
                    <p>
                        <#if vac.salary??>
                            Зарплата: $${vac.salary?string["#,##0.00"]} <br>
                        </#if>

                        Статус:
                        <#if vac.isActive?? && vac.isActive>
                            <span class="badge bg-success">Активная</span>
                        <#else>
                            <span class="badge bg-secondary">Неактивная</span>
                        </#if> <br>

                        <#if vac.createdDate??>
                            Дата публикации: ${vac.createdDate} <br>
                        </#if>
                    </p>
                    <a href="vacancy/${vac.id}" class="btn btn-primary">Подробнее</a>
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

