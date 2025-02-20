package com.workintech.fswebs18challengemaven.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardErrorResponse {
    private Integer status;
    private String message;
    private Long timestamp;

    public CardErrorResponse(String message)
    {
        this.message = message;
    }

}