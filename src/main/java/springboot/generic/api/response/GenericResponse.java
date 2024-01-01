package springboot.generic.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {

    private Boolean success;
    private String message;
    private T data;

    public static <T> GenericResponse<T> success(T data, String message) {
        return GenericResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> error(T data, String message) {
        return GenericResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}
