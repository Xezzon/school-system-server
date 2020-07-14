package indi.xezzon.school.common.model;

import java.util.List;

/**
 * 分页数据统一返回
 *
 * @author xezzon
 */
public class PageResult<T> {
    private Integer total;
    private Integer page;
    private Integer pageSize;
    private List<T> items;
    
    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public List<T> getItems() {
        return items;
    }
    
    public void setItems(List<T> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "PageResult{" + "total=" + total + ", page=" + page + ", pageSize=" + pageSize + ", items=" + items + '}';
    }
}
