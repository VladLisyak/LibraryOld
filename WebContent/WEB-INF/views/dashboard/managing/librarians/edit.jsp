<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.edit.librarian"/>
<db:wrapper title="${title}" id = "${requestScope.librarian.id}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="/dashboard/users/librarians/edit" method="post">
                    <input type="hidden" name="id" value="${requestScope.librarian.id}">
                    <utl:text label="txt.firstName" id="firstName" value="${requestScope.librarian.firstName}"/>
                    <utl:text label="txt.lastName" id="lastName" value="${requestScope.librarian.lastName}"/>
                    <utl:text label="txt.email" id="email" value="${requestScope.librarian.email}"/>
                    <utl:text label="txt.login" id="login" value="${requestScope.librarian.login}"/>
                    <div class="btn btn-outline btn-primary btn-xs toggle" id="toggle">
                        <fmt:message key="txt.edit.password"/>
                    </div>
                    <div class="btn btn-outline btn-primary btn-xs toggle" id="cancelChanging">
                        <fmt:message key="txt.cancelChanging"/>
                    </div>
                    <div id="toggle-password">
                        <input type="hidden" name="changePassword" id="changePassword" value="0">
                        <utl:text id="password">
                        <jsp:attribute name="label">
                            txt.password
                        </jsp:attribute>
                        </utl:text>
                    </div>
                    <utl:confirm link="/dashboard/users/librarians"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="head">
       <!--  <style>
            #toggle-password, #cancelChanging {
                display: none;
            }

            #toggle, #cancelChanging {
                margin-bottom: 10px;
            }
        </style> -->
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script>
            $(function () {
                $('#toggle').on('click', function () {
                    $('#toggle').hide();
                    $('#cancelChanging').css('display', 'inline-block');
                    $('#toggle-password').show('slow');
                    $('#changePassword').val(true);
                });
                $('#cancelChanging').on('click', function () {
                    $('#toggle').css('display', 'inline-block');
                    $('#cancelChanging').hide();
                    $('#toggle-password').hide('slow');
                    $('#changePassword').val(false);
                });
            });
        </script>
    </jsp:attribute>
</db:wrapper>