package org.tiei.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.domain.mallNoticeAdmin;
import org.tiei.mall.db.domain.mallNoticeAdminExample;

public interface mallNoticeAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    long countByExample(mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int deleteByExample(mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int insert(mallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int insertSelective(mallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    mallNoticeAdmin selectOneByExample(mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    mallNoticeAdmin selectOneByExampleSelective(@Param("example") mallNoticeAdminExample example, @Param("selective") mallNoticeAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    List<mallNoticeAdmin> selectByExampleSelective(@Param("example") mallNoticeAdminExample example, @Param("selective") mallNoticeAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    List<mallNoticeAdmin> selectByExample(mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    mallNoticeAdmin selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") mallNoticeAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    mallNoticeAdmin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    mallNoticeAdmin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") mallNoticeAdmin record, @Param("example") mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") mallNoticeAdmin record, @Param("example") mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(mallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(mallNoticeAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") mallNoticeAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_notice_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}