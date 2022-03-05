package pl.mati.neolosowanie;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class WyslijEmail extends Activity {

	GrupyTxt g;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wyslij_email);

		Spinner s = (Spinner) findViewById(R.id.spinerEmail);

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
	}

	public void wyslij(View view) {
		Spinner s = (Spinner) findViewById(R.id.spinerEmail);
		int pos = s.getSelectedItemPosition();
		if (pos == -1)
			return;
		this.g = MainActivity.glowna.grupy.get(pos);
		createAlertDialogWithButtons().show();

	}

	private Dialog createAlertDialogWithButtons() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Wysy³anie Emaila");
		dialogBuilder.setMessage("Czy na pewno chcesz wys³aæ emaila z grupami: " + this.g.nazwa + "?");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				email();
			}
		});
		dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {

			}
		});
		return dialogBuilder.create();
	}

	public void email() {
		ArrayList<String> listM = new ArrayList<String>();

		int i = 0;
		for (Bracia b : MainActivity.glowna.listaBraci) {
			if (b.getStan() == 2) {
				Malzenstwo m = (Malzenstwo) b;
				listM.add(m.getAdam().getEmail());
				i++;
				listM.add(m.getEwa().getEmail());
			} else {
				Samotna s = (Samotna) b;
				listM.add(s.getEmail());
			}
			i++;
		}
		String[] mailList = new String[listM.size()];
		listM.toArray(mailList);

		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, mailList);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, this.g.nazwa);
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Losowanie z dnia: " + this.g.data + "\n\n" + this.g.grupy);

		try {
			startActivity(Intent.createChooser(emailIntent, "Wyœlij Emaila z Grupami"));
			Log.i("Finished sending email...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}
}
