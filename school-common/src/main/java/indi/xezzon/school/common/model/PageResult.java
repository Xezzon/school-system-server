package indi.xezzon.school.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 分页数据统一返回
 *
 * @author xezzon
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PageResult<T> {
    private Integer total;
    private Integer page;
    private Integer pageSize;
    private List<T> items;
}
