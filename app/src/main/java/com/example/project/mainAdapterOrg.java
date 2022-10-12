package com.example.project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import de.hdodenhof.circleimageview.CircleImageView;



public class mainAdapterOrg extends FirebaseRecyclerAdapter<MainModel, mainAdapterOrg.myviewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public mainAdapterOrg(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mainAdapterOrg.myviewHolder holder, final int position, @NonNull MainModel model) {

        holder.nombre.setText(model.getNombre());
        holder.cantidadAct.setText(String.valueOf(model.getCantidadAct()));
        holder.cantidadDese.setText(String.valueOf(model.getCantidadDes()));

        Glide.with(holder.img.getContext())
                .load(model.getTulr())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1200)
                        .create();
                //dialogPlus.show();
                View view = dialogPlus.getHolderView();
                EditText name = view.findViewById(R.id.txtName);
                EditText CantidadDes = view.findViewById(R.id.txtDese);
                EditText CantidadAct= view.findViewById(R.id.txtAct);
                EditText turl = view.findViewById(R.id.txtImage);
                Button btnUpdate = view. findViewById(R.id.btnUpdate);

                name.setText(model.getNombre());
                CantidadDes.setText(model.getCantidadDes());
                CantidadAct.setText(model.getCantidadAct());
                turl.setText(model.getTulr());

                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("CantidadAct", CantidadAct.getText().toString());
                        map.put("CantidadDes",CantidadDes.getText().toString());
                        map.put("turl",turl.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Inventario")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>(){
                                    @Override
                                    public void onSuccess(Void unused){
                                        Toast.makeText(holder.nombre.getContext(),"Los datos se actualizaron",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                    })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.nombre.getContext(),"Los datos se NO actualizaron",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.nombre.getContext());
                        builder.setTitle("¿Seguro?");
                        builder.setMessage("Los datos eliminados no podran ser eliminados");

                        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Inventario")
                                        .child(getRef(position).getKey()).removeValue();
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.nombre.getContext(),"Cancelado", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });
            }
        });

    }

    @NonNull
    @Override
    public mainAdapterOrg.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_itemorg,parent,false);
        return new mainAdapterOrg.myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nombre, cantidadDese,cantidadAct;

        Button btnEdit, btnDelete;

        public myviewHolder(@Nonnull View itemView){
            super(itemView);

            img= (CircleImageView)itemView.findViewById(R.id.img1);
            nombre = (TextView)itemView.findViewById(R.id.producto);
            cantidadDese =(TextView)itemView.findViewById(R.id.CantidadDese);
            cantidadAct =(TextView)itemView.findViewById(R.id.CantidadAct);
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }

    }


}

