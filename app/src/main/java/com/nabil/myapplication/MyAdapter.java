package com.nabil.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ProductViewHolder>  {

    private final Context context;
    private final List<Demo> demos;

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
        productViewHolder.textView1.setText(demo.getTextView1());
        productViewHolder.textView2.setText(demo.getTextView2());
        productViewHolder.cardView.setOnClickListener(v -> {
            String tv1 = demo.getTextView1();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("text_1", tv1);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            ((Activity)context).finish();
            Animatoo.animateSlideRight(context);
        });
    }

    @Override
    public int getItemCount() {
        return demos.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2;
        CardView cardView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
