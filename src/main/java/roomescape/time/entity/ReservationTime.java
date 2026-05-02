package roomescape.time.entity;

import java.time.LocalTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import roomescape.time.exception.InValidReservationTimeException;
import roomescape.time.exception.ReservationTimeErrorCode;

@Getter
@EqualsAndHashCode(of = "id")
public class ReservationTime {

    private final Long id;
    private final LocalTime startAt;

    private ReservationTime(Long id, LocalTime startAt) {
        validate(startAt);
        this.id = id;
        this.startAt = startAt;
    }

    public static ReservationTime createNew(LocalTime startAt) {
        return new ReservationTime(null, startAt);
    }

    public static ReservationTime of(long id, LocalTime startAt) {
        return new ReservationTime(id, startAt);
    }

    private void validate(LocalTime startAt) {
        if (startAt == null) {
            throw new InValidReservationTimeException(
                    ReservationTimeErrorCode.RESERVATION_TIME_START_AT_NOT_NULL.getMessage());
        }
    }

    public ReservationTime withId(long id) {
        return new ReservationTime(id, startAt);
    }

}
