package shop;

import org.junit.jupiter.api.*;

class CartTest {

    private static final double BOOK_PRICE = 25.2;
    private static final double SOFT_PRICE = 99.0;
    private Cart cart;

    private Cart addItemsToCartTest() {

        RealItem rItem = new RealItem();
        rItem.setName("Book");
        rItem.setPrice(BOOK_PRICE);

        VirtualItem vItem = new VirtualItem();
        vItem.setName("IntellIJ Idea");
        vItem.setPrice(SOFT_PRICE);

        Cart cart = new Cart("john-cart");
        cart.addRealItem(rItem);
        cart.addVirtualItem(vItem);
        return cart;
    }

    @BeforeEach
    void initialize() {
        this.cart = addItemsToCartTest();
    }

    @Test
    void verifyThatGetPriceReturnsCorrectValueTest() {

        Assertions.assertEquals(SOFT_PRICE + SOFT_PRICE * 0.2 + BOOK_PRICE + BOOK_PRICE * 0.2, cart.getTotalPrice());
    }

    @AfterEach
    void cleanCart() {
        cart.clearCart();
    }
}
