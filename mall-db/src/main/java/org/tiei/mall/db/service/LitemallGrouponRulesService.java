package org.tiei.mall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallGoodsMapper;
import org.tiei.mall.db.dao.mallGrouponRulesMapper;
import org.tiei.mall.db.domain.mallGoods;
import org.tiei.mall.db.domain.mallGrouponRules;
import org.tiei.mall.db.domain.mallGrouponRulesExample;
import org.tiei.mall.db.util.GrouponConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class mallGrouponRulesService {
    @Resource
    private mallGrouponRulesMapper mapper;
    @Resource
    private mallGoodsMapper goodsMapper;
    private mallGoods.Column[] goodsColumns = new mallGoods.Column[]{mallGoods.Column.id, mallGoods.Column.name, mallGoods.Column.brief, mallGoods.Column.picUrl, mallGoods.Column.counterPrice, mallGoods.Column.retailPrice};

    public int createRules(mallGrouponRules rules) {
        rules.setAddTime(LocalDateTime.now());
        rules.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(rules);
    }

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    public mallGrouponRules findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<mallGrouponRules> queryByGoodsId(Integer goodsId) {
        mallGrouponRulesExample example = new mallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }

    public int countByGoodsId(Integer goodsId) {
        mallGrouponRulesExample example = new mallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        return (int)mapper.countByExample(example);
    }

    public List<mallGrouponRules> queryByStatus(Short status) {
        mallGrouponRulesExample example = new mallGrouponRulesExample();
        example.or().andStatusEqualTo(status).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }

    /**
     * 获取首页团购规则列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<mallGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    public List<mallGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        mallGrouponRulesExample example = new mallGrouponRulesExample();
        example.or().andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return mapper.selectByExample(example);
    }

    /**
     * 判断某个团购规则是否已经过期
     *
     * @return
     */
    public boolean isExpired(mallGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购规则列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<mallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        mallGrouponRulesExample example = new mallGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        mallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }

    public void delete(Integer id) {
        mapper.logicalDeleteByPrimaryKey(id);
    }

    public int updateById(mallGrouponRules grouponRules) {
        grouponRules.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(grouponRules);
    }
}