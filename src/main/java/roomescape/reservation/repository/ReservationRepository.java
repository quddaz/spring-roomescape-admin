package roomescape.reservation.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import roomescape.reservation.entity.Reservation;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    void deleteById(long id);

    Optional<Reservation> findById(long id);

    List<Reservation> findAll();

    boolean existsByDateAndTimeId(LocalDate date, long timeId);
}
