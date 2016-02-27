package fr.iutvalence.info.m4104.gildedroseinn;

public class FoundationItem extends Item {
    public FoundationItem(String name, int sellIn, int quality, int price) {
        super(name, sellIn, quality, price);
    }

    protected void updateItemQuality(){
        this.incrementItemQualityIfNotFifty();
    }
}
