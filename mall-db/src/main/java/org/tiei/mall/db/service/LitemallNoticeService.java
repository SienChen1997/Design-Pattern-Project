package org.tiei.mall.db.service;

import com.github.pagehelper.PageHelper;
import org.tiei.mall.db.dao.mallNoticeMapper;
import org.tiei.mall.db.domain.mallNotice;
import org.tiei.mall.db.domain.mallNoticeAdmin;
import org.tiei.mall.db.domain.mallNoticeAdminExample;
import org.tiei.mall.db.domain.mallNoticeExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class mallNoticeService {
    @Resource
    private mallNoticeMapper noticeMapper;


    public List<mallNotice> querySelective(String title, String content, Integer page, Integer limit, String sort, String order) {
        mallNoticeExample example = new mallNoticeExample();
        mallNoticeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return noticeMapper.selectByExample(example);
    }

    public int updateById(mallNotice notice) {
        notice.setUpdateTime(LocalDateTime.now());
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    public void deleteById(Integer id) {
        noticeMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(mallNotice notice) {
        notice.setAddTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        noticeMapper.insertSelective(notice);
    }

    public mallNotice findById(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    public void deleteByIds(List<Integer> ids) {
        mallNoticeExample example = new mallNoticeExample();
        example.or().andIdIn(ids).andDeletedEqualTo(false);
        mallNotice notice = new mallNotice();
        notice.setUpdateTime(LocalDateTime.now());
        notice.setDeleted(true);
        noticeMapper.updateByExampleSelective(notice, example);
    }
}
