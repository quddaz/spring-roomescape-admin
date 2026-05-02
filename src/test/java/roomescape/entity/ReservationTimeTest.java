package roomescape.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import roomescape.reservation.exception.InvalidReservationException;
import roomescape.time.entity.ReservationTime;
import roomescape.time.exception.ReservationTimeErrorCode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTimeTest {

    @Test
    @DisplayName("정상 예약 시간 생성")
    void createNew_Success() {
        // given
        LocalTime time = LocalTime.parse("10:00");

        // when
        ReservationTime reservationTime = ReservationTime.createNew(time);

        // then
        assertThat(reservationTime.getId()).isNull();
        assertThat(reservationTime.getStartAt()).isEqualTo(time);
    }

    @Test
    @DisplayName("예약 시간 null 예외")
    void validate_NullStartAt_ThrowsException() {
        // given
        LocalTime nullTime = null;

        // when & then
        assertThatThrownBy(() -> ReservationTime.createNew(nullTime))
                .isInstanceOf(InvalidReservationException.class)
                .hasMessage(ReservationTimeErrorCode.RESERVATION_TIME_START_AT_NOT_NULL.getMessage());
    }
}
