package pl.mati.neolosowanie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class Glowna implements Serializable {

	private static final long serialVersionUID = 1L;
	static public Context main;
	ArrayList<Bracia> listaBraci;
	public boolean prio;
	public boolean rozdziel;
	public boolean malzenstwaJakoDwa;
	public boolean malzenstwaPierwsze;
	public int iloscGrup;
	public ArrayList<GrupyTxt> grupy;

	public String grupyTXT;

	public Glowna(Context main) {
		super();
		this.main = main;
		this.listaBraci = null;

		Glowna x = getConfFile();
		if (x == null) {
			getList();
			setClassFile(this);

		} else {
			this.listaBraci = x.listaBraci;
			this.prio = x.prio;
			this.rozdziel = x.rozdziel;
			this.malzenstwaJakoDwa = x.malzenstwaJakoDwa;
			this.malzenstwaPierwsze = x.malzenstwaPierwsze;
			this.iloscGrup = x.iloscGrup;
			this.grupyTXT = x.grupyTXT;
			if (x.grupy == null)
				grupy = new ArrayList<GrupyTxt>();
			else
				this.grupy = x.grupy;
		}
	}
	
	

	public static void setClassFile(Glowna c) {
		ObjectOutputStream pl = null;
		try {

			File f = Environment.getExternalStorageDirectory();
			pl = new ObjectOutputStream(new FileOutputStream(f.getAbsolutePath() + "/NeoLosowanie/conf.txt"));

			pl.writeObject(c);
			pl.flush();
			pl.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static Glowna getConfFile() {
		File f = Environment.getExternalStorageDirectory();
		File file2 = new File(f.getAbsolutePath() + "/NeoLosowanie/conf.txt");
		Glowna c = null;

		if (file2.exists()) {
			ObjectInputStream pl2 = null;
			try {
				pl2 = new ObjectInputStream(new FileInputStream(f.getAbsolutePath() + "/NeoLosowanie/conf.txt"));
				c = (Glowna) pl2.readObject();
				pl2.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}

		return c;
	}

	public void getList() {
		listaBraci = new ArrayList<Bracia>();
		File f = Environment.getExternalStorageDirectory();
		File file2 = new File(f.getAbsolutePath() + "/NeoLosowanie/lista.txt");
		if (!file2.exists()) {
			Toast.makeText(main, "Nie ma listy!", Toast.LENGTH_SHORT).show();
		}

		try {

			Scanner s = new Scanner(file2, "ISO-8859-2");
			while (s.hasNextLine()) {
				String linia = s.nextLine();
				if (linia.indexOf("<m>") >= 0) {
					String nazwa = linia.substring(3);
					linia = s.nextLine();
					String nazwisko = linia.substring(0, linia.indexOf(";"));
					String email = linia.substring(linia.lastIndexOf(";") + 2);
					String imie = linia.substring(linia.indexOf(";") + 2, linia.lastIndexOf(";"));
					Samotna adam = new Samotna(nazwisko, email, imie);
					adam.setName(nazwisko + " " + imie);
					linia = s.nextLine();
					nazwisko = linia.substring(0, linia.indexOf(";"));
					email = linia.substring(linia.lastIndexOf(";") + 2);
					imie = linia.substring(linia.indexOf(";") + 2, linia.lastIndexOf(";"));
					Samotna ewa = new Samotna(nazwisko, email, imie);
					ewa.setName(nazwisko + " " + imie);
					Malzenstwo m = new Malzenstwo(nazwa, adam, ewa);
					m.setName(nazwa);
					listaBraci.add(m);
				} else {
					String nazwisko = linia.substring(0, linia.indexOf(";"));
					String email = linia.substring(linia.lastIndexOf(";") + 2);
					String imie = linia.substring(linia.indexOf(";") + 2, linia.lastIndexOf(";"));
					Samotna osoba = new Samotna(nazwisko, email, imie);
					osoba.setName(nazwisko + " " + imie);

					listaBraci.add(osoba);

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setClassFile(this);
	}

}
