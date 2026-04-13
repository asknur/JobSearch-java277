<#import "../layout.ftlh" as main>
<@main.layout>
    <h2 class="mb-4">Вакансии</h2>
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
                        <#if vac.active>
                            <span class="badge bg-success">Активная</span>
                        <#else>
                            <span class="badge bg-secondary">Неактивная</span>
                        </#if> <br>

                        <#if vac.createdTime??>
                            Дата публикации: ${vac.createdTime} <br>
                        </#if>
                    </p>
                    <a href="vacancy/${vac.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </#list>
    </div>
</@main.layout>