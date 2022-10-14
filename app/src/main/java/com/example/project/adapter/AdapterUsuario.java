package com.example.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.pojo.Usuario;

import java.util.List;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.viewholderusuarios> {
    // Lista que vamos a llamar en el recyclerview
    // Lista de "Usuario" porque así se llama la estructura en usuario.java

    List<Usuario> usuarioList;

    // Constructor de Usuario para llamarlo desde el mainActivity
    public AdapterUsuario(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public viewholderusuarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflar un layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_usuarios,parent,false);
        viewholderusuarios holder = new viewholderusuarios(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderusuarios holder, int position) {
        // Cuando se llame a cada uno de los objetos, los llama solo por ms y el tipo de objeto
        Usuario ms = usuarioList.get(position);

        // Adaptador configurado
        holder.tv_email.setText(ms.getEmail());
        holder.tv_nombre.setText(ms.getNombre());



    }

    @Override
    // Cuenta los resgistros que estan dentro del adaptador
    public int getItemCount() {
        return usuarioList.size();
    }

    public class viewholderusuarios extends RecyclerView.ViewHolder {

        // Declarando los valores del layout
        TextView tv_email, tv_nombre;


        public viewholderusuarios(@NonNull View itemView) {
            super(itemView);

            tv_email = itemView.findViewById(R.id.tv_email);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);
        }
    }
}
