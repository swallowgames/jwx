<em>房号</em>
<select name="room" id="room" style="width:150px" >
<option value="" >请选择</option>
<#list rooms as room>
	<option value="${room.id}" >${room.name}</option>
</#list>
</select>