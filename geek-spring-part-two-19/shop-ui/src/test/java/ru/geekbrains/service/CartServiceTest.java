package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;
    private BigDecimal bidPrice;
    private ProductRepr expectedProduct;
    Map<LineItem, BigDecimal> lineItems;
    LineItem lineItem;

    @BeforeEach
    public void init() {
        bidPrice = new BigDecimal(123);
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOneProduct() {
        createTestProductWithDefaultParams();
        addTestProductToCart();
        assertEquals(bidPrice, lineItem.getBidPrice());
        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
    }

    @Test
    public void testRemoveProduct(){
        createTestProductWithDefaultParams();
        addTestProductToCart();
        cartService.removeProduct(lineItem);
        assertEquals(0, lineItems.size());
    }

    @Test
    public void testUpdateCart(){
        createTestProductWithDefaultParams();
        addTestProductToCart();
        BigDecimal newBidPrice = new BigDecimal(321);
        cartService.updateCart(lineItem);
        assertNotNull(lineItem.getBidPrice());
        assertNotNull(lineItems.keySet().iterator().next());
        assertEquals(newBidPrice, lineItem.getBidPrice());
    }

    @Test
    public void testGetLineItems(){
        createTestProductWithDefaultParams();
        cartService.addProductBidPrice(expectedProduct, bidPrice);
        lineItems = cartService.getLineItems().stream().collect(Collectors.toMap(li -> li, LineItem::getBidPrice));
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());
    }

    @Test
    public void testGetSubTotal(){
        createTestProductsWithParams(1L, "Test Product 1");
        addTestProductToCart();
        createTestProductsWithParams(2L, "Test Product 2");
        addTestProductToCart();
        BigDecimal subTotalPrice = cartService.getSubTotal();
        assertNotNull(subTotalPrice);
        BigDecimal sumOfPricesInCart = bidPrice.add(bidPrice);
        assertNotNull(sumOfPricesInCart);
        assertEquals(subTotalPrice,sumOfPricesInCart);
    }

    private void createTestProductWithDefaultParams() {
        createTestProductsWithParams(1L,"Product name");
    }

    private void createTestProductsWithParams(Long id, String name) {
        expectedProduct = new ProductRepr();
        expectedProduct.setId(id);
        expectedProduct.setName(name);
    }

    private void addTestProductToCart() {
        cartService.addProductBidPrice(expectedProduct, bidPrice);
        lineItems = cartService.getLineItems().stream().collect(Collectors.toMap(li -> li, LineItem::getBidPrice));
        lineItem = lineItems.keySet().iterator().next();
    }
}
