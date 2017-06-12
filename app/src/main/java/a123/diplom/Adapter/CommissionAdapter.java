package a123.diplom.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import a123.diplom.Model.Commission.Comm;
import a123.diplom.R;


public class CommissionAdapter extends RecyclerView.Adapter<CommissionAdapter.ViewHolder> {
    private ArrayList<Comm> comm;


    public CommissionAdapter(ArrayList<Comm> comm) {
        this.comm = comm;

    }

    @Override
    public CommissionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commission_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommissionAdapter.ViewHolder holder, int position) {

            holder.userEmail.setText(comm.get(position).getUser_email());
            holder.points.setText(comm.get(position).getPoints());
            Log.d("LoginActivity", "email:" + comm.get(position).getUser_email());
            Log.d("LoginActivity", "points:" + comm.get(position).getPoints());

    }

    @Override
    public int getItemCount() {
        return comm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userEmail;
        private TextView points;
        private CardView commCard;
        public ViewHolder(View view) {
            super(view);
            userEmail = (TextView) view.findViewById(R.id.comm_email);
            points = (TextView) view.findViewById(R.id.comm_points);
            commCard = (CardView) view.findViewById(R.id.cardCommission);
        }
    }
}
