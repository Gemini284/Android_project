package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.ktx.Firebase;

import javax.annotation.Nonnull;

import de.hdodenhof.circleimageview.CircleImageView;



public class mainAdapter extends FirebaseRecyclerAdapter<MainModel,mainAdapter.myviewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public mainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, int position, @NonNull MainModel model) {

        holder.nombre.setText(model.getNombre());
        holder.cantidadAct.setText(String.valueOf(model.getCantidadAct()));
        holder.cantidadDese.setText(String.valueOf(model.getCantidadDes()));

        Glide.with(holder.img.getContext())
                .load(model.getTulr())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nombre, cantidadDese,cantidadAct;

        public myviewHolder(@Nonnull View itemView){
            super(itemView);

            img= (CircleImageView)itemView.findViewById(R.id.img1);
            nombre = (TextView)itemView.findViewById(R.id.producto);
            cantidadDese =(TextView)itemView.findViewById(R.id.CantidadDese);
            cantidadAct =(TextView)itemView.findViewById(R.id.CantidadAct);

        }

    }


}
