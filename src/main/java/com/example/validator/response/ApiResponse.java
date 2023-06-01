package com.example.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Result result;
    private CommonData commonData;
    private List<ErrorInfo> errors;
    private T payload;

    public ApiResponse(T payload){
        this.result = Result.builder()
                .code("00")
                .status("SUCCESS")
                .build();
        this.payload = payload;
        this.commonData = CommonData.builder()
                .timestamp("" + new Date().getTime())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    public ApiResponse(T payload, String requestId){
        this(payload);
        this.commonData = CommonData.builder()
                .timestamp("" + new Date().getTime())
                .requestId(StringUtils.isNotBlank(requestId) ? requestId : UUID.randomUUID().toString())
                .build();
    }

    public static ApiResponse getApiResponseError(List<ErrorInfo> errors, String requestId){
        return ApiResponse.builder()
                .commonData(CommonData.builder()
                        .timestamp("" + new Date().getTime())
                        .requestId(StringUtils.isNotBlank(requestId) ? requestId : UUID.randomUUID().toString())
                        .build())
                .errors(errors)
                .build();
    }
}
