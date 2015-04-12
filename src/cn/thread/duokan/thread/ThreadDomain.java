package cn.thread.duokan.thread;

public class ThreadDomain {
	public static void main(String[] args) {
		Thread t1 = new Common();
		Thread t2 = new Thread(new MyRunnable());
		t2.setDaemon(true);
		t2.start();
		t1.start();
	}
}

class Common extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("线程1第" + i + "次执行!");
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyDaemon implements Runnable {
	
	public static int i = 0;
	
	@Override
	public void run() {
		while(true){
			System.out.println("后台线程第" + i + "次执行!");
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}