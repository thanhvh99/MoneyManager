package com.mobile.moneymanager.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.mobile.moneymanager.Adapter.CategoryAdapter;
import com.mobile.moneymanager.Database.DatabaseConect;
import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.R;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ExpenseFragment extends Fragment {
    public ListView listView;
    public String outCategory;
    public String afterCategory="";
    private ArrayList<Category> categoryList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_expense, container, false);
        listView=view.findViewById(R.id.list_expense);
        categoryList=new ArrayList<>();
        DatabaseConect database= new DatabaseConect(getContext(),"MoneyManager",null,1);
        Cursor dataExpense= database.getData("SELECT * FROM Category");
        while (dataExpense.moveToNext()){
            if(dataExpense.getInt(2)==0){
                String nameCategory= dataExpense.getString(1);
                int id = dataExpense.getInt(0);
                categoryList.add(new Category(id,nameCategory,0));
            }
        }
        CategoryAdapter categoryAdapter= new CategoryAdapter(this.getContext(),R.layout.item_expense, categoryList);
        listView.setAdapter(categoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category category = categoryList.get(position);
                outCategory=category.getName();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", category);
                getActivity().setResult(Activity.RESULT_OK,returnIntent);
                getActivity().finish();
            }
        });
        categoryAdapter.notifyDataSetChanged();
        return view;
    }

}