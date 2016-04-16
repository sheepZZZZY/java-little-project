package 文件;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import projet3.Client;

public class Recive_thread implements Runnable{
	ServerSocket server;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	File file1,file2;
	String path;
	public void start1()
	{
		Thread thread=new Thread(this);
		thread.start();
	}
	public void run()
	{
		try {
			server=new ServerSocket(8888);
			System.out.println("等待用户加入");
			socket=server.accept();
			System.out.println("用户加入");
			JFileChooser jfc=new JFileChooser();
    		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    		jfc.showDialog(new JLabel(), "保存");
    		file1=jfc.getSelectedFile();
    		in=new DataInputStream(socket.getInputStream());
    		path=file1.toString()+"\\"+in.readUTF();
    		file2=new File(path);
    		out=new DataOutputStream(new FileOutputStream(file2));
    		byte read[]=new byte[8888];
    		while(true)
    		{
    			System.out.println("文件传输");
    			int w=0;
    			if(in!=null)
    			{
    				w=in.read(read);
    			}
    			if(w==-1)
    			{
    				break;
    			}
    			out.write(read,0,w);
    			out.flush();
    		}
    		out.close();
    		in.close();
    		server.close();
    		socket.close();
    		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 
}
