package com.samill.application;

import java.util.List;

public interface ChurchService {
    Church createChurch(Church church);
    Church updateChurch(Church church);
    void deleteChurch(Church church);
    Church getChurch(Long churchId);
    List<Church> getChurchList();
}
