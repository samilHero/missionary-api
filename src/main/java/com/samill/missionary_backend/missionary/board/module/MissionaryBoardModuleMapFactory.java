package com.samill.missionary_backend.missionary.board.module;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MissionaryBoardModuleMapFactory {

    private final Map<String, MissionaryBoardModule> stringMissionaryBoardModuleMap;

    public MissionaryBoardModule getMissionaryBoardModule(final @NonNull Class<? extends MissionaryBoardModule> clazz) {
        final var simpleName = clazz.getSimpleName();
        final var name = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
        return stringMissionaryBoardModuleMap.get(name);
    }
}
