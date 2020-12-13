package indi.xezzon.school.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页数据统一返回
 *
 * @author xezzon
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PageResult<T> {
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> items;

    public PageResult() {
        super();
    }

    public PageResult(int total, int pageNum, int pageSize, List<T> items) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.items = items;
    }
}
