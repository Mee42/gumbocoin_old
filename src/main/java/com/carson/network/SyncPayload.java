package com.carson.network;

import com.carson.other.BlockChain;

public class SyncPayload {
	public SyncPayload(BlockChain chain2, String str) {
		this.chain = chain2;
		this.publicKey = str;
	}
	public BlockChain chain;
	public String publicKey;

}
