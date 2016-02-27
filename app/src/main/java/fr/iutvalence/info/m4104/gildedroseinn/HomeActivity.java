package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/*
 * Chaque activité a un cycle de vie décrit par certaines méthodes comme onCreate(), onStop() ...
 * Chaque activité dispose aussi d'un layout associé qui permet de définir l'affichage de l'application
 */
public class HomeActivity extends Activity {

    private GildedRoseApplication application;
    public static final String PREF_NAME = "My prefs";
    private TextView dayTextView;

    /* Timer pour permettre d'appeler une même méthode à intervalle régulier */
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            nextDay();
            /*permet de recommencer l'action run toutes les 10 secondes */
            timerHandler.postDelayed(this, 10000);
        }
    };

    @Override
    /* Méthode appelée lors de la création de l'activité */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.application = (GildedRoseApplication) getApplication();

        this.dayTextView = (TextView) findViewById(R.id.day_text);
        loadSettings();
        loadInventoryList();

        setContentView(R.layout.home_layout);
        updateText();

        timerHandler.postDelayed(timerRunnable, 10000);

    }

    /* Méthode appelelée lorsque l'activité est remise en premier plan */
    public void onResume(){
        super.onResume();
        updateText();
    }

    /* Méthode appelée lorsque l'application n'est plus visible */
    public void onStop(){
        super.onStop();

        saveSettings();
        saveInventory();
    }

    /* Méthode appelée lorsque l'on appuie sur un des boutons android appelés (pour API < level 21)*/
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /* Méthode appelée lorsqu'on appuie sur le bouton retour (API >= level 21) */
    public void onBackPressed() {
        finish();
    }

    /* Méthode pour gérer les clics sur les boutons de la page d'accueil et effectuer l'action correspondante */
    public void homeActivityClickListener(View view)
    {
        switch (view.getId())
        {
            case R.id.shop_button :
                startShopActivity();
                break;
            case R.id.inventory_button :
                startInventoryActivity();
                break;
            case R.id.next_button :
                nextDay();
                break;
            case R.id.plusMoneyButton :
                addMoney();
                break;
            default :
        }
    }

    private void addMoney() {
        this.application.addMoney(50);
        updateMoneyText();
    }

    /* Permet en plus du timer de changer manuellement de jour (l'application commence au jour 0) */
    private void nextDay()
    {
        List<AndroidItem> shopItems = application.getShopItems();
        List<AndroidItem> inventoryItems = application.getInventoryItems();

        application.updateItems(shopItems);
        application.updateItems(inventoryItems);

        application.addDays();
        updateDayText();
    }

    private void startInventoryActivity()
    {
        this.startActivity(new Intent(this, InventoryActivity.class));
    }

    private void startShopActivity()
    {
        this.startActivity(new Intent(this, ShopActivity.class));
    }

    private void updateText() {
        updateMoneyText();
        updateDayText();
    }

    /* Pour changer un texte d'un TextView il faut retrouver la vue par son id dans les ressources de l'application */
    private void updateDayText() {
        TextView dayText = (TextView) findViewById(R.id.day_text);
        dayText.setText(getResources().getString(R.string.day) + " " + String.valueOf(this.application.getDays()));
    }

    private void updateMoneyText() {
        TextView money = (TextView) findViewById(R.id.money);
        money.setText(String.valueOf(this.application.getMoney()));
    }

    /* Méthode permettant de récupérer les items sauvegardés à chaque fermeture de l'application à partir d'un fichier
       texte sauvegardé en interne sur le téléphone.
     */
    private void loadInventoryList() {
        try {
            FileInputStream fis = openFileInput("inventory.txt");
            System.out.println(fis);
            if (fis!=null){
                ObjectInputStream is = null;
                try {
                    is = new ObjectInputStream(fis);
                    List<AndroidItem> inventoryItems = (List<AndroidItem>) is.readObject();
                    this.application.setInventoryItems(inventoryItems);
                    is.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Méthode permettant de récupérer les paramètres sauvegardés sur l'application */
    private void loadSettings() {
        SharedPreferences settings = getSharedPreferences(HomeActivity.PREF_NAME, 0);
        int moneySaved = settings.getInt("money",100);
        this.application.setMoney(moneySaved);
    }

    /* Méthode qui sauvegarde les préférences en interne sur le téléphone -> détruites en cas de suppression de l'application */
    private void saveSettings() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("money", this.application.getMoney());
        editor.commit();
    }

    /* Méthode qui sauvegarde les items de l'inventaire dans un fichier texte stocké localement (mémoire interne) et qui
       sera détruit en cas de suppression de l'application
     */
    private void saveInventory() {
        try {
            FileOutputStream fos = openFileOutput("inventory.txt", MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.reset();
            os.writeObject(this.application.getInventoryItems());
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
