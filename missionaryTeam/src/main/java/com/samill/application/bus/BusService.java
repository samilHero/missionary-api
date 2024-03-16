package com.samill.application.bus;

public interface BusService {
    Bus createTeamBus(Bus bus);
    Bus getTeamBus(Long busId);
    Bus updateTeamBus(Bus bus);
    void deleteTeamBus(Long busId);
}
