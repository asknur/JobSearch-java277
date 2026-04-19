<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Резюме</h2>
        <div class="row">
            <#list resumes as res>
                <div class="col-md-4 mb-3">
                    <div class="card p-3">
                        <h5>${res.name}</h5>
                        <p>
                            <#if res.salary??>
                                Зарплата: $${res.salary?string["#,##0.00"]} USD <br>
                            </#if>
                            Статус:
                            <#if res.isActive>
                                <span class="badge bg-success">Активное</span>
                            <#else>
                                <span class="badge bg-secondary">Неактивное</span>
                            </#if> <br>
                            <#if res.createdDate??>
                                Дата публикации: ${res.createdDate}<br>
                            </#if>
                        </p>
                        <a href="resume/${res.id}" class="btn btn-primary">Подробнее</a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@main.layout>