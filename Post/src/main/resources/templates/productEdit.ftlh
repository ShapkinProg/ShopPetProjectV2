<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>Edit staff</h3>
    <div class="card" style="width: 250px;margin-left: auto;
    margin-right: auto;">
        <#if staff.filename??>
            <img src="/img/${staff.filename}" class="card-img-top">
        </#if>
        <div class="collapse <#if staff??>show</#if>" id="collapseExample">
            <div class="form-group">
                <form method="post" action="/product/${staff.id}/edit" enctype="multipart/form-data">
                    <div class="form-group">
                        Введите тег
                        <input type="text" class="form-control"
                               value="<#if staff??>${staff.tag}</#if>" name="tag" placeholder="${staff.tag}">
                        <#if tagError??>
                            <div class="invalid-feedback">
                                ${tagError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        Введите текст сообщения
                        <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                               value="<#if staff??>${staff.text}</#if>" name="text" placeholder="${staff.text}" />
                        <#if textError??>
                            <div class="invalid-feedback">
                                ${textError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <div class="custom-file">
                            <label class="custom-file-label" for="customFile">Выберите файл(не обязательно)</label>
                            <input type="file" name="file" id="customFile">

                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group mt-3">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@c.page>