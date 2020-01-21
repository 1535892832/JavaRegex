package com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

public class ClearRegexListener implements ActionListener{
    private JTextArea regexTextArea = null;
	public ClearRegexListener(JTextArea regexTextArea) {
		this.regexTextArea=regexTextArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//清空正则表达式
	  regexTextArea.setText("");
	}
}
