package org.skypro.skyshop.model.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Поле (name) заводимого продукта пустое");
        }
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public abstract boolean isSpecial();

    public String getName() {
        return name;
    }

    public abstract float getCost();

    @JsonIgnore
    public String getSearchTerm(){
        return this.getName();
    }

    @JsonIgnore
    public String getContentType(){
        return "PRODUCT";
    }

    public UUID getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
