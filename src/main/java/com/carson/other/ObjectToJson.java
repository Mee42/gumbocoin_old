package com.carson.other;

import com.carson.network.SyncPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectToJson {
	public static SyncPayload stringToSyncPayload(String input) {
    	GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(input, SyncPayload.class);
		
	}
	public static String dataPayloadToString(DataPayload data) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result = gson.toJson(data);
    	return result;
	}
	public static DataPayload stringToDataPayload(String input) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(input, DataPayload.class);
	}
	public static String syncPayloadToString(SyncPayload sync) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result = gson.toJson(sync);
    	return result;
	}
	
	public static String blockChainDataContainerToString(BlockChainDataContainer c) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String result = gson.toJson(c);
    	return result;
	}
	
	
}
