package fr.iutvalence.info.m4104.gildedroseinn;

public class LipItem extends Item {
    public LipItem(String name, int sellIn, int quality, int price) {
        super(name, sellIn, quality, price);
    }

    protected void updateItemQuality(){
        this.updateNormalQuality();
        this.updateNormalQuality();
    }

}
