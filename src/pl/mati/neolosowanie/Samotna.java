package pl.mati.neolosowanie;

import java.io.Serializable;

public class Samotna extends Bracia implements Serializable {

	private String imie;
	private String email;

	public Samotna(String nazwisko, String email, String imie) {
		super(nazwisko);
		this.imie = imie;
		this.email = email;
		setStan(1);

	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
