<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-body d-flex align-items-center gap-4">
                <#if company.avatar?? && company.avatar != "">
                    <img src="/avatars/${company.avatar}" alt="avatar"
                         class="rounded-circle" style="width:80px;height:80px;object-fit:cover;">
                <#else>
                    <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center"
                         style="width:80px;height:80px;">
                        <span class="text-white fs-2">${company.name?substring(0,1)?upper_case}</span>
                    </div>
                </#if>
                <div>
                    <h3>${company.name}<#if company.surname??> ${company.surname}</#if></h3>
                    <p class="mb-1"><strong>Email:</strong> ${company.email}</p>
                    <#if company.phoneNumber??>
                        <p class="mb-1"><strong>Телефон:</strong> ${company.phoneNumber}</p>
                    </#if>
                    <#if company.age??>
                        <p class="mb-1"><strong>Возраст:</strong> ${company.age}</p>
                    </#if>
                </div>
            </div>
        </div>
        <a href="/company" class="btn btn-secondary mt-3">← Назад к списку</a>
    </div>
</@main.layout>
