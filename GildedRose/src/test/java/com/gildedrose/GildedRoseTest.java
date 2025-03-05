package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    @Test
    void testSulfuros(){
        Item[] items = new Item[] {new Item(SULFURAS_HAND_OF_RAGNAROS, 1, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    @Test
    void testBackStagePass(){
        Item[] items = new Item[] {new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 15),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 10, 15),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 15),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 1, 15)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(16, app.items[0].quality);
        assertEquals(17, app.items[1].quality);
        assertEquals(18, app.items[2].quality);
        assertEquals(0, app.items[3].quality);
    }
    @Test
    void testBrie(){
        Item[] items = new Item[] {new Item(AGED_BRIE, 1, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void testNormalQuality(){
        Item[] items = new Item[] {new Item("Osef", 20, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality);
    }
    @Test
    void testZeroQuality(){
        Item[] items = new Item[] {new Item("Osef", 20, 0),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 20, 0),new Item(AGED_BRIE, 20, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(1, app.items[1].quality);
        assertEquals(1, app.items[2].quality);
    }
    @Test
    void testSellInOutdated(){
        Item[] items = new Item[] {new Item("Osef", 0, 20),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 0, 20),new Item(AGED_BRIE, 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
        assertEquals(21, app.items[2].quality);
    }
    @Test
    void testMaxQuality(){
        Item[] items = new Item[] {new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 20, 50),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 9, 49),new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 4, 49),new Item(AGED_BRIE, 20, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
        assertEquals(50, app.items[2].quality);
        assertEquals(50, app.items[3].quality);
    }
}
