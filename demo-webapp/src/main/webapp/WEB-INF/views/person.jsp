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



    <form class="form-horizontal" method="post" id="personForm" name="personForm">

        <c:if test="${person != null }" >
            <input type="hidden" name="id" id="id" value="${person.id}" />
        </c:if>


        <fieldset>
            <legend>Person Information</legend>
            <div class="control-group">
                <label class="control-label" for="Name">Name</label>
                <div class="controls">
                    <input type="text" class="input-xlarge" id="name" name="name"  value="${person.name}" >
                </div>
            </div>

            <legend>Number of Views by Category</legend>


            <label class="control-label" for="Name">Cars</label>
            <div class="controls">
                <input type="text" class="input" id="meta.cars" name="meta.cars"  value="${person.meta.cars}" >
            </div>

            <label class="control-label" for="Name">Shoes</label>
            <div class="controls">
                <input type="text" class="input" id="meta.shoes" name="meta.shoes"  value="${person.meta.shoes}" >
            </div>

            <label class="control-label" for="Name">Toys</label>
            <div class="controls">
                <input type="text" class="input" id="meta.toys" name="meta.toys"  value="${person.meta.toys}" >
            </div>

            <label class="control-label" for="Name">Fashion</label>
            <div class="controls">
                <input type="text" class="input" id="meta.fashion" name="meta.fashion"  value="${person.meta.fashion}" >
            </div>

            <label class="control-label" for="Name">Music</label>
            <div class="controls">
                <input type="text" class="input" id="meta.music" name="meta.music"  value="${person.meta.music}" >
            </div>

            <label class="control-label" for="Name">Garden</label>
            <div class="controls">
                <input type="text" class="input" id="meta.garden" name="meta.garden"  value="${person.meta.garden}" >
            </div>

            <label class="control-label" for="Name">Electronics</label>
            <div class="controls">
                <input type="text" class="input" id="meta.meta.electronics" name="meta.electronics"  value="${person.meta.electronic}" >
            </div>

            <label class="control-label" for="Name">Hifi</label>
            <div class="controls">
                <input type="text" class="input" id="meta.hifi" name="meta.hifi"  value="${person.meta.hifi}" >
            </div>

            <label class="control-label" for="Name">Food</label>
            <div class="controls">
                <input type="text" class="input" id="meta.food" name="meta.food"  value="${person.meta.food}" >
            </div>


            <div class="form-actions">
                <button type="submit" class="btn btn-primary" id="actionSave" name="action"  value="save" >Save changes</button>
                <button type="submit" value="delete" id="actionDelete" name="action" class="btn">Delete</button>
                <button type="reset" class="btn">Cancel</button>
            </div>
        </fieldset>
    </form>


</jsp:body>
</t:template>

