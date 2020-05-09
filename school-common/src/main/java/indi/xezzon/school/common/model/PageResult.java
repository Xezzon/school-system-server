package indi.xezzon.school.common.model;

import lombok.Data;

import java.util.List;

/**
 * 分页数据统一返回
 *
 * @author xezzon
 */
@Data
public class PageResult<T> {
    private int total;
    private int page;
    private int pageSize;
    private List<T> items;
}
