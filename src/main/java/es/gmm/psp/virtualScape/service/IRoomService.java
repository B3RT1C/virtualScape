package es.gmm.psp.virtualScape.service;

import es.gmm.psp.virtualScape.dto.ByMostBookedDto;
import es.gmm.psp.virtualScape.dto.ByThemeDto;
import es.gmm.psp.virtualScape.model.Room;

import java.util.List;

public interface IRoomService {
    Room create(Room toCreate);
    List<Room> findAll();
    Room findById(long id);
    Room findByName(String name);
    void update(Room toUpdate);
    List<ByThemeDto> findByTheme(String theme);
    List<ByMostBookedDto> findMostBooked(int amountToReturn);
}
