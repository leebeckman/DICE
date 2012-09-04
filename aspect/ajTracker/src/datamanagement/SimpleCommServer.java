package datamanagement;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleCommServer implements Runnable {

	public void run() {
		try {
			ServerSocket welcomeSocket = new ServerSocket(6789);
	
			while (true) {
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(
						connectionSocket.getOutputStream());
				while (true) {
					String input = inFromClient.readLine();
					outToClient.writeBytes("Got input: " + input + "\n");
					if (input.startsWith("enabletracking")) {
						outToClient.writeBytes("Enabling tracking\n");
						SimpleCommControl.getInstance().enableTracking();
						continue;
					}
					else if (input.startsWith("enablentr")) {
						outToClient.writeBytes("Enabling NTR\n");
						SimpleCommControl.getInstance().enableNTR();
						continue;
					}
					else if (input.startsWith("disabletracking")) {
						outToClient.writeBytes("Disabling tracking\n");
						SimpleCommControl.getInstance().disableTracking();
						continue;
					}
					else if (input.startsWith("disablentr")) {
						outToClient.writeBytes("Disabling NTR\n");
						SimpleCommControl.getInstance().disableNTR();
						continue;
					}
					else if (input.startsWith("enable")) {
						outToClient.writeBytes("Enabling all\n");
						SimpleCommControl.getInstance().enableTracking();
						SimpleCommControl.getInstance().enableNTR();
						continue;
					}
					else if (input.startsWith("setaddrA")) {
						outToClient.writeBytes("Forcing Remote Addr A\n");
						SimpleCommControl.getInstance().setForcedRemoteAddr("192.168.0.1");
						continue;
					}
					else if (input.startsWith("setaddrB")) {
						outToClient.writeBytes("Forcing Remote Addr B\n");
						SimpleCommControl.getInstance().setForcedRemoteAddr("192.168.0.2");
						continue;
					}
				}
			}
		} catch (Exception e) {
			
		}

	}

}
