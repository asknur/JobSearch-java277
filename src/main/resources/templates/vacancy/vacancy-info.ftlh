<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <div class="card shadow-lg p-4">
            <h2 class="mb-3">${vacancy.name}</h2>
            <p class="text-muted">${vacancy.description}</p>
            <p>
                <strong><@spring.message 'common.category'/></strong> ${vacancy.categoryId} <br>
                <strong><@spring.message 'common.salary'/></strong> $${vacancy.salary?string["#,##0.00"]} USD <br>
                <strong><@spring.message 'vacancy.info.experience'/>:</strong>
                <@spring.message 'vacancy.info.experience.from'/> ${vacancy.expFrom}
                <@spring.message 'vacancy.info.experience.to'/> ${vacancy.expTo}
                <@spring.message 'vacancy.info.experience.years'/> <br>
                <strong><@spring.message 'common.status'/></strong></strong>
                <#if vacancy.isActive>
                    <span class="badge bg-success"><@spring.message 'common.status.active'/></strong></span>
                <#else>
                    <span class="badge bg-secondary"><@spring.message 'common.status.inactive'/></strong></span>
                </#if>
                <br>
                <#if vacancy.createdDate??>
                    <strong><@spring.message 'common.published'/></strong> ${vacancy.createdDate} <br>
                </#if>
                <#if vacancy.updateTime??>
                    <strong><@spring.message 'common.updated'/></strong> ${vacancy.updateTime} <br>
                </#if>
            </p>
            <hr>
            <a href="/vacancy/edit/${vacancy.id}" class="btn btn-outline-primary"><@spring.message 'common.edit'/></a>
            <a href="/vacancy" class="btn btn-outline-primary"><@spring.message 'vacancy.info.back'/></a>
        </div>
    </div>
</@main.layout>