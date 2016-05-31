package controller;

import android.support.v7.widget.RecyclerView;
import com.akhyanvaidya.secusafe.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by akhyanvaidya on 23/05/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
   /* private ArrayList<Dataprovider> arrayList= new ArrayList<Dataprovider>();//create dataprovider.java

    public RecyclerAdapter(ArrayList<Dataprovider> arrayList){//constructer
        this.arrayList= arrayList;

    }
   */
    //continue from here 19:00 min in RecyclerView Example
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //learn what this is doing?
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemImage= (ImageView) itemView.findViewById(R.id.interPic);
            itemName= (TextView) itemView.findViewById(R.id.interText);

        }
    }
}
