package roomescape.reservation.exception;

import roomescape.exception.exception.BadRequestException;

public class InvalidReservationException extends BadRequestException {
    public InvalidReservationException(String message) {
        super(message);
    }
}
