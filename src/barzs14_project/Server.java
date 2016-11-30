package barzs14_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final int port = 19991;
	
	public static void main(String[] args) {		
		try {
			ServerSocket socket = new ServerSocket(port);
			while (true) {
				Socket client = socket.accept();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
