package me.soknight.minigram.frontend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDto(
        @JsonProperty("error_code") String errorCode,
        @JsonProperty("error_message") String errorMessage,
        @JsonProperty("payload") Object payload
) {

    public ErrorDto(@NonNull String errorCode, @Nullable String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public ErrorDto(@NonNull String errorCode, @Nullable String errorMessage, @Nullable Object payload) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.payload = payload;
    }

}