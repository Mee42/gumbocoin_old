package com.carson.other;

import java.util.Date;


public class Block {
	private String hash;
	final private String lastHash;
	private BlockChainDataContainer data;
	final private long timestamp;
	private long nonce;
	public String hashString;
	public String lastHashString;
	
	private boolean gensis;
	
	public Block(String lastHash, BlockChainDataContainer data) {
		this.lastHash = lastHash;
		this.data = data;
		this.timestamp = new Date().getTime();
		hash = this.hashQuiet();
		this.gensis = false;
		if(lastHash.equals("GensisBlockHash")) {
			System.out.println("block marked as gensis");
			this.gensis = true;
		}
	}
	

	public Block(String hash, String lastHash, BlockChainDataContainer data, long timestamp, long nonce, boolean gensis,String hashString) {
		this.lastHash = lastHash;
		this.data = data;
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.gensis = gensis;
		this.hash= hash;
		this.lastHashString = hashString;
		this.hash = hash();
	}

	public static Block makeGensisBlock() {
		System.out.println("made gensis block");
		return new Block(
				"GensisBlockHash",
				BlockChainDataContainer.forGensisBlock()
				);
		
		
	}
	
	public String getHash() {
		return hash;
	}

	public String getLastHash() {
		return lastHash;
	}
	
	public Block(Block bl, BlockChainDataContainer data) {
		this.lastHash = bl.hash;
		this.data = data;
		this.timestamp = new Date().getTime();
		this.nonce = 0;
		this.hashQuiet();
		randomNonce();
	}
	
	public String hash() {
//		System.out.print("hashed.   old hash: " + hash.substring(0,5));
		
		this.hash = Hasher.hash(genHashString());
//		System.out.println("    new hash:  " + hash.substring(0,5));
//		System.out.println("HASHED WITH:" +hashString);

		return hash;
	}
	
	public String hashQuiet() {//TODO FOR DEV, FOR FINDING WRONG HASHES
		
		this.hash = Hasher.hash(genHashString());
//		System.out.println("HASHED WITH:" +hashString);
		return hash;
	}
	
	public String genHashString() {
		hashString = lastHash +Long.toString(timestamp) + Long.toString(nonce) + data.toString();
		return hashString;
	}
	
	public boolean hasSameHashAs(String otherHash) {
		return hash.equals(otherHash);
	}
		

	
	public Block mineBlock(int difficulty) {
		System.out.println("starting mining block");
		while(true){
			nonce ++;
//			System.out.print("starting mining nonce:" + nonce + " ");
			hashQuiet();
			
			String hashed = Hasher.hash(nonce + "GUMBOCOIN!");
			
			for(int i = -0;i<10;i++) {
				hashed = Hasher.hash(hashed);
			}
			
			
			StringBuilder strB = new StringBuilder();
			
			for(int i = 0;i<difficulty;i++) {
				strB.append("0");
			}
			String str = strB.toString();
//			System.out.println("str:" + str);
			
			if(hashed.substring(0, (difficulty % 64)).equals(str)) {
				System.out.println("\taccepted hash! nonce:" + nonce + " hashed:" + hashed);
//				System.out.println(toStringForDebug());
				break;
			}else {
				if(BlockChainManager.PRINT_REJECTED) {
					System.out.println("\t\t rejected hash nonce:" + nonce + " hashed:" + hashed);
				}
			}
			
			}
		
		System.out.println("Block mined!");
//		System.out.println("hash:" + hashQuiet().substring(0, 10));
		
		return this;
			
	}

	public long getNonce() {
		return nonce;
	}

	public BlockChainDataContainer getData() {
		return data;
	}
	
	public Block copy() {
		Block b =new Block(hash,lastHash,data,timestamp,nonce,gensis,hashString);
//		System.out.println("copyed block. hash:" + getHash().substring(0, 3) + "   new hash:" + b.getHash().substring(0, 3));
		return b;
		
	}

//
//	public void setNonce(long nonce) {
//		this.nonce = nonce;
//		hash();
//	}
	
//
//
//	public String toStringForDebug() {
//		return "HASH:" + hash + " LASTHASH:" + lastHash + " DATA:" + data.toString() + " TIMESTAMP:" + timestamp + " NONCE:" + nonce;
//	}
	
	public void randomNonce() {
		System.out.println("random nonce made");
		nonce = (int)(Math.random() * Integer.MAX_VALUE - (Integer.MAX_VALUE / 2));	
		hash();
	}


	public boolean isGensis() {
		return gensis;
	}


	

}







