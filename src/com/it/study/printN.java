package com.it.study;

public class printN {
	public static void main(String[] args) {
		int n = 1000;
		long startTime1 = System.currentTimeMillis();
		forPrintN(n);
		long endTime1 = System.currentTimeMillis();
		System.err.println((endTime1 - startTime1) +  "第一个");	//16
		long startTime2 = System.currentTimeMillis();
		recPrintN(n);
		long endTime2 = System.currentTimeMillis();
		System.err.println((endTime2 - startTime2) + "第二个"); 	//6
	}

	private static void forPrintN(int n) {
		for (int i = 0; i <= n; i++) {
			System.out.println(n);
		}
	}

	private static void recPrintN(int n) {
		if(n >= 0){
			recPrintN(n -1);
			System.out.println(n);
		}
	}
}
