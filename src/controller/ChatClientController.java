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
import view.ChatClient;
import view.RoomChoice;

public class ChatClientController implements ActionListener, Runnable {

	ChatClient chatClient;
	public ChatClientController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	public void service()
	{
		String serverIP = "127.0.0.1";
		
		try
		{
			chatClient.socket = new Socket(serverIP, 9300);
			
			chatClient.ois = new ObjectInputStream(chatClient.socket.getInputStream());
			chatClient.oos = new ObjectOutputStream(chatClient.socket.getOutputStream());
			
			InfoDTO dto = new InfoDTO();
			dto.setChat(Chat.LOGIN);
			dto.setLoginId(chatClient.loginId);
			dto.setCsLoginId("Manager" + chatClient.loginId);
			dto.setInfo(Info.COSTOMER);
			
			chatClient.oos.writeObject(dto);
			chatClient.oos.flush();
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
		chatClient.send.addActionListener(this);
		chatClient.input.addActionListener(this);
		
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
				dto = (InfoDTO)chatClient.ois.readObject();
				
				if(dto.getInfo() == Info.MANAGER)
				{
					if(dto.getCsLoginId().equals(chatClient.loginId))
					{
						if(dto.getChat() == Chat.LOGIN)
						{
							chatClient.output.append(dto.getLoginId() + "님이 입장하셨습니다.\n");
						
							int pos = chatClient.output.getText().length();
							chatClient.output.setCaretPosition(pos);
						}
						else if(dto.getChat() == Chat.SEND)
						{
							chatClient.output.append("[" + dto.getLoginId() + "] : " + dto.getMessage() + "\n");
							
							int pos = chatClient.output.getText().length();
							chatClient.output.setCaretPosition(pos);
						}
					}
				}
				else if(dto.getInfo() == Info.COSTOMER)
				{
					if(dto.getLoginId().equals(chatClient.loginId))
					{
						if(dto.getChat() == Chat.EXIT)
						{
							chatClient.ois.close();
							chatClient.oos.close();
							chatClient.socket.close();
							
							new RoomChoice(chatClient.loginId, chatClient.name, chatClient.tel);
							chatClient.setVisible(false);
							break;
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
		String line = chatClient.input.getText();	
		chatClient.output.append("[" + chatClient.loginId + "] : " + line + "\n");
		InfoDTO dto = new InfoDTO();
		
		if(e.getSource() == chatClient.send)
		{
			dto.setChat(Chat.SEND);
			dto.setMessage(line);
			dto.setCsLoginId("Manager" + chatClient.loginId);
			dto.setLoginId(chatClient.loginId);
			dto.setInfo(Info.COSTOMER);
		}
		
		try
		{
			chatClient.oos.writeObject(dto);
			chatClient.oos.flush();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		chatClient.input.setText("");
	}
}
