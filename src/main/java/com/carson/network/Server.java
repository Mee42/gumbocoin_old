package com.carson.network;

import java.net.*;

import com.carson.other.BlockChainManager;
import com.carson.other.ObjectToJson;
import com.carson.other.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Server {
	private ServerSocket serverSocket;
    private boolean stop = false;
    private BlockChainManager manager;
    
    
    
    public Server(BlockChainManager manager) {
		super();
		this.manager = manager;
	}


	public void start(int port) throws Exception {
    	System.out.println("server: starting server");
        serverSocket = new ServerSocket(port);
    
        while(!stop) {
        	ServerHandle serverHandle = new ServerHandle(serverSocket.accept()); //accsepts an incoming client
        	System.out.println("\n\nstarting connection------------------------");

        	System.out.println("S\n.\n.\n.\n.\n.starting connection------------------------");
        	String input = serverHandle.getInput();
        	serverHandle.sendData("got");
        	System.out.println("got input:" + input);
        	GsonBuilder builder = new GsonBuilder();
    		Gson gson = builder.create();
        	switch(input) {
	        	case "@get-data":
	        		System.out.println("got a data request. sending it");
	        		serverHandle.sendData(getStringPayload());
	        		break;
	        	
	        		
	        	case "@new-user":
	        		System.out.println("awaiting new data");
	        		String newUserDataStr = serverHandle.getInput();
	        		
//	        		serverHandle.sendData("got it");
//	        		
	        		System.out.println("got new data:" + newUserDataStr);
	        		
	        		if(newUserDataStr == null) {
	        			System.err.println("data is null");
	        			return;
	        		}
	        		UserPayload userData = gson.fromJson(newUserDataStr, UserPayload.class);
	        		manager.addUser(userData);
	        		manager.printUsers();
	        		manager.printTransactions();
	        		break;
	        	case "@transaction":
	        		String nextImport = serverHandle.getInput();
	        		Transaction t= gson.fromJson(nextImport, Transaction.class);
	        		serverHandle.sendData(manager.addTransactionString(t));
	        		break;
	        	
	        	default://if this happens then it must be an upload
	        		System.out.println("=========attempting to import");
	        		SyncPayload sync = ObjectToJson.stringToSyncPayload(input);
	        		System.out.println("=========printing got chain:");
	        		sync.chain.printChainHashes();
	        		System.out.println("=========checking chain");
	        		sync.chain.printIsChainValid();
	        		System.out.println("=========hashing all blocks");
	        		sync.chain.hashAllBlocks();
	        		System.out.println("=========checking chain");
	        		sync.chain.printIsChainValid();
	        		System.out.println("=========sending request sync");
	        		String result = manager.requestSync(sync);
	        		System.out.println("result: " + result);
	        		serverHandle.sendData(result);

	        		
        	}
        	
        	
        	//updates stuff
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
    
   }
    
    
    

	public String getStringPayload() {
		return ObjectToJson.dataPayloadToString(manager.getDataPayload());
	}
    
}
