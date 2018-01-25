package parser;

import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class JsonParserTest {

    private static final double BOOK_PRICE = 25.2;
    private static final double SOFT_PRICE = 99.0;
    private Cart cart;
    private JsonParser parser;
    private List<String> cartList = new ArrayList<>();

    private Cart addItemsToCartTest() {

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

    @Nested
    class Suite1 {
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
            List<RealItem> actualResultName = johnCart.getRealItems();
            Assertions.assertEquals(expectedResultName.get(0).getWeight(), actualResultName.get(0).getWeight());
            Assertions.assertEquals(expectedResultName.get(0).getPrice(), actualResultName.get(0).getPrice());
            Assertions.assertEquals(expectedResultName.get(0).getName(), actualResultName.get(0).getName());
        }
    }

    @Test
    @DisplayName("JSON TEST - EXCEPTION")
    void readFromFileTest() {
        BufferedReader in;

        try {
            in = new BufferedReader(new FileReader("src/test/datasets/carts.txt"));
            String str;

            while ((str = in.readLine()) != null) {
                cartList.add(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            new JsonParser().readFromFile(Paths.get(cartList.get((new Random()).nextInt(cartList.size()))).toFile());
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void cleanCart() {
        cart.clearCart();
    }
}
