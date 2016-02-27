package fr.iutvalence.info.m4104.gildedroseinn;

import java.io.Serializable;

/*
 * Item pour l'application android avec une image qui lui est associé pour permettre l'affichage
 * de celle-ci dans les listes d'item.
 */
public class AndroidItem implements Serializable {
    private Item item;
    private Integer image;

    public AndroidItem(Item item, Integer image){
        this.item=item;
        this.image=image;
    }

    public Item getItem(){
        return this.item;
    }

    public Integer getImage(){
        return this.image;
    }

}
