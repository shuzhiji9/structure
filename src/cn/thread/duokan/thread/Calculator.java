package cn.thread.duokan.thread;

public class Calculator extends Thread {
	int total;

	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				total += i;
			}
			notifyAll();
		}
	}

}
