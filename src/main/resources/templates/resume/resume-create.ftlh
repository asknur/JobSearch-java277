<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Создание резюме</h2>

        <div class="card p-4 shadow-sm">
            <form method="post" action="/resume/create">
                <div class="mb-4">
                    <label for="name" class="form-label fw-bold">Название резюме</label>
                    <input type="text" id="name" name="name" class="form-control form-control-lg"
                           placeholder="Введите название" required>
                </div>

                <div class="mb-4">
                    <label for="salary" class="form-label fw-bold">Ожидаемый уровень оклада</label>
                    <div class="input-group">
                        <input type="number" id="salary" name="salary" class="form-control form-control-lg"
                               placeholder="Введите оклад" required>
                        <span class="input-group-text">KGZ</span>
                    </div>
                </div>

                <div class="mb-4">
                    <h5 class="fw-bold mb-3">Опыт работы</h5>

                    <div class="work-experience-item mb-3">
                        <div class="row g-3">
                            <div class="col-md-3">
                                <input type="number" name="workExperiences[0].years" class="form-control"
                                       placeholder="Годы работы" required>
                            </div>
                            <div class="col-md-3">
                                <label>
                                    <input type="text" name="workExperiences[0].companyName" class="form-control"
                                           placeholder="Компания" required>
                                </label>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="workExperiences[0].position" class="form-control"
                                       placeholder="Должность" required>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="workExperiences[0].responsibilities" class="form-control"
                                       placeholder="Обязанности" required>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="btn btn-outline-secondary btn-sm add-experience-btn">
                        + Добавить место работы
                    </button>
                </div>

                <div class="mb-4">
                    <h5 class="fw-bold mb-3">Образование</h5>

                    <div class="education-item mb-3">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <input type="text" name="educations[0].institution" class="form-control"
                                       placeholder="Учебное заведение" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" name="educations[0].period" class="form-control"
                                       placeholder="Годы обучения" required>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="btn btn-outline-secondary btn-sm add-education-btn">
                        + Добавить образование
                    </button>
                </div>

                <div class="mb-4">
                    <h5 class="fw-bold mb-3">Контактная информация</h5>

                    <div class="mb-3">
                        <input type="email" name="contacts.email" class="form-control"
                               placeholder="Email" required>
                    </div>

                    <div class="mb-3">
                        <input type="tel" name="contacts.phone" class="form-control"
                               placeholder="Телефон" required>
                    </div>
                </div>

                <div class="form-check form-switch mb-4">
                    <input class="form-check-input" type="checkbox" id="isActive" name="isActive" checked>
                    <label class="form-check-label fw-bold" for="isActive">Активно искать работу</label>
                </div>

                <div class="d-flex justify-content-end gap-2">
                    <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                    <button type="submit" class="btn btn-primary">Сохранить резюме</button>
                </div>
            </form>
        </div>
    </div>
</@main.layout>