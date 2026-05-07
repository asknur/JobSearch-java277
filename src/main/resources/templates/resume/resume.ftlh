<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-4"><@spring.message 'resume.list.title'/></h2>
            <#list resumes as res>
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">
                        <h5>${res.name}</h5>
                        <p>
                            <#if res.salary??>
                                <@spring.message 'common.salary'/>$${res.salary?string["#,##0.00"]} USD <br>
                            </#if>
                            <@spring.message 'common.status'/>
                            <#if res.isActive>
                                <span class="badge bg-success"><@spring.message 'resume.status.active'/></span>
                            <#else>
                                <span class="badge bg-secondary"><@spring.message 'resume.status.inactive'/></span>
                            </#if> <br>
                            <#if res.createdDate??>
                                <@spring.message 'common.published'/>${res.createdDate}<br>
                            </#if>
                        </p>
                        <a href="resume/${res.id}" class="btn btn-primary"><@spring.message 'common.details'/></a>
                    </div>
                </div>
            </#list>
        </div>
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
</@main.layout>