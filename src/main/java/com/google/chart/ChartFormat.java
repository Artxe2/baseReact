package com.google.chart;

public class ChartFormat {
	public static final int ORG_CHART = 13;
	public static final int TABLE = 15;
	public static final int TIMELINE = 16;
	public int type;
	public String format;
	public String[] formatArray;

	public ChartFormat(int type, String format, String[] formatArray) {
		this.type = type;
		this.format = format;
		this.formatArray = formatArray;
	}
}
