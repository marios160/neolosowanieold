package pl.mati.neolosowanie;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Grupy extends Activity {

	int pos;
	String nazwa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grupy);
		Bundle tobolek = getIntent().getExtras();
		nazwa = tobolek.getString("id");
		Spinner s = (Spinner) findViewById(R.id.listaGrup);

		Glowna g = MainActivity.glowna;
		List<String> list = new ArrayList<String>();
		for (GrupyTxt x : MainActivity.glowna.grupy) {
			list.add(x.nazwa);
		}

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		s.setAdapter(dataAdapter);

		if (!nazwa.contains("null")) {
			for (GrupyTxt i : MainActivity.glowna.grupy) {
				if (i.nazwa.contains(nazwa)) {
					TextView text = (TextView) findViewById(R.id.grupy);
					TextView nazwa = (TextView) findViewById(R.id.nazwaGr);
					TextView data = (TextView) findViewById(R.id.dataLosowania);
					data.setText(i.data);
					nazwa.setText(i.nazwa);
					text.setText(i.grupy);
					break;
				}

			}

		}

	}

	public void pokaz(View view) {
		Spinner s = (Spinner) findViewById(R.id.listaGrup);
		pos = s.getSelectedItemPosition();
		if (pos == -1)
			return;
		GrupyTxt g = MainActivity.glowna.grupy.get(pos);
		TextView text = (TextView) findViewById(R.id.grupy);
		TextView nazwa = (TextView) findViewById(R.id.nazwaGr);
		TextView data = (TextView) findViewById(R.id.dataLosowania);
		nazwa.setText(g.nazwa);
		text.setText(g.grupy);
		data.setText(g.data);
	}

	public void usun(View view) {
		Spinner s = (Spinner) findViewById(R.id.listaGrup);
		pos = s.getSelectedItemPosition();
		String nazwa2 = MainActivity.glowna.grupy.get(pos).nazwa;
		MainActivity.glowna.grupy.remove(pos);
		Glowna g = MainActivity.glowna;
		List<String> list = new ArrayList<String>();
		for (GrupyTxt x : MainActivity.glowna.grupy) {
			list.add(x.nazwa);
		}

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		s.setAdapter(dataAdapter);
		TextView text = (TextView) findViewById(R.id.grupy);
		TextView nazwa = (TextView) findViewById(R.id.nazwaGr);
		TextView data = (TextView) findViewById(R.id.dataLosowania);
		nazwa.setText("");
		text.setText("");
		data.setText("");
		Toast.makeText(this, "Usuniêto grupê " + nazwa2, Toast.LENGTH_SHORT).show();
		g.setClassFile(g);
	}
	public void aktualizuj(View view) {
	
	
	
	}

}
