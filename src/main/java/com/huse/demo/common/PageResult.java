package com.huse.demo.common;

import java.util.List;

// 统一封装分页返回结果，方便前端直接读取 content 和 totalElements
public class PageResult<T> {

    // 当前页的数据列表
    private List<T> content;

    // 总记录数
    private Long totalElements;

    // 当前页码
    private Integer pageNum;

    // 每页条数
    private Integer pageSize;

    // 总页数
    private Integer totalPages;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
