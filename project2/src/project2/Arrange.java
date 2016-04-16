package project2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class Arrange {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Arrange window = new Arrange();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 

	/**
	 * Create the application.
	 * @return 
	 */
	public  Arrange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u667A\u80FD\u6392\u8BFE\u7CFB\u7EDF-\u6392\u8BFE");
		frame.setBounds(100, 100, 494, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button_2 = new JButton("\u6392\u8BFE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrangeCourse.main(null);
					frame.setVisible(false);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
						| UnsupportedLookAndFeelException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 18));
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setBounds(37, 149, 120, 29);
		frame.getContentPane().add(button_2);
		
		JLabel label = new JLabel("点击后开始排课,请稍后...");
		label.setForeground(new Color(64, 224, 208));
		label.setFont(new Font("楷体", Font.BOLD, 17));
		label.setBackground(new Color(135, 206, 235));
		label.setBounds(206, 97, 255, 111);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u5B89\u5168\u9000\u51FA");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(400, 0, 88, 29);
		frame.getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {    // 对button按钮添加监听事件
		    public void mouseClicked(MouseEvent e) {    // 当鼠标点击时
		       Logout.main3();   // 退出窗口	
		       frame.setVisible(false);
		    }
		});
		
		JLabel label_1 = new JLabel("\u667A\u80FD\u6392\u8BFE\u7CFB\u7EDF");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(30, 144, 255));
		label_1.setFont(new Font("楷体", Font.BOLD, 40));
		label_1.setBounds(107, 0, 252, 79);
		frame.getContentPane().add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 68, 468, 184);
		frame.getContentPane().add(separator);
		
		table = new JTable();
		table.setBounds(190, 86, 262, 137);
		frame.getContentPane().add(table);
	}
}
