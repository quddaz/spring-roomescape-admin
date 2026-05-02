package roomescape.exception.response;

import java.util.List;

public record ErrorResponse(
        String message,
        List<ValidationError> validationErrors
) {
    public static ErrorResponse of(String message) {
        return new ErrorResponse(message, null);
    }

    public static ErrorResponse of(String message, List<ValidationError> errors) {
        return new ErrorResponse(message, null);
    }
}
