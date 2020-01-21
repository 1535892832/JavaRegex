package com.entity;

public class MatchResult {
	/*
	 * 匹配结果类
	 */

	// 开始位置
	private int start;
	// 结束位置
	private int end;
	// 结果
	private String result;

	public MatchResult() {

	}

	public MatchResult(int start, int end, String result) {
		super();
		this.start = start;
		this.end = end;
		this.result = result;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getLength() {
		return this.result.length();
	}

	@Override
	public String toString() {
		if (result == null) {
			return "";
		}
		return this.result;
	}

}
