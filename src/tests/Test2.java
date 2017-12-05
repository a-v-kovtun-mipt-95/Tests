package tests;

public class Test2 {
	
	public static class TestPrint implements Runnable{
		public void print() {
			synchronized(this) {
				for(int i=0;i<10;i++) {
					System.out.println(Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
					try{Thread.sleep(100);}catch(InterruptedException ie) {return;}
				}
			}
		}
		@Override
		public void run() {
			while(!Thread.interrupted()) {
				print();
				Thread.yield();
			}
			System.out.println("fin: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
		}
	}

	public static void main(String[] args) {
		TestPrint tp=new TestPrint();
		Thread th1=new Thread(tp);
		th1.setName("A");
		Thread th2=new Thread(tp);
		th2.setName("B");
		th1.start();
		th2.start();
		//
	}

}
