package cn.thread.duokan.thread;

public class Test {
	public static void main(String[] args) {
		Thread t1 = new MyThread1();
		Thread t2 = new Thread(new MyRunnable());
		t1.setPriority(10);
		t2.setPriority(1);
		t1.start();
		t2.start();
	}
	
}

class MyThread1 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("线程1第 " + i + "次执行");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("线程2第 " + i + "次执行");
			//				Thread.sleep(50);
			Thread.yield();
		}
		
	}
	
}