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
				String input = inFromClient.readLine();
				outToClient.writeBytes("Got input: " + input + "\n");
				if (input.startsWith("enable")) {
					outToClient.writeBytes("Enabling tracking");
					SimpleCommControl.getInstance().enableTracking();
					break;
				}
			}
		} catch (Exception e) {
			
		}

	}

}
