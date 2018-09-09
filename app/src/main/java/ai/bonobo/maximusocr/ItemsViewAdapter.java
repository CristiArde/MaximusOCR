package ai.bonobo.maximusocr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cristi Arde on 9/9/2018.
 */

public class ItemsViewAdapter extends RecyclerView.Adapter<ItemsViewAdapter.ItemsViewHolder>{

    private Context mCtx;
    private List<ShoppingItem> itemsList;

    public ItemsViewAdapter(Context mCtx, List<ShoppingItem> itemsList) {
        this.mCtx = mCtx;
        this.itemsList = itemsList;
    }





    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.items_list_layout, null);
        ItemsViewHolder holder = new ItemsViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder itemsViewHolder, int i) {
        ShoppingItem item = itemsList.get(i);
        itemsViewHolder.txtViewTitle.setText(item.getName());
        itemsViewHolder.txtViewPrice.setText(String.valueOf(item.getPrice()));

        //itemsViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(item.getImage()));


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtViewTitle, txtViewPrice;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.imageView);
            txtViewTitle = itemView.findViewById(R.id.textViewTitle);
            txtViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
