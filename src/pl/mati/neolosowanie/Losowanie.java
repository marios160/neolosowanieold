package pl.mati.neolosowanie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Losowanie {

	ArrayList<Bracia> org;
	ArrayList<Bracia> lista;
	ArrayList<ArrayList<Bracia>> grupy;
	Glowna g;
	String nazwa;
	int iloscGrup;

	public Losowanie(String nazwa) {
		g = MainActivity.glowna;
		iloscGrup = MainActivity.glowna.iloscGrup;
		this.nazwa = nazwa;
		org = g.listaBraci;
		grupy = new ArrayList<ArrayList<Bracia>>();
		for (int i = 0; i < iloscGrup; i++) {
			grupy.add(new ArrayList<Bracia>());
		}
		//if (g.prio) {
			losowaniePrio2();
//		} else {
//			losowanie();
//		}
		String grupytxt = "";
		int j = 1;
		for (ArrayList<Bracia> arrayList : grupy) {
			grupytxt += "\nGrupa " + j + "\n\n";
			for (Bracia bracia : arrayList) {
				grupytxt += bracia.getName() + "\n";
			}
			j++;
		}

		g.grupy.add(new GrupyTxt(this.nazwa, grupytxt, new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
		g.setClassFile(g);

	}

	public void losowanie() {
		lista = new ArrayList<Bracia>();
		for (Bracia bracia : org) {

			if (bracia.getStan() == 2) {
				if (g.rozdziel) {
					Malzenstwo m = (Malzenstwo) bracia;
					lista.add(m.getAdam());
					lista.add(m.getEwa());
				} else
					lista.add(bracia);
			} else
				lista.add(bracia);
		}
		int j = 1;
		Random random = new Random();
		while (!lista.isEmpty()) {

			for (ArrayList<Bracia> arrayList : grupy) {
				if (lista.isEmpty())
					break;
				if (arrayList.size() == j)
					continue;
				int x = random.nextInt(lista.size());
				Bracia b = lista.get(x);
				lista.remove(x);
				if (b.getStan() == 2) {
					if (g.malzenstwaJakoDwa) {
						Malzenstwo m = (Malzenstwo) b;
						arrayList.add(m.getAdam());
						arrayList.add(m.getEwa());
					} else
						arrayList.add(b);
				} else
					arrayList.add(b);

			}
			j++;
		}

	}

	public void losowaniePrio() {

		for (int i = 1; i < 6; i++) { 					// przygotowanie listy wg ustawien

			lista = new ArrayList<Bracia>();
			for (Bracia bracia : org) {
				if (bracia.getPrio() == i) { 			// jesli dana osoba jest w danej
														// grupie prio
					if (bracia.getStan() == 2) { 		// czy jest w malzenstwie
						if (g.rozdziel) { 				// czy chcemy rozdzielac malzenstwa
							Malzenstwo m = (Malzenstwo) bracia;
							lista.add(m.getAdam());
							lista.add(m.getEwa());
						} else
							lista.add(bracia);
					} else
						lista.add(bracia);
				}
			}
			int j = 1;
			Random random = new Random();
			while (!lista.isEmpty()) { // losowanie

				for (ArrayList<Bracia> arrayList : grupy) {
					if (lista.isEmpty())
						break;
					if (arrayList.size() == j)
						continue;
					int x = random.nextInt(lista.size());
					Bracia b = lista.get(x);
					lista.remove(x);
					if (b.getStan() == 2) {
						if (g.malzenstwaJakoDwa) {
							Malzenstwo m = (Malzenstwo) b;
							arrayList.add(m.getAdam());
							arrayList.add(m.getEwa());
						} else
							arrayList.add(b);
					} else
						arrayList.add(b);

				}
				j++;
			}

		}

		// lista = new ArrayList<Bracia>();
		// for (Bracia bracia : org) {
		// if (bracia.getPrio() == 0) {
		// if (bracia.getStan() == 2) {
		// if (g.rozdziel) {
		// Malzenstwo m = (Malzenstwo) bracia;
		// lista.add(m.getAdam());
		// lista.add(m.getEwa());
		// } else
		// lista.add(bracia);
		// } else
		// lista.add(bracia);
		// }
		// }
		// int j = 1;
		// Random random = new Random();
		// while (!lista.isEmpty()) {
		//
		// for (ArrayList<Bracia> arrayList : grupy) {
		// if (lista.isEmpty())
		// break;
		// if (arrayList.size() == j)
		// continue;
		// int x = random.nextInt(lista.size());
		// Bracia b = lista.get(x);
		// lista.remove(x);
		// if (b.getStan() == 2) {
		// if (g.malzenstwaJakoDwa) {
		// Malzenstwo m = (Malzenstwo) b;
		// arrayList.add(m.getAdam());
		// arrayList.add(m.getEwa());
		// } else
		// arrayList.add(b);
		// } else
		// arrayList.add(b);
		//
		// }
		// j++;
		// }

	}

	

	public void losowaniePrio2() {
		boolean var = true;
		do {
			grupy = new ArrayList<ArrayList<Bracia>>();
			for (int i = 0; i < iloscGrup; i++) {
				grupy.add(new ArrayList<Bracia>());
			}
			for (int i = 1; i < 6; i++) {

				lista = new ArrayList<Bracia>(); 			// przygotowanie listy wg
															// ustawien
				for (Bracia bracia : org) {
					if (bracia.getPrio() == i) { 			// jesli dana osoba jest w
															// danej
															// grupie prio
						if (bracia.getStan() == 2) { 		// czy jest w malzenstwie
							if (g.rozdziel) { 				// czy chcemy rozdzielac
															// malzenstwa
								Malzenstwo m = (Malzenstwo) bracia;
								lista.add(m.getAdam());
								lista.add(m.getEwa());
							} else
								lista.add(bracia);
						} else
							lista.add(bracia);
					}
				}
				// int j = 1;
				Random random = new Random();

				int rozm = lista.size();
				for (Bracia arrayList : lista) {
					if (arrayList.getStan() == 2)
						rozm++;
				}
				rozm /= iloscGrup;
				while (!lista.isEmpty()) { // losowanie

					for (ArrayList<Bracia> arrayList : grupy) {
//						System.out.println("_____________");
						for (int j = 0; j < rozm; j++) {

							if (lista.isEmpty())
								break;
							// if (arrayList.size() == j)
							// continue;
							int x = random.nextInt(lista.size());
							Bracia b = lista.get(x);
							lista.remove(x);
							if (b.getStan() == 2) {
								if (g.malzenstwaJakoDwa) {
									j++;
									Malzenstwo m = (Malzenstwo) b;
									arrayList.add(m.getAdam());
//									System.out.println(m.getName());
									arrayList.add(m.getEwa());
//									System.out.println(m.getName());
								} else {
									arrayList.add(b);
//									System.out.println(b.getName());
								}
							} else {
								arrayList.add(b);
//								System.out.println(b.getName());
							}

							
						}

					}
					rozm = lista.size();
					for (Bracia arrayList : lista) {
						if (arrayList.getStan() == 2)
							rozm++;
					}
					rozm = (int) Math.ceil((double) rozm / iloscGrup);
				}

			}
			
			int rozmiar = org.size();
			if (g.malzenstwaJakoDwa) {
				for (Bracia bracia : org) {
					if (bracia.getStan() == 2) {
						rozmiar++;
					}
				}
			}
			rozmiar  = (int) Math.ceil((double)rozmiar/iloscGrup);
			
			for (ArrayList<Bracia> arrayList : grupy) {
				if (arrayList.size() > rozmiar) {
					var = true;
					break;
				} else {
					var = false;
				}
			}
		} while (var);

	}

}
