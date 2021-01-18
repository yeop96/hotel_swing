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

import controller.ManagerChatClientController;
import model.InfoDTO;
import model.InfoDTO.Chat;
import model.InfoDTO.Info;

public class ManagerChatClient extends JFrame 
{
	private static final long serialVersionUID = 1L;
	public JTextField input;
	public JTextArea output;
	public JButton send;
	public Socket socket;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public String csLoginId;
	
	public ManagerChatClientController managerChatClientController;

	public ManagerChatClient(String csLoginId)
	{
		this.csLoginId = csLoginId;
		
		managerChatClientController = new ManagerChatClientController(this);
		
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

		setTitle(csLoginId);
		setBounds(100, 80, 300, 300);
		setVisible(true);

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if(oos == null || ois == null)
				{
					System.exit(0);
				}
				InfoDTO dto = new InfoDTO();
				
				dto.setChat(Chat.EXIT);
				dto.setInfo(Info.MANAGER);
				dto.setLoginId("Manager" + csLoginId);
				dto.setCsLoginId(csLoginId);
				
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