package pl.mati.neolosowanie;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GrupyPriorytetowe extends Activity {

	private ListView list;
	private PrioAdapter adapter;
	int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grupy_priorytetowe);

	}

	public void pokaz(View view) {

		Spinner s = (Spinner) findViewById(R.id.listaGrup);
		pos = s.getSelectedItemPosition() + 1;
		list = (ListView) findViewById(R.id.listaPrio);
		adapter = new PrioAdapter(this, R.layout.grupy_prio, MainActivity.glowna.listaBraci, pos);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView text = (TextView) view.findViewById(R.id.prioName);
				Bracia s = (Bracia) parent.getItemAtPosition(position);
				if (s.getPrio() == pos) {
					text.setTextColor(Color.RED);
					s.setPrio(5);
				} else {
					text.setTextColor(Color.GREEN);
					s.setPrio(pos);
				}

			}

		});
		MainActivity.glowna.setClassFile(MainActivity.glowna);

	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
	}

}
