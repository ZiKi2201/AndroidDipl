package a123.diplom;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import a123.diplom.Adapter.StudentsAdapter;
import a123.diplom.Interface.API;
import a123.diplom.Model.Student.JSONstud;
import a123.diplom.Model.Student.Students;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static a123.diplom.Login.savedKey;


public class Student extends BaseOther implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private ArrayList<Students> stud;
    private StudentsAdapter adapter;
    private String id;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initViews();
        String name = getIntent().getExtras().getString("group_name");
        getSupportActionBar().setTitle(name);
    }


    private void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.list_stud);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        id = getIntent().getExtras().getString("group_id");
        loadJSON();
    }
    private void loadJSON(){


        API.Converter.getConvert().getAllStudents(savedKey,id).enqueue(new Callback<JSONstud>() {
            @Override
            public void onResponse(Call<JSONstud> call, Response<JSONstud> response) {
                JSONstud json = response.body();
                stud = new ArrayList<>((json.getStudents()));
                adapter = new StudentsAdapter(stud,getApplicationContext());
                recyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }


            @Override
            public void onFailure(Call<JSONstud> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        initViews();
    }

}






      /*  mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }
    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentStudOff(), "Не защитились");
        adapter.addFragment(new FragmentStudOn() , "Защитились");
        mViewPager.setAdapter(adapter);
    }



*/