package com.samill.missionary_backend.church.church.entity;

import java.util.List;


public class Churches {

    private final List<Church> list;

    public Churches(List<Church> list) {
        this.list = List.copyOf(list);
    }

    public List<Church> getValues() {
        return list;
    }


    public Boolean hasNext(Integer pageSize) {
        return list.size() >= pageSize;
    }

}
