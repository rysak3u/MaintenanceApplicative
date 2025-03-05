package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    public int handleReverseQuality(Item i ){
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
    public void updateQuality() {
        for (Item i : items) {
            if(!i.name.equals("Sulfuras, Hand of Ragnaros")){
                i.sellIn = i.sellIn -1;
                if (i.name.equals(AGED_BRIE)|| i.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)){
                    i.quality = handleReverseQuality(i);
                }else{
                    i.quality = handleQuality(i);
                }
            }
        }
    }
}
