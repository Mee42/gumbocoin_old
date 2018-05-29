package com.carson.other;

public class BlockChainPayment {

	private String publicKey;
	
	
	public BlockChainPayment(String publicKey) {
		super();
		this.publicKey = publicKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public int getAmount() {
		return BlockChainManager.PRIZE;
	}
	
	@Override
	public String toString() {
		return publicKey + " gets payed " + getAmount();
	}
}
