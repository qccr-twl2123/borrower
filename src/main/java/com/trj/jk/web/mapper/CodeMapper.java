package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.CodeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    long countByCriteria(CodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int deleteByCriteria(CodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int insert(Code record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int insertSelective(Code record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    List<Code> selectByCriteriaWithRowbounds(CodeCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    List<Code> selectByCriteria(CodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    Code selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int updateByCriteriaSelective(@Param("record") Code record, @Param("example") CodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int updateByCriteria(@Param("record") Code record, @Param("example") CodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int updateByPrimaryKeySelective(Code record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_code
     *
     * @mbg.generated Wed May 23 09:41:09 CST 2018
     */
    int updateByPrimaryKey(Code record);
}