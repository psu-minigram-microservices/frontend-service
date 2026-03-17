package me.soknight.minigram.frontend.exception;

import lombok.Getter;
import me.soknight.minigram.frontend.model.dto.ErrorDto;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;

@Getter
public class GenericErrorException extends Exception {

    protected final @NonNull HttpStatusCode statusCode;
    protected final @NonNull String errorCode;
    protected final @Nullable String errorMessage;

    public GenericErrorException(@Nullable Throwable cause) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected_error", "An unexpected error was occured! Try again later.", cause);
    }

    public GenericErrorException(@NonNull String errorCode, @Nullable String errorMessage) {
        this(HttpStatus.BAD_REQUEST, errorCode, errorMessage, null);
    }

    public GenericErrorException(
            @NonNull HttpStatusCode statusCode,
            @NonNull String errorCode,
            @Nullable String errorMessage
    ) {
        this(statusCode, errorCode, errorMessage, null);
    }

    public GenericErrorException(
            @NonNull HttpStatusCode statusCode,
            @NonNull String errorCode,
            @Nullable String errorMessage,
            @Nullable Throwable cause
    ) {
        super(errorMessage, cause);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static @NonNull GenericErrorException fromFieldError(@NonNull FieldError fieldError) {
        return fromConstraintViolation(fieldError.getField(), fieldError.getDefaultMessage());
    }

    public static @NonNull GenericErrorException fromConstraintViolation(
            @Nullable String fieldName,
            @Nullable String message
    ) {
        return new GenericErrorException(
                HttpStatus.BAD_REQUEST,
                "incorrect_field_value",
                "%s: %s".formatted(fieldName, message)
        );
    }

    public @NonNull ErrorDto constructModel() {
        return new ErrorDto(errorCode, errorMessage);
    }

}