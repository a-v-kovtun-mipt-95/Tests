package tests;

public class Test1 {

	public static void main(String[] args) {
		final Thread th1=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(!Thread.interrupted()) {
						System.out.println("run: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
						Thread.sleep(1000);
					}
				}
				catch(InterruptedException ie) {
					System.out.println("intrpt ex: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
					return;
				}
				System.out.println("fin: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
			}
		});
		th1.setName("One sec Thread");
		//
		final Thread th2=new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					System.out.println("run: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
					Thread.yield();;
				}
				System.out.println("fin: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
			}
		});
		th2.setName("Yield Thread");
		//
		final Thread th3=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					th1.join();
					System.out.println("join: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
				}
				catch(InterruptedException ie) {
					System.out.println("intrpt ex: "+Thread.currentThread().getName()+" "+Long.toString(Thread.currentThread().getId())+" "+Long.toString(System.currentTimeMillis()));
					return;
				}
			}
		});
		th3.setName("Join Thread");
		//
		th1.start();
		th2.start();
		th3.start();
		//
		try {
			Thread.sleep(3500);
		}
		catch(InterruptedException ie) {
			
		}
		//
		th2.interrupt();
		th1.interrupt();
		
	}

}
