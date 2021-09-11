package oppgave2;


public class Kokk extends Thread {

	private String navn;
	private Brett brett;
	
	public Kokk (Brett brett, String navn) {
		this.navn=navn;
		this.brett=brett;
	}
	

	public void run() {
		while(true) {
			try { 
				sleep((long)(Math.random() * 4000) + 2000);
			}
			catch (InterruptedException e) {
				System.out.println(e);
			}
			if (brett.isFull()) {
				System.out.println("Brettet er fullt, kokken " + navn + " venter");
			} else {
				brett.addBurger(navn);
			}
		}
	}
}