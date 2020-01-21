package com.gui;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import com.action.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import com.entity.MatchResult;
import com.regex.Regex;

public class RegexGui extends JFrame {
	// 正则表达式文本框
	JTextArea regexTextArea;
	// 输入要匹配的字符串
	JTextPane textPane;
	// 输出结果列表
	JList<MatchResult> resultList;
	// 结果数据
	Vector<MatchResult> list = new Vector<MatchResult>();
	// 文档样式
	StyledDocument doc = null;
    //弹出菜单
	JPopupMenu utilPopup;
	public RegexGui() {
		// 主面板
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(Color.WHITE);
		// 菜单
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("菜单");
		JMenu util = new JMenu("功能");
		JMenuItem query = new JMenuItem("查询");
		query.addActionListener(new QueryListener());
		JMenuItem clearRegex = new JMenuItem("清空正则表达式");
		JMenuItem clearText = new JMenuItem("清空匹配字符串");
		util.add(query);
		util.add(clearRegex);
		util.add(clearText);
		JMenuItem exit = new JMenuItem("退出");
		exit.addActionListener(new ExitListener());
		menu.add(exit);
		menubar.add(menu);
		menubar.add(util);
		menuPanel.add(menubar);
		// 垂直分割
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		// 初始化文本控件
		regexTextArea = new JTextArea();
		regexTextArea.setLineWrap(true);
		regexTextArea.setFont(new Font("楷体", Font.BOLD, 20));
		clearRegex.addActionListener(new ClearRegexListener(regexTextArea));
		textPane = new JTextPane();
		clearText.addActionListener(new ClearTextAreaListener(textPane));
		textPane.setFont(new Font("宋体", Font.BOLD, 18));
		textPane.setForeground(Color.GRAY);
		textPane.setToolTipText("这里输入要匹配的字符串");
		// 添加文本样式
		doc = textPane.getStyledDocument();
		Style style = textPane.addStyle("Red", null);
		StyleConstants.setForeground(style, Color.RED);
		style = textPane.addStyle("Default", null);
		StyleConstants.setForeground(style, Color.BLACK);
		resultList = new JList<MatchResult>();
		resultList.addListSelectionListener(new ResultListener());
		resultList.addMouseListener(new ListMouseListener(this));
		resultList.setBackground(new Color(40, 254, 216));
		resultList.setForeground(new Color(254, 136, 40));
		resultList.setFixedCellWidth(80);
     
		// 文本滚动条
		JScrollPane textJsp = new JScrollPane(textPane);
		jsp.setDividerLocation(150);
		jsp.setEnabled(false);
		jsp.setTopComponent(regexTextArea);
		jsp.setBottomComponent(textJsp);
		mainPanel.add(menuPanel, BorderLayout.NORTH);
		mainPanel.add(resultList, BorderLayout.EAST);
		mainPanel.add(jsp, BorderLayout.CENTER);
		
		utilPopup = new JPopupMenu();
		JMenuItem copym= new JMenuItem("复制");
		copym.addActionListener(new CopyListener());
		utilPopup.add(copym);
		// 窗口设置
		this.setTitle("JavaRegex 1.0");
		this.getContentPane().add(mainPanel);
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	class CopyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
         int index = resultList.getSelectedIndex();
			if(index>-1) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable contents = new StringSelection(list.get(index).toString());
				cb.setContents(contents, null);
			}
		}
	}
	
	
	class ListMouseListener extends MouseAdapter{
		//List鼠标点击
		private Component c;
		public ListMouseListener(Component c) {
		 this.c=c;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			//如果右键点击
		  if(e.getButton()==e.BUTTON3) {
			  //从位置判断选择索引
			  Point p= e.getPoint();
			  int index = resultList.locationToIndex(p);
			  if(index>-1) {
			  reductionTextPaneColor();
              resultList.setSelectedIndex(index);
              selectByIndex(index);
			  utilPopup.show(c,(int)(resultList.getX()+p.getX()),(int)(resultList.getY()+p.getY()));
			  }
		  }
		}
		
	}
	
	class ResultListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// List选择事件
			JList<String> rlist = (JList) e.getSource();
			int index = rlist.getSelectedIndex();
			if (index != -1 && e.getValueIsAdjusting()) {
				// 还原默认颜色
				reductionTextPaneColor();
				selectByIndex(index);
			}
		}
	}

	
	private void selectByIndex(int index) {
		MatchResult mr = list.get(index);
		// 设置鼠标光标
		textPane.setCaretPosition(mr.getStart());
		// 设置选中为红色
		doc.setCharacterAttributes(mr.getStart(), mr.getLength(), textPane.getStyle("Red"), true);
	}
	
	// 还原TextPane颜色
	private void reductionTextPaneColor() {
		doc.setCharacterAttributes(0, textPane.getText().length(), textPane.getStyle("Default"), true);
	}

	class QueryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 查询
			reductionTextPaneColor();
			String regex = regexTextArea.getText();
			String str = textPane.getText();
			list = Regex.getResult(regex, str);
			// 刷新结果列表
			resultList.setListData(list);
		}
	}

}
