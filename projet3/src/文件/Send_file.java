package ÎÄ¼þ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Send_file implements ActionListener{
	String ip;
	
	public void get_ip(String ip)
	{
		this.ip=ip;
	}


	public void actionPerformed(ActionEvent arg0) {
		Socket socket;
		DataOutputStream out;
		DataInputStream in;
		File file;
		Recive_thread t=new Recive_thread();
		t.start1();
		try {
			JFileChooser jfc=new JFileChooser();
    		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
    		jfc.showDialog(new JLabel(), "Ñ¡Ôñ");
    		file=jfc.getSelectedFile();
			socket=new Socket(ip,8888);
			in=new DataInputStream(new FileInputStream(file));
			out=new DataOutputStream(socket.getOutputStream());
			out.writeUTF(file.getName());
			byte read[]=new byte[8888];
			while(true)
			{
				int r=0;
				if(in!=null)
				{
					r=in.read(read);
				}
				if(r==-1)
				{
					break;
				}
				out.write(read,0,r);
				out.flush();
			}
			
			in.close();
			out.close();
			socket.close();
			
			
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
