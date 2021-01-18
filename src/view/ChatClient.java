package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import controller.ChatClientController;
import model.InfoDTO;
import model.InfoDTO.Chat;
import model.InfoDTO.Info;



public class ChatClient extends JFrame 
{
	private static final long serialVersionUID = 1L;
	public JTextField input;
	public JTextArea output;
	public JButton send;
	public Socket socket;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public String loginId;
	public String name;
	public String tel;
	
	public ChatClientController chatClientController;
	
	public ChatClient(String loginId, String name, String tel)
	{
		this.loginId = loginId;
		
		chatClientController = new ChatClientController(this);
		
		System.out.println("aaa="+loginId);
		System.out.println("this = " + this);
		
		input = new JTextField(15);
		output = new JTextArea();
		send = new JButton("보내기");
		
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel p = new JPanel(new BorderLayout());
		
		output.setEditable(false);
		p.add("Center", input);
		p.add("East", send);
		
		Container c = this.getContentPane();
		
		c.add("Center", scroll);
		c.add("South", p);
		
		setTitle(loginId);
		setBounds(100, 80, 300, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if(ois == null || ois == null)
				{
					System.exit(0);
				}
				InfoDTO dto = new InfoDTO();
				
				dto.setLoginId(loginId);
				dto.setChat(Chat.EXIT);
				dto.setInfo(Info.COSTOMER);
					
				try 
				{
					oos.writeObject(dto);
					oos.flush();
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
	}
	
	
}