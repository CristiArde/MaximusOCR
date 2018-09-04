package ai.bonobo.maximusocr;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cardel6 on 23/08/2018.
 */

public class ListViewItemsAdapter extends ArrayAdapter<String> {
    private ArrayList<String> itemDescription = new ArrayList<String>();
    private  ArrayList<Integer> itemPrice = new ArrayList<Integer>();
    private final Activity context;
    // add image variable

    //add image in constructor
    public ListViewItemsAdapter(Activity context, ArrayList<String> itemDescription, ArrayList<Integer> itemPrice) {
        super(context, R.layout.listviw_layout,itemDescription);

        this.context = context;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null){
            //converst xml to java class with coresponding obejct
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listviw_layout,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) r.getTag();
        }
        viewHolder.txtDesc.setText(itemDescription.get(position));
        viewHolder.txtPrice.setText(Integer.toString(itemPrice.get(position)));

        return r;
    }

    class ViewHolder{
        TextView txtDesc;
        TextView txtPrice;
        //ImageView itemImg;

        ViewHolder(View v){
            this.txtDesc = v.findViewById(R.id.descriptionTxt);
            this.txtPrice = v.findViewById(R.id.totalTxt);
            //this.itemImg = v.findViewById(R.id.imageView);
        }
    }

}
