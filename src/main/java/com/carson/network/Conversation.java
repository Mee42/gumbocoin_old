package com.carson.network;



import java.io.*;
import java.net.*;

import com.carson.other.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Conversation {

	
	
	
	
	public static void summitUser(UserPayload user) {
		ClientHandler handle = new ClientHandler();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String data = gson.toJson(user);
		handle.startConnection("localhost", 1111);
		handle.sendMessage("@new-user");
		handle.sendMessage(data);

	}
	
	public static String requestSync(SyncPayload sync) {
		ClientHandler handle = new ClientHandler();
		handle.startConnection("localhost", 1111);
		String got = handle.sendMessage(ObjectToJson.syncPayloadToString(sync));
		return got;
	}


	public static DataPayload getDataPayload() {
		ClientHandler handle = new ClientHandler();
		handle.startConnection("localhost", 1111);
		String got = handle.sendMessage("@get-data");
		return ObjectToJson.stringToDataPayload(got);
	}
	
}

class ClientHandler {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void startConnection(String ip, int port) {
        try {
			clientSocket = new Socket(ip, port);
	        out = new PrintWriter(clientSocket.getOutputStream());
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
			e.printStackTrace();
		} 
    }
 
    public String sendMessage(String msg) {
    	try {
    		sendMessageDontWait(msg);
	        String resp = in.readLine();
	        return resp;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "";
    	}
    }
    public void sendMessageDontWait(String msg) {
        System.out.println("\tsending:" + msg);
		out.println(msg);
		System.out.println("\tsent");
    }
 
    public void stopConnection() {
        try {
			in.close();
	        out.close();
	        clientSocket.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}