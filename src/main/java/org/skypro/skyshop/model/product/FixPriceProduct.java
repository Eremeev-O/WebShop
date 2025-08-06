package org.skypro.skyshop.model.product;

public class FixPriceProduct extends Product{
    private static final float COST = 30f;

    public FixPriceProduct(String name){
        super(name);
    }
    @Override
    public float getCost(){
        return COST;
    }
    @Override
    public String toString() {
        return this.getName() + " : " + getCost() + " : Фиксированная цена " + COST;
    }
    @Override
    public boolean isSpecial(){
        return true;
    }
}
