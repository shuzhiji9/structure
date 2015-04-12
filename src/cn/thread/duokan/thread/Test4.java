package cn.thread.duokan.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test4 {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		Thread t1 = new MyThread4();
		Thread t2 = new MyThread4();
		Thread t3 = new MyThread4();
		Thread t4 = new MyThread4();
		Thread t5 = new MyThread4();
		
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		
		pool.shutdown();
	}
}

class MyThread4 extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行!");
	}
}
