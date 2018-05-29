package com.carson.network;

import com.carson.other.BlockClient;

public class UserPayload {
	public final String publicKey;
	public final String sig;
	public UserPayload(String publicKey, String privateKey) {
		super();
		this.publicKey = publicKey;
		this.sig = BlockClient.hashKeys(publicKey, privateKey);
	}
	public String getPublicKey() {
		return publicKey;
	}
	public String getSig() {
		return sig;
	}
	
}
