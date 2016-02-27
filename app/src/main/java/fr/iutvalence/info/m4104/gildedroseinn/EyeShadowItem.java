package fr.iutvalence.info.m4104.gildedroseinn;

public class EyeShadowItem extends Item {
    public EyeShadowItem(String name, int sellIn, int quality, int price) {
        super(name, sellIn, quality, price);
    }

    protected void updateItemSellIn(){
        return;
    }

    protected void updateItemQuality(){
        return;
    }
}
