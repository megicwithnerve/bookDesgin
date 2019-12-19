package com.magician.book.utils;

import java.util.List;

public class Pager {

    private Integer indexpage = 1;//当前页
    private Integer countrows;//行数
    private Integer countpage;//页数
    private Integer pagesize = 10;
    private Integer beginrows;//行
    private List data;

    public void setData(List data) {
        this.data = data;
    }

    public List getData() {
        return data;
    }

    public Integer getBeginrows() {

        this.beginrows = ((getIndexpage() - 1) * getPagesize()) + 1;
        return beginrows;
    }

    public Pager(Integer countrows, Integer indexpage) {
        this.countrows = countrows;
        this.indexpage = indexpage;
    }

    public Pager(Integer countrows, Integer indexpage, Integer pagesize) {
        this.countrows = countrows;
        this.indexpage = indexpage;
        this.pagesize = pagesize;
    }

    public Integer getCountrows() {
        return countrows;
    }

    public void setCountpage(Integer countpage) {
        this.countpage = countpage;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getIndexpage() {
        return indexpage;
    }



    public Integer getCountpage() {
        this.countpage = getCountrows() % getPagesize() == 0
                ? getCountrows() / getPagesize()
                : getCountrows() / getPagesize() + 1;
        return countpage;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "indexpage=" + getIndexpage() +
                ", countrows=" + getCountrows() +
                ", countpage=" + getCountpage() +
                ", pagesize=" + getPagesize() +
                ", beginrows=" + getBeginrows() +
                '}';
    }
}
