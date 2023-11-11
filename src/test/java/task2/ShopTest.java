package task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
public class ShopTest {
    public Shop shop;
    public Product product1;
    public Product product2;
    public Product product3;

    @BeforeEach
    public void setup() {
        shop = new Shop();
        product1 = new Product(100);
        product2 = new Product(200);
        product3 = new Product(300);
        shop.addProduct(product2);
        shop.addProduct(product3);
        shop.addProduct(product1);
    }

    @AfterEach
    public void clear() {
        shop = null;
        product1 = null;
        product2 = null;
        product3 = null;
    }

    @Test
    public void testProductsStorageSize() {
        assertThat(shop.getProducts().size()).isEqualTo(3);
    }

    @Test
    public void testProductsStorageElements() {
        assertThat(shop.getProducts().get(0)).isEqualTo(product2);
        assertThat(shop.getProducts().get(1)).isEqualTo(product3);
        assertThat(shop.getProducts().get(2)).isEqualTo(product1);
    }

    @Test
    public void testSortProductsByPrice() {
        List<Product> checkList = new LinkedList<>();
        checkList.add(product1);
        checkList.add(product2);
        checkList.add(product3);
        assertThat(shop.sortProductsByPrice()).isEqualTo(new Shop(checkList).getProducts());
    }

    @Test
    public void testGetMostExpensiveProduct() {
        assertThat(shop.getMostExpensiveProduct()).isEqualTo(product3);
    }

}
