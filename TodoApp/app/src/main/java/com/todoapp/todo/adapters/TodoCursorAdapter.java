package com.todoapp.todo.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todoapp.todo.R;
import com.todoapp.todo.activities.TodoDetailsActivity;
import com.todoapp.todo.database.Todo;
import com.todoapp.todo.utils.Constants;
import com.todoapp.todo.utils.CursorRecyclerAdapter;

import java.text.SimpleDateFormat;


public class TodoCursorAdapter extends CursorRecyclerAdapter<TodoCursorAdapter.TodoViewHolder> {

    private Context context;

    public TodoCursorAdapter(Context context) {
        super(null);
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolderCursor(TodoViewHolder holder, Cursor cursor) {
        final Todo todo = Todo.fromCursor(cursor);
        holder.todoTitleTextView.setText(todo.getTitle().replace("\n", " "));
        holder.todoDateTimeTextView.setText(todo.getDateTime().getTimeInMillis() == 0 ? "" : DateFormat.is24HourFormat(context) ? new SimpleDateFormat("MMMM dd, yyyy  h:mm").format(todo.getDateTime().getTime()) : new SimpleDateFormat("MMMM dd, yyyy  h:mm a").format(todo.getDateTime().getTime()));
        holder.todoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TodoDetailsActivity.class);
                intent.putExtra(Constants.TODO_ID, todo.getId());
                context.startActivity(intent);
            }
        });
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout todoLayout;
        public TextView todoTitleTextView;
        public TextView todoDateTimeTextView;

        public TodoViewHolder(View itemView) {
            super(itemView);
            todoLayout = itemView.findViewById(R.id.todo_layout_item);
            todoTitleTextView = itemView.findViewById(R.id.todo_title_text_view_item);
            todoDateTimeTextView = itemView.findViewById(R.id.todo_date_time_text_view_item);
        }
    }

}
