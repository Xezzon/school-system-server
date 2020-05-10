package indi.xezzon.school.common.model;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 分页数据统一返回
 *
 * @author xezzon
 */
@Data
public class PageResult<T> {
    private Integer total;
    private Integer page;
    private Integer pageSize;
    private List<T> items;
    
    /**
     * 从PageInfo中生成
     */
    public static <T> PageResult<T> from(PageInfo<T> pageInfo) {
        PageResult<T> result = new PageResult<>();
        result.setTotal(Math.toIntExact(pageInfo.getTotal()));
        result.setPage(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setItems(pageInfo.getList());
        return result;
    }
}
