package com.carson.network;
import java.io.*;
import java.net.*;

public class ServerHandle extends Thread {
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public ServerHandle(Socket socket) {
        this.clientSocket = socket;
        try {
			out = new PrintWriter(clientSocket.getOutputStream());
	        in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public String getInput()  {
        try {
	        String inputLine = in.readLine();
	        System.out.println("\t    got:" +inputLine);
	        return inputLine;
        } catch (IOException e) {
			e.printStackTrace();
        }
        System.err.println("no input found");
        return null;
    }
    
    public void sendData(String data) {
    	System.out.println("\tsending:" + data);
		out.println(data);
    }
    
    public void close(){
	     out.close();
    }
    
}
