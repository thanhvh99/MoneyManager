package com.mobile.moneymanager.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.moneymanager.Adapter.CategoryAdapter;
import com.mobile.moneymanager.Database.DatabaseConect;
import com.mobile.moneymanager.Model.Category;
import com.mobile.moneymanager.R;

import java.util.ArrayList;


public class IncomeFragment extends Fragment {
    public ListView listView;
    public String outCategory;
    public String afterCategory="";
    private ArrayList<Category> incomeList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_income, container, false);
        listView=view.findViewById(R.id.list_income);
        incomeList=new ArrayList<>();
        DatabaseConect database= new DatabaseConect(getContext(),"MoneyManager",null,1);
        Cursor dataIncome= database.getData("SELECT * FROM Category");
        while (dataIncome.moveToNext()){
            if(dataIncome.getInt(2)==1){
                String nameCategory= dataIncome.getString(1);
                int id = dataIncome.getInt(0);
                incomeList.add(new Category(id,nameCategory,1));
            }
        }
        CategoryAdapter categoryAdapter= new CategoryAdapter(this.getContext(),R.layout.item_expense, incomeList);
        listView.setAdapter(categoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category category = incomeList.get(position);
                outCategory=category.getName();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",category);
                getActivity().setResult(Activity.RESULT_OK,returnIntent);
                getActivity().finish();
            }
        });
        categoryAdapter.notifyDataSetChanged();
        return view;
    }
}