package com.ashik.demoblog.payloads.ResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Integer id;

    @NotBlank(message = "Comment should not be empty!")
    private String content;

}
