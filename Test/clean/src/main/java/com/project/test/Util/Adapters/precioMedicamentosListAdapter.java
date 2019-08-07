package com.project.test.Util.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.test.Core.PriceList.view.PriceListActivity;
import com.project.test.Core.PriceList.view.PriceListView;
import com.project.test.R;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class precioMedicamentosListAdapter extends RecyclerView.Adapter<precioMedicamentosListAdapter.ViewHolder>{
    List<Object> data;
    Context context;
    PriceListView view;

    public precioMedicamentosListAdapter(List<Object> data, PriceListActivity activity) {
        this.data = data;
        this.view = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.precio_medicamento_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Object item = data.get(position);
        String[] stringArray = null;
        try{
            String array = item.toString();
            array.replace("[", "");
            array.replace("]", "");
            stringArray = array.split(",");
        }catch(Exception e){}

        holder.name.setText(stringArray[3].trim());
        holder.price.setText(stringArray[7]);
        if(position == data.size() - 1){
            holder.separator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public ImageView map;
        public View separator;

        public ViewHolder(View v) {
            super(v);
            name      = (TextView) v.findViewById(R.id.nombre_price_med_row);
            price     = (TextView) v.findViewById(R.id.precio_price_med_row);
            map       = (ImageView) v.findViewById(R.id.map_price_med_row);
            separator = (View) v.findViewById(R.id.separator_price_med_row);

            map.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Object item = data.get(getAdapterPosition());
                    String[] stringArray = null;
                    try{
                        String array = item.toString();
                        array = StringUtils.removeStart(StringUtils.removeEnd(array, "]"), "[");
                        stringArray = array.split(",");
                    }catch(Exception e){}
                    if(!stringArray[8].isEmpty()){
                        view.onMapClicked(stringArray[0], stringArray[5]);
                    }
                }
            });
        }
    }
}
