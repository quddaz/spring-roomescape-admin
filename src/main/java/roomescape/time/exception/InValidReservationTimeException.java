package roomescape.time.exception;

import roomescape.exception.exception.BadRequestException;

public class InValidReservationTimeException extends BadRequestException {
    public InValidReservationTimeException(String message) {
        super(message);
    }
}
