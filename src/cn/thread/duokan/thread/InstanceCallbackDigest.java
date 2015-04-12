package cn.thread.duokan.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 求文件的信息摘要码
 */
public class InstanceCallbackDigest implements Runnable{
	
	private File inputFile;	//目标文件
	
	private InstanceCallbackDigestUserInterface instanceCallback;
	
	
	public InstanceCallbackDigest(InstanceCallbackDigestUserInterface instanceCallback,File inputFile) {
		this.instanceCallback = instanceCallback;
		this.inputFile = inputFile;
	}

	

	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(inputFile);
			MessageDigest sha = MessageDigest.getInstance("MD5");
			DigestInputStream din = new DigestInputStream(in,sha);
			int b ;
			while((b = din.read()) != -1);
			din.close();
			byte[] digest = sha.digest();
			instanceCallback.receiveDigest(digest);	//回掉方法
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
