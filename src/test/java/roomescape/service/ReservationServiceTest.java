package roomescape.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.reservation.entity.Reservation;
import roomescape.reservation.exception.ReservationDuplicateException;
import roomescape.reservation.exception.ReservationNotFoundException;
import roomescape.reservation.service.ReservationService;
import roomescape.service.stub.StubReservationRepository;
import roomescape.service.stub.StubReservationTimeRepository;
import roomescape.time.entity.ReservationTime;
import roomescape.time.exception.ReservationTimeDuplicateException;
import roomescape.time.service.ReservationTimeService;

class ReservationServiceTest {

    private ReservationService reservationService;
    private ReservationTimeService reservationTimeService;

    @BeforeEach
    void setup() {
        reservationTimeService = new ReservationTimeService(new StubReservationTimeRepository());
        reservationService = new ReservationService(new StubReservationRepository(), reservationTimeService);
    }

    @Test
    @DisplayName("예약 저장")
    void save_test() {
        // given
        LocalDate date = LocalDate.parse("2026-08-06");
        LocalTime time = LocalTime.parse("10:00");
        ReservationTime reservationTime = reservationTimeService.save(time);

        // when
        Reservation result = reservationService.save("쿠다", date, reservationTime.getId());
        Reservation saved = reservationService.getById(result.getId());

        // then
        assertThat(result).isEqualTo(saved);
    }

    @Test
    @DisplayName("예약 중복 저장 예외")
    void reservation_save_duplicate_test() {
        //given
        LocalDate date = LocalDate.parse("2026-08-06");
        LocalTime time = LocalTime.parse("10:00");
        ReservationTime reservationTime = reservationTimeService.save(time);

        //when & then
        assertThatThrownBy(() -> {
            reservationService.save("쿠다", date, reservationTime.getId());
            reservationService.save("아루", date, reservationTime.getId());
        })
                .isInstanceOf(ReservationDuplicateException.class)
                .hasMessageContaining("예약은 중복 생성이 불가능합니다.");
    }

    @Test
    @DisplayName("예약 단일 조회 id 없음 예외")
    void reservation_findById_null_search_test() {
        //given & when & then
        assertThatThrownBy(() -> reservationService.getById(99L))
                .isInstanceOf(ReservationNotFoundException.class)
                .hasMessageContaining("찾는 예약이 없습니다.");
    }

}
