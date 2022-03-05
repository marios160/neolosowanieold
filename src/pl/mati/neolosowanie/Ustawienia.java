package pl.mati.neolosowanie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Ustawienia extends Activity {

	String nazwag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ustawienia);
		Glowna g = MainActivity.glowna;
//		CheckBox prio = (CheckBox) findViewById(R.id.checkPrioOsobe);
//		prio.setChecked(g.prio);
		CheckBox rozdziel = (CheckBox) findViewById(R.id.rozdziel);
		rozdziel.setChecked(g.rozdziel);
		CheckBox malz = (CheckBox) findViewById(R.id.malzenstwaJedno);
		malz.setChecked(g.malzenstwaJakoDwa);
		CheckBox malzPierw = (CheckBox) findViewById(R.id.malzenstwaPierwsze);
		malzPierw.setChecked(g.malzenstwaPierwsze);
		TextView ilosc = (TextView) findViewById(R.id.ilosc);
		ilosc.setText(Integer.toString(g.iloscGrup));
	}

	public void grupyPriorytetowe(View view) {
		Intent intent = new Intent(this, GrupyPriorytetowe.class);
		startActivity(intent);
	}

	public void zapisz(View view) {
		Glowna g = MainActivity.glowna;
//		CheckBox prio = (CheckBox) findViewById(R.id.checkPrioOsobe);
//		g.prio = prio.isChecked();
		CheckBox rozdziel = (CheckBox) findViewById(R.id.rozdziel);
		g.rozdziel = rozdziel.isChecked();
		CheckBox malz = (CheckBox) findViewById(R.id.malzenstwaJedno);
		g.malzenstwaJakoDwa = malz.isChecked();
		CheckBox malzPierw = (CheckBox) findViewById(R.id.malzenstwaPierwsze);
		g.malzenstwaPierwsze = malzPierw.isChecked();
		EditText ilosc = (EditText) findViewById(R.id.ilosc);
		g.iloscGrup = Integer.parseInt(ilosc.getText().toString());
		EditText nazwa = (EditText) findViewById(R.id.nazwaGrup);
		nazwag = nazwa.getText().toString();
		g.setClassFile(g);

		createAlertDialogWithButtons().show();

	}

	private Dialog createAlertDialogWithButtons() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Modlitwa");
		dialogBuilder.setMessage("Pomódl siê o Ducha Œwiêtego!");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("Losuj", new Dialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				losowanie();
			}
		});
		dialogBuilder.setNegativeButton("Wróæ", new Dialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {

			}
		});
		return dialogBuilder.create();
	}

	public void losowanie() {
		Losowanie l = new Losowanie(nazwag);
		Intent intent = new Intent(this, Grupy.class);
		intent.putExtra("id", nazwag);
		startActivity(intent);
		finish();
	}
}
