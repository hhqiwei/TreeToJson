package com.ccmc.jdbc;

import com.alibaba.fastjson.JSON;
import java.util.List;

@SuppressWarnings("all")
public class Tree {

    private int id;
    private String name;
    private int pid;
    private List<Tree> children;

    public Tree() {
        super();
    }

    public Tree(int id, String name, int pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public void spark() {
        System.out.println(this.id + "\t" + this.name + this.pid + "\t" + this.children);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
