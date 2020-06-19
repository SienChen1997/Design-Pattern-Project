package org.tiei.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.domain.mallOrder;
import org.tiei.mall.db.domain.mallOrderExample;

public interface mallOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    long countByExample(mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int deleteByExample(mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int insert(mallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int insertSelective(mallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    mallOrder selectOneByExample(mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    mallOrder selectOneByExampleSelective(@Param("example") mallOrderExample example, @Param("selective") mallOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    List<mallOrder> selectByExampleSelective(@Param("example") mallOrderExample example, @Param("selective") mallOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    List<mallOrder> selectByExample(mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    mallOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") mallOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    mallOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    mallOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") mallOrder record, @Param("example") mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") mallOrder record, @Param("example") mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(mallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(mallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") mallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_order
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}