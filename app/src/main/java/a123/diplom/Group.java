package a123.diplom;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import a123.diplom.Adapter.GroupsAdapter;
import a123.diplom.Interface.API;
import a123.diplom.Model.Group.Groups;
import a123.diplom.Model.Group.JSON;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a123.diplom.Login.savedKey;


public class Group extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private ArrayList<Groups> data;
    private GroupsAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
                mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.list_group);
        initViews();
    }



    private void initViews(){

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON(){

        API.Converter.getConvert().getAllGroups(savedKey).enqueue(new Callback<JSON>() {
            @Override
            public void onResponse(Call<JSON> call, Response<JSON> response) {
               // Groups gf = new Groups();

                JSON jsonResponse = response.body();
                data = new ArrayList<>(jsonResponse.getGroups());
                adapter = new GroupsAdapter(data,getApplicationContext());
                recyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<JSON> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        initViews();
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}



