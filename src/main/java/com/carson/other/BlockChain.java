package com.carson.other;

import java.util.ArrayList;
import java.util.List;

import com.carson.exceptions.IllegalChainException;

public class BlockChain {

//	public List<Block> blocks;
	private List<Block> blocks;
	private int difficulty;
	
	public BlockChain() {
		blocks = new ArrayList<>();
		this.difficulty = BlockChainManager.DIFFICULTY;
	}
	
	public BlockChain setDifficulty(int difficulty){
		this.difficulty = difficulty;
		return this;
	}
	
	public void makeGensisBlock() {
		blocks.add(Block.makeGensisBlock());
	}
	
	public long getBiggestHash() {
		return blocks.get(blocks.size() - 1).getNonce();
	}
	
	
	public boolean isChainValid() {
		
		
		/*
		 * 
		 * stackdump:
		   =========data from server:{"blockchain":{"blocks":[{"hash":"3f253dedb6a336bb93f91ab49ea1c5fabbbbd487f8527e774755769b682031d4","lastHash":"GensisBlockHash","data":{"tData":[],"pData":[]},"timestamp":1527270755222,"nonce":0,"gensis":true},{"hash":"48a265d834fa1786c3ca65aef0a5955648eceb4ab6492b1cf66bf5c0c5b7fc1b","lastHash":"3f253dedb6a336bb93f91ab49ea1c5fabbbbd487f8527e774755769b682031d4","data":{"tData":[],"pData":[]},"timestamp":1527270758984,"nonce":45099,"gensis":false}],"difficulty":4},"data":{"tData":[],"pData":[{"publicKey":"key--publicKey1"}]}}
			---BLOCKCHAIN
					block nonce:0   HASH:3f253   LASTHASH:Gensi
					block nonce:45099   HASH:48a26   LASTHASH:3f253
			---END
			received data
			hashed. new hash:f14caeba907363c47d33fea10a094490fe4edebdf367b7e6a905e445f1f15be0
				accepted hash! nonce:84998 hashed:00001e8ec8c0d5ac3849bbfa3d0c75809a195353ee7f5d2b88e3160cfe1d50f6
			Block mined!
			---BLOCKCHAIN
					block nonce:0   HASH:3f253   LASTHASH:Gensi
					block nonce:45099   HASH:48a26   LASTHASH:3f253
					block nonce:84998   HASH:a9bfd   LASTHASH:48a26
			---END
			=========new (mined)chain:{"blockchain":{"blocks":[{"hash":"3f253dedb6a336bb93f91ab49ea1c5fabbbbd487f8527e774755769b682031d4","lastHash":"GensisBlockHash","data":{"tData":[],"pData":[]},"timestamp":1527270755222,"nonce":0,"gensis":true},{"hash":"48a265d834fa1786c3ca65aef0a5955648eceb4ab6492b1cf66bf5c0c5b7fc1b","lastHash":"3f253dedb6a336bb93f91ab49ea1c5fabbbbd487f8527e774755769b682031d4","data":{"tData":[],"pData":[]},"timestamp":1527270758984,"nonce":45099,"gensis":false},{"hash":"a9bfd6822ded95cb6f49de25251bfa396a3f348b2b5341730c386230ed7d290d","lastHash":"48a265d834fa1786c3ca65aef0a5955648eceb4ab6492b1cf66bf5c0c5b7fc1b","data":{"tData":[],"pData":[{"publicKey":"key--publicKey1"}]},"timestamp":1527271049463,"nonce":84998,"gensis":false}],"difficulty":4},"data":{"tData":[],"pData":[]}}
			Chain was not valid		
		 * 
		 * 
		 */
		
		for(int i = 1;i<blocks.size();i++) {
			
			Block current = blocks.get(i);
			
			Block last = blocks.get(i - 1);
			
			if(!current.hasSameHashAs(current.hash())) {//verifys the hash of the current block
				System.err.println("hash variable is wrong, block no " + i);
				return false;
			}
			
			System.out.println("current hash:" + current.getHash());

			if(!last.getHash().equals(current.getLastHash())) { //if the current block incorrectly contains old hash values
				System.err.println("blocks do not agree on hash values");
//				System.out.println("last.getHash:" + last.getHash());
//				System.out.println("current.getLastHash:" + current.getLastHash());
				printChainHashes();
				return false;
			}
			
//			if(!current.getHashed().substring(0, (difficulty % 64)).equals(str)) {//throws if the block can't actually be mined
//				System.err.println("block can not be mined");
//				return false;
//			}
			
			
		}
		
		return true;
	}
	
	public boolean printIsChainValid() {
		return printIsChainValid("");
	}
	public boolean printIsChainValid(String name) {
		if(isChainValid()) {
			System.out.println(name + " Chain is valid");
			return true;
		}else {
			System.err.println(name + " Chain is not valid");
			return false;
		}
	}
	
//	public void mineBlockWithData(BlockChainDataContainer data) throws Exception{
//		Block b = new Block(
//				blocks.get(blocks.size() - 1),
//				data
//			).mineBlock(difficulty);
//		
//		
//	}
	
	public BlockChain addBlock(Block b){
		blocks.add(b);
//		if(!isChainValid()) {
//			try {
//				throw new IllegalChainException();
//			} catch (IllegalChainException e) {
//				e.printStackTrace();
//			}
//		}
		return this;
	}
	
	public Block getLastBlock() {
		return blocks.get(blocks.size()-1);
	}
	
	public void printBlocks() {
		for(int i = 0;i<blocks.size();i++) {
			Block b = blocks.get(i);
			System.out.println(
					"block:" + i +
					"\nnonce:" + b.getNonce() +
					"\ndata:" + b.getData().toString() +
					"\n"
					);
			
			
			
			
		}
		
		
	}
	
	public void printChainHashes() {
		System.out.println("---BLOCKCHAIN");
		for(Block b : blocks) {
			System.out.println("\t\tblock nonce:" + b.getNonce() + "   HASH:"+b.getHash().substring(0, 5)+"   LASTHASH:"+b.getLastHash().substring(0, 5));
			
		}
		System.out.println("---END");
	}
	
	
	public void clearBlocks() {
		blocks = new ArrayList<>();
		makeGensisBlock();
	}

	public void printNonces() {
		StringBuilder b = new StringBuilder();
		
		for(Block block : blocks) {
			b.append(block.getNonce() + ",");
			if(b.lastIndexOf("\n") < b.length() - 100) {
				b.append('\n');
			}
		}
		System.out.println("Nunces:\n"+b.toString().substring(0, b.length()-1));
		
		
	}
	

		
	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public void mine() { //ONLY FOR BENCHMARKING
		blocks.add(
				new Block(
						getLastBlock(),
						new BlockChainDataContainer()
						).mineBlock(BlockChainManager.DIFFICULTY)				
				);
		
	}
	
	public void mine(BlockChainDataContainer c) throws Exception{ //ONLY FOR TESTING MINING WITHOUT A MINER
		Block b = new Block(getLastBlock(),c).mineBlock(BlockChainManager.DIFFICULTY);
		addBlock(b);
		System.out.println("just added block. no testing done");
		printChainHashes();
		System.out.println("running test"); //RIGHT HERE WE ARE GOOD
		printIsChainValid("just mined");
		System.out.println("after checking chain");
		printChainHashes();
		
	}
	
	public BlockChain mine(DataPayload payload)throws Exception {//again only for testing lol
		mine(payload.data);
		return this;
	}
	
	public void hashAllBlocks() {
		System.out.println("---hashing all blocks");
		for(Block b : blocks) {
			b.hash();
		}
		System.out.println("---done");
	}
	
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public int blockSize() {
		return blocks.size();
	}

	public BlockChain copy() {
		BlockChain chain = new BlockChain().setDifficulty(difficulty);
		List<Block> newBlocks = new ArrayList<>();
		
		for(Block block : blocks) {
			newBlocks.add(
					block.copy()
					);
		}
		
		
		chain.setBlocks(newBlocks);
		return chain;
	}

//	@Override
//	public String toString() {
//		StringBuilder  b = new StringBuilder();
//		
//		for(Block block : blocks) {
//			b.append("BLOCKOBJ:" + block.toString());
//		}
//		return b.toString();
//		
//	}
//	
//	public static BlockChain fromString(String chain) {
//		String[] blocks = chain.split("BLOCKOBJ:");
//		List<Block> blocksAr = new ArrayList<>();
//		for(String b : blocks) {
//			blocksAr.add(Block.stringToBlock(b));
//		}
//		BlockChain bc = new BlockChain();
//		bc.setBlocks(blocksAr);
//		return bc;
//	}
}
