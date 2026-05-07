<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-lg p-4">
            <h2 class="mb-3">${resume.name}</h2>
            <p>
                <strong><@spring.message 'resume.info.categoryId'/>:</strong> ${resume.categoryId} <br>
                <strong><@spring.message 'common.salary'/></strong> $${resume.salary?string["#,##0.00"]} USD <br>
                <#if resume.createDate??>
                    <strong><@spring.message 'common.published'/></strong> ${resume.createDate} <br>
                </#if>
                <strong><@spring.message 'common.status'/></strong>
                <#if resume.isActive>
                    <span class="badge bg-success"><@spring.message 'common.status.active'/></span>
                <#else>
                    <span class="badge bg-secondary"><@spring.message 'common.status.inactive'/></span>
                </#if>
            </p>
            <hr>
            <a href="/resume/edit/${resume.id}" class="btn btn-outline-primary"><@spring.message 'common.edit'/></a>
            <a href="/resume" class="btn btn-outline-primary"><@spring.message 'resume.info.back'/></a>
        </div>
    </div>
</@main.layout>