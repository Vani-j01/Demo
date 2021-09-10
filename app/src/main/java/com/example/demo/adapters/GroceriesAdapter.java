package com.example.demo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.model.ProductsDataModel;
import com.example.demo.views.Details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GroceriesAdapter extends RecyclerView.Adapter<GroceriesAdapter.ViewHolder> {

private ArrayList<ProductsDataModel> productsDataModels;
private Context context;
private  int count=1;

public GroceriesAdapter(ArrayList<ProductsDataModel> productsDataModels, Context context) {
        this.productsDataModels = productsDataModels;
        this.context = context;
        }

    @NonNull
    @Override
    public GroceriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroceriesAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.groceries,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductsDataModel model = productsDataModels.get(position);

        holder.title.setText(model.getTitle());
        Picasso.get().load(model.getImage()).into(holder.image);

        if (count%2==0){
            holder.cardView.setCardBackgroundColor(holder.cardView.getContext().getResources().getColor(R.color.pink));
            holder.relativeLayout.setBackgroundColor(holder.relativeLayout.getContext().getResources().getColor(R.color.pink));

        }
        else{
            holder.cardView.setCardBackgroundColor(holder.cardView.getContext().getResources().getColor(R.color.blue));
            holder.relativeLayout.setBackgroundColor(holder.relativeLayout.getContext().getResources().getColor(R.color.blue));
        }
        count++;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,Details.class);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("quantity",model.getQuantity());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("image",model.getImage());
                context.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return productsDataModels.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image;
        private CardView cardView;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.grc_image);
            title = itemView.findViewById(R.id.grc_title);
            cardView = itemView.findViewById(R.id.grc_card);
            relativeLayout= itemView.findViewById(R.id.grc_rel);
        }
    }
}
