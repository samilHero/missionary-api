package com.samill.missionary_backend.missionary.church.entity;


import java.util.List;


public class MissionaryChurches {

    private final List<MissionaryChurch> list;

    public MissionaryChurches(List<MissionaryChurch> list) {
        this.list = List.copyOf(list);
    }

    public List<MissionaryChurch> getValues() {
        return list;
    }


    public Boolean hasNext(Integer pageSize) {
        return list.size() >= pageSize;
    }

}
