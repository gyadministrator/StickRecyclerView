package com.fld.stickrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 仅拖拽排序
 * Created by YoKeyword on 16/1/4.
 */
public class DragAdapter extends RecyclerView.Adapter<DragAdapter.DragViewHolder> implements OnItemMoveListener {
    private List<String> mItems;
    private LayoutInflater mInflater;

    public DragAdapter(Context context, List<String> items) {
        mInflater = LayoutInflater.from(context);
        this.mItems = items;
    }

    @NonNull
    @Override
    public DragViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DragViewHolder(mInflater.inflate(R.layout.item_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(DragViewHolder holder, int position) {
        holder.tv.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        String item = mItems.get(fromPosition);
        mItems.remove(fromPosition);
        mItems.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    class DragViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener{
        TextView tv;

        DragViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemFinish() {
            itemView.setBackgroundColor(0);
        }
    }
}
