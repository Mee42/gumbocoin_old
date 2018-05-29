package com.carson.other;

final public class Transaction {

	private String sig;
	private String publicKey1;
	private String publicKey2;
	private boolean signed;
	private int amount;
	
	public Transaction() {
		super();
	}
	
	

	public Transaction(String sig, String publicKey1, String publicKey2)  {
		super();
		this.sig = sig;
		this.publicKey1 = publicKey1;
		this.publicKey2 = publicKey2;
		this.signed = false;
		this.amount = -1;
	}
	
	public boolean sign(String privateKey) {
		String newSig = BlockClient.hashKeys(publicKey1, privateKey);
		if(newSig.equals(sig)) {
			signed = true;
			return true;
		}else {
			System.err.println("wrong privateKey");
			return true;
		}
	}
	
	public boolean unsign(String privateKey) {
		String newSig = BlockClient.hashKeys(publicKey1, privateKey);
		if(newSig.equals(sig)) {
			signed = false;
			return true;
		}else {
			System.err.println("wrong privateKey");
			return true;
		}
	}
	
	public boolean setAmount(int amount) {
		if(!signed) {
			System.err.println("you need to sign it first");
			return false;
		}
		this.amount = amount;
		return true;
	}

	public boolean verify(String privateKey) {
		String newSig = BlockClient.hashKeys(publicKey1, privateKey);
		if(newSig.equals(sig)) {
			return true;
		}else {
			System.err.println("wrong privateKey");
			return true;
		}
	}

	
	public boolean signed() {
		return signed;
	}
	
	
	public int getTransactionAmount() {
		return amount;
	}

	public String getPublicKey1() {
		return publicKey1;
	}

	public String getPublicKey2() {
		return publicKey2;
	}
	
	@Override
	public String toString() {
		return publicKey1 + " sending to " + publicKey2  + " amount:" + amount+ " (verifyed:" + signed + ')';
	}

	
	
	
	public String getSig() {
		return sig;
	}



	
	
	
	
}
