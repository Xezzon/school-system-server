package indi.xezzon.school.common.repository;

import indi.xezzon.school.common.model.BaseEntity;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xezzon
 */
public interface BaseMapper<T extends BaseEntity> {
    /**
     * 分页查询
     *
     * @param t 查询条件
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param orderBy 排序依据
     * @param desc 降序
     * @return 查询结果
     */
    List<T> query(@NotNull @Param("t") T t, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy, @Param("desc") Boolean desc);

    /**
     * 根据主键查询
     *
     * @param primaryKey 主键
     * @return 查询结果
     */
    T queryByPrimaryKey(@Param("primaryKey") Object primaryKey);

    /**
     * 根据主键集合批量查询
     *
     * @param primaryKeys 主键集合
     * @return 查询结果集合
     */
    List<T> queryAll(@Param("primaryKeys") List<Object> primaryKeys);

    /**
     * 新增
     *
     * @param t 元素
     */
    void insert(@Param("t") T t);

    /**
     * 批量新增
     *
     * @param ts 元素集合
     * @return 影响行数
     */
    int insertAll(@Param("ts") List<T> ts);

    /**
     * 更新
     *
     * @param t 元素
     */
    void update(@Param("t") T t);

    /**
     * 删除
     *
     * @param t 元素
     * @return 影响行数
     */
    int delete(@Param("t") T t);

    /**
     * 根据主键批量删除
     *
     * @param primaryKeys 主键集合
     * @return 影响行数
     */
    int deleteAll(@Param("primaryKeys") List<Object> primaryKeys);

    /**
     * 符合条件的记录数
     *
     * @param t 查询条件
     * @return 结果条数
     */
    int count(@Param("t") T t);
}
