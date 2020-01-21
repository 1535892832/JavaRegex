package com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

public class ClearTextAreaListener implements ActionListener{

	 private JTextPane textArea = null;
		public ClearTextAreaListener(JTextPane textArea) {
			this.textArea=textArea;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		textArea.setText("");
	}
}
