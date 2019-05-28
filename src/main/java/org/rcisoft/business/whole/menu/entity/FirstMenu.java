package org.rcisoft.business.whole.menu.entity;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.List;

/**
 * 一级菜单封装对象
 */
public class FirstMenu implements Comparable<FirstMenu>{
    private String name;
    private String url;
    @Ignore
    private Integer ordered;
    private List<ChildMenu> childMenuList;

    @Override
    public int compareTo(FirstMenu o) {
        // TODO Auto-generated method stub
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

    public List<ChildMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<ChildMenu> childMenuList) {
        this.childMenuList = childMenuList;
    }
}
