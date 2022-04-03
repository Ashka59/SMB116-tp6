package cnam.smb116.smb116_tp6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyStringRecyclerViewAdapter extends RecyclerView.Adapter<MyStringRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final Context context;
    private String[] strSplit;

    public MyStringRecyclerViewAdapter(List<String> items, Context context) {
        this.context = context;
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        strSplit = mValues.get(position).split(";");
        holder.mLigne.setText(strSplit[9]);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mLigne;

        public ViewHolder(View view) {
            super(view);
            mLigne = view.findViewById(R.id.ligne);
            mLigne.setOnClickListener(v -> Toast.makeText(context, strSplit[4],Toast.LENGTH_LONG).show());
        }
    }
}