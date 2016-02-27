package fr.iutvalence.info.m4104.gildedroseinn;

import java.io.Serializable;

/*
 * Chaque item pourra appartenir instancier la classe Item ou une classe héritant de celle-ci pour
 * un comportement différent lors des mises à jours.
 */
public class Item implements Serializable{
    protected final String name;
	protected int sellIn;
	protected int quality;
	protected int price;

	public Item(String name, int sellIn, int quality, int price)
	{
		this.name = name;
		this.sellIn = sellIn;
		this.price = price;
		this.setQuality(quality);
	}

	public String getName()
	{
		return this.name;
	}

	public void setSellIn(int sellIn)
	{
		this.sellIn = sellIn;
	}

	public int getSellIn()
	{
		return this.sellIn;
	}

	public int getQuality()	{
		return this.quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int i) {
		this.price=i;
	}

	public void updateItem(){
		int previousQuality = this.quality;
		this.updateItemQuality();
		this.updateItemSellIn();
		this.updateItemPrice(previousQuality);

	}

	private void updateItemPrice(int previousQuality) {
		this.calculatePriceInFunctionOfTheQuality(previousQuality);
	}

	private void calculatePriceInFunctionOfTheQuality(int previousQuality) {
		if (previousQuality!=0)
			this.price=(this.price * this.quality)/previousQuality;
	}

	protected void updateItemSellIn() {
		this.decrementItemSellIn();
	}

	protected void updateItemQuality() {
		this.updateNormalQuality();
	}

	protected boolean hasItemSellInDatePassed() {
		return this.sellIn<=0;
	}

	protected void decrementItemQualityIfNotZero()
	{
		if (this.quality > 0)
			decrementItemQuality();
	}

	protected void decrementItemSellIn()
	{
		this.sellIn--;
	}

	protected void decrementItemQuality()
	{
		this.quality--;
	}

	protected void incrementItemQualityIfNotFifty() {
		if (this.quality < 50)
			this.incrementItemQuality();
	}

	private void incrementItemQuality() {
		this.quality++;
	}

	protected void updateNormalQuality() {
		if (!this.hasItemSellInDatePassed())
			this.decrementItemQualityIfNotZero();
	}
}

