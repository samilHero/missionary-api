package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryStaffMapper {

    MissionaryStaffMapper INSTANCE = Mappers.getMapper(MissionaryStaffMapper.class);


    default @NonNull MissionaryStaff toMissionaryStaff(Missionary missionary, AppointMissionaryStaffsCommandStaff staff) {
        return MissionaryStaff.builder()
            .missionary(missionary)
            .userId(staff.userId())
            .role(staff.role())
            .build();
    }

}
