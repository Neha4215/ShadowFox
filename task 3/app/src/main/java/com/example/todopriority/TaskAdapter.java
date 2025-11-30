package com.example.todopriority;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.VH> {

    public interface Listener {
        void onToggleComplete(Task task, boolean isChecked);
        void onDelete(Task task);
    }

    private final LayoutInflater inflater;
    private final Listener listener;
    private final List<Task> items = new ArrayList<>();

    public TaskAdapter(Context ctx, Listener listener) {
        this.inflater = LayoutInflater.from(ctx);
        this.listener = listener;
    }

    public void submitList(List<Task> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_task, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Task t = items.get(position);
        h.check.setOnCheckedChangeListener(null);
        h.check.setChecked(t.isCompleted());
        h.title.setText(t.getTitle());
        h.title.setPaintFlags(t.isCompleted()
                ? h.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                : h.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        // Priority label + color
        int color;
        String label;
        switch (t.getPriority()) {
            case Task.PRIORITY_HIGH:
                color = ContextCompat.getColor(h.itemView.getContext(), R.color.priority_high);
                label = h.itemView.getContext().getString(R.string.high);
                break;
            case Task.PRIORITY_MEDIUM:
                color = ContextCompat.getColor(h.itemView.getContext(), R.color.priority_medium);
                label = h.itemView.getContext().getString(R.string.medium);
                break;
            default:
                color = ContextCompat.getColor(h.itemView.getContext(), R.color.priority_low);
                label = h.itemView.getContext().getString(R.string.low);
        }
        h.priority.setText(label);
        h.priority.setTextColor(color);

        h.check.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onToggleComplete(t, isChecked));
        h.btnDelete.setOnClickListener(v -> listener.onDelete(t));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        CheckBox check;
        TextView title;
        TextView priority;
        ImageButton btnDelete;
        VH(@NonNull View itemView) {
            super(itemView);
            check = itemView.findViewById(R.id.checkComplete);
            title = itemView.findViewById(R.id.textTitle);
            priority = itemView.findViewById(R.id.textPriority);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
