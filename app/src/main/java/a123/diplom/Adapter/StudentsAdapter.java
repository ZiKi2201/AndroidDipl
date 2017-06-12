package a123.diplom.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import a123.diplom.Count;
import a123.diplom.CountSecretary;
import a123.diplom.Model.Student.Students;
import a123.diplom.R;

import static a123.diplom.Login.savedRole;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    private ArrayList<Students> students;
    private Context context;
    private ImageView imageView;

    public StudentsAdapter(ArrayList<Students> students, Context applicationContext){
        this.students = students;
        this.context = applicationContext;

    }




    @Override
    public StudentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row, parent, false);
        return new StudentsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final StudentsAdapter.ViewHolder holder, final int position) {
        if(students.get(position).getCoins()==0){
            holder.coins.setVisibility(View.GONE);
            imageView.setImageResource(android.R.drawable.checkbox_off_background);
        }else {
            imageView.setImageResource(android.R.drawable.checkbox_on_background);
            holder.coins.setText("Баллы: "+students.get(position).getCoins());
        }
        Log.d("LoginActivity", "onResponse:" + students.get(position).getCoins() );
        holder.theme.setText(students.get(position).getTheme());
        holder.number.setText(students.get(position).getNumber());

        holder.cardStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("LoginActivity", "savedRole:" + savedRole);

                if (savedRole.equals("member")) {
                    if (students.get(position).getCoins() == 0) {
                        Intent intent = new Intent(context, Count.class);
                        intent.putExtra("std_theme", students.get(position).getTheme());
                        intent.putExtra("std_id", students.get(position).getId_stud());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }else{
                     //   Toast.makeText(v.getContext(), "Coins student: " + students.get(position).getCoins(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (savedRole.equals("secretary")) {

                    Intent intent = new Intent(context, CountSecretary.class);
                    intent.putExtra("std_id", students.get(position).getId_stud());
                    intent.putExtra("std_theme", students.get(position).getTheme());
                    intent.putExtra("std_coins", students.get(position).getCoins());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return students.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView theme,number,coins;
        private CardView cardStudent;

        public ViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.imageV);
            theme = (TextView) view.findViewById(R.id.stud_thema);
            number = (TextView) view.findViewById(R.id.stud_number);
            cardStudent = (CardView) view.findViewById(R.id.cardStudent);
            coins = (TextView) view.findViewById(R.id.coins);
        }
    }
}


