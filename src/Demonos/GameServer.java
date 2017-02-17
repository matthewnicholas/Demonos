package Demonos;

import java.net.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.regex.*;

public class GameServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	class Server extends Thread {
		boolean serverRun;
		private ArrayList<Client> clientList = new ArrayList<Client>();
		
		Server() throws Exception{
			serverRun = true;
		}
		
		public void run() {
			try {
			this.startServer();
			}catch(Exception e) {}
		}
		
		public void startServer() throws Exception{
			DatagramSocket serverSocket = new DatagramSocket(6666);
			byte[] receive = new byte[1024];
			byte[] send = new byte[1024];
			while(this.serverRun) {
				DatagramPacket recPacket = new DatagramPacket(receive, receive.length);
				serverSocket.receive(recPacket);
				String data = new String(recPacket.getData());
				
				//connect request
				if(data.equals("!!CONNECT##")) {
					this.clientList.add(new Client(recPacket.getAddress()));
					Timestamp ts = new Timestamp(System.currentTimeMillis());
					System.out.println(ts + " ====> Recieved connect request from " + recPacket.getAddress());
					send = "##HANDSHAKE!!".getBytes();
					DatagramPacket sndPacket = new DatagramPacket(send, send.length, recPacket.getAddress(), recPacket.getPort());
					serverSocket.send(sndPacket);
				}
				
				//handshake request
				if(data.equals("!!HANDSHAKE##")) {
					for (Client c : this.clientList) {
						if (c.getIp().equals(recPacket.getAddress())) {
							c.handshakeSucess();
							send = "##NAME!!".getBytes();
							DatagramPacket sndPacket = new DatagramPacket(send, send.length, recPacket.getAddress(), recPacket.getPort());
							serverSocket.send(sndPacket);
						}
					}
				}
				
				//name request
				Pattern r = Pattern.compile("!!NAME.(.*).##");
				Matcher m = r.matcher(data);
				if(m.find()) {
					String name[] = data.split(".");
					for(Client c : this.clientList) {
						if (c.getIp().equals(recPacket.getAddress())) {
							c.setName(name[1]);
							send = "##READY!!".getBytes();
							DatagramPacket sndPacket = new DatagramPacket(send, send.length, recPacket.getAddress(), recPacket.getPort());
							serverSocket.send(sndPacket);
						}
					}
				}
			}
		}
		
		public void stopServer() {
			this.serverRun = false;
		}
		
	}

}

class Client{
	private InetAddress ip;
	private String name;
	private boolean handShake;
	
	Client(InetAddress ip){
		this.ip = ip;
		this.handShake = false;
	}
	
	public InetAddress getIp(){
		return this.ip;
	}
	
	public void handshakeSucess() {
		this.handShake = true;
	}
	
	public boolean connected() {
		if(this.ip != null && this.handShake) 
			return true;
		
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}