package com.fruitsalesplatform.entity;

/**
 * @author dpcraft
 * @date 2018/12/31
 * @time 12:51
 */
public class PageEntity {
    private Integer currentPage;
    private Integer startPage;
    private Integer pageSize;

    public Integer getCurrentPage() {
        if(currentPage == null) {
            currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getStartPage() {
        if(startPage == null) {
            startPage = 0;
        }
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getPageSize() {
        if(pageSize == null){
            pageSize = 5;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
