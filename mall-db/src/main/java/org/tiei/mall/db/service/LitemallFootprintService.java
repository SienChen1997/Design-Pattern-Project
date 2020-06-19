package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallFootprintMapper;
import org.tiei.mall.db.domain.mallFootprint;
import org.tiei.mall.db.domain.mallFootprintExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallFootprintService {
    @Resource
    private mallFootprintMapper footprintMapper;

    public List<mallFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        mallFootprintExample example = new mallFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(mallFootprint.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }

    public mallFootprint findById(Integer id) {
        return footprintMapper.selectByPrimaryKey(id);
    }

    public mallFootprint findById(Integer userId, Integer id) {
        mallFootprintExample example = new mallFootprintExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return footprintMapper.selectOneByExample(example);
    }

    public void deleteById(Integer id) {
        footprintMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(mallFootprint footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        footprintMapper.insertSelective(footprint);
    }

    public List<mallFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        mallFootprintExample example = new mallFootprintExample();
        mallFootprintExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.valueOf(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }
}
