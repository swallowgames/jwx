<em>楼栋</em>
<select name="building" id="building" style="width:150px" onchange="changeBuilding(this);">
<option value="" >请选择</option>
<#list buildings as building>
	<option value="${building.id}" >${building.name}</option>
</#list>
</select>