package com.carson.network;

public class TestObject {

	
	public String data;

	public static TestObject stringToObject(String readLine) {
		return new TestObject().setData(readLine.substring(6, readLine.length()));
	}
	
	public TestObject setData(String s) {
		TestObject t = new TestObject();
		t.data = s;
		return t;
	}
	@Override
	public String toString() {
		return "tstob:" + data;
	}

	public static boolean isTest(String message) {
		return message.startsWith("tstob:");
	}
}
