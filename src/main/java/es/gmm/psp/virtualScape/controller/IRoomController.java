package es.gmm.psp.virtualScape.controller;

import es.gmm.psp.virtualScape.dto.ByMostBookedDto;
import es.gmm.psp.virtualScape.dto.ByThemeDto;
import es.gmm.psp.virtualScape.model.ResponseData;
import es.gmm.psp.virtualScape.model.Room;
import es.gmm.psp.virtualScape.util.consts.ApiExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/swagger-ui/index.html#
// http://localhost:8080/api/virtual-escape

@RequestMapping("/api/virtual-escape")
public interface IRoomController {

    @Tag(name = "Salas - GET")
    @Operation(summary = "Obtener todas las salas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salas encontradas en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Room.class), examples = @ExampleObject(value = ApiExample.ROOM_LIST))),
            @ApiResponse(responseCode = "204", description = "No hay salas en la base de datos", content = @Content)
    })
    @GetMapping("/salas")
    ResponseEntity<List<Room>> getRoom();



    @Tag(name = "Salas - GET")
    @Operation(summary = "Obtener una sala por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala encontrada en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Room.class), examples = @ExampleObject(value = ApiExample.ROOM))),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada en la base de datos", content = @Content),
    })
    @GetMapping("/salas/{id}")
    ResponseEntity<Room> getRoom(
            @Parameter(description = "Identificador de la sala", example = "1")
            @PathVariable Long id);



    @Tag(name = "Salas - POST")
    @Operation(summary = "Crear una sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sala creada con éxito",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_OK))),
            @ApiResponse(responseCode = "400", description = "Datos de la sala no válidos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
    })
    @PostMapping("/salas")
    ResponseEntity<ResponseData> postRoom(
            @Parameter(description = "Datos de la sala a crear", example = ApiExample.ROOM)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la sala a crear",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Room.class), examples = @ExampleObject(value = ApiExample.ROOM)))
            @RequestBody Room toPost);



    @Tag(name = "Salas - PUT")
    @Operation(summary = "Actualizar una sala")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sala actualizada con éxito",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_OK))),
            @ApiResponse(responseCode = "404", description = "Sala no encontrada en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
            @ApiResponse(responseCode = "400", description = "Datos nuevos de la sala no válidos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
    })
    @PutMapping("/salas/{id}")
    ResponseEntity<ResponseData> putRoom(
            @Parameter(description = "Identificador de la sala", example = "1")
            @PathVariable Long id,

            @Parameter(description = "Datos de la sala a actualizar", example = ApiExample.ROOM)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la sala a actualizar",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Room.class), examples = @ExampleObject(value = ApiExample.ROOM)))
            @RequestBody Room toPut);



    @Tag(name = "Salas - GET")
    @Operation(summary = "Obtener las salas por temática", description = "Devuelve todas las salas que contenengan la temática indicada en su lista de temáticas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salas encontradas en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ByThemeDto.class), examples = @ExampleObject(value = ApiExample.BY_THEME_DTO_LIST))),
            @ApiResponse(responseCode = "204", description = "No hay salas en la base de datos con la temática indicada", content = @Content),
    })
    @GetMapping("/salas/tematica/{nombreTematica}")
    ResponseEntity<List<ByThemeDto>> getRoomByTheme(
            @Parameter(description = "Nombre de la temática", example = "Terror")
            @PathVariable String nombreTematica);



    @Tag(name = "Salas - GET")
    @Operation(summary = "Obtener las salas más reservadas", description = "Devuelve las 2 salas con más reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salas encontradas en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ByMostBookedDto.class), examples = @ExampleObject(value = ApiExample.BY_MOST_BOOKED_DTO_LIST))),
            @ApiResponse(responseCode = "204", description = "No hay salas en la base de datos", content = @Content),
    })
    @GetMapping("/salas/mas-reservadas")
    ResponseEntity<List<ByMostBookedDto>> getMostReservedRooms();
}
