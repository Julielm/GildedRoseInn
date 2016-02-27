package fr.iutvalence.info.m4104.gildedroseinn;

public class PolishItem extends Item {

    private static final int SELL_IN_DATE_THRESHOLD_FOR_POLISH_QUALITY_BEING_DECREMENTED_THREE_TIMES = 5;
    private static final int SELL_IN_DATE_THRESHOLD_FOR_POLISH_QUALITY_BEING_DECREMENTED_TWICE = 10;

    public PolishItem(String name, int sellIn, int quality, int price) {
        super(name, sellIn, quality, price);
    }

    protected void updateItemQuality(){
        if (this.hasItemSellInDatePassed())
        {
            this.quality=0;
            return;
        }

        this.decrementItemQualityIfNotZero();
        if (this.sellIn <= SELL_IN_DATE_THRESHOLD_FOR_POLISH_QUALITY_BEING_DECREMENTED_TWICE)
            this.decrementItemQualityIfNotZero();
        if (this.sellIn <= SELL_IN_DATE_THRESHOLD_FOR_POLISH_QUALITY_BEING_DECREMENTED_THREE_TIMES)
            this.decrementItemQualityIfNotZero();;
    }

}
