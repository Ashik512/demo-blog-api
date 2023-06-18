package com.ashik.demoblog.payloads.RequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {

    @NotBlank
    private String title;

    private String content;

}
