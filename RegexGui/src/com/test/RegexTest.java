package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		String str = "xiasd@163.com, sdlfkj@.com sdflkj@180.com solodfdsf@123.com sdlfjxiaori@139.com saldkfj.com oisdfo@.sodf.com.com";
		String regex = "\\w+?@\\w+?\\.com";
		// 正则匹配
		Matcher m = Pattern.compile(regex).matcher(str);
		//System.out.println("-------------------------");
		while (m.find()) {
			final String r = m.group();
			System.out.print(r+"\t");
			//System.out.println("-------------------------");
		}

	}
}
