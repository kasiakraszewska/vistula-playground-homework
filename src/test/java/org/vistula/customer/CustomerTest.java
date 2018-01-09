package org.vistula.customer;

import org.junit.Assert;
import org.junit.Test;
import org.vistula.basket.BasketItem;
import org.vistula.basket.PromoItem;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void testIsAdult() {
        Customer kasia = new Customer("Kasia", "Kraszewska", 37, null);
        Assert.assertTrue(kasia.isAdult());
    }

    @Test
    public void testGetTotalBasketAmount() {
        BasketItem pen = new BasketItem(99, false);
        BasketItem wine = new BasketItem(20, true);
        List<BasketItem> basket = new ArrayList<>();
        basket.add(pen);
        basket.add(wine);
        Customer kasia = new Customer("Kasia", "Kraszewska", 37, basket);
        assertThat(kasia.getTotalBasketAmount()).isEqualTo(119);
        Customer marcin = new Customer("Marcin", "Kraszewski", 37, null);
        assertThat(marcin.getTotalBasketAmount()).isEqualTo(0);
    }

    @Test
    public void testAddToBasket() {
        BasketItem wine = new BasketItem(20, true);
        BasketItem pen = new BasketItem(99, false);
        Customer kasia = new Customer("Kasia", "Kraszewska", 37, null);
        kasia.addToBasket(wine);
        kasia.addToBasket(pen);
        assertThat(kasia.getTotalBasketAmount()).isEqualTo(119);
        assertThat(kasia.getBasket().size()).isEqualTo(2);
        kasia.showBasket();
        Customer franek = new Customer("Franek", "Kraszewski", 10, null);
        franek.addToBasket(wine);
        franek.addToBasket(pen);
        assertThat(franek.getTotalBasketAmount()).isEqualTo(99);
        assertThat(franek.getBasket().size()).isEqualTo(1);
        franek.showBasket();
    }

    @Test
    public void testRemoveFromBasket() {
        BasketItem wine = new BasketItem(20, true);
        BasketItem pen = new BasketItem(99, false);
        Customer kasia = new Customer("Kasia", "Kraszewska", 37, null);
        kasia.addToBasket(wine);
        kasia.addToBasket(pen);
        assertThat(kasia.getTotalBasketAmount()).isEqualTo(119);
        assertThat(kasia.getBasket().size()).isEqualTo(2);
        kasia.showBasket();
        kasia.removeFromBasket(wine);
        assertThat(kasia.getTotalBasketAmount()).isEqualTo(99);
        assertThat(kasia.getBasket().size()).isEqualTo(1);
        kasia.showBasket();


    }

    @Test
    public void testAddToBasketPromoItem() {
        PromoItem wine = new PromoItem(20, true);
        wine.setDiscount(20);
        PromoItem pen = new PromoItem(99, false);
        pen.setDiscount(10);
        Customer kasia = new Customer("Kasia", "Kraszewska", 37, null);
        kasia.addToBasket(wine);
        kasia.addToBasket(pen);
        assertThat(kasia.getTotalBasketAmount()).isEqualTo(105.1f);
        assertThat(kasia.getBasket().size()).isEqualTo(2);
        assertThat(kasia.getBasket().get(0).getPrice()).isEqualTo(16);
        assertThat(kasia.getBasket().get(1).getPrice()).isEqualTo(89.1f);
        kasia.showBasket();
    }


}
