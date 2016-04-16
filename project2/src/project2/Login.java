package project2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.sql.*;
import java.lang.String;

public class Login {

	private JFrame frame;
	static JTextField textField;
	//static Arrange window;
	static JPasswordField passwordField;
	static Connection con;
	static Statement sql;
	static ResultSet res;
	static JLabel label_2;
	
	public Connection getConnection(){//建立返回值为Connection的方法
		try{//加载数据库驱动类
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库驱动加载成功！");
		}catch (ClassNotFoundException e){
			
		}
		try{//通过访问数据库的ＵＲＬ获取数据库链接对象
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=userdb1","Lily","000000");
			System.out.println("数据库连接成功！");
		}catch (SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[]args) {
		Login c = new Login();
		con=c.getConnection();//调用连接数据库的方法
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u667A\u80FD\u6392\u8BFE\u7CFB\u7EDF-\u7528\u6237\u767B\u5F55");
		frame.getContentPane().setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		frame.setBackground(SystemColor.control);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setFont(new Font("Batang", Font.PLAIN, 15));
		frame.setBounds(100, 100, 438, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(182, 129, 137, 28);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(101, 129, 71, 25);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(101, 170, 57, 25);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u667A\u80FD\u6392\u8BFE\u7CFB\u7EDF");
		label.setBounds(85, 10, 252, 79);
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("楷体", Font.BOLD, 40));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(182, 94, 137, 21);
		comboBox.setFont(new Font("宋体", Font.PLAIN, 16));
		comboBox.setToolTipText("");
		frame.getContentPane().add(comboBox);
		comboBox.addItem("管理员");
		comboBox.addItem("学生");
		
		JButton button = new JButton("\u767B\u9646");
		button.setBounds(101, 215, 93, 23);
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				try{
					sql=con.createStatement();//执行ＳＱＬ语句，返回结果集
					String user=textField.getText();
						res=sql.executeQuery("select * from t_u where uname ='"+user+"'");
					
					while(res.next())
					{
						String upsd = new String();
								upsd=res.getString("upsd");
						String upower = res.getString("upower");
						char[] pass = passwordField.getPassword();
						String password = new String(pass);
						String a =(String) comboBox.getSelectedItem();
						
						if(password.equals(upsd))
						{
							if(upower.equals(a))
							{
								if(upower.equals("管理员")){
								Arrange.main2();
								frame.setVisible(false);
								}
								if(upower.equals("学生")){
									DisplayS.main(null);
									frame.setVisible(false);
								}
							}
							else break;
						}
						else break;
				    }			
				//}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				}	
		});
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setBounds(226, 215, 93, 23);
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		frame.getContentPane().add(button_1);
		button_1.addMouseListener(new MouseAdapter() {    // 对button按钮添加监听事件
		    public void mouseClicked(MouseEvent e) {    // 当鼠标点击时
		        System.exit(0);        // 退出
		        frame.setVisible(false);
		    }
		});
		
	
		JLabel label_1 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		label_1.setBounds(95, 87, 99, 32);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		frame.getContentPane().add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 74, 412, 188);
		frame.getContentPane().add(separator);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 170, 136, 28);
		frame.getContentPane().add(passwordField);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(329, 129, 93, 28);
		frame.getContentPane().add(label_2);
		passwordField.addKeyListener(new KeyAdapter()
		  {    
			public void keyPressed(KeyEvent e1)   {
		    if(e1.getKeyChar()==KeyEvent.VK_ENTER)  {//如果密码中输入enter键
		    	button.doClick();//点击登录按钮
		   }  
		    }  
		  });
	}
}
