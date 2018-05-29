package com.carson.client;

import java.util.*;
import java.util.concurrent.*;

public class ClientConsole {

	private boolean mining;
	private String publicKey;
	
	public ClientConsole(String publicKey) {
		this.publicKey = publicKey;
	}
	
	public void run() {
		ConsoleInput input = new ConsoleInput();
		ExecutorService executer = Executors.newFixedThreadPool(1);
		executer.submit(input);
		
		while(true) {
			boolean ran = false;
			
			while(input.nextInputExists()) {//this is where we run stuff. should probably use methods tbh
				ran = true;
				String str = input.getNextInput();
				switch(str) {
					case "stop mining":
						mining = false;
						break;
					case "start mining":
						mining = true;
						break;
					case "transaction":
						//do stuff
						break;
					case "help":
						
						
						break;
					case "exit":
					case "stop":
						System.out.println("\nExiting the miner");
						System.out.println("");
					
					
				}
			}
			if(ran)System.out.print(">");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}

	}
	
	
	
	public void printHelp() {
		System.out.println("GumboCoin terminal might be confusing at first, so here's a guild to using it:\n");
		System.out.println("+--------------------------+--------------------------------------------+");
		System.out.println("|Commands you can use      |        discription                         |");
		System.out.println("+--------------------------+--------------------------------------------+");
		System.out.println("|stop mining               |        stops the miner from mining blocks  |");
		System.out.println("|start mining              |        starts the miner                    |");
		System.out.println("|transaction               |        pay someone something               |");
		System.out.println("|exit                      |        stops the program                   |");
		System.out.println("|stop                      |        same as exit                        |");
		System.out.println("+--------------------------+--------------------------------------------+");

		
		System.out.println(
				"\nthe GumboCoin terminal is build differently then other terminals\n"
				+ "Commands entered will only be ran after the miner mines a block\n"
				+ "this is to avoid concurrency problems\n"
				+ "Try and avoid entering multiple commands\n"
				+ "this will screw up stuff\n"
				+ "\nusing gumbocoin involves making an identity, or an account\n"
				+ "accounts only consist of two values: a public key and a private key\n"
				+ "the public key is used to identify you, and the private key is used to verify \"signing in\"\n"
				+ "KEEP YOUR PRIVATE KEY PRIVATE AT ALL TIMES. It can NOT be changed\n"
				+ "private and public keys are 10-character-long randomly generated strings\n"
				+ "AGAIN, YOUR PRIVATE AND PUBLIC KEY CAN NOT BE CHANGED\n"
				+ "\nyou will need to store your keys somewhere. If you lose them, you lose your account, and therefor your balance of GumboCoin(GC from here on out)"
				+ "\n");
		System.out.println("When you first join you will need to register to the server. If you need to sign into an account or make a new one --needs to be added-- contact Carson\n\n"
				+ "You will need your public key when you:\n"
				+ "\t-Sign in to the console (this program)\n"
				+ "\t-have someone else pay you\n"
				+ "\t-get support\n"
				+ "you will need your private key when you:\n"
				+ "\t-sign in to the console\n"
				+ "\t-pay someone else\n"
				);
		
		System.out.println("for more information, contact Carson at AwesomeCarson123#5069, carson42g@gmail.com\n"
				+ "enjoy using GumboCoin!\n\n\n");

	}
	
	
}



class ConsoleInput implements Runnable{
	
	private List<String> strs;
	private Scanner scan;
	
	public ConsoleInput() {
		strs = new ArrayList<>();
	}

	@Override
	public void run() {
		scan = new Scanner(System.in);
		while(true) {
			System.out.print(">");
			strs.add(scan.nextLine());
		}
	}
	
	
	public String getNextInput() {
		if(strs.size() == 0)return null;
		return strs.remove(0);
	}
	public boolean nextInputExists() {
		return (strs.size() == 0) ?  false : true;
	}
	
	public void close() {
		scan.close();
	}
	
}