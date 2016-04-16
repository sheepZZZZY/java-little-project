package projetc1;
import java.awt.*;
import javax.swing.*;
import projetc1.ExcelFile.StudentInfo;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.io.File;
public class Login {
     ExcelFile excel=new ExcelFile();//声明一个成员变量
     
     private double[] Weight=new double[10];//权重
	JFrame window1=new JFrame("钟老师，你好！");//窗口名字
	//返回的是学号字符串
	public String Astr(){
		 String str=textField.getText();
		return str;
	}
	
     private static  JTextField textField;
     private static JTextField textField_0;
 	private static JTextField textField_1;
 	private static JTextField textField_2;
 	private static JTextField textField_3;
 	private static JTextField textField_4;
 	private static JTextField textField_5;
 	private static JTextField textField_6;
 	private static JTextField textField_7;
 	private static JTextField textField_8;
 	private static JTextField textField_9;
 	
 	private static JTextField textField_00;
	private static JTextField textField_11;
	private static JTextField textField_22;
	private static JTextField textField_33;
	private static JTextField textField_44;
	private JTextField textField_10;
		// TODO Auto-generated method stub
		//构造方法
		public Login(){
			init();
			window1.setVisible(true);
			window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		class okAction implements ActionListener{
			Login parent;
			public okAction(Login p) {parent=p;}
			public void actionPerformed(ActionEvent e) {
					  //ExcelFile excel=new ExcelFile();
					   String text=parent.Astr();
					   
			    	   String filename="e:/Project1_成绩.xls";  
			    	   
			    	  excel.readExcel(filename,text);
			        //显示第二个界面
			    	  init_2();
			    	  
			}
		}
		//定义str函数,可以用到ExcelFile中传递过去
		void init(){
			window1.getContentPane().setLayout(null);
			
			JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5B66\u53F7");
			label.setBounds(123, 200, 78, 199);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setForeground(SystemColor.textText);
			window1.getContentPane().add(label);
			
			textField = new JTextField();
			textField.setBounds(247, 283, 146, 34);
			window1.getContentPane().add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("\u786E\u5B9A\r\n");
			btnNewButton.setBounds(333, 363, 60, 23);
			window1.getContentPane().add(btnNewButton);
			
			JLabel lblNewLabel_3 = new JLabel("\u5B66\u751F\u6210\u7EE9\u7BA1\u7406\u5E73\u53F0");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBounds(70, 46, 396, 115);
			window1.getContentPane().add(lblNewLabel_3);
			
			JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jfc=new JFileChooser();
		    		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		    		jfc.showDialog(new JLabel(), "选择");
		    File filename=jfc.getSelectedFile();
		    textField_10.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			});
			button.setBounds(401, 242, 93, 23);
			window1.getContentPane().add(button);
			
			textField_10 = new JTextField();
			textField_10.setBounds(247, 243, 146, 30);
			window1.getContentPane().add(textField_10);
			textField_10.setColumns(10);
			
			JButton btnNewButton_2 = new JButton("\u6DFB\u52A0");
			btnNewButton_2.setBounds(238, 363, 66, 23);
			window1.getContentPane().add(btnNewButton_2);
			//con.setBackground();
			window1.setBounds(400,150,600,553);
		
			//确定 按钮的监听函数
			btnNewButton.addActionListener(new okAction(this));
}
		void init_2(){
			
			// TODO Auto-generated method stub
			JFrame window2=new JFrame("成绩信息");
			window2.setBounds(260, 100, 568, 388);
			
	       window2.getContentPane().setLayout(null);
	        JLabel label = new JLabel("\u5B66\u53F7");
	        label.setBounds(22, 33, 43, 15);
	        label.setForeground(Color.BLACK);
	        window2.getContentPane().add(label);
	        textField_0 = new JTextField();
	        textField_0.setBounds(75, 30, 160, 21);
	        window2.getContentPane().add(textField_0);
	        textField_0.setColumns(10);
	        JLabel lblNewLabel = new JLabel("\u59D3\u540D");
	        lblNewLabel.setBounds(22, 74, 54, 15);
	        window2.getContentPane().add(lblNewLabel);
	        textField_1 = new JTextField();
	        textField_1.setBounds(75, 71, 160, 21);
	        window2.getContentPane().add(textField_1);
	        textField_1.setColumns(10);
	        JLabel lblNewLabel_1 = new JLabel("\u4E13\u4E1A");
	        lblNewLabel_1.setBounds(22, 117, 54, 15);
	        window2.getContentPane().add(lblNewLabel_1);
	        JLabel lblNewLabel_2 = new JLabel("\u73ED\u7EA7");
	        lblNewLabel_2.setBounds(22, 153, 54, 15);
	        window2.getContentPane().add(lblNewLabel_2);
	        JLabel lblNewLabel_4 = new JLabel("\u8BFE\u5802\u51FA\u52E4");
	        lblNewLabel_4.setBounds(11, 194, 65, 15);
	        window2.getContentPane().add(lblNewLabel_4);
	        JLabel lblNewLabel_5 = new JLabel("\u5E73\u65F6\u4F5C\u4E1A");
	        lblNewLabel_5.setBounds(286, 33, 54, 15);
	        window2.getContentPane().add(lblNewLabel_5);
	        JLabel lblNewLabel_6 = new JLabel("\u5B9E\u9A8C\u6210\u7EE9");
	        lblNewLabel_6.setBounds(286, 74, 54, 15);
	        window2.getContentPane().add(lblNewLabel_6);
	        JLabel lblNewLabel_7 = new JLabel("Project");
	        lblNewLabel_7.setBounds(286, 117, 54, 15);
	        window2.getContentPane().add(lblNewLabel_7);
	        JLabel label_1 = new JLabel("\u8003\u8BD5\u6210\u7EE9");
	        label_1.setBounds(286, 153, 54, 15);
	        window2.getContentPane().add(label_1);
	        JLabel lblNewLabel_8 = new JLabel("\u603B\u6210\u7EE9");
	        lblNewLabel_8.setBounds(286, 194, 54, 15);
	        window2.getContentPane().add(lblNewLabel_8);
	        textField_2 = new JTextField();
	        textField_2.setBounds(75, 117, 160, 21);
	        window2.getContentPane().add(textField_2);
	        textField_2.setColumns(10);
	        textField_3 = new JTextField();
	        textField_3.setBounds(75, 150, 160, 21);
	        window2.getContentPane().add(textField_3);
	        textField_3.setColumns(10);
	        textField_4 = new JTextField();
	        textField_4.setBounds(75, 194, 160, 21);
	        window2.getContentPane().add(textField_4);
	        textField_4.setColumns(10);
	        textField_5 = new JTextField();
	        textField_5.setBounds(350, 30, 160, 21);
	        window2.getContentPane().add(textField_5);
	        textField_5.setColumns(10);
	        textField_6 = new JTextField();
	        textField_6.setBounds(350, 71, 160, 21);
	        window2.getContentPane().add(textField_6);
	        textField_6.setColumns(10);
	        textField_7 = new JTextField();
	        textField_7.setBounds(350, 114, 160, 21);
	        window2.getContentPane().add(textField_7);
	        textField_7.setColumns(10);
	        textField_8 = new JTextField();
	        textField_8.setBounds(350, 153, 160, 21);
	        window2.getContentPane().add(textField_8);
	        textField_8.setColumns(10);
	        textField_9 = new JTextField();
	        textField_9.setBounds(350, 191, 160, 21);
	        window2.getContentPane().add(textField_9);
	        textField_9.setColumns(10);
	        JButton btnNewButton = new JButton("\u8FD4\u56DE");
	        window2.setVisible(true);
	        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //给textField赋值  stu.TablestuStr[i]);
	      //  System.out.println(excelfile.TablestuStr[1]);
	    
	      textField_0.setText(excel.TablestuStr[0]);
	      textField_1.setText(excel.TablestuStr[1]);
	      textField_2.setText(excel.TablestuStr[2]);
	      textField_3.setText(excel.TablestuStr[3]);
	      textField_4.setText(excel.TablestuStr[4]);
	      textField_5.setText(excel.TablestuStr[5]);
	      textField_6.setText(excel.TablestuStr[6]);
	      textField_7.setText(excel.TablestuStr[7]);
	      textField_8.setText(excel.TablestuStr[8]);
	      textField_9.setText(excel.TablestuStr[9]);
	        
	        //返回按钮的响应函数
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		window2.setVisible(true);
	        		//关闭成绩窗口，返回登陆窗口
	        		window2.dispose();
	        	}
	        });

	        btnNewButton.setBounds(142, 280, 93, 23);
	        window2.getContentPane().add(btnNewButton);
	        JButton btnNewButton_1 = new JButton("\u786E\u5B9A");
	        JButton  button_00=new JButton("权重");
	        window2.getContentPane().add(button_00);
	        button_00.setBounds(250, 280, 93, 23);
	        //权重按钮
	        button_00.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//关闭窗口，保存权重
					//确定权重
	        		init_3();
				}
			});
	        
	        //第二个窗口确定按钮的响应函数
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		//将当前文本款中内容储存到excel所在行中
	        		//前四个文本框不用存姓名学号班级专业
	        		String filename="e:/Project1_成绩.xls";
	        		int a;int b;int c;int d;int e1;double f;
	    	        ExcelFile.Memory[4]=textField_4.getText();
	    	        a=Integer.parseInt(ExcelFile.Memory[4]);
	    	        ExcelFile.Memory[5]=textField_5.getText();
	        		 b=Integer.parseInt(ExcelFile.Memory[5]);
	        		 ExcelFile.Memory[6]=textField_6.getText();
	        		 c=Integer.parseInt(ExcelFile.Memory[6]);
	        		 ExcelFile.Memory[7]=textField_7.getText();
	        		 d=Integer.parseInt(ExcelFile.Memory[7]);
	        		 ExcelFile.Memory[8]=textField_8.getText();
	        		e1 =Integer.parseInt(ExcelFile.Memory[8]);
	        		f=Weight[0]*a+Weight[1]*b+Weight[2]*c+Weight[3]*d+Weight[4]*e1;
	        		ExcelFile.Memory[9]=String.valueOf(f);
	        		//更新当前页面
	        		textField_9.setText(ExcelFile.Memory[9]);
	        		//Memory数组记录下当时读取excel文件记录的行号
	        		ExcelFile.Memory[0]=excel.TablestuStr[15];
	        		
	        	    try {
						excel.writeExcel(filename);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	        	    
	        	}
		
	        });
	        btnNewButton_1.setBounds(417, 280, 93, 23);
	        window2.getContentPane().add(btnNewButton_1);
			
	}
		//权重窗口
		void init_3(){
			JFrame window3=new JFrame("权重");
			window3.setBounds(560, 100, 300, 400);
			window3.setVisible(true);
			window3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window3.getContentPane().setLayout(null);
			
			JLabel lblNewLabel_00 = new JLabel("\u6CE8\u610F\uFF1A\u6743\u91CD\u4E4B\u548C\u4E3A1");
			lblNewLabel_00.setBounds(10, 21, 141, 28);
			window3.getContentPane().add(lblNewLabel_00);
			
			JLabel lblNewLabel_11 = new JLabel("\u8BFE\u5802\u51FA\u52E4");
			lblNewLabel_11.setBounds(10, 73, 54, 15);
			window3.getContentPane().add(lblNewLabel_11);
			
			JLabel lblNewLabel_22 = new JLabel("\u5E73\u65F6\u4F5C\u4E1A");
			lblNewLabel_22.setBounds(10, 110, 54, 15);
			window3.getContentPane().add(lblNewLabel_22);
			
			JLabel lblNewLabel_33 = new JLabel("\u5B9E\u9A8C\u6210\u7EE9");
			lblNewLabel_33.setBounds(10, 147, 54, 15);
			window3.getContentPane().add(lblNewLabel_33);
			
			JLabel lblNewLabel_44 = new JLabel("Project");
			lblNewLabel_44.setBounds(10, 188, 54, 15);
			window3.getContentPane().add(lblNewLabel_44);
			
			JLabel lblNewLabel_55 = new JLabel("考试成绩");
			lblNewLabel_55.setBounds(10, 231, 54, 15);
			window3.getContentPane().add(lblNewLabel_55);
			
			textField_00 = new JTextField();
			textField_00.setBounds(100, 70, 66, 21);
			window3.getContentPane().add(textField_00);
			textField_00.setColumns(10);
			
			textField_11 = new JTextField();
			textField_11.setBounds(100, 107, 66, 21);
			window3.getContentPane().add(textField_11);
			textField_11.setColumns(10);
			
			textField_22 = new JTextField();
			textField_22.setBounds(100, 144, 66, 21);
			window3.getContentPane().add(textField_22);
			textField_22.setColumns(10);
			
			textField_33 = new JTextField();
			textField_33.setBounds(100, 185, 66, 21);
			window3.getContentPane().add(textField_33);
			textField_33.setColumns(10);
			
			textField_44 = new JTextField();
			textField_44.setBounds(100, 228, 66, 21);
			window3.getContentPane().add(textField_44);
			textField_44.setColumns(10);
			
			JButton btnNewButton_00 = new JButton("\u786E\u5B9A");
			btnNewButton_00.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//关闭窗口，保存权重
					Weight[0]=Double.parseDouble(textField_00.getText());
					Weight[1]=Double.parseDouble(textField_11.getText());
					Weight[2]=Double.parseDouble(textField_22.getText());
					Weight[3]=Double.parseDouble(textField_33.getText());
					Weight[4]=Double.parseDouble(textField_44.getText());
					window3.dispose();
				}
			});
			btnNewButton_00.setBounds(150, 295, 93, 23);
			window3.getContentPane().add(btnNewButton_00);
		}
			
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	        //显示登陆登陆
			String filename="e:/Project1_成绩.xls";
			Login win1=new Login();
            
		}
}

