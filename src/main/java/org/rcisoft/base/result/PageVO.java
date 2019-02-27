package org.rcisoft.base.result;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

public final class PageVO<T> implements Serializable {
  private static final long serialVersionUID = -4106030982324955419L;
  private long total;        //总记录数
  private List<T> list;    //结果集
  private int pageNo;    // 第几页
  private int pageSize;    // 每页记录数
  private int pages;        // 总页数
  private int size;        // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性

  public static <T> PageVO<T> pageResult(List<T> list) {
    return new PageVO<>(list);
  }

  /**
   * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
   * 而出现一些问题。
   * @param list          page结果
   */
  public PageVO(List<T> list) {
    if (list instanceof Page) {
      Page<T> page = (Page<T>) list;
      this.pageNo = page.getPageNum();
      this.pageSize = page.getPageSize();
      this.total = page.getTotal();
      this.pages = page.getPages();
      this.list = page.getResult();
      this.size = page.size();
    }
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
