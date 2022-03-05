package pl.mati.neolosowanie;

import java.io.Serializable;

public class Malzenstwo extends Bracia implements Serializable {

	private Samotna adam;
	private Samotna ewa;

	public Malzenstwo(String nazwisko, Samotna adam, Samotna ewa) {
		super(nazwisko);
		setStan(2);
		this.adam = adam;
		this.ewa = ewa;
	}

	public Samotna getAdam() {
		return adam;
	}

	public void setAdam(Samotna adam) {
		this.adam = adam;
	}

	public Samotna getEwa() {
		return ewa;
	}

	public void setEwa(Samotna ewa) {
		this.ewa = ewa;
	}

}
