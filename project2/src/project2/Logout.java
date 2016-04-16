package project2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logout {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main3() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logout window = new Logout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Logout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u667A\u80FD\u6392\u8BFE\u7CFB\u7EDF-\u7528\u6237\u767B\u51FA");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u667A\u80FD\u6392\u8BFE\u7CFB\u7EDF");
		label.setBounds(88, 29, 252, 79);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("楷体", Font.BOLD, 40));
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\uFF1A");
		label_1.setBounds(147, 134, 54, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setText(Login.textField.getText());
		label_2.setBounds(210, 135, 54, 15);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u767B\u51FA\u6210\u529F\uFF01");
		label_3.setBounds(179, 159, 85, 15);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.setBounds(171, 215, 93, 23);
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		frame.getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {    // 对button按钮添加监听事件
		    @Override
		    public void mouseClicked(MouseEvent e) {    // 当鼠标点击时
		    	Login.main(null);
		    	frame.setVisible(false);
		    }
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 118, 403, 146);
		frame.getContentPane().add(separator);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss"); 
		String currentTime = sdf.format(new Date());
		JLabel label_4 = new JLabel(currentTime);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(130, 184, 172, 21);
		frame.getContentPane().add(label_4);
		
		}
	}

