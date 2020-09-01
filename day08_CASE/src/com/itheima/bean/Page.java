package com.itheima.bean;

import java.util.List;

public class Page<T> {

    //每页信息
    List<T> pageList;
    //当前页
    int currentpage;
    //每页数
    int pagesize;
    //总页数
    int totalpage;
    //??总数量 是否需要封装


    public Page() {
    }

    public Page(List<T> pageList, int currentpage, int pagesize, int totalpage) {
        this.pageList = pageList;
        this.currentpage = currentpage;
        this.pagesize = pagesize;
        this.totalpage = totalpage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageList=" + pageList +
                ", currentpage=" + currentpage +
                ", pagesize=" + pagesize +
                ", totalpage=" + totalpage +
                '}';
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}
