package com.zzb.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZBException extends RuntimeException{
    private Integer code;
    private String msg;
}
