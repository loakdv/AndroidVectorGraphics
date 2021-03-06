package ru.ecostudiovl.vectorgraphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import ru.ecostudiovl.vectorgraphics.figure.Figure;

public class AdapterFiguresList extends RecyclerView.Adapter<AdapterFiguresList.ViewHolder> {


    private List<Figure> mData;
    private FigureSelect figureSelect;
    private Context context;

    public AdapterFiguresList(List<Figure> figures, FigureSelect figureSelect, Context context){
        mData = figures;
        this.figureSelect = figureSelect;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_figure, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figureSelect.onSelectFigure(position);
            }
        });

        if (mData.get(position).isSelected){
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.selected_figure));
        }
        else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.simple_figure));
        }

        holder.tvName.setText(mData.get(position).name);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figureSelect.onDeletedFigure(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        LinearLayout linearLayout;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.etElementFigureName);
            linearLayout = itemView.findViewById(R.id.lnElFigureMain);
            btnDelete = itemView.findViewById(R.id.btnDeleteFigure);
        }
    }


    public interface FigureSelect{
        void onSelectFigure(int index);
        void onDeletedFigure(int index);
    }
}
