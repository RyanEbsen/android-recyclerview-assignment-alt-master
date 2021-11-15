package com.ualr.recyclerviewassignment.Utils;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;


public class AdapterListBasic extends RecyclerView.Adapter{

    private List<Inbox> mItems;
    private Context mContext;

    public AdapterListBasic(Context context, List<Inbox> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        vh = new InboxViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        InboxViewHolder viewHolder = (InboxViewHolder)holder;
        Inbox p = mItems.get(position);
        viewHolder.name.setText(p.getFrom());
        viewHolder.date.setText(p.getDate());
        viewHolder.email.setText(p.getEmail());
        viewHolder.message.setText(p.getMessage());
    }

    @Override
    public int getItemCount(){
        return this.mItems.size();
    }

    public class InboxViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public TextView email;
        public TextView message;
        public View lyt_parent;

        public InboxViewHolder(View v) {
            super(v);

            name = v.findViewById(R.id.name);
            date = v.findViewById(R.id.date);
            email = v.findViewById(R.id.email);
            message = v.findViewById(R.id.message);
            lyt_parent = v.findViewById(R.id.lyt_parent);

           lyt_parent.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    mOnItemClickListener.onItemCLick(view, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemCLick(View view, Inbox obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;
    }

    public void addItem(int position, Inbox inbox){
        mItems.add(position, inbox);
        notifyItemInserted(position);
    }

}

