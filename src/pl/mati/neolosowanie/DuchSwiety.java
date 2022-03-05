package pl.mati.neolosowanie;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DuchSwiety extends Activity {

	TextView czas;
	boolean stop = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duch_swiety);
		czas = (TextView) findViewById(R.id.czas);

		Thread myThread = null;

		Runnable runnable = new CountDownRunner();
		myThread = new Thread(runnable);
		myThread.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		doWork();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duch_swiety, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void doWork() {
		runOnUiThread(new Runnable() {
			public void run() {
				try {
					Date dt = new Date();
					czas.setText(Long.toString(dt.getTime()));
				} catch (Exception e) {
				}
			}
		});
	}

	class CountDownRunner implements Runnable {
		// @Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					doWork();
					Thread.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Exception e) {
				}
			}
		}
	}
}
