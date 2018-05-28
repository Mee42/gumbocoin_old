package com.carson.other;

import com.carson.network.Conversation;
import com.carson.network.SyncPayload;

public class MiningThread implements Runnable{

	
	
	private boolean stop = false;
	private boolean update = false;
	private DataPayload data;
	private MinerThread miner;
	
	
	
	public MiningThread(DataPayload data, MinerThread miner) {
		this.data = data;
		this.miner = miner;
		miner.updateData(data);
	}



	@Override
	public void run() {
		stop = false;
		while(!stop) {
			if(update) {
				System.out.println("updated data for" + miner.getPublicKey());
				miner.updateData(data);
				update = false;
			}
			
			System.out.println("started miner manager for "  + miner.getPublicKey());
			System.out.println("mining block for " + miner.getPublicKey());
			boolean minedWrongBlock = false;
			miner.run();
			if(stop) {break;}
			if(update) {minedWrongBlock = true;}
			if(!minedWrongBlock) {
				SyncPayload sync = new SyncPayload(data.blockchain.addBlock(miner.getBlock()), miner.getPublicKey());
				String result = Conversation.requestSync(sync);
				if(result.equals("worked! you got paid!")) {
					System.out.println("block mined!");
				}else {
					System.err.println("failure to sync");
				}
			}else {
				System.err.println("non-critical: invalid block mined due to updates");
				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateData(DataPayload data) {
		update = true;
		this.data = data;
	}

	
	public void stop() {
		System.out.println("----- stoping thread for " + miner.getPublicKey());
		stop = true;
	}
	
}
