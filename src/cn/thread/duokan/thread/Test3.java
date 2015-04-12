package cn.thread.duokan.thread;

public class Test3 {
	public static void main(String[] args) {
		DeadLockRisk dead = new DeadLockRisk();
		MyThread3 t1 = new MyThread3(dead, 1, 2);
		MyThread3 t2 = new MyThread3(dead, 3, 4);
		MyThread3 t3 = new MyThread3(dead, 5, 6);
		MyThread3 t4 = new MyThread3(dead, 7, 8);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class MyThread3 extends Thread{
	private DeadLockRisk dead;
	private int a,b;
	MyThread3(DeadLockRisk dead, int a, int b) {
		this.dead = dead;
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void run() {
		dead.read();
		dead.write(a, b);
	}
	
}
class DeadLockRisk{
	private static class Resource{
		public int value;
	}
	private Resource resourceA= new Resource();
	private Resource resourceB= new Resource();
	
	public int read (){
		synchronized (resourceA) {
			System.out.println("read() :" + Thread.currentThread().getName() + "获取了resourceA 的锁!");
			synchronized (resourceB) {
				System.out.println("read() :" + Thread.currentThread().getName() + "获取了resourceB 的锁!");
				return resourceB.value + resourceA.value;
			}
		}
	}
	
	public void write(int a,int b){
		synchronized (resourceB) {
			System.out.println("read() :" + Thread.currentThread().getName() + "获取了resourceA 的锁!");
			synchronized (resourceA) {
				System.out.println("read() :" + Thread.currentThread().getName() + "获取了resourceB 的锁!");
				resourceA.value = a;
				resourceB.value = b;
			}
		}
	}
	
}