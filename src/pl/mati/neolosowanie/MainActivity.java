package pl.mati.neolosowanie;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	static Glowna glowna;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File f = Environment.getExternalStorageDirectory();
		File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "NeoLosowanie");
		if (!folder.exists()) {
			folder.mkdir();
		}
		glowna = new Glowna(getApplicationContext());

	}

	public void losowanie(View view) {
		ustawienia();
	}

	public void duchSwiety(View view) {
		Intent intent = new Intent(this, DuchSwiety.class);
		startActivity(intent);
	}

	public void email(View view) {
		Intent intent = new Intent(this, WyslijEmail.class);
		startActivity(intent);
	}

	public void grupy(View view) {
		Intent intent = new Intent(this, Grupy.class);
		intent.putExtra("id", "null");
		startActivity(intent);
	}

	public void ustawienia() {
		Intent intent = new Intent(this, Ustawienia.class);
		startActivity(intent);
	}

	public void pomoc(View view) {
		Intent intent = new Intent(this, Pomoc.class);
		startActivity(intent);
	}
	
	public void aktualizuj(View view) {
		
		Intent intent = new Intent(this, Aktualizacja.class);
		startActivity(intent);
		
	}

}
