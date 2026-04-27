<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4">Компании</h2>
        <#list companies as company>
            <div class="card mb-3 shadow-sm">
                <div class="card-body d-flex align-items-center gap-3">
                    <#if company.avatar?? && company.avatar != "">
                        <img src="/avatars/${company.avatar}" alt="avatar"
                             class="rounded-circle" style="width:60px;height:60px;object-fit:cover;">
                    <#else>
                        <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center"
                             style="width:60px;height:60px;">
                            <span class="text-white fs-4">${company.name?substring(0,1)?upper_case}</span>
                        </div>
                    </#if>
                    <div>
                        <h5 class="mb-1">${company.name}<#if company.surname??> ${company.surname}</#if></h5>
                        <p class="mb-1 text-muted">${company.email}</p>
                        <#if company.phoneNumber??>
                            <p class="mb-0 text-muted">${company.phoneNumber}</p>
                        </#if>
                    </div>
                    <a href="/company/${company.id}" class="btn btn-primary ms-auto">Подробнее</a>
                </div>
            </div>
        </#list>
    </div>

    <nav aria-label="...">
        <ul class="pagination justify-content-center mt-3">
            <li class="page-item <#if currentPage == 0>disabled</#if>">
                <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
            </li>
            <#list 0..(totalPages - 1) as p>
                <li class="page-item <#if p == currentPage>active</#if>">
                    <a class="page-link" href="?page=${p}">${p + 1}</a>
                </li>
            </#list>
            <li class="page-item <#if currentPage == totalPages - 1>disabled</#if>">
                <a class="page-link" href="?page=${currentPage + 1}">Next</a>
            </li>
        </ul>
    </nav>
</@main.layout>
