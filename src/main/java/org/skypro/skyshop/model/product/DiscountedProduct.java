package org.skypro.skyshop.model.product;

public class DiscountedProduct extends Product{
    private float cost;
    private int discount;

    public DiscountedProduct(String name, float cost, int discount){
        super(name);
        if (cost <= 0 || discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Ошибка в стоимости или дисконта заводимого продукта");
        }
        this.cost = cost;
        this.discount = discount;
    }
    public int getDiscount(){
        return this.discount;
    }
    @Override
    public float getCost(){
        return this.cost * (1 - (float) this.getDiscount()/100);
    }
    @Override
    public String toString() {
        return this.getName() + " : " + getCost() + " (скидка " + getDiscount() + "%)";
    }
    @Override
    public boolean isSpecial(){
        return true;
    }

}
