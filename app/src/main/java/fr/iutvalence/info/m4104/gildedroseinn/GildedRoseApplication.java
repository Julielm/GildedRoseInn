package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class GildedRoseApplication extends Application{

    private List<AndroidItem> shopItems;
    private List<AndroidItem> inventoryItems;
    private Integer money;
    private Integer days;



    public GildedRoseApplication(){
        super();

        this.money = 100;
        this.days =0;

        this.shopItems =new LinkedList<AndroidItem>();

        this.inventoryItems=new LinkedList<AndroidItem>();
    }

    public void onCreate(){
        super.onCreate();
        this.shopItems.add(new AndroidItem(new LipItem(getResources().getString(R.string.lipstick_chanel_item), 20, 40, 25), R.drawable.rougealevre));
        this.shopItems.add(new AndroidItem(new Item(getResources().getString(R.string.blush_nocibe_item), 15, 10, 10), R.drawable.blushitem));
        this.shopItems.add(new AndroidItem(new EyeShadowItem(getResources().getString(R.string.naked1_item), 30, 50, 49), R.drawable.naked1item));
        this.shopItems.add(new AndroidItem(new EyeShadowItem(getResources().getString(R.string.naked2_item), 13, 50, 49), R.drawable.naked2item));
        this.shopItems.add(new AndroidItem(new Item(getResources().getString(R.string.mascara_loreal_item), 14, 35, 15), R.drawable.mascaraitem));
        this.shopItems.add(new AndroidItem(new PolishItem(getResources().getString(R.string.polish_OPI_red_item), 25, 32, 13), R.drawable.opirouge));
        this.shopItems.add(new AndroidItem(new PolishItem(getResources().getString(R.string.polish_OPI_pink_item), 22, 32, 13), R.drawable.opirose));
        this.shopItems.add(new AndroidItem(new Item(getResources().getString(R.string.eyeliner_sephora_item), 12, 24, 10), R.drawable.eyelineritem));
        this.shopItems.add(new AndroidItem(new FoundationItem(getResources().getString(R.string.foundation_bourgeois_item), 10, 36, 14), R.drawable.fonddeteintitem));
        this.shopItems.add(new AndroidItem(new FoundationItem(getResources().getString(R.string.anti_age_gemey_maybelline_item), 20, 24, 8), R.drawable.anticerneitem));
    }

    public List<AndroidItem> getShopItems(){
        return this.shopItems;
    }

    public List<AndroidItem> getInventoryItems() {
        return this.inventoryItems;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void addMoney(int i) {
        this.money=this.money+i;
    }

    public void removeMoney(Integer price) {
        this.money=this.money-price;
    }

    public Integer getDays() {
        return this.days;
    }

    public void addDays() {
        this.days++;
    }


    public void setMoney(int money) {
        this.money = money;
    }

    public void setInventoryItems(List<AndroidItem> inventoryItems) {
        this.inventoryItems.clear();
        this.inventoryItems.addAll(inventoryItems);
    }

    public void updateItems(List<AndroidItem> items) {

        for (AndroidItem item:items) {
            item.getItem().updateItem();
        }
    }
}
