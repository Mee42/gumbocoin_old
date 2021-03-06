package com.carson;

import java.net.*;
import java.io.*;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.carson.client.ClientConsole;
import com.carson.network.*;
import com.carson.other.*;
import com.google.gson.*;


@SuppressWarnings("unused")
public class Main {

	
	/*
	 * communications between server and client:
	 * 
	 * one: client -> server | for requesting sync
	 * three: client <- server | for checking / getting updates @get-data
	 * 
	 * idea: wrap communications into the Converstation object, which makes clients and polls the server
	 * all methods are static
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
		
	public static void main(String[] args) throws Exception{

		Logo.printLogo();
		System.out.print("GUMBOCOIN version: 0-0-0 made by Carson");
//		for(int i = 0;i<5;i++){
//			Thread.sleep(100);
//			System.out.print(".");
//		}
		System.out.println("\nstarting!\n\n");
		
//		
//		new ClientConsole("public key").prifatHelp();
//		
//		System.exit(0);
		
		boolean server = false;
		
		
		String publicKey1 = "key--publicKey1";
		String privateKey1 = "key--privateKey1";
		String publicKey2 = "key--publicKey2";
		String privateKey2 = "key--privateKey2";
		
		
		
		if(server) {
			BlockChainManager manager = new BlockChainManager();
			new Server(manager).start(1111);
			System.exit(0);
		}
		
		Conversation.summitUser(new UserPayload(publicKey1,privateKey1));
		
		
//		UserPayload user1 = new UserPayload(publicKey1, privateKey1);
//		UserPayload user2 = new UserPayload(publicKey2, privateKey2);
//		
//		Conversation.summitUser(user1);
		
//		
//		Transaction t = new Transaction(BlockClient.hashKeys(publicKey1, privateKey1),publicKey1,publicKey2);
//		t.sign(privateKey1);
//		t.setAmount(10);
		
//		manager.addTransaction(t);

		
		
		
		
		
		
		
		
//		for(int i = 0;i<5;i++) {
//			Thread.sleep(1000);
//			System.out.println("\n\n\nstarting loop");
//			DataPayload data = Conversation.getDataPayload();
//			System.out.println("=========\\/data from server:" + ObjectToJson.dataPayloadToString(data));
//			data.blockchain.printChainHashes();
//			System.out.println("received data");
//			BlockChain chain;
//			chain = data.blockchain.copy();
//			System.out.println("========hashing data");
//			chain.hashAllBlocks();
//			System.out.println("========mining");
//			chain.mine(data);
//			System.out.println("=========\\/total chain:" + ObjectToJson.dataPayloadToString(new DataPayload(chain,new BlockChainDataContainer())));//just to print the chain
//			chain.printChainHashes();
//			System.out.println(Conversation.requestSync(new SyncPayload(chain, (i%2 == 0 ? publicKey1 : publicKey2))));
//		}
//		
		
		
		
		
		

		for(int i = 0;i<5;i++) {
			Thread.sleep(1000);
			System.out.println("\n\n\nstarting loop");
			DataPayload data = Conversation.getDataPayload();
			System.out.println("=========\\/data from server:" + ObjectToJson.dataPayloadToString(data));
			data.blockchain.printChainHashes();
			System.out.println("received data");
			BlockChain chain;
			chain = data.blockchain.copy();
			System.out.println("========hashing data");
			chain.hashAllBlocks();
			System.out.println("========mining");
			chain.mine(data);
			System.out.println("=========\\/total chain:" + ObjectToJson.dataPayloadToString(new DataPayload(chain,new BlockChainDataContainer())));//just to print the chain
			chain.printChainHashes();
			System.out.println(Conversation.requestSync(new SyncPayload(chain, (i%2 == 0 ? publicKey1 : publicKey2))));
		}
}
//		
		
			
//		final boolean runServer = true;
//		final boolean runServer = false;
//		Scanner scan = new Scanner(System.in);
//		
//		if(runServer) {
//			scan.close();
//			Server server = new Server();
//			server.start(1111);
//			
//		}else {
//					    
//		}
//		
//		scan.close();
		
		
		//server
		
		
		
		
		
		
//		
		
//		
//		
//		ExecutorService executer = Executors.newFixedThreadPool(10);
//		
//		
//
//			
//		manager.addUser(publicKey1, privateKey1);
//		manager.addUser(publicKey2, privateKey2);
//		
//		
//		MinerThread miner = manager.getClient(publicKey1).buildMiner(privateKey1);
//		MinerThread miner2 = manager.getClient(publicKey2).buildMiner(privateKey2);
//		
//		miner.sign(privateKey1);
//		miner2.sign(privateKey2);
//		
//		
//		MiningThread thread1 = new MiningThread(manager.getDataPayload(),miner);
//		MiningThread thread2 = new MiningThread(manager.getDataPayload(),miner2);

//		manager.registerMinerManager(thread1);
		
		
//		manager.addTransactions(publicKey1, privateKey1, publicKey2,5);
//		manager.addTransactions(publicKey2, privateKey2, publicKey1,7);
//		
//
		
		
//		
//		GsonBuilder builder = new GsonBuilder();
//		builder.setPrettyPrinting();
//		
//		Gson gson = builder.create();
//
//		DataPayload data = manager.getDataPayload();
//		String test = gson.toJson(data);
//		
//		DataPayload result = gson.fromJson(test, DataPayload.class);
//		
//		System.out.println(test);
//		
//		manager.printTransactions();
	
//		manager.registerMinerManager(thread2);
//		
//		Thread.sleep(1000);
//		
//		executer.submit(thread1);
//		executer.submit(thread2);
//		
//		Thread.sleep(10000);
//	
//		thread1.stop();
//		thread2.stop();
//		manager.printTransactions();
//		System.out.println(SyncObject.getRejectedBlocks());

	

}



	


/*
 * what to do next
 * create an object that calc's the amount of GC for a public key
 * use a internal class,
 * class user{
 * 
 * public String publicKey;
 * public int amount;
 * 
 * }
 * 
 * and then simply loop through each transaction and make the changes to the users
 * you can also past a List<String> publicKeys and it will return an List<Integer> in the same order as before.
 * you should also be able to the the raw user data from this, so write that first and then build the methods that return int last
 * then write something to validate transactions
 * and.... yeah.
 * thats it
 * 
<<<<<<< HEAD
=======
 * ALSO
 * create a github for this
>>>>>>> 2fc83994fe431beb4e2f367758ee001829fba536
 */







