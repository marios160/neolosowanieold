package pl.mati.neolosowanie;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrioAdapter extends ArrayAdapter<Bracia> {

	Context context;
	int textVievResourceId;
	List<Bracia> objects = null;
	int grupa;

	public PrioAdapter(Context context, int textViewResourceId, List<Bracia> objects, int grupa) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textVievResourceId = textViewResourceId;
		this.objects = objects;
		this.grupa = grupa;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(R.layout.grupy_prio, parent, false);
		}

		Bracia object = objects.get(position);

		TextView check = (TextView) row.findViewById(R.id.prioName);
		if (object.getStan() == 1) {
			Samotna ob = (Samotna) object;
			check.setText(ob.getImie() + ob.getNazwisko());
		} else {
			check.setText(object.getNazwisko());
		}

		if (object.getPrio() == 0)
			check.setTextColor(Color.BLACK);
		else if (object.getPrio() == grupa)
			check.setTextColor(Color.GREEN);
		else
			check.setTextColor(Color.RED);

		return row;
	}

}
