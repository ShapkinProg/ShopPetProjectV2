<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row row-cols-1 row-cols-md-4 g-4">
    <#list allStaff as staff>
        <div class="col">
            <div class="card" style="width: 300px;">

                <#if staff.filename??>
                    <img src="/img/${staff.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <a href="/product/${staff.id}">${staff.tag}</a>
                    <form action="/basket/${staff.id}/delete" method="post" class="ml-5">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <input type="submit" value="Delete from basket"/>
                    </form>
                </div>

                <div class="card-footer text-muted">
                    <span>${staff.text}</span>
                </div>
            </div>
        </div>
    <#else>
        No staff
        </div>
        </div>
    </#list>
    </div>
</@c.page>