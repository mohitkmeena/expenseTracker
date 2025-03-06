package com.mohit.expenseservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {
    @JsonProperty("external_id")
    private String externalId;
    @JsonProperty(  "user_id")
    private String userId;
    @JsonProperty(  "merchant")
    private  String merchant;
    @JsonProperty(  "amount")
    private String amount;
    @JsonProperty(  "currency")
    private String currency;
    @JsonProperty(  "created_at")
    private Timestamp createdAt;
}
