package org.tiei.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.domain.mallNotice;
import org.tiei.mall.db.domain.mallNoticeExample;

public interface mallNoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    long countByExample(mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int deleteByExample(mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int insert(mallNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int insertSelective(mallNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    mallNotice selectOneByExample(mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    mallNotice selectOneByExampleSelective(@Param("example") mallNoticeExample example, @Param("selective") mallNotice.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    List<mallNotice> selectByExampleSelective(@Param("example") mallNoticeExample example, @Param("selective") mallNotice.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    List<mallNotice> selectByExample(mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    mallNotice selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") mallNotice.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    mallNotice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    mallNotice selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") mallNotice record, @Param("example") mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") mallNotice record, @Param("example") mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(mallNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(mallNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") mallNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}