<#import "../layout.ftlh" as main>
<@main.layout>
    <h2 class="mb-4">Редактирование вакансии</h2>
    <#list vacancies as vacancy>
    <form method="post" action="/vacancy/edit/${vacancy.id}">
        <div class="mb-3">
            <label for="title" class="form-label">Название вакансии</label>
            <input type="text" id="title" name="title" class="form-control" value="${vacancy.name!}">
        </div>
        <div class="mb-3">
            <label for="company" class="form-label">Компания</label>
            <input type="text" id="company" name="company" class="form-control" value="${vacancy.description!}">
        </div>
    </#list>
        <button type="submit" class="btn btn-success">Сохранить</button>
    </form>
</@main.layout>