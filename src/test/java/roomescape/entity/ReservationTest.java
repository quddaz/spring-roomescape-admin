package roomescape.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.reservation.entity.Reservation;
import roomescape.reservation.exception.InvalidReservationException;
import roomescape.time.entity.ReservationTime;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTest {

    @DisplayName("예약 정상 생성")
    @Test
    void createReservation_Success() {
        // given
        String name = "쿠다";
        LocalDate date = LocalDate.parse("2026-03-08");
        ReservationTime time = ReservationTime.createNew(LocalTime.parse("10:00"));

        // when & then
        assertThatCode(() -> Reservation.createNew(name, date, time))
                .doesNotThrowAnyException();
    }

    @DisplayName("예약자 이름 null, 공백 예외")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void validateName_ThrowsException(String name) {
        // given
        LocalDate date = LocalDate.parse("2026-03-08");
        ReservationTime time = ReservationTime.createNew(LocalTime.parse("10:00"));

        // when & then
        assertThatThrownBy(() -> Reservation.createNew(name, date, time))
                .isInstanceOf(InvalidReservationException.class)
                .hasMessage("예약자 이름은 비어있을 수 없습니다.");
    }

    @DisplayName("예약 날짜 null 예외")
    @Test
    void validateDate_ThrowsException() {
        // given
        String name = "쿠다";
        ReservationTime time = ReservationTime.createNew(LocalTime.parse("10:00"));

        // when & then
        assertThatThrownBy(() -> Reservation.createNew(name, null, time))
                .isInstanceOf(InvalidReservationException.class)
                .hasMessage("예약 날짜는 비어있을 수 없습니다.");
    }

    @DisplayName("예약 시간 null 예외")
    @Test
    void validateTime_ThrowsException() {
        // given
        String name = "쿠다";
        LocalDate date = LocalDate.parse("2026-03-08");

        // when & then
        assertThatThrownBy(() -> Reservation.createNew(name, date, null))
                .isInstanceOf(InvalidReservationException.class)
                .hasMessage("예약 시간 정보가 없습니다.");
    }

}
