package indi.xezzon.school.common.model;

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
public class PagedDTO<T> {
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> items;

    public PagedDTO(int total, int pageNum, int pageSize, List<T> items) {
        this.total = total;
        this.pageNum = fixPageNum(total, pageNum, pageSize);
        this.pageSize = pageSize;
        this.items = items;
    }

    public static int fixPageNum(int total, int pageNum, int pageSize) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        int maxPageNum = (total - 1) / pageSize + 1;
        if (pageNum > maxPageNum) {
            pageNum = maxPageNum;
        }
        return pageNum;
    }

    public void fixPageNum() {
        this.pageNum = fixPageNum(this.total, this.pageNum, this.pageSize);
    }
}
