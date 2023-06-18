package com.ashik.demoblog.payloads.ResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {

   private Integer postId;
   private String tittle;
   private String content;
   private String imageName;

   private CatDto catDto;

   private UserOnPostDto userOnPostDto;

}
