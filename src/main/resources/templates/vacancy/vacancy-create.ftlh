<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Создание вакансии</h2>

        <div class="card p-4 shadow-sm">
            <form method="post" action="/vacancy/create">
                <div class="mb-4">
                    <label for="name" class="form-label fw-bold">Название вакансии</label>
                    <input type="text" id="name" name="name" class="form-control form-control-lg"
                           placeholder="Введите название" required>
                </div>

                <div class="mb-4">
                    <label for="salary" class="form-label fw-bold">Заработная плата</label>
                    <div class="input-group">
                        <input type="number" id="salary" name="salary" class="form-control form-control-lg"
                               placeholder="Введите оклад" required>
                        <span class="input-group-text">KGZ</span>
                    </div>
                </div>

                <div class="mb-4">
                    <label for="description" class="form-label fw-bold">Описание вакансии</label>
                    <textarea id="description" name="description" class="form-control"
                              rows="5" placeholder="Опишите вакансию..." required></textarea>
                </div>

                <div class="row mb-4">
                    <div class="col-md-6">
                        <label for="expFrom" class="form-label fw-bold">Требуемый опыт от</label>
                        <input type="number" id="expFrom" name="expFrom" class="form-control"
                               placeholder="0" min="0">
                    </div>
                    <div class="col-md-6">
                        <label for="expTo" class="form-label fw-bold">до</label>
                        <input type="number" id="expTo" name="expTo" class="form-control"
                               placeholder="10" min="0">
                    </div>
                </div>

                <div class="form-check form-switch mb-4">
                    <input class="form-check-input" type="checkbox" id="isActive" name="isActive" checked>
                    <label class="form-check-label fw-bold" for="isActive">Активно</label>
                </div>

                <div class="d-flex justify-content-end gap-2">
                    <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                    <button type="submit" class="btn btn-primary">Создать вакансию</button>
                </div>
            </form>
        </div>
    </div>


</@main.layout>