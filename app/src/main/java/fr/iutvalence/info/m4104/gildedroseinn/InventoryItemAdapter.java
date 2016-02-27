package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/* Le list adapter va permettre d'implémenter une liste View, ici celle pour l'inventaire*/
public class InventoryItemAdapter implements ListAdapter{

    private List<AndroidItem> items;
    private List<Integer> images;
    private Context context;

    public InventoryItemAdapter(Activity activity){
        this.items =((GildedRoseApplication) activity.getApplication()).getInventoryItems();
        this.context=activity.getBaseContext();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    /* Méthode appelée pour chaque item présent dans la liste des items, chaque item a sa propre vue qui sera stockée dans
    * la liste view
    */
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.inventory_item_layout, parent, false);
        }
        AndroidItem androidItem = this.items.get(position);
        Item item = androidItem.getItem();

        TextView text = (TextView) convertView.findViewById(R.id.itemName);
        text.setText(item.getName());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.itemImage);
        imageView.setImageResource(androidItem.getImage());
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.itemQuality);
        ratingBar.setRating((item.getQuality() * 3) / 50.0f);

        TextView sellIn = (TextView) convertView.findViewById(R.id.itemSellIn);
        if (item.getSellIn()<=0){
            sellIn.setText(context.getResources().getString(R.string.outdated));
        }
        else {
            sellIn.setText(String.valueOf(item.getSellIn()));
        }
        convertView.setTag(androidItem);

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
