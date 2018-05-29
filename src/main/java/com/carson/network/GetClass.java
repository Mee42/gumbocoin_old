package com.carson.network;

public class GetClass {

	
	public static String getClass(String line) {
		if(line.startsWith("payload:")) {return "DataPayload";}
		if(line.startsWith("SyncObject")){return "SyncObject";}
		if(line.startsWith("user:")) {return "user";}
		return "failure";
	}
	
}
