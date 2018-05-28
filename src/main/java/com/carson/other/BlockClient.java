package com.carson.other;

import com.carson.exceptions.IllegalChainException;
import com.carson.network.Conversation;

public class BlockClient {
	
	private String publicKey;
	private String sig;
	
	
	
	
	private BlockChainManager manager;
	
	public BlockClient(String publicKey, String sig, BlockChainManager manager) {
		this.publicKey = publicKey;
		this.sig = sig;
		this.manager = manager;
	}
		
	
	public String getSig() {
		return sig;
	}

	public static String hashKeys(String publicKey, String privateKey) { 
		return Hasher.hash(Hasher.hash(publicKey  + privateKey));
	}

//// 	public boolean checkLogin(String privateKey) {
//		return verify(privateKey);
//	}

	public MinerThread buildMiner(String privateKey) throws Exception{
		if(!verify(privateKey)) {System.err.println("could not log in");return null;}
		
		BlockChain chain = manager.getOfficalBlockChain().copy();
		boolean copyVerifyed = chain.printIsChainValid();
		
		if(!copyVerifyed){
			throw new IllegalChainException();
		}
		
		
		MinerThread miner = new MinerThread(Conversation.getDataPayload()).claim(publicKey, privateKey); //loads the payload. 
//		miner.run(); //starts the mining process. should be kept externally for now
		return miner;
	}

	//if(!verify(privateID)) {return false;}
	private boolean verify(String privateKey) {
		if(hashKeys(publicKey, privateKey).equals(sig)) {
			return true;
		}else {
			return false;
		}
	}

	
	public String getPublicKey() {
		return publicKey;
	}
	
	
	
	

}
