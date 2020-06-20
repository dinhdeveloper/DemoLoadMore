package com.dinh.demoloadmore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dinh.demoloadmore.MainActivity;
import com.dinh.demoloadmore.R;
import com.dinh.demoloadmore.model.Song;

import java.util.List;

public class SongNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Song> songList;
    Context context;
    int total;
    boolean isLoading;

    public SongNewAdapter(List<Song> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            // Here Inflating your recyclerview item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            // Here Inflating your header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header_rc, parent, false);
            return new HeaderViewHolder(itemView);
        } else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.rc_header.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            SongAdapter songAdapter = new SongAdapter(songList, context);
            headerViewHolder.rc_header.setAdapter(songAdapter);
        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Glide.with(context).load(songList.get(position).getImages()).into(itemViewHolder.image);
            itemViewHolder.text.setText(songList.get(position).getName());
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rc_header;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            rc_header = itemView.findViewById(R.id.rc_header);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

//    public SongNewAdapter(List<Song> songList, Context context) {
//        this.songList = songList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.custom_item,parent,false);
//        return new SongNewAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setText(songList.get(position).getName());
//        Glide.with(context).load(songList.get(position).getImages()).into(holder.imageView);
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView textView;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.image);
//            textView = itemView.findViewById(R.id.text);
//        }
//    }
}
