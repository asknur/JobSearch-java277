<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-lg p-4">
            <h2 class="mb-3">${vacancy.name}</h2>
            <p class="text-muted">${vacancy.description}</p>
            <p>
                <strong>Категория:</strong> ${vacancy.categoryId} <br>
                <strong>Зарплата:</strong> $${vacancy.salary?string["#,##0.00"]} USD <br>
                <strong>Опыт:</strong> от ${vacancy.expFrom} до ${vacancy.expTo} лет <br>
                <strong>Статус:</strong>
                <#if vacancy.active>
                    <span class="badge bg-success">Активная</span>
                <#else>
                    <span class="badge bg-secondary">Неактивная</span>
                </#if>
                <br>
                <#if vacancy.createdTime??>
                    <strong>Дата публикации:</strong> ${vacancy.createdTime} <br>
                </#if>
                <#if vacancy.updateTime??>
                    <strong>Последнее обновление:</strong> ${vacancy.updateTime} <br>
                </#if>
            </p>
            <hr>
            <a href="/vacancy" class="btn btn-outline-primary">Назад к списку вакансий</a>
        </div>
    </div>
</@main.layout>