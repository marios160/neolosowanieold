package pl.mati.neolosowanie;

import java.io.Serializable;

public class GrupyTxt implements Serializable {

	String nazwa;
	String grupy;
	String data;

	public GrupyTxt(String nazwa, String grupy, String data) {
		super();
		this.nazwa = nazwa;
		this.grupy = grupy;
		this.data = data;
	}

}
