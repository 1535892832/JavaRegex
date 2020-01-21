package com.regex;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.entity.MatchResult;

public class Regex {
	/*
	 * 核心功能 返回匹配结果
	 */
	public static Vector<MatchResult> getResult(String regex, String str) {
		Vector<MatchResult> rlist = new Vector<MatchResult>(0);
		// 编译正则表达式
		try {
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		MatchResult mr = null;
		while (m.find()) {
			// 把结果加入list
			mr = new MatchResult(m.start(), m.end(), m.group());
			rlist.add(mr);
		}
		}catch(Exception ex) {
			JOptionPane.showConfirmDialog(null, "正则表达式分析失败!","异常",JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
		}
		return rlist;
	}

}
