package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.InfoDTO;
import model.InfoDTO.Chat;
import model.InfoDTO.Info;
import view.Manager;
import view.ManagerChatClient;

public class ManagerChatClientController implements ActionListener, Runnable {

	ManagerChatClient managerChatClient;
	
	
	public ManagerChatClientController(ManagerChatClient managerChatClient) {
		this.managerChatClient = managerChatClient;
	}
	
	public void service()
	{
		
		String serverIP = "127.0.0.1";
		
		try
		{
			managerChatClient.socket = new Socket(serverIP, 9300);
			
			managerChatClient.oos = new ObjectOutputStream(managerChatClient.socket.getOutputStream());
			managerChatClient.ois = new ObjectInputStream(managerChatClient.socket.getInputStream());
			
			InfoDTO dto = new InfoDTO();
			
			dto.setChat(Chat.LOGIN);
			dto.setInfo(Info.MANAGER);
			dto.setLoginId("Manager" + managerChatClient.csLoginId);
			dto.setCsLoginId(managerChatClient.csLoginId);
			
			managerChatClient.oos.writeObject(dto);
			managerChatClient.oos.flush();
		}
		catch (UnknownHostException e)
		{
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		}
		catch (IOException e) 
		{
			System.out.println("서버를 연결이 안되었습니다");
			e.printStackTrace();
			System.exit(0);
		}
		managerChatClient.send.addActionListener(this);
		managerChatClient.input.addActionListener(this);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() 
	{
		InfoDTO dto = null;
		
		while(true)
		{
			try
			{
				dto = (InfoDTO)managerChatClient.ois.readObject();
				
				if(dto.getInfo() == Info.COSTOMER)
				{
					try
					{
						if(dto.getCsLoginId().equals("Manager" + managerChatClient.csLoginId))
						{
							if(dto.getChat() == Chat.LOGIN)
							{
								managerChatClient.output.append(dto.getLoginId() + "님이 입장하였습니다.\n");
								
								int pos = managerChatClient.output.getText().length();
								managerChatClient.output.setCaretPosition(pos);
							}
							else if(dto.getChat() == Chat.SEND)
							{
								managerChatClient.output.append("[" + dto.getLoginId() + "] : " + dto.getMessage() + "\n");
								
								int pos = managerChatClient.output.getText().length();
								managerChatClient.output.setCaretPosition(pos);
							}
						}
					}
					catch (NullPointerException e33) 
					{
						e33.printStackTrace();
					}
				}
				else if(dto.getInfo() == Info.MANAGER)
				{
					System.out.println(dto.getInfo() + "\n" + dto.getCsLoginId() + "\n" + dto.getLoginId());
					if(dto.getCsLoginId().equals(managerChatClient.csLoginId))
					{
						if(dto.getChat() == Chat.EXIT)
						{
							managerChatClient.ois.close();
							managerChatClient.oos.close();
							managerChatClient.socket.close();
							
							new Manager().event();
							managerChatClient.setVisible(false);
							//dispose();
							break;
							//System.exit(0);
						}
					}
				}
			}
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String line = managerChatClient.input.getText();
		managerChatClient.output.append("[Manager" + managerChatClient.csLoginId + "] : " + line + "\n");
		InfoDTO dto = new InfoDTO();
		
		if(e.getSource() == managerChatClient.send)
		{
			dto.setChat(Chat.SEND);
			dto.setMessage(line);
			dto.setCsLoginId(managerChatClient.csLoginId);
			dto.setLoginId("Manager" + managerChatClient.csLoginId);
			dto.setInfo(Info.MANAGER);
		}
		
		try 
		{
			managerChatClient.oos.writeObject(dto);
			managerChatClient.oos.flush();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		managerChatClient.input.setText("");
	}
}
