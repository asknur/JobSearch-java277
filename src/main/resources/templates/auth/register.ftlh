<#import "../layout.ftlh" as layout>
<@layout.layout>
    <div class="d-flex justify-content-center align-items-center" style="min-height: 100vh; background: #f9fafb;">
        <div class="card shadow-lg rounded-4 p-4" style="max-width: 450px; width: 100%; border: none;">
            <div class="card-body py-5 px-md-4">
                <h2 class="fw-bold mb-4 text-center">Регистрация</h2>
                <form id="register-form" action="/auth/register" method="post">
                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope-fill"></i></span>
                            <input type="email" name="email" class="form-control" id="email"
                                   placeholder="you@example.com" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Пароль</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Введите пароль" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="name" class="form-label">Имя пользователя</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                            <input type="text" name="name" class="form-control" id="name"
                                   placeholder="Введите имя" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Номер телефона</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-telephone-fill"></i></span>
                            <input type="tel" name="phoneNumber" class="form-control" id="phoneNumber"
                                   placeholder="+996 XXX XXX XXX" required>
                        </div>
                    </div>

                    <div class="mb-3 text-center">
                        <label class="form-label d-block mb-2">Тип аккаунта</label>
                        <div class="btn-group" role="group">
                            <input type="radio" class="btn-check" name="accountType" id="applicant"
                                   value="APPLICANT" checked>
                            <label class="btn btn-outline-primary" for="applicant">
                                <i class="bi bi-person-badge-fill"></i> Соискатель
                            </label>

                            <input type="radio" class="btn-check" name="accountType" id="employer"
                                   value="EMPLOYER">
                            <label class="btn btn-outline-success" for="employer">
                                <i class="bi bi-building"></i> Компания
                            </label>
                        </div>
                    </div>

                    <div class="d-grid mt-4">
                        <button type="submit" class="btn btn-primary btn-lg rounded-3">
                            <i class="bi bi-check-circle-fill"></i> Зарегистрироваться
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@layout.layout>