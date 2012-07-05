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
					if (input.startsWith("enablentr")) {
						outToClient.writeBytes("Enabling NTR\n");
						SimpleCommControl.getInstance().enableNTR();
						continue;
					}
					if (input.startsWith("disabletracking")) {
						outToClient.writeBytes("Disabling tracking\n");
						SimpleCommControl.getInstance().disableTracking();
						continue;
					}
					if (input.startsWith("disablentr")) {
						outToClient.writeBytes("Disabling NTR\n");
						SimpleCommControl.getInstance().disableNTR();
						continue;
					}
					if (input.startsWith("enable")) {
						outToClient.writeBytes("Enabling all\n");
						SimpleCommControl.getInstance().enableTracking();
						SimpleCommControl.getInstance().enableNTR();
						continue;
					}
				}
			}
		} catch (Exception e) {
			
		}

	}

}
