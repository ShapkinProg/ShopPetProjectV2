<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="card" style="width: 300px;margin-left: auto;
    margin-right: auto;">
        <#if staff.filename??>
            <img src="/img/${staff.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <h4>${staff.tag}</h4>
            <p>${staff.text}</p>
        </div>
    </div>
</@c.page>