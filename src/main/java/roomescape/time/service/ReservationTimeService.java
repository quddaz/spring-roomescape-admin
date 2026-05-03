package roomescape.time.service;

import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.time.entity.ReservationTime;
import roomescape.time.exception.ReservationTimeDuplicateException;
import roomescape.time.exception.ReservationTimeNotFoundException;
import roomescape.time.repository.ReservationTimeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationTimeService {

    private final ReservationTimeRepository reservationTimeRepository;

    @Transactional
    public ReservationTime save(LocalTime startAt) {
        ReservationTime reservationTime = ReservationTime.createNew(startAt);

        if (reservationTimeRepository.existsByStartAt(startAt)) {
            throw new ReservationTimeDuplicateException();
        }

        return reservationTimeRepository.save(reservationTime);
    }

    @Transactional
    public void deleteById(long id) {
        reservationTimeRepository.deleteById(id);
    }

    public ReservationTime getById(long id) {
        return reservationTimeRepository.findById(id)
                .orElseThrow(ReservationTimeNotFoundException::new);
    }

    public List<ReservationTime> findAll() {
        return reservationTimeRepository.findAll();
    }

}
