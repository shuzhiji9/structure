package cn.thread.duokan.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListCallbackDigest implements Runnable{
	
	private File inputFile;
	
	private List<DigestListener> listeners = Collections.synchronizedList(new ArrayList<DigestListener>());
	
	public ListCallbackDigest(File inputFile){
		this.inputFile = inputFile;
	}
	
	public synchronized void addDigestListener(DigestListener ler){
		listeners.add(ler);
	}
	
	public synchronized void removeDigestListener(DigestListener ler){
		listeners.remove(ler);
	}
	
	private synchronized void sendDigest(byte [] digest){
		for (DigestListener ler : listeners) {
			ler.digestCalculated(digest);
		}
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
			System.out.println(digest);
			this.sendDigest(digest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

}
