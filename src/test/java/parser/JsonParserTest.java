package parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.util.List;

class JsonParserTest {

    private static final double BOOK_PRICE = 25.2;
    private static final double SOFT_PRICE = 99.0;
    private Cart cart;
    private JsonParser parser;


    Cart addItemsToCartTest() {

        RealItem rItem = new RealItem();
        rItem.setName("Book");
        rItem.setWeight(1.5);
        rItem.setPrice(BOOK_PRICE);

        VirtualItem vItem = new VirtualItem();
        vItem.setName("IntellIJ Idea");
        vItem.setSizeOnDisk(512.42);
        vItem.setPrice(SOFT_PRICE);

        Cart cart = new Cart("john-cart");
        cart.addRealItem(rItem);
        cart.addVirtualItem(vItem);
        return cart;
    }

    @BeforeEach
    void initialize() {
        this.cart = addItemsToCartTest();
        this.parser = new JsonParser();
        parser.writeToFile(cart);
    }

    @Test
    void validateCorrectCartNameWrittenToFileTest() {
        Cart johnCart = parser.readFromFile(new File("src/main/resources/john-cart.json"));
        String expectedResultName = cart.getCartName();
        String actualResultName = johnCart.getCartName();
        Assertions.assertEquals(expectedResultName, actualResultName);
    }

    @Test
    void validateCorrectCartPriceWrittenToFileTest() {
        Cart johnCart = parser.readFromFile(new File("src/main/resources/john-cart.json"));
        double expectedResultName = cart.getTotalPrice();
        double actualResultName = johnCart.getTotalPrice();
        Assertions.assertEquals(expectedResultName, actualResultName);
    }

    @Test
    void validateCorrectRealItemWrittenToFileTest() {
        Cart johnCart = parser.readFromFile(new File("src/main/resources/john-cart.json"));
        List<RealItem> expectedResultName = cart.getRealItems();
        List<RealItem>  actualResultName = johnCart.getRealItems();
        Assertions.assertEquals(expectedResultName.get(0).getWeight(), actualResultName.get(0).getWeight());
        Assertions.assertEquals(expectedResultName.get(0).getPrice(), actualResultName.get(0).getPrice());
        Assertions.assertEquals(expectedResultName.get(0).getName(), actualResultName.get(0).getName());
    }

    @AfterEach
    void cleanCart() {
        cart.clearCart();
    }

}
