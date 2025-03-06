package com.gildedrose;

class GildedRose {
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    public void updateQuality() {
        for (Item item : items) {
            if(!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)){
                item.sellIn = item.sellIn -1;
                if (item.name.equals(AGED_BRIE)|| item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)){
                    item.quality = handleReverseQuality(item);
                }else{
                    item.quality = handleQuality(item);
                }
            }
        }
    }
    
    public int handleReverseQuality(Item i){
        if(i.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)){
            return handleBackstagePass(i);
        }else {
            return handleAgedBrie(i);
        }
    }
    public int handleAgedBrie(Item i){
        if (i.quality < 50){
            return i.quality +1;
        }else {
            return i.quality;
        }
    }
    public int handleBackstagePass(Item i){
        if(i.quality < 50){
            i.quality = i.quality +1;
            if (i.sellIn < 11) {
                if (i.quality < 50) {
                    i.quality = i.quality + 1;
                }
            }

            if (i.sellIn < 6) {
                if (i.quality < 50) {
                    i.quality = i.quality + 1;
                }
            }
            if (i.sellIn <= 0){
                i.quality = 0;
            }
        }
        return i.quality;
    }
    public int handleQuality(Item i){
        if(i.quality>0){
            if (i.sellIn>0){
                i.quality = i.quality-1;
            }else{
             i.quality = i.quality -2;   
            }
        }
        return i.quality;
    }
}
