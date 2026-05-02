package roomescape.reservation.entity;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import roomescape.reservation.exception.InvalidReservationException;
import roomescape.reservation.exception.ReservationErrorCode;
import roomescape.time.entity.ReservationTime;

@Getter
@EqualsAndHashCode(of = "id")
public class Reservation {

    private final Long id;
    private final String name;
    private final LocalDate date;
    private final ReservationTime time;

    private Reservation(Long id, String name, LocalDate date, ReservationTime time) {
        validate(name, date, time);

        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static Reservation createNew(String name, LocalDate date, ReservationTime time) {
        return new Reservation(null, name, date, time);
    }

    public static Reservation of(long id, String name, LocalDate date, ReservationTime time) {
        return new Reservation(id, name, date, time);
    }

    private void validate(String name, LocalDate date, ReservationTime time) {
        if (name == null || name.isBlank()) {
            throw new InvalidReservationException(ReservationErrorCode.RESERVATION_NAME_NOT_BLANK.getMessage());
        }
        if (date == null) {
            throw new InvalidReservationException(ReservationErrorCode.RESERVATION_DATE_NOT_NULL.getMessage());
        }
        if (time == null) {
            throw new InvalidReservationException(ReservationErrorCode.RESERVATION_TIME_NOT_NULL.getMessage());
        }
    }

    public Reservation withId(long id) {
        return new Reservation(id, this.name, this.date, this.time);
    }

}
