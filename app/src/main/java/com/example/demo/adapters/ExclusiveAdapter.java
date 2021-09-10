package com.example.demo.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.model.ProductsDataModel;
import com.example.demo.views.Details;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ExclusiveAdapter extends RecyclerView.Adapter<ExclusiveAdapter.ViewHolder> {

    private ArrayList<ProductsDataModel> productsDataModels;
    private Context context;
    private DatabaseReference reference;

    public ExclusiveAdapter(ArrayList<ProductsDataModel> productsDataModels, Context context, DatabaseReference reference) {
        this.productsDataModels = productsDataModels;
        this.context = context;
        this.reference= reference;
    }

    @NonNull
    @Override
    public ExclusiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExclusiveAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductsDataModel model = productsDataModels.get(position);
        Log.e("TAG", "ExclusiveAdapter: "+model );

        String tag = model.getTag();

        holder.title.setText(model.getTitle());
        holder.quantity.setText(model.getQuantity());
        holder.price.setText("$"+model.getPrice());
        holder.count.setText(Integer.toString(model.getCount()));
        Picasso.get().load(model.getImage()).into(holder.image);

        Log.e("TAG", "onBindViewHolder: "+ model.getTitle() );

        if (model.getCount()<=0) {
            holder.count.setVisibility(View.GONE);
            holder.subtract.setVisibility(View.GONE);
        }



        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = model.getId();
                int temp = model.getCount() + 1;
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Products").document(id).update("count", temp);
                notifyDataSetChanged();

            }
        });

        holder.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = model.getId();
                int temp = model.getCount() - 1;
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Products").document(id).update("count", temp);
                notifyDataSetChanged();
            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("quantity",model.getQuantity());
                intent.putExtra("image",model.getImage());
                context.startActivity(intent);
            }
        });


        //For Best Sell
        if (tag.equals("best selling")){
            holder.quantity.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return productsDataModels.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, quantity,price,count;
        private ImageView image;
        private ImageButton add, subtract;
        private CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.add);
            subtract = itemView.findViewById(R.id.subtract);
            count = itemView.findViewById(R.id.itemValue);
            cardView = itemView.findViewById(R.id.product_card);
        }
    }
}
