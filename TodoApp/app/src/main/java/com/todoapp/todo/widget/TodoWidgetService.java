package com.todoapp.todo.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class TodoWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TodoViewsFactory(getApplicationContext(), intent);
    }
}
