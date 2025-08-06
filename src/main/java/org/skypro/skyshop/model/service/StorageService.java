package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService(){
        this.storageProduct = new HashMap<>();
        this.storageArticle = new HashMap<>();
        addTestData();
    }

    public void addProduct(Product product){
        storageProduct.put(product.getId(), product);
    }

    public void addArticle(Article article){
        storageArticle.put(article.getId(), article);
    }

    public void addTestData(){
        addProduct(new SimpleProduct("Мороженое", 100));
        addProduct(new DiscountedProduct("Пироженое", 110, 15));
        addProduct(new FixPriceProduct("Твороженое"));
        addProduct(new SimpleProduct("Семечки", 60));
        addProduct(new DiscountedProduct("Кешью - орехи", 90, 20));
        addProduct(new SimpleProduct("Печенье", 30));
        addProduct(new SimpleProduct("Арахис - орехи", 40));
        addProduct(new SimpleProduct("Фундук - орехи", 100));
        addProduct(new SimpleProduct("Миндаль - орехи", 100));

        addArticle(new Article("Бумбоксбббббоксииибоммирррбокс", "Chrome1"));
        addArticle(new Article("Бумбоксбббббоксииибомбумирррбокс", "Chrome2"));
        addArticle(new Article("Мясо как источник белка", "Чрезмерное употребление мяса вредит здоровью."));
        addArticle(new Article("Ты все еще голодный?", "Если ты голодный - заставь себя поесть!"));
        addArticle(new Article("Магазин автозапчастей", "Широкий выбор комплектующих для авто."));
        addArticle(new Article("Ozon", "Закажи, оплати, дождись, забери, убедись что не подходит - выкинь!"));
        addArticle(new Article("Полет птицы", "Статья про сложное строение перьев у пернатых."));
        addArticle(new Article("Огородные вредители", "Купил перегной - беги за препаратами от вредителей! :)"));
    }
    public Set<Searchable> generalCollection(){
        Set<Searchable> searchable = new HashSet<>();
        searchable.addAll(storageProduct.values());
        searchable.addAll(storageArticle.values());
        return searchable;
    }

    public Collection<Product> getProducts() {
        return storageProduct.values();
    }

    public Collection<Article> getArticles() {
        return storageArticle.values();
    }
}
