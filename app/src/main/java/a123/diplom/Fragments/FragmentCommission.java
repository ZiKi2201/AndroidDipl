package a123.diplom.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import a123.diplom.Adapter.CommissionAdapter;
import a123.diplom.Group;
import a123.diplom.Interface.API;
import a123.diplom.Model.Commission.Comm;
import a123.diplom.Model.Commission.JSONcomm;
import a123.diplom.Model.Student.JSONstud;
import a123.diplom.Model.Sum.Sum;
import a123.diplom.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a123.diplom.CountSecretary.coins;
import static a123.diplom.CountSecretary.f1;
import static a123.diplom.CountSecretary.id;
import static a123.diplom.CountSecretary.id_std;
import static a123.diplom.Login.savedKey;


public class FragmentCommission extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private ArrayList<Comm> comm;
    private CommissionAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button btnRemove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commission, container, false);
        btnRemove = (Button) view.findViewById(R.id.btn_remove);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_com);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        if(coins!=0){
            btnRemove.setText("Изменить");
        }else {
            btnRemove.setText("Посчитать");
        }
        btnRemove.setOnClickListener(btn);
        Log.d("LoginActivity", "stdId:" + id_std);
        Log.d("LoginActivity", "key:" + savedKey);
        loadData();


   return view;

    }

    public void loadData() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        API.Converter.getConvert().getComm(savedKey,id_std).enqueue(new Callback<JSONcomm>() {
            @Override
            public void onResponse(Call<JSONcomm> call, Response<JSONcomm> response) {

                JSONcomm jsonResponse = response.body();
                if (!jsonResponse.isValid()){
                    return;
                }
                comm = new ArrayList<>(jsonResponse.getComm());
                adapter = new CommissionAdapter(comm);
                recyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<JSONcomm> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    View.OnClickListener btn = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (coins != 0) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Изменение итогового балла")
                        .setMessage("Вы действительно хотите изменить итоговый балл?")
                        .setNegativeButton("Нет", null)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                API.Converter.getConvert().getDeleteCoinsStd(savedKey, id).enqueue(new Callback<JSONstud>() {
                                    @Override
                                    public void onResponse(Call<JSONstud> call, Response<JSONstud> response) {
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.show(f1);
                                        ft.commit();
                                        Intent intent = new Intent(getContext(), Group.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<JSONstud> call, Throwable t) {
                                    }
                                });
                            }
                        }).create().show();
            } else {
                new AlertDialog.Builder(getContext())
                        .setTitle("Подсчет итогового балла")
                        .setMessage("Вы действительно хотите подсчитать итоговый балл?")
                        .setNegativeButton("Нет", null)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                API.Converter.getConvert().getPutCoinsStd(savedKey, id).enqueue(new Callback<Sum>() {
                                    @Override
                                    public void onResponse(Call<Sum> call, Response<Sum> response) {
                                        Intent intent = new Intent(getContext(), Group.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }



                                    @Override
                                    public void onFailure (Call < Sum > call, Throwable t){
                                    }
                                });
                            }
                        }).create().show();
            }

        }
    };

        @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
    }
}