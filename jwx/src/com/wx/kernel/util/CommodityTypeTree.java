package com.wx.kernel.util;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public final class CommodityTypeTree {
	private static Map<Integer, Object> typeSidTreeMap = null;
	private static Date lastUpdate;

	public static void updateTreeMap(Map<Integer, Object> treeMap) {
		//
		typeSidTreeMap = treeMap;
		lastUpdate = new Date();
	}

	public static Boolean isToUpdate() {
		if (lastUpdate == null) {
			return true;
		}
		Date nowDate = new Date();
		if (nowDate.getTime() - lastUpdate.getTime() > 2 * 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void mapTraversal(Map<Integer, Object> treeMap, Integer keySid, StringBuilder sb) {
		Set<Integer> keySet = treeMap.keySet();
		for (Integer key : keySet) {
			sb.append(key.toString());
			sb.append(",");
			Object value = treeMap.get(key);
			if (value != null) {
				mapTraversal((Map<Integer, Object>) value, key, sb);
			}
		}
	}

	public static String getChilds(Integer rootSid) {
		StringBuilder sb = new StringBuilder();
		if (typeSidTreeMap != null && typeSidTreeMap.containsKey(rootSid)) {
			sb.append(rootSid.toString());
			sb.append(",");
			mapTraversal(typeSidTreeMap, rootSid, sb);
		}
		return sb.toString();
	}
}
