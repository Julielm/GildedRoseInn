package fr.iutvalence.info.m4104.gildedroseinn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class ShopActivity extends ActionBarActivity {

    private ShopItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        ListView itemsView = (ListView) findViewById(R.id.shopItemsView);
        itemAdapter= new ShopItemAdapter(this);
        itemsView.setAdapter(itemAdapter);
        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AndroidItem item = (AndroidItem) view.getTag();
                if (item.getItem().getSellIn()<=0) {
                    Context context = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.cant_buy_outdated_item);
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                else {
                    Integer price = item.getItem().getPrice();
                    Integer money = ((GildedRoseApplication) getApplication()).getMoney();

                    if (price<=money){
                        List<AndroidItem> inventoryItems = ((GildedRoseApplication) getApplication()).getInventoryItems();
                        inventoryItems.add(item);
                        List<AndroidItem> shopItems = ((GildedRoseApplication) getApplication()).getShopItems();
                        shopItems.remove(item);
                        ((GildedRoseApplication) getApplication()).removeMoney(price);

                        Context context = getApplicationContext();
                        CharSequence text = getResources().getString(R.string.item_available_in_inventory);
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        Intent intent = getIntent();
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = getResources().getString(R.string.not_enough_money_to_buy_item);
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        finish();
    }
}
