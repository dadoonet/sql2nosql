<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  ~ Licensed to Tugdual Grall and David Pilato (the "Author") under one
  ~ or more contributor license agreements. See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership. Author licenses this
  ~ file to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License. You may obtain a copy of the License at
  ~
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
  ~ either express or implied. See the License for the specific
  ~ language governing permissions and limitations under the License.
  --%>

<t:template>
    <jsp:body>


        <c:if test="${msg != null }" >
            <div class="alert fade in">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <div class="row">
        <form action="${pageContext.request.contextPath}/home" method="get">
            <div class="span8">
                <input type="text" class="input-medium search-query span8"
                    name="query" value="${query}" placeholder="Type something...">
            </div>
            <div class="span2 offset2">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
        </div>

        <div class="row">
            <div class="span12"></div>
        </div>

        <div class="row">
            <div class="span12">
                <table class="table table-striped table-bordered .table-hover" >
                    <thead>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Date of Birth</th>
                        <th>City</th>
                        <th>Country</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${persons}" var="person">
                        <tr>
                            <td>${person.id}</td>
                            <td><a href="${pageContext.request.contextPath}/person?id=${person.id}">${person.name}</a></td>
                            <td>${person.dateOfBirth}</td>
                            <td>${person.address.city}</td>
                            <td>${person.address.country}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </jsp:body>
</t:template>
