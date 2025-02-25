package es.gmm.psp.virtualScape.controller;

import es.gmm.psp.virtualScape.dto.ByDayDto;
import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.model.ResponseData;
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
public interface IReservationController {

    @Tag(name = "Reservas - GET")
    @Operation(summary = "Obtener todas las reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Reservation.class), examples = @ExampleObject(value = ApiExample.RESERVATION_LIST))),
            @ApiResponse(responseCode = "204", description = "No hay reservas en la base de datos", content = @Content),
    })
    @GetMapping("/reservas")
    ResponseEntity<List<Reservation>> getReservation();



    @Tag(name = "Reservas - GET")
    @Operation(summary = "Obtener una reserva por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Reservation.class), examples = @ExampleObject(value = ApiExample.RESERVATION))),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada en la base de datos", content = @Content),
    })
    @GetMapping("/reservas/{id}")
    ResponseEntity<Reservation> getReservation(
            @Parameter(description = "Identificador de la reserva", example = "1")
            @PathVariable Long id);



    @Tag(name = "Reservas - POST")
    @Operation(summary = "Crear una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada con éxito",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_OK))),
            @ApiResponse(responseCode = "400", description = "Datos de la reserva no válidos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
            @ApiResponse(responseCode = "409", description = "Ya existe una reserva en el mismo día y hora",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
    })
    @PostMapping("/reservas")
    ResponseEntity<ResponseData> postReservation(
            @Parameter(description = "Datos de la reserva a crear", example = ApiExample.RESERVATION)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la reserva a crear",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Reservation.class), examples = @ExampleObject(value = ApiExample.RESERVATION)))
            @RequestBody Reservation toPost);



    @Tag(name = "Reservas - PUT")
    @Operation(summary = "Actualizar una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada con éxito",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_OK))),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
            @ApiResponse(responseCode = "400", description = "Datos nuevos de la reserva no válidos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
            @ApiResponse(responseCode = "409", description = "Ya existe una reserva en el mismo día y hora",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
    })
    @PutMapping("/reservas/{id}")
    ResponseEntity<ResponseData> putReservation(
            @Parameter(description = "Identificador de la reserva", example = "1")
            @PathVariable Long id,

            @Parameter(description = "Datos de la reserva a actualizar", example = ApiExample.RESERVATION)
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la reserva a actualizar",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = Reservation.class), examples = @ExampleObject(value = ApiExample.RESERVATION)))
            @RequestBody Reservation toPut);



    @Tag(name = "Reservas - DELETE")
    @Operation(summary = "Eliminar una reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada con éxito",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_OK))),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ResponseData.class), examples = @ExampleObject(value = ApiExample.RESPONSE_DATA_ERROR))),
    })
    @DeleteMapping("/reservas/{id}")
    ResponseEntity<ResponseData> deleteReservation(
            @Parameter(description = "Identificador de la reserva", example = "1")
            @PathVariable Long id);



    @Tag(name = "Reservas - GET")
    @Operation(summary = "Obtener todas las reservas de un día", description = "Devuelve una lista de reservas de un día concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas encontradas en la base de datos",
                    content = @Content(mediaType = "json", schema = @Schema(implementation = ByDayDto.class), examples = @ExampleObject(value = ApiExample.BY_DAY_DTO_LIST))),
            @ApiResponse(responseCode = "204", description = "No hay reservas en la base de datos para el día indicado", content = @Content),
    })
    @GetMapping("/reservas/dia/{numDia}")
    ResponseEntity<List<ByDayDto>> getReservationByDay(
            @Parameter(description = "Número del día", example = "1")
            @PathVariable Integer numDia);
}
