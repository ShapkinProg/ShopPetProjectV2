<#include "security.ftlh">
<#import "login.ftlh" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand px-5" href="/">PostPet</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse pl-2" id="navbarSupportedContent" >
            <ul class="navbar-nav pl-1">
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" aria-current="page" href="/">Home</a>-->
<#--                </li>-->
                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/user">User list</a>
                </li>
                </#if>
            </ul>
            <div class="navbar-text me-auto mx-auto">
                <form method="get" action="/" class="row g-2">
                    <div class="col-auto"><input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search..." /></div>
                    <div class="col-auto"><button type="submit" class="btn btn-primary">Search</button></div>
                </form>
            </div>
            <div class="navbar-text mr-auto">
                <#if user??><#if !isAdmin><a href="/basket"> <img src="https://img.icons8.com/material-sharp/452/shopping-cart.png" width="50" alt="shopping bag"></a></#if></#if>
            </div>
            <div class="navbar-text px-1">

                <#if user??><#if name =="unknown">Guest<#else><a class="nav-link" aria-current="page" href="/user/profile">${name}</a></#if></#if>
            </div>
            <#if name !="unknown">
                <@l.logout/>
            <#else>
                <a href="/login"><button class="btn btn-primary" >Sign in</button></a>
            </#if>
        </div>
</nav>
