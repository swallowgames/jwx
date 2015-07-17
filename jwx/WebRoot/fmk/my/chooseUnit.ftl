<em>单元</em>
<select name="unit" id="unit" style="width:150px" onchange="changeUnit(this);">
<option value="" >请选择</option>
<#list units as unit>
	<option value="${unit.id}" >${unit.name}</option>
</#list>
</select>