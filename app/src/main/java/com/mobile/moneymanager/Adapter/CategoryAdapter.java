package com.mobile.moneymanager.Adapter;

import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.R;

import java.util.Arrays;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private int layout;
    private List<Category> categoryList;
    private String value;

    public CategoryAdapter(Context mContext, int layout, List<Category> categoryList) {
        this.mContext = mContext;
        this.layout = layout;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private  class  viewHolder{
        CheckedTextView checkedTextView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            holder=new viewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            holder.checkedTextView= (CheckedTextView) convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        }else{
            holder= (viewHolder) convertView.getTag();
        }
        Category category = categoryList.get(position);
        holder.checkedTextView.setText(category.getName());
//        for (int i = 0; i < Title.length; i++) {
//            // get the value at current array index
//            String arrayValue = Title[i];
//            // compare values
////            if (afterCategory.equals(arrayValue)) {
////                value = "Checked";
////                simpleCheckedTextView.setCheckMarkDrawable(R.drawable.correct);
////                simpleCheckedTextView.setChecked(true);
////            }else{
////                value = "un-Checked";
////                simpleCheckedTextView.setCheckMarkDrawable(null);
////                simpleCheckedTextView.setChecked(false);
////            }
//        }
//
        return (convertView);
    }
}
