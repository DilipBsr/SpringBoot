package com.admiral.springBootExample1.demo.dto;

import lombok.*;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseDto {
    private String id;
    private String name;
    private Map<String,?> data;

}
