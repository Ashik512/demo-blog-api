package com.ashik.demoblog.payloads.ResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserOnPostDto {

    private Integer userId;
    private String name;

}
