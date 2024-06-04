package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommand;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryStaffMapper {

    MissionaryStaffMapper INSTANCE = Mappers.getMapper(MissionaryStaffMapper.class);


    MissionaryStaff appointMissionaryStaffToMissionaryStaff(@NonNull AppointMissionaryStaffsCommand command);

}
