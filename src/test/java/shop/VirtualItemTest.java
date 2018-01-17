package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

class VirtualItemTest {

    private static final String EXPECTED_TO_STRING_RESULT = "Class: class shop.VirtualItem; Name: Ubuntu; Price: 20.3; Size on disk: 900.3";

    private VirtualItem createVirtualItemTest() {
        VirtualItem item = new VirtualItem();
        item.setName("Ubuntu");
        item.setPrice(20.3);
        item.setSizeOnDisk(900.3);
        return item;
    }

    @Test
    void verifyThatToStringReturnsCorrectResultTest() {
        String actualResult = createVirtualItemTest().toString();
        Assertions.assertEquals(EXPECTED_TO_STRING_RESULT, actualResult);
    }
}
