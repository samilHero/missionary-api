package com.samill.missionary_backend.missionary.region.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class MissionaryRegion extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MissionaryRegionType type;


    public boolean getSameType(MissionaryRegionType type) {
        return this.type.equals(type);
    }

    @Override
    public String toString() {
        return "MissionaryRegion{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}

//    JEJU("제주선교", MissionaryCategory.DOMESTIC),
//    SEOUL("서울선교", MissionaryCategory.DOMESTIC),
//    ARMY("군선교", MissionaryCategory.DOMESTIC),
//    JAPAN("일본선교", MissionaryCategory.abroad),
//    AMERICA("미국선교", MissionaryCategory.abroad),
//    ;
//
//
//    private final String value;
//
//    private final MissionaryCategory missionaryCategory;
//
//    MissionaryRegion(String value, MissionaryCategory missionaryCategory) {
//        this.value = value;
//        this.missionaryCategory = missionaryCategory;
//    }
//
//    public static MissionaryRegion keyOf(String key) {
//        return Arrays.stream(values()).filter(value -> value.getKey().equals(key)).findFirst().orElseThrow();
//    }
//
//    public static List<MissionaryRegion> getValuesByCategory(MissionaryCategory category) {
//        return Arrays.stream(values()).filter(value -> value.getMissionaryCategory().equals(category)).toList();
//    }
//
//    @Override
//    public String getKey() {
//        return name();
//    }
//
//    @Override
//    public String getValue() {
//        return value;
//    }
//
//    public MissionaryCategory getMissionaryCategory() {
//        return missionaryCategory;
//    }