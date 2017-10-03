package getprint.source.id.getprint.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import getprint.source.id.getprint.R;
import getprint.source.id.getprint.module.Order;


/**
 * Created by Chandra on 7/30/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private final String[] prodId;
    private final String[] prodImg;
    private final String[] label;

    public RecyclerViewAdapter(Context context, String[] prodId, String[] prodImg, String[] label) {
        this.prodId= prodId;
        this.prodImg= prodImg;
        this.label= label;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.prodIdText.setText(prodId[position]);
        holder.prodNameText.setText(label[position]);
        holder.imgText.setText(prodImg[position]);
        holder.labelButton.setText("\u2022 "+label[position]+" \u2022");
        //Resources resources = mContext.getResources();
        //int source = resources.getIdentifier(prodImg[position], "drawable", mContext.getPackageName());
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(prodImg[position])
                .into(holder.prodImggbr, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (holder.progressBar != null) {
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return prodId.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView prodIdText;
        private TextView prodNameText;
        private TextView imgText;
        private ImageView prodImggbr;
        private Button labelButton;
        private ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            prodIdText = (TextView)itemView.findViewById(R.id.myId);
            prodNameText = (TextView)itemView.findViewById(R.id.prodName);
            imgText = (TextView)itemView.findViewById(R.id.imgGone);
            prodImggbr = (ImageView)itemView.findViewById(R.id.imageView);
            labelButton = (Button)itemView.findViewById(R.id.button_r);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loading);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Order.class);
                    intent.putExtra("prodId", String.valueOf(prodIdText.getText()));
                    intent.putExtra("label", String.valueOf(prodNameText.getText()));
                    intent.putExtra("img", String.valueOf(imgText.getText()));
                    intent.putExtra("sizeName", "6cm");
                    intent.putExtra("price", "Rp.45.000,-/100pcs");
                    intent.putExtra("bentuk", "");
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
