package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandStaff;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryStaffMapper {

    MissionaryStaffMapper INSTANCE = Mappers.getMapper(MissionaryStaffMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "missionary", source = "missionary")
    MissionaryStaff appointMissionaryStaffToMissionaryStaff(Missionary missionary, AppointMissionaryStaffsCommandStaff staff);

}
