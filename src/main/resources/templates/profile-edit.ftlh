<#import "layout.ftlh" as main>
<@main.layout>
    <h2 class="mb-4">Редактирование профиля</h2>
    <form method="post" action="/profile/edit">
        <div class="mb-3">
            <label for="name" class="form-label">Имя</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}">
        </div>
        <button type="submit" class="btn btn-success">Сохранить</button>
    </form>
</@main.layout>