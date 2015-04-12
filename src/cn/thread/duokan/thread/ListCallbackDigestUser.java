package cn.thread.duokan.thread;

import java.io.File;

public class ListCallbackDigestUser implements DigestListener{

	private File inputFile;
	
	private byte[] digest;
	
	public ListCallbackDigestUser(File inputFile){
		this.inputFile = inputFile;
	}
	
	@Override
	public void digestCalculated(byte[] digest) {
		this.digest = digest;
		System.out.println(digest);
	}
	
	public void calculateDigest(){
		ListCallbackDigest callback = new ListCallbackDigest(inputFile);
		Thread t = new Thread(callback);
		t.start();
	}
	@Override
	public String toString() {
		String result = inputFile.getName() + " ";
		if (digest != null) {
			for (byte b : digest) {
				result += b + " ";
			}
		} else {
			System.out.println("digest 不可用");
		}
		return result;
	}
	
	public static void main(String[] args) {
		String arr[] = { "c:\\xcopy.txt", "C:\\x.txt", "C:\\xb.txt",
				"C:\\bf2.txt" };
		args = arr;
		for (int i = 0; i < args.length; i++) {
			File f = new File(args[i]);
			ListCallbackDigestUser cb = new ListCallbackDigestUser(f);
			cb.calculateDigest();
		}
	}

}
