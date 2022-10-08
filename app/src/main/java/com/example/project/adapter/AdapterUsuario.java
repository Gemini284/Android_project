package com.example.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.bd.Usuario;

import java.util.List;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.viewholderusuarios> {

    List<Usuario> usuarioList;

    public AdapterUsuario(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public viewholderusuarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_usuarios,parent,false);
        viewholderusuarios holder = new viewholderusuarios(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderusuarios holder, int position) {

        Usuario ms = usuarioList.get(position);

        holder.tv_email.setText(ms.getEmail());
        holder.tv_nombre.setText(ms.getNombre());



    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class viewholderusuarios extends RecyclerView.ViewHolder {

        TextView tv_email, tv_nombre;


        public viewholderusuarios(@NonNull View itemView) {
            super(itemView);

            tv_email = itemView.findViewById(R.id.tv_email);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);
        }
    }
}
