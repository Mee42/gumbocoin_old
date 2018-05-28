package com.carson.other;

import java.util.ArrayList;
import java.util.List;

public class BlockChainDataContainer {

//	public List<IBlockChainData> data = new ArrayList<>();
	public List<Transaction> tData = new ArrayList<>();
	public List<BlockChainPayment> pData = new ArrayList<>();

	public List<Transaction> getTransactionData(){
		return tData;
	}
	public List<BlockChainPayment> getPaymentData(){
		return pData;
	}
	
	public BlockChainDataContainer addPayment(BlockChainPayment d) {
		pData.add(d);
		return this;
	}
	
	public BlockChainDataContainer addTransaction(Transaction d) {
		tData.add((Transaction)d);
		return this;
	}

	public static BlockChainDataContainer forGensisBlock() {
		return new BlockChainDataContainer();
	}

	public DataPayload toPayload(BlockChain chain) {
		return new DataPayload(chain,this);
	}
	
	public boolean equals(BlockChainDataContainer c) {
		if(c.pData.size() != pData.size()) 
			return false;
		if(c.tData.size() != tData.size()) 
			return false;
		
		return true;
	
	}

//	public BlockChainDataContainer addMany(List<BlockChainDataContainer> waitingData) {
//		for(BlockChainDataContainer waitingDataPoint : waitingData) {
//			for(IBlockChainData d : waitingDataPoint.getData()) {
//				addData(d);
//			}
//		}
//		return this;
//	}
	
	@Override
	public String toString() {
		return ObjectToJson.blockChainDataContainerToString(this);
	}

	
}
