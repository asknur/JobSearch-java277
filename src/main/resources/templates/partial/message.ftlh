<#import "../layout.ftlh" as layout>
<#import "/spring.ftl" as spring>
<@layout.layout>
    <div class="text-center">
        <h2><@spring.message 'auth.reset.title'/></h2>
        <p class="text-warning">${message}</p>
    </div>
</@layout.layout>