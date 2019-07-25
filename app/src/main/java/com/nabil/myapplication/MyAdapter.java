package com.nabil.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ProductViewHolder>  {

    private Context context;
    private List<Demo> demos;



    public MyAdapter(Context context, List<Demo> demos) {
        this.context = context;
        this.demos = demos;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_layout, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        final Demo demo = demos.get(i);
        productViewHolder.textview1.setText(demo.getTextview1());
        productViewHolder.textview2.setText(demo.getTextview2());


        productViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv1 = demo.getTextview1();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("text_1", tv1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
                Animatoo.animateSlideRight(context);

            }
        });



    }





    @Override
    public int getItemCount() {
        return demos.size();
    }



    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textview1, textview2;
        CardView cardView;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textview1 = itemView.findViewById(R.id.textview1);
            textview2 = itemView.findViewById(R.id.textview2);
            cardView = itemView.findViewById(R.id.cardview);
        }

    }
}
