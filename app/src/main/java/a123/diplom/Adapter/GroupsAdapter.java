package a123.diplom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import a123.diplom.Model.Group.Groups;
import a123.diplom.R;
import a123.diplom.Student;


public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    private ArrayList<Groups> groups;
    private Context context;

    public GroupsAdapter(ArrayList<Groups> groups, Context applicationContext) {
        this.groups = groups;
        this.context = applicationContext;
    }


    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GroupsAdapter.ViewHolder holder, final int position) {

        holder.name.setText(groups.get(position).getName());
        holder.groupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, Student.class);
                    intent.putExtra("group_name", groups.get(position).getName());
                    intent.putExtra("group_id", groups.get(position).getId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                   // Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return groups.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private CardView groupCard;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.group_name);
            groupCard = (CardView) view.findViewById(R.id.cardGroup);
        }
    }

}



