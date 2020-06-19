package org.tiei.mall.admin.dto;

import org.tiei.mall.db.domain.mallGoods;
import org.tiei.mall.db.domain.mallGoodsAttribute;
import org.tiei.mall.db.domain.mallGoodsProduct;
import org.tiei.mall.db.domain.mallGoodsSpecification;

public class GoodsAllinone {
    mallGoods goods;
    mallGoodsSpecification[] specifications;
    mallGoodsAttribute[] attributes;
    mallGoodsProduct[] products;

    public mallGoods getGoods() {
        return goods;
    }

    public void setGoods(mallGoods goods) {
        this.goods = goods;
    }

    public mallGoodsProduct[] getProducts() {
        return products;
    }

    public void setProducts(mallGoodsProduct[] products) {
        this.products = products;
    }

    public mallGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(mallGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public mallGoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(mallGoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

}
