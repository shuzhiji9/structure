package cn.thread.duokan.thread;

import java.io.File;

public class CallBackDigestUserInterface {

	public static void receiveDigest(byte[] digest, String name) {
		StringBuffer result = new StringBuffer(name);
		result.append(": ");
		for (int j = 0; j < digest.length; j++) {
			result.append(digest[j] + " ");
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		String arr[] = {"c:\\xcopy.txt","C:\\x.txt","C:\\xb.txt","C:\\bf2.txt"};
		args = arr;
		for (int i = 0; i < args.length; i++) {
			File f = new File(args[i]);
			CallBackDigest cb = new CallBackDigest(f);
			Thread t = new Thread(cb);
			t.start();
		}
	}
}
