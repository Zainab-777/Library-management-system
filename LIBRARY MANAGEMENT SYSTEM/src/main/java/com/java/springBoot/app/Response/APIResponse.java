package com.java.springBoot.app.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.springBoot.app.Enum.ResponseStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
public class APIResponse<T> {
    private int resultCode;
    private String message;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("list")
    private List<T> dataList;

    public APIResponse(ResponseStatus status) {
        this.resultCode = status.getCode();
        this.message = status.getMessage();
    }

    // Success response with single data
    public static <T> APIResponse<T> success(T data) {
        APIResponse<T> response = new APIResponse<>(ResponseStatus.SUCCESS);
        response.setData(data);
        return response;
    }

    // Success response with list of data
    public static <T> APIResponse<T> successList(List<T> dataList) {
        APIResponse<T> response = new APIResponse<>(ResponseStatus.SUCCESS);
        response.setDataList(dataList);
        return response;
    }

    // Error response based on ResponseStatus
    public static <T> APIResponse<T> error(ResponseStatus status) {
        return new APIResponse<>(status);
    }

    // Optional error method with custom resultCode and resultDescription (less preferred)
    public static <T> APIResponse<T> error(int resultCode, String resultDescription) {
        APIResponse<T> response = new APIResponse<>();
        response.setResultCode(resultCode);
        response.setMessage(resultDescription);
        return response;
    }
    public static <T> APIResponse<T> errorValidation(int resultCode, T errors) {
        APIResponse<T> response = new APIResponse<>();
        response.setResultCode(resultCode);
        response.setData(errors);
        response.setMessage("Validation failed");
        return response;
    }
}
