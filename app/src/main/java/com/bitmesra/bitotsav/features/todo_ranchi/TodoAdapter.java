package com.bitmesra.bitotsav.features.todo_ranchi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.todoranchi.TodoModel;
import com.bitmesra.bitotsav.ui.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 7/3/17 for Bitotsav.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final Context context;
    private List<TodoModel> items;

    public TodoAdapter(Context context, List<TodoModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        return new TodoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_todo_partial_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        TodoModel item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        // Picasso.with(context).load(item.getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.todo_partial_item_title)
        CustomTextView title;
        @BindView(R.id.todo_partial_item_text)
        CustomTextView desc;
        @BindView(R.id.todo_partial_item_image)
        ImageView image;

        TodoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}