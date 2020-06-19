package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallRegionMapper;
import org.tiei.mall.db.domain.mallRegion;
import org.tiei.mall.db.domain.mallRegionExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class mallRegionService {

    @Resource
    private mallRegionMapper regionMapper;

    public List<mallRegion> getAll(){
        mallRegionExample example = new mallRegionExample();
        byte b = 4;
        example.or().andTypeNotEqualTo(b);
        return regionMapper.selectByExample(example);
    }

    public List<mallRegion> queryByPid(Integer parentId) {
        mallRegionExample example = new mallRegionExample();
        example.or().andPidEqualTo(parentId);
        return regionMapper.selectByExample(example);
    }

    public mallRegion findById(Integer id) {
        return regionMapper.selectByPrimaryKey(id);
    }

    public List<mallRegion> querySelective(String name, Integer code, Integer page, Integer size, String sort, String order) {
        mallRegionExample example = new mallRegionExample();
        mallRegionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return regionMapper.selectByExample(example);
    }

}
