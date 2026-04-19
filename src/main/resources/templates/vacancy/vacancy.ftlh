<#import "../layout.ftlh" as main>
<@main.layout>
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
</@main.layout>