package getprint.source.id.getprint.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import getprint.source.id.getprint.module.Order;

/**
 * Created by Chandra on 11/8/2016.
 */
public class SpecialAdapter extends SimpleAdapter {
    private int[] colors = new int[] {Color.parseColor("#fefefe"), Color.parseColor("#d2d3d5")};
    private Context context;
    private String[] from;
    private String[] atr;
    private List<HashMap<String, String>> items;
    public SpecialAdapter(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to, String[] atr) {
        super(context, items, resource, from, to);
        this.context=context;
        this.from=from;
        this.atr=atr;
        this.items=items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ITEMS:", String.valueOf(items.get(position).get("price")));
                Intent intent = new Intent(context, Order.class);
                intent.putExtra("sizeName", String.valueOf(items.get(position).get("sizeName")));
                intent.putExtra("price", String.valueOf(items.get(position).get("price")));
                intent.putExtra("img", String.valueOf(atr[0]));
                intent.putExtra("label", String.valueOf(atr[1]));
                intent.putExtra("bentuk", String.valueOf(atr[2]));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
