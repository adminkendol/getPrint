package getprint.source.id.getprint.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Chandra on 11/22/2016.
 */
public class SpecialAdapterB extends SimpleAdapter {
    private int[] colors = new int[] {Color.parseColor("#fefefe"), Color.parseColor("#d2d3d5")};
    private Context context;
    private String[] from;
    private String[] atr;
    private List<HashMap<String, String>> items;
    public SpecialAdapterB(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to, String[] atr) {
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
        return view;
    }
}
