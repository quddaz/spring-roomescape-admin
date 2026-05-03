package roomescape.time.controller;


import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roomescape.time.controller.dto.ReservationTimeRequest;
import roomescape.time.controller.dto.ReservationTimeResponse;
import roomescape.time.entity.ReservationTime;
import roomescape.time.service.ReservationTimeService;

@RestController
@RequestMapping("/times")
public class ReservationTimeController {

    private final ReservationTimeService reservationTimeService;

    public ReservationTimeController(ReservationTimeService reservationTimeService) {
        this.reservationTimeService = reservationTimeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationTimeResponse> readAll() {
        return reservationTimeService.findAll().stream()
                .map(ReservationTimeResponse::from)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationTimeResponse add(
            @Valid @RequestBody ReservationTimeRequest reservationTimeRequest) {
        ReservationTime reservationTime = reservationTimeService.save(reservationTimeRequest.startAt());
        return ReservationTimeResponse.from(reservationTime);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        reservationTimeService.deleteById(id);
    }

}
