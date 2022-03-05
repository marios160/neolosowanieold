package pl.mati.neolosowanie;

import java.io.Serializable;
import org.apache.http.impl.io.SocketOutputBuffer;

public class Bracia implements Serializable {

	private String nazwisko;
	private int stan;
	private int prio;
	private String name;

	public Bracia(String nazwisko) {
		super();
		this.nazwisko = nazwisko;
		this.prio = 5;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public int getStan() {
		return stan;
	}

	public void setStan(int stan) {
		this.stan = stan;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}

	@Override
	public String toString() {

		return this.nazwisko + " " + this.stan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
