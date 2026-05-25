<#import 'spring.ftl' as spring>
<#import "./layout.ftlh" as layout>
<@layout.layout>
    <script src="/static/js/vacancy.js"></script>
    <form method="get" action="/vacancy" class="d-flex gap-2 align-items-center mb-3">
        <input type="hidden" name="page" value="0">
        <select id="filterSort" class="form-select form-select-sm w-auto" onchange="applyFilter()">
            <option value="newest"
                    <#if sort == "newest">selected</#if>><@spring.message 'vacancy.sort.newest'/></option>
            <option value="oldest"
                    <#if sort == "oldest">selected</#if>><@spring.message 'vacancy.sort.oldest'/></option>
            <option value="responded_desc"
                    <#if sort == "responded_desc">selected</#if>><@spring.message 'vacancy.sort.responded.desc'/></option>
            <option value="responded_asc"
                    <#if sort == "responded_asc">selected</#if>><@spring.message 'vacancy.sort.responded.asc'/></option>
        </select>
    </form>
    <h2 class="mb-4"><@spring.message 'index.title'/></h2>
    <#list vacancy as vac>
        <div class="card mb-3 shadow-sm">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-start">
                    <h5 class="card-title mb-2">
                        ${vac.name}
                    </h5>
                    <#if vac.isActive>
                        <span class="badge bg-success"><@spring.message 'common.status.active'/></span>
                    <#else>
                        <span class="badge bg-secondary"><@spring.message 'common.status.inactive'/></span>
                    </#if>
                </div>
                <p class="card-text text-muted">
                    ${vac.description}
                </p>
                <div class="mb-2">
                    <#if vac.salary??>
                        <div>
                            <strong><@spring.message 'common.salary'/></strong>
                            $${vac.salary?string["#,##0.00"]}
                        </div>
                    </#if>
                    <#if vac.createdDate??>
                        <@spring.message 'common.published'/> ${vac.createdDate} <br>
                    </#if>
                </div>
                <a href="/vacancy/${vac.id}" class="btn btn-primary btn-sm">
                    <@spring.message 'common.details'/>
                </a>
            </div>
        </div>
    </#list>
    </div>
    <nav aria-label="...">
        <ul class="pagination justify-content-center mt-3">
            <li class="page-item <#if currentPage == 0>disabled</#if>">
                <a class="page-link" href="?page=${currentPage - 1}"><@spring.message 'common.previous'/></a>
            </li>
            <#list 0..(totalPages - 1) as p>
                <li class="page-item <#if p == currentPage>active</#if>">
                    <a class="page-link" href="?page=${p}">${p + 1}</a>
                </li>
            </#list>
            <li class="page-item <#if currentPage == totalPages - 1>disabled</#if>">
                <a class="page-link" href="?page=${currentPage + 1}"><@spring.message 'common.next'/></a>
            </li>
        </ul>
    </nav>
</@layout.layout>

