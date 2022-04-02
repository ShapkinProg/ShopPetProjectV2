<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

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
<#--                    <p style="text-align: right">${staff.cost}</p>-->
                    <#if isAdmin>
                        <a href="/product/${staff.id}/edit" class="btn btn-primary">Edit</a>
                        <form action="/product/${staff.id}/delete" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Delete Slot"/>
                        </form>
                    </#if>
                    <#if user??>
                    <form action="/basket/${staff.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <input type="submit" value="Add to busket"/>
                    </form>
                    </#if>
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