package com.google.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GoogleChart {
	private static final com.google.gson.Gson gson = new com.google.gson.Gson();

	public static String toChartDataTable(String rowName, List<Map<String, Object>> list, String[] strs, ChartFormat cf) {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> data = new HashMap<>();

		List<Map<String, Object>> cols = new ArrayList<>();
		data.put("cols", cols);
		Map<String, Object> col;
		if (rowName != null) {
			col = new HashMap<>();
			col.put("type", "string");
			col.put("id", rowName);
			col.put("label", rowName);
			cols.add(col);
		}
		F: for (Map.Entry<String, Object> e : list.get(0).entrySet()) {
			if (rowName == null || !rowName.equals(e.getKey())) {
				if (cf != null && cf.type == ChartFormat.TABLE && ("F_" + cf.format).equals(e.getKey())) {
					continue F;
				}
				col = new HashMap<>();
				if (strs != null) {
					for (String s : strs) {
						if (s.equals(e.getKey())) {
							col.put("type", "string");
							col.put("label", e.getKey());
							cols.add(col);
							continue F;
						}
					}
				}
				if (cf != null && cf.type == ChartFormat.TABLE) {
					for (String s : cf.formatArray) {
						if (s.equals(e.getKey())) {
							col.put("type", "boolean");
							col.put("label", e.getKey());
							cols.add(col);
							continue F;
						}
					}
				}
				if (cf != null && cf.type == ChartFormat.TIMELINE)  col.put("type", "date");
				else  col.put("type", "number");
				col.put("label", e.getKey());
				cols.add(col);
			}
		}

		List<Map<String, List<Map<String, Object>>>> rows = new ArrayList<>();
		data.put("rows", rows);
		Map<String, List<Map<String, Object>>> row;
		List<Map<String, Object>> cells;
		for (Map<String, Object> map : list) {
			row = new HashMap<>();
			cells = new ArrayList<Map<String, Object>>();
			row.put("c", cells);
			Map<String, Object> cell;
			if (rowName != null) {
				cell = new HashMap<>();
				cell.put("v", map.get(rowName));
				if (cf != null && cf.type == ChartFormat.ORG_CHART) {
					sb.setLength(0);
					for (String t : cf.formatArray) {
						if (t.indexOf('#') == 0) {
							if (map.get(t.substring(1)) == null)
								continue;
							sb.append(map.get(t.substring(1)));
						} else
							sb.append(t);
					}
					cell.put("f", sb.toString());
				}
				cells.add(cell);
			}
			F: for (Map.Entry<String, Object> e : map.entrySet()) {
				if (rowName == null || !rowName.equals(e.getKey())) {
					cell = new HashMap<>();
					if (cf != null && cf.type == ChartFormat.TABLE && ("F_" + cf.format).equals(e.getKey())) {
						cell.put("f", e.getValue());
						continue F;
					}
					cell.put("v", e.getValue());
					cells.add(cell);
				}
			}
			rows.add(row);
		}
		return gson.toJson(data)
				.replaceAll("\"null\"", "null")
				.replaceAll("\"true\"", "true")
				.replaceAll("\"false\"", "false");
	}

	public static String toSimpleTable(String rowName, List<Map<String, Object>> list) {
		Map<String, Object> data = new HashMap<>();

		List<Map<String, Object>> cols = new ArrayList<>();
		data.put("cols", cols);
		Map<String, Object> col;
		if (rowName != null) {
			col = new HashMap<>();
			col.put("type", "string");
			cols.add(col);
		}
		for (Map.Entry<String, Object> e : list.get(0).entrySet()) {
			if (rowName == null || !rowName.equals(e.getKey())) {
				col = new HashMap<>();
				col.put("label", e.getKey());
				cols.add(col);
			}
		}

		List<Map<String, List<Map<String, Object>>>> rows = new ArrayList<>();
		data.put("rows", rows);
		Map<String, List<Map<String, Object>>> row;
		List<Map<String, Object>> cells;
		for (Map<String, Object> map : list) {
			row = new HashMap<>();
			cells = new ArrayList<Map<String, Object>>();
			row.put("c", cells);
			Map<String, Object> cell;
			if (rowName != null) {
				cell = new HashMap<>();
				cell.put("v", map.get(rowName));
				cells.add(cell);
			}
			for (Map.Entry<String, Object> e : map.entrySet()) {
				if (rowName == null || !rowName.equals(e.getKey())) {
					cell = new HashMap<>();
					cell.put("v", e.getValue());
					cells.add(cell);
				}
			}
			rows.add(row);
		}
		return gson.toJson(data);
	}
}