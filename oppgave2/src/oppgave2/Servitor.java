package oppgave2;


public class Servitor extends Thread {
	
	private String navn;
	private Brett brett;
	
	
	 public Servitor(Brett brett, String navn) {
		this.navn=navn;
		this.brett=brett;
	 }

	 @Override
	 public void run() {
		 while (true) {

			 try {
				 sleep((long) (Math.random() * 4000) + 2000);
			 } catch (InterruptedException e) {

			 }
			 if (brett.isEmpty()) {
				 System.out.println("brett er tomt, servitør " + navn + " venter");
			 } else {
				 brett.removeBurger(navn);
			 }
	 	}
	}
}