package org.rcisoft.business.whole.menu.entity;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * 二级菜单封装对象
 */
public class ChildMenu implements Comparable<ChildMenu>{
    private String name;
    private String url;
    @Ignore
    private Integer ordered;

    @Override
    public int compareTo(ChildMenu o) {
            if(this.ordered>o.getOrdered()){
                return 1;
            }
            else if(this.ordered<o.getOrdered()){
                return -1;
            }
            else{
                return 0;
            }
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
