package org.tiei.mall.db.service;

import org.tiei.mall.db.dao.mallOrderGoodsMapper;
import org.tiei.mall.db.domain.mallOrderGoods;
import org.tiei.mall.db.domain.mallOrderGoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallOrderGoodsService {
    @Resource
    private mallOrderGoodsMapper orderGoodsMapper;

    public int add(mallOrderGoods orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return orderGoodsMapper.insertSelective(orderGoods);
    }

    public List<mallOrderGoods> queryByOid(Integer orderId) {
        mallOrderGoodsExample example = new mallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public List<mallOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
        mallOrderGoodsExample example = new mallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public mallOrderGoods findById(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    public void updateById(mallOrderGoods orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    public Short getComments(Integer orderId) {
        mallOrderGoodsExample example = new mallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        long count = orderGoodsMapper.countByExample(example);
        return (short) count;
    }

    public boolean checkExist(Integer goodsId) {
        mallOrderGoodsExample example = new mallOrderGoodsExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.countByExample(example) != 0;
    }

    public void deleteByOrderId(Integer orderId) {
        mallOrderGoodsExample example = new mallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        orderGoodsMapper.logicalDeleteByExample(example);
    }
}
