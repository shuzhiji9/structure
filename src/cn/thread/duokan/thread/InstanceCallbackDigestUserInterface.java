package cn.thread.duokan.thread;

import java.io.File;
import java.util.Arrays;

/**
 * 静态非同步回掉
 */
public class InstanceCallbackDigestUserInterface {

	private File inputFile; // 回掉与每个文件绑定

	private byte[] digest; // 文件的信息摘要码

	public InstanceCallbackDigestUserInterface(File inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * 计算某个文件的信息摘要码
	 */
	public void calculateDigest() {
		InstanceCallbackDigest callback = new InstanceCallbackDigest(
				this,inputFile);
		Thread t = new Thread(callback);
		t.start();
	}

	public void receiveDigest(byte[] digest) {
		this.digest = digest;
		System.out.println(this);
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
			InstanceCallbackDigestUserInterface cb = new InstanceCallbackDigestUserInterface(
					f);
			cb.calculateDigest();
		}
	}
}
