package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.NoSuchProductException;
import org.skypro.skyshop.model.service.StorageService;
import org.skypro.skyshop.model.service.UserBasket;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    ProductBasket productBasketMock;
    @Mock
    StorageService storageServiceMock;

    @InjectMocks
    BasketService basketService;

    @Test
    //Тестируем Выброс исключения из-за добавления несуществующего товара
    public void exclusionDueToAdditionOfNonExistentProduct() {
        UUID id = UUID.randomUUID();

        Mockito.when(storageServiceMock.getProductById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addToBasket(id));

        Mockito.verify(storageServiceMock).getProductById(id);
    }

    @Test
    // Тестируем случай, когда добавление существующего товара вызывает метод addProduct у мока ProductBasket
    public void existingProductIsAddedToBasket() {
        Product existingProduct = new SimpleProduct("Тестовый продукт", 100);
        UUID id = existingProduct.getId(); // Предполагаем, что Product имеет сеттер для ID или конструктор с ID

        Mockito.when(storageServiceMock.getProductById(id)).thenReturn(Optional.of(existingProduct));

        basketService.addToBasket(id);

        Mockito.verify(storageServiceMock, Mockito.times(1)).getProductById(id);
        Mockito.verify(productBasketMock, Mockito.times(1)).addProdictToBasket(id);
    }

    @Test
    // Тестируем случай, когда getUserBasket возвращает пустую корзину, если ProductBasket пуста
    public void getUserBasketReturnsEmptyWhenProductBasketIsEmpty() {

        Mockito.when(productBasketMock.getProductBasket()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getUserBasket().isEmpty());
        assertEquals(0.0, userBasket.getTotal(), 0.001);

        Mockito.verify(productBasketMock, Mockito.times(1)).getProductBasket();
    }

    @Test
// Тестируем, что getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары
    public void getUserBasketReturnsCorrectBasketWhenProductsExist() {

        Product product1 = new SimpleProduct("Яблоко", 50);
        Product product2 = new DiscountedProduct("Груша", 120, 10); // Скидка 10%

        UUID id1 = product1.getId();
        UUID id2 = product2.getId();

        Map<UUID, Integer> productBasketContent = new HashMap<>();
        productBasketContent.put(id1, 2); // 2 яблока
        productBasketContent.put(id2, 1); // 1 груша

        Mockito.when(productBasketMock.getProductBasket()).thenReturn(productBasketContent);
        Mockito.when(storageServiceMock.getProductById(id1)).thenReturn(Optional.of(product1));
        Mockito.when(storageServiceMock.getProductById(id2)).thenReturn(Optional.of(product2));

        UserBasket userBasket = basketService.getUserBasket();

        assertNotNull(userBasket);
        assertEquals(2, userBasket.getUserBasket().size());
        assertEquals(208.0, userBasket.getTotal(), 0.001); // 0.001 - это дельта для сравнения double

        Mockito.verify(storageServiceMock).getProductById(id1);
        Mockito.verify(storageServiceMock).getProductById(id2);
        Mockito.verify(productBasketMock).getProductBasket();
    }
}
