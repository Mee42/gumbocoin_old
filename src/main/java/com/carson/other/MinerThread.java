package com.carson.other;

import com.carson.network.Conversation;
import com.carson.network.SyncPayload;

public class MinerThread{//TODO just all needs to be re written
	
	private Block block;
	private boolean mined;
	
	private String sig;
	private String publicKey;
	
	private Block importedBlock;
	
	private boolean signed;
	
	private boolean changed = false;
	
	
	public MinerThread(DataPayload data) {
		super();
//		updateData(data); //outdated
		block = new Block(data.blockchain.getLastBlock(),data.data);
		signed = false;
		mined = false;
	}
	
	
		
	public MinerThread claim(String publicKey, String privateKey) {

		this.publicKey = publicKey;
		this.sig = BlockClient.hashKeys(publicKey, privateKey);
		return this;
	}
	
	public MinerThread sign(String privateKey) {
		if(this.sig.equals(BlockClient.hashKeys(publicKey,privateKey))) {
			signed = true;
			return this;
		}
		System.err.println("incorrect private key");
		return this;
				
		
	}
	
	public void unsign() {
		signed = false;
	}
	
	public void run() {
		System.out.println("starting to mine block for " + publicKey);
		if(!signed) {System.err.println("miner not signed, trying to mine");return;}
		
		String hashed;
		StringBuilder strB = new StringBuilder();
		
		for(int i = 0;i<(BlockChainManager.DIFFICULTY % 64);i++) {
			strB.append("0");	
		}
		
		
		String str = strB.toString();
		
		
			block.randomNonce();
		while(true) {
			if(changed) {
				System.out.println("changed imported for " + publicKey);
				block = importedBlock;
				changed = false;
				block.randomNonce();
			}else {
//				block.setNonce(block.getNonce() +1);
//				
				block.hash();
				
							
				if(block.getHash().substring(0, (BlockChainManager.DIFFICULTY % 64)).equals(str)) {
					System.out.println("\taccepted hash! nonce:" + block.getNonce() + " hash:" + block.hash());
					mined = true;
					
				}else {
					if(BlockChainManager.PRINT_REJECTED) {
						System.out.println("\t\t rejected hash nonce:" + block.getNonce() + " hash:" + block.hash());
					}
				}
				if(mined) {mined = false;break;}
			}
		}
		

	}
	
	public void updateData(DataPayload data) {
		System.out.println("data uploaded to miner");
		this.importedBlock = new Block(data.blockchain.getLastBlock(),data.data);
		changed = true;
	}
	
	
	
	
	public boolean hasNextBlock() {
		if(!signed) {System.err.println("you need to sign the miner");return false;}
		return mined;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public String sync() {
		try {
			BlockChain chain = Conversation.getDataPayload().blockchain;
			chain.addBlock(block);
			mined = false;
			return Conversation.requestSync(new SyncPayload(chain,publicKey));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	public String getPublicKey() {
		return publicKey;
	}
	
	
	
}





