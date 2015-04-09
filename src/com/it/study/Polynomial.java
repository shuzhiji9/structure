package com.it.study;

public class Polynomial {
	public static void main(String[] args) {
		//1. 正常思维算法
		double fx = 0D;
		int n = 2;
		double a[] = {1,5,6};
		double x = 15D;
		x = first(n,a,x);
		System.out.println(x);
		
		//2. 程序员常用算法
		x = 15D;
		x = second(n,a,x);
		System.out.println(x);
		
	}
	
	private static double second(int n, double[] a, double x) {
		double p = a[n];
		for (int i = n; i > 0; i--) {
			p = a[i -1 ] + x* p;
		}
		return p;
	}

	private static double first(int n, double[] a, double x) {
		double p = a[0];
		for (int i = 0; i < n; i++) {
			p += (a[i] * Math.pow(x, i));
		}
		return p;
	}

}
