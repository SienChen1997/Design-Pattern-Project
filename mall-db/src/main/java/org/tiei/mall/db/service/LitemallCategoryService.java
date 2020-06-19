package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallCategoryMapper;
import org.tiei.mall.db.domain.mallCategory;
import org.tiei.mall.db.domain.mallCategoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallCategoryService {
    @Resource
    private mallCategoryMapper categoryMapper;
    private mallCategory.Column[] CHANNEL = {mallCategory.Column.id, mallCategory.Column.name, mallCategory.Column.iconUrl};

    public List<mallCategory> queryL1WithoutRecommend(int offset, int limit) {
        mallCategoryExample example = new mallCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }

    public List<mallCategory> queryL1(int offset, int limit) {
        mallCategoryExample example = new mallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }

    public List<mallCategory> queryL1() {
        mallCategoryExample example = new mallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<mallCategory> queryByPid(Integer pid) {
        mallCategoryExample example = new mallCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<mallCategory> queryL2ByIds(List<Integer> ids) {
        mallCategoryExample example = new mallCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public mallCategory findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public List<mallCategory> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        mallCategoryExample example = new mallCategoryExample();
        mallCategoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return categoryMapper.selectByExample(example);
    }

    public int updateById(mallCategory category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void deleteById(Integer id) {
        categoryMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(mallCategory category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
    }

    public List<mallCategory> queryChannel() {
        mallCategoryExample example = new mallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExampleSelective(example, CHANNEL);
    }
}
