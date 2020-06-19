package org.tiei.mall.db.service;

import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.dao.GoodsProductMapper;
import org.tiei.mall.db.dao.mallGoodsProductMapper;
import org.tiei.mall.db.domain.mallGoodsProduct;
import org.tiei.mall.db.domain.mallGoodsProductExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallGoodsProductService {
    @Resource
    private mallGoodsProductMapper mallGoodsProductMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    public List<mallGoodsProduct> queryByGid(Integer gid) {
        mallGoodsProductExample example = new mallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return mallGoodsProductMapper.selectByExample(example);
    }

    public mallGoodsProduct findById(Integer id) {
        return mallGoodsProductMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        mallGoodsProductMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(mallGoodsProduct goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        mallGoodsProductMapper.insertSelective(goodsProduct);
    }

    public int count() {
        mallGoodsProductExample example = new mallGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) mallGoodsProductMapper.countByExample(example);
    }

    public void deleteByGid(Integer gid) {
        mallGoodsProductExample example = new mallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        mallGoodsProductMapper.logicalDeleteByExample(example);
    }

    public int addStock(Integer id, Short num){
        return goodsProductMapper.addStock(id, num);
    }

    public int reduceStock(Integer id, Short num){
        return goodsProductMapper.reduceStock(id, num);
    }

    public void updateById(mallGoodsProduct product) {
        product.setUpdateTime(LocalDateTime.now());
        mallGoodsProductMapper.updateByPrimaryKeySelective(product);
    }
}