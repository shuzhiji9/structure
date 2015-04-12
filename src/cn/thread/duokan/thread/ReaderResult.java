package cn.thread.duokan.thread;

public class ReaderResult extends Thread{

	Calculator c ;
	
	public ReaderResult(Calculator c){
		this.c = c;
	}
	@Override
	public void run() {
		synchronized (c) {
			try {
				System.out.println(Thread.currentThread() + "等待计算结果..");
				c.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "计算结果为: " + c.total);
		}
	}
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		
		new ReaderResult(calculator).start();
		new ReaderResult(calculator).start();
		new ReaderResult(calculator).start();
		calculator.start();
	}
	
}
