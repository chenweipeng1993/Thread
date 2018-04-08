package peng.setDaemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

//设置守护线程
public class SetDaemonDemo {
	public static void main(String[] args) {
		/*MyThread mt = new MyThread();
		mt.setDaemon(true);*/
		System.out.println("程序进入了主线程"+Thread.currentThread().getName());
		DaemonThread daemonThread = new DaemonThread();
		Thread thread = new Thread(daemonThread);
		//设置守护线程-需要在start之前设置
		thread.setDaemon(true);
		thread.start();
		
		Scanner sc = new  Scanner(System.in);
		sc.next();
		
		System.out.println("程序结束了主线程"+Thread.currentThread().getName());
	}
}
class MyThread extends Thread{

	@Override
	public void run() {
		//super.run();
		System.out.println("守护线程的案例");
	}
}

class DaemonThread implements Runnable{

	public void run() {
		System.out.println("进入守护线程"+Thread.currentThread().getName());
		try {
			writeToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("退出守护线程"+Thread.currentThread().getName());

	}

	private void writeToFile() throws Exception {
		File filename = new File("d:"+File.separator+"daemon.txt");
		/*if(!filename.exists()) {
			filename.mkdirs();
			filename.createNewFile();
		}*/
		OutputStream os = new FileOutputStream(filename,true);
		int count = 0;
		while(count<999) {
			os.write(("word"+count+"\r\n").getBytes());
			System.out.println("守护线程"+Thread.currentThread().getName()+"向文件中写入word"+count++);
			Thread.sleep(1000);
		}
	}
	
}