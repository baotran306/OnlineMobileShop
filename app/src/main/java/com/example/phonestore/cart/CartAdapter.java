package com.example.phonestore.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonestore.R;
import com.example.phonestore.entity.Phone;
import com.example.phonestore.entity.login.info;
import com.example.phonestore.menu.activityPhone;
import com.example.phonestore.sharedPreferences.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    private Context mContext;
    private List<Phone> mData;
    private ItemClickListener listener;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private static String url = "http://10.0.2.2:5000/get-image/";

    //set method
    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }
    //Defining interface
    public interface ItemClickListener{
        //Achieve the click method, passing the subscript.
        void onItemClick(int position);
    }

    public CartAdapter(Context mContext, List<Phone> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_addcart,parent,false);

        return new CartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        Picasso.with(mContext).load(url+mData.get(position).getImage()).into(holder.imageView);
        holder.textInfor.setText(mData.get(position).getPhoneName());
        holder.textPrice.setText(formatter.format(mData.get(position).getPrice())+" VNĐ");
        holder.textDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                        .setCancelable(false)
                        .setTitle("Confirmation")
                        .setMessage("Do you want to remove this item?")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                List<Phone> temp = new ArrayList<>();
                                info info = DataLocalManager.getInfo();
                                try {
                                    rootNode = FirebaseDatabase.getInstance();
                                    reference = rootNode.getReference("Cart").child(info.getId());
                                    reference.child(String.valueOf(mData.get(position).getId())).removeValue();
                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                                Phone phone = dataSnapshot.getValue(Phone.class);
                                                temp.add(phone);
                                            }
                                            mData=temp;
                                            notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("No",null)
                        .create();
                alertDialog.show();

            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mData.get(position).isSelect){
                    holder.checkBox.setChecked(false);
                    mData.get(position).isSelect = false;
                }else {
                    holder.checkBox.setChecked(true);
                    mData.get(position).isSelect = true;
                }
                listener.onItemClick(position);
            }
        });
      //  holder.bind(position);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, activityPhone.class);
                Phone phone = new Phone();
                phone = mData.get(position);
                intent.putExtra("Phone", phone);
                intent.putExtra("show","show");
                mContext.startActivity(intent);
            }
        });

        holder.btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = mData.get(position).getQuantity();
                i++;
                mData.get(position).setQuantity(i);
                holder.textNumber.setText(String.valueOf(i));
                listener.onItemClick(position);
            }
        });
        holder.btn_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = mData.get(position).getQuantity();
                i--;
                if(i>=1) {
                    mData.get(position).setQuantity(i);
                    holder.textNumber.setText(String.valueOf(i));
                    listener.onItemClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<Phone> getSelectItem(){
        List<Phone> item = new ArrayList<>();
        for (Phone items:mData){
            if(items.isSelect)
                item.add(items);
        }
        return item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        ConstraintLayout constraintLayout;
        private TextView textInfor, textPrice, textNumber, textDelete;
        private CheckBox checkBox;
        private Button btn_increase, btn_decrease;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewCart_id);
            textInfor = (TextView) itemView.findViewById(R.id.informationItem_Cart_id);
            textPrice = (TextView) itemView.findViewById(R.id.priceItemCart_id);
            textNumber = (TextView) itemView.findViewById(R.id.textnumberCart_id);
            textDelete = (TextView) itemView.findViewById(R.id.textDeleteCart_id);
            btn_decrease = (Button) itemView.findViewById(R.id.btn_decrease);
            btn_increase = (Button) itemView.findViewById(R.id.btn_increase);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_cart_id);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.ContrainCart_id);
        }

    }
}
