package org.tiei.mall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallGrouponMapper;
import org.tiei.mall.db.domain.mallGroupon;
import org.tiei.mall.db.domain.mallGrouponExample;
import org.tiei.mall.db.util.GrouponConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallGrouponService {
    @Resource
    private mallGrouponMapper mapper;

    /**
     * 获取用户发起的团购记录
     *
     * @param userId
     * @return
     */
    public List<mallGroupon> queryMyGroupon(Integer userId) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andUserIdEqualTo(userId).andCreatorUserIdEqualTo(userId).andGrouponIdEqualTo(0).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return mapper.selectByExample(example);
    }

    /**
     * 获取用户参与的团购记录
     *
     * @param userId
     * @return
     */
    public List<mallGroupon> queryMyJoinGroupon(Integer userId) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdNotEqualTo(0).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return mapper.selectByExample(example);
    }

    /**
     * 根据OrderId查询团购记录
     *
     * @param orderId
     * @return
     */
    public mallGroupon queryByOrderId(Integer orderId) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);
    }

    /**
     * 获取某个团购活动参与的记录
     *
     * @param id
     * @return
     */
    public List<mallGroupon> queryJoinRecord(Integer id) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andGrouponIdEqualTo(id).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        example.orderBy("add_time desc");
        return mapper.selectByExample(example);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public mallGroupon queryById(Integer id) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);
    }

    /**
     * 根据ID查询记录
     *
     * @param userId
     * @param id
     * @return
     */
    public mallGroupon queryById(Integer userId, Integer id) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(id).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);
    }

    /**
     * 返回某个发起的团购参与人数
     *
     * @param grouponId
     * @return
     */
    public int countGroupon(Integer grouponId) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andGrouponIdEqualTo(grouponId).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        return (int) mapper.countByExample(example);
    }

    public boolean hasJoin(Integer userId, Integer grouponId) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdEqualTo(grouponId).andStatusNotEqualTo(GrouponConstant.STATUS_NONE).andDeletedEqualTo(false);
        return  mapper.countByExample(example) != 0;
    }

    public int updateById(mallGroupon groupon) {
        groupon.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(groupon);
    }

    /**
     * 创建或参与一个团购
     *
     * @param groupon
     * @return
     */
    public int createGroupon(mallGroupon groupon) {
        groupon.setAddTime(LocalDateTime.now());
        groupon.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(groupon);
    }


    /**
     * 查询所有发起的团购记录
     *
     * @param rulesId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<mallGroupon> querySelective(String rulesId, Integer page, Integer size, String sort, String order) {
        mallGrouponExample example = new mallGrouponExample();
        mallGrouponExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(rulesId)) {
            criteria.andRulesIdEqualTo(Integer.parseInt(rulesId));
        }
        criteria.andDeletedEqualTo(false);
        criteria.andStatusNotEqualTo(GrouponConstant.STATUS_NONE);
        criteria.andGrouponIdEqualTo(0);

        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }

    public List<mallGroupon> queryByRuleId(int grouponRuleId) {
        mallGrouponExample example = new mallGrouponExample();
        example.or().andRulesIdEqualTo(grouponRuleId).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }
}
