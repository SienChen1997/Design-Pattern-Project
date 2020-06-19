package org.tiei.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.tiei.mall.db.domain.mallGoodsAttribute;
import org.tiei.mall.db.domain.mallGoodsAttributeExample;

public interface mallGoodsAttributeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    long countByExample(mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int deleteByExample(mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int insert(mallGoodsAttribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int insertSelective(mallGoodsAttribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    mallGoodsAttribute selectOneByExample(mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    mallGoodsAttribute selectOneByExampleSelective(@Param("example") mallGoodsAttributeExample example, @Param("selective") mallGoodsAttribute.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    List<mallGoodsAttribute> selectByExampleSelective(@Param("example") mallGoodsAttributeExample example, @Param("selective") mallGoodsAttribute.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    List<mallGoodsAttribute> selectByExample(mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    mallGoodsAttribute selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") mallGoodsAttribute.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    mallGoodsAttribute selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    mallGoodsAttribute selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") mallGoodsAttribute record, @Param("example") mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") mallGoodsAttribute record, @Param("example") mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(mallGoodsAttribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(mallGoodsAttribute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") mallGoodsAttributeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mall_goods_attribute
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}