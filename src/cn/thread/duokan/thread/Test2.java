package cn.thread.duokan.thread;

public class Test2 {
	public static void main(String[] args) {
		Godown godown = new Godown(30);
		Consumer c1 = new Consumer(50, godown);
		Consumer c2 = new Consumer(20, godown);
		Consumer c3 = new Consumer(30, godown);
		
		Producer p1 = new Producer(10, godown);
		Producer p2 = new Producer(10, godown);
		Producer p3 = new Producer(10, godown);
		Producer p4 = new Producer(10, godown);
		Producer p5 = new Producer(10, godown);
		Producer p6 = new Producer(10, godown);
		Producer p7 = new Producer(10, godown);
		
		c1.start();
		c2.start();
		c3.start();
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
	}
}

/**
 * 仓库
 */
class Godown {
	public static final int MAX_SIZE = 100; // 最大库存
	public int curnum; // 当前库存

	Godown() {
	}

	Godown(int curnum) {
		this.curnum = curnum;
	}

	/**
	 * 生产指定数量的产品
	 */
	public synchronized void produce(int neednum) {
		while (neednum + curnum > MAX_SIZE) {
			System.out.println("要生存的产品数量" + neednum + "超过剩余库存"
					+ (MAX_SIZE - curnum) + ",暂时不能执行生产任务!");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 满足生产条件,进行生产,简单的更改当前库存
		curnum += neednum;
		System.out.println("已经生产了" + neednum + "个产品,库存为: " + curnum);
		// 唤醒在次对象监视器上等待的所有线程
		notifyAll();
	}

	/**
	 * 消费指定数量的产品
	 * 
	 * @param neednum
	 */
	public synchronized void consume(int neednum) {
		// 测试是否可以消费
		while (curnum < neednum) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		curnum -= neednum;
		System.out.println("已经消费了" + neednum + "个产品,现库存为" + curnum);
		// 唤醒在此对象监视器上等待的所有线程
		notifyAll();
	}
}

/**
 * 生产者
 */
class Producer extends Thread {
	private int neednum; // 生产产品的数量
	private Godown godown; // 仓库

	Producer(int neednum, Godown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}

	@Override
	public void run() {
		godown.produce(neednum);
	}
}

/**
 * 消费者
 */
class Consumer extends Thread {
	private int neednum; // 生产产品的数量
	private Godown godown; // 仓库

	Consumer(int neednum, Godown godown) {
		super();
		this.neednum = neednum;
		this.godown = godown;
	}
	@Override
	public void run() {
		godown.consume(neednum);
	}
}