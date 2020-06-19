package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallAdMapper;
import org.tiei.mall.db.domain.mallAd;
import org.tiei.mall.db.domain.mallAdExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallAdService {
    @Resource
    private mallAdMapper adMapper;

    public List<mallAd> queryIndex() {
        mallAdExample example = new mallAdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return adMapper.selectByExample(example);
    }

    public List<mallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        mallAdExample example = new mallAdExample();
        mallAdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adMapper.selectByExample(example);
    }

    public int updateById(mallAd ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        adMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(mallAd ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        adMapper.insertSelective(ad);
    }

    public mallAd findById(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }
}
