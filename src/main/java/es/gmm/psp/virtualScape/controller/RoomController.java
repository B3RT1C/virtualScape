package es.gmm.psp.virtualScape.controller;

import es.gmm.psp.virtualScape.dto.ByMostBookedDto;
import es.gmm.psp.virtualScape.dto.ByThemeDto;
import es.gmm.psp.virtualScape.exception.RoomNotFoundException;
import es.gmm.psp.virtualScape.exception.VirtualScapeException;
import es.gmm.psp.virtualScape.model.ResponseData;
import es.gmm.psp.virtualScape.model.Room;
import es.gmm.psp.virtualScape.service.IRoomService;
import es.gmm.psp.virtualScape.util.consts.ApiMSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController implements IRoomController {

    @Autowired
    private IRoomService roomService;

    @Override
    public ResponseEntity<List<Room>> getRoom() {
        List<Room> salas = roomService.findAll();

        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> getRoom(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Room room = roomService.findById(id);
        if (room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseData> postRoom(@RequestBody Room toPost) {
        try {
            Long createdId = roomService.create(toPost).getId();
            return new ResponseEntity<>(new ResponseData(true, ApiMSG.ROOM_CREATED_SUCCESSFUL, createdId), HttpStatus.CREATED);

        } catch (VirtualScapeException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseData> putRoom(@PathVariable Long id, @RequestBody Room toPut) {
        try {
            toPut.setId(id);
            roomService.update(toPut);
            return new ResponseEntity<>(new ResponseData(true, ApiMSG.ROOM_UPDATED_SUCCESSFUL, id), HttpStatus.OK);

        } catch (RoomNotFoundException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.NOT_FOUND);
        } catch (VirtualScapeException e) {
            return new ResponseEntity<>(new ResponseData(false, e.getMessage(), -1L), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ByThemeDto>> getRoomByTheme(@PathVariable String nombreTematica) {
        List<ByThemeDto> salas = roomService.findByTheme(nombreTematica);

        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salas, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ByMostBookedDto>> getMostReservedRooms() {
        List<ByMostBookedDto> salas = roomService.findMostBooked(2);

        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salas, HttpStatus.OK);
    }
}
