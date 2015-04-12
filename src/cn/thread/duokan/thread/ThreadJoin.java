package cn.thread.duokan.thread;

public class ThreadJoin {
	public static void main(String[] args) {
		Thread t1 = new MyThread1();
		t1.start();
		for (int i = 0; i < 20; i++) {
			System.out.println("主线程第" + i + "次执行!");
			if(i > 2) try {
				t1.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}