package com.example.threeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;

public class ItemAdapter {

    private Context context;
    private List<Item> itemList;

    public ItemAdapter(Context context, int item_task, List<Item> taskList) {
        this.context = context;
        this.itemList = itemList;
    }

    private static class ViewHolder {
        TextView textViewTitle;
        CheckBox checkBoxCompleted;
    }
}
