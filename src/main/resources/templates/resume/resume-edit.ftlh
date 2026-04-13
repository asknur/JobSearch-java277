<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Редактирование резюме</h2>
        <div class="card p-4 shadow-sm">
            <form method="post" action="/resume/edit/${resume.id}">
                <div class="mb-3">
                    <label for="name" class="form-label">Название</label>
                    <input type="text" id="name" name="name" class="form-control" value="${resume.name!}">
                </div>
                <div class="mb-3">
                    <label for="salary" class="form-label">Зарплата</label>
                    <input type="number" id="salary" name="salary" class="form-control" value="${resume.salary!}">
                </div>
                <div class="mb-3">
                    <label for="categoryId" class="form-label">Категория ID</label>
                    <input type="number" id="categoryId" name="categoryId" class="form-control" value="${resume.categoryId!}">
                </div>
                <div class="form-check form-switch mb-4">
                    <input class="form-check-input" type="checkbox" id="isActive" name="isActive"
                           <#if resume.active>checked</#if>>
                    <label class="form-check-label" for="isActive">Активно искать работу</label>
                </div>
                <div class="d-flex justify-content-end gap-2">
                    <a href="/resume/${resume.id}" class="btn btn-outline-secondary">Отмена</a>
                    <button type="submit" class="btn btn-success">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>