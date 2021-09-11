package oppgave2;

import java.util.ArrayList;
import java.util.Arrays;

public class Brett {
	private int kapasitet;
	private Hamburger[] hamburgers;
	private int counter = 0;
	final ArrayList<Integer> madeBurgerIds = new ArrayList<Integer>();
	private boolean inUse = false;

	private int[] getBurgerIds (){
		if (counter == 0) return new int[0];
		int [] ids = new int[counter];
		for (int i = 0; i < counter; i++) {
			ids[i] = hamburgers[i].getBurgerId();
		}
		return ids;
	}

	public boolean isFull () {
		if (kapasitet == counter) return true;
		return false;
	}

	public boolean isEmpty () {
		if (counter == 0) return true;
		return false;
	}

	public Brett(int capacity) {
		kapasitet = capacity;
		hamburgers = new Hamburger[capacity];
	}

	private void waitHandler() {
		while (inUse) {
			try {
				wait();
			} catch (InterruptedException e)  {
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted" + e);
			}
		}
		inUse = true;
	}

	private synchronized void makeAvailable() {
		inUse = false;
		notifyAll();
	}

	public synchronized void addBurger (String cook) {
		waitHandler();

		int newBurgerId = madeBurgerIds.size() + 1;
		hamburgers[counter] = new Hamburger(newBurgerId);
		madeBurgerIds.add(newBurgerId);
		counter++;
		System.out.println("Kokken " + cook + " lagde en ny burger: " + newBurgerId);
		System.out.println("Brettet har nå burgerene: " + Arrays.toString(getBurgerIds()) + "  på seg");

		makeAvailable();
	}

	public synchronized void removeBurger(String servitor) {
		waitHandler();

		if (counter == 0) return;
		System.out.println("Servitor " + servitor + " hentet burger " + hamburgers[counter-1].getBurgerId());
		hamburgers[counter-1] = null;
		counter--;
		System.out.println("Brettet har nå burgerene: " + Arrays.toString(getBurgerIds()) + "  på seg");

		makeAvailable();
	}
}