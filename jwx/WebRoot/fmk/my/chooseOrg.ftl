<em>业态</em>
<select name="orgid" id="orgid" style="width:150px" onchange="changeOrg(this);">
<option value="" >请选择</option>
<#list orgs as org>
	<option value="${org.id}" >${org.orgName}</option>
</#list>
</select>