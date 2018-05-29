package com.carson.other;

import java.util.ArrayList;
import java.util.List;

import com.carson.network.SyncPayload;
import com.carson.network.UserPayload;


public class BlockChainManager {
	public static int PRIZE = 1;
	public static int DIFFICULTY = 4;
	public static boolean PRINT_REJECTED = false;
	private BlockChain officalBlockChain;
	private List<UserPayload> users;
	
	public List<MiningThread> miners = new ArrayList<>();
	
	BlockChainDataContainer waitingData = new BlockChainDataContainer();
	
	public BlockChainManager() {
		users = new ArrayList<>();
		officalBlockChain = new BlockChain();
		officalBlockChain.makeGensisBlock();
	}
	
	public boolean addUser(UserPayload p) {
		if(exists(p.publicKey)) {
			System.err.println("user exists");
			return false;
		}
		
		users.add(p);
		return true;
	}
	
	public void printUsers() {
		for(UserPayload user : users) {
			System.out.println("sig:" + user.getSig() + "   public key: " + user.getPublicKey());
		}
	}
	
	

	public BlockChain getOfficalBlockChain() {
		return officalBlockChain.copy();
	}

	public String requestSync(SyncPayload payload) {
		System.out.println("startings requestSynx");
		BlockChain chain = payload.chain.copy();
		String publicKey = payload.publicKey;
		
		if(!chain.printIsChainValid()) {
			return "Chain was not valid";
		}
		
		if(!chain.getBlocks().get(chain.getBlocks().size() - 1).getData().equals(waitingData)) {
			System.out.println("rejected due to invalid transactions");
			return "Transactions were rejected";
		}
		
		if(!(chain.blockSize() > officalBlockChain.blockSize())) {
			return "you didn't mine enough";
		}
			
			officalBlockChain = chain.copy(); 
			if(!officalBlockChain.isChainValid()) {
				System.err.println("copyed chain is corrupted");
				try {
					return "could not copy blockchain. server shutting down";
				}finally{
					System.exit(9);
				}
			}
			
		
		
		System.out.println("synced sucsesfully");
		
		//clears waitingdata
		waitingData = new BlockChainDataContainer();
				
		//creates the payment object and adds it to the waiting data
		waitingData.addPayment(new BlockChainPayment(publicKey));
			
		return "worked! you got paid!";
	}

	public UserPayload getUser(String publicKey) {
		for(UserPayload user : users) {
			if(user.getPublicKey().equals(publicKey)) {
				return user;
			}
		}
		return null;
	}
	public boolean exists(String publicKey) {
		for(UserPayload user : users) {
			if(user.getPublicKey().equals(publicKey)) {
				return true;
			}
		}
		return false;
	}
	
	public void addTransactions(String publicKey1, String privateKey1, String publicKey2,int ammount) { // FOR TESTING FOR NOW
		
		
		
		Transaction t = new Transaction(
				getUser(publicKey1).getSig(), //gets the signeture of publicKey1
				publicKey1, //sender public key
				publicKey2 //revicer
				);
		
		t.sign(privateKey1);
		t.setAmount(ammount);
				
		addTransaction(t);
		
		
	}
	
	public boolean addTransaction(Transaction t) {
		return addTransactionString(t).equals("worked");
	}
	
	public String addTransactionString(Transaction t) {
		if((!exists(t.getPublicKey1())) || (!exists(t.getPublicKey2()))){
			return "user does not exist";
			
		}
		if(!t.getSig().equals(getUser(t.getPublicKey1()).getSig())){
			return "signiture is wrong";
		}
		if(!t.signed()) {
			return "not signed";
		}
		if(t.getTransactionAmount() == 0) {
			return "transaction was zero";
		}
		
		waitingData.addTransaction(t);
		
		
		
		return "worked";
		
		
		
		
		
		
		
	}
	
	
	
	public BlockChainDataContainer getWaitingData() {
		return waitingData;
	}
	
	public void registerMinerManager(MiningThread thread1) {
		miners.add(thread1);
	}
	
	public DataPayload getDataPayload() {
		return new DataPayload(getOfficalBlockChain(), getWaitingData());
	}
	
	

	
	
	public void printTransactions() {
		System.out.println("transactions\n====================");
		for(Block b : getOfficalBlockChain().getBlocks()) {
			for(Transaction datapoint : b.getData().getTransactionData()) {
				System.out.println(datapoint.toString());
			}
			for(BlockChainPayment datapoint : b.getData().getPaymentData()) {
				System.out.println(datapoint.toString());
			}

			
		}
	}
	
	
	
	
	
	
	public static BenchResult bench(BlockChain chain, int blocks){
		
		
		
		BenchResult b = new BenchResult(blocks, DIFFICULTY);
		chain.setDifficulty(DIFFICULTY);
		chain.clearBlocks();
		
		for(int i = 0;i<blocks;i++) {
			chain.mine();
		}
		b.stop(chain.getBiggestHash());
		return b;
	}

		
	
	
	
	
	
}



