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


public class InventoryActivity extends ActionBarActivity {

    private InventoryItemAdapter itemAdapter;

    /* Même principe pour le cycle de vie que pour HomeActivity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);
        ListView itemsView = (ListView) findViewById(R.id.inventoryItemsView);
        this.itemAdapter= new InventoryItemAdapter(this);
        itemsView.setAdapter(this.itemAdapter);
        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /* Permet de gérer le clic sur un item dans l'inventaire */
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AndroidItem item = (AndroidItem) view.getTag();

                if (item.getItem().getSellIn() <= 0) {

                    /* Création d'un toast pour afficher un message */
                    Context context = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.cant_use_outdated_item);
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    List<AndroidItem> items = ((GildedRoseApplication) getApplication()).getInventoryItems();
                    items.remove(item);

                    Context context = getApplicationContext();
                    CharSequence text = getResources().getString(R.string.use_item);
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
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
