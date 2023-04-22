package com.example.pictgram.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.example.pictgram.validation.constraints.ImageByte;
import com.example.pictgram.validation.constraints.ImageNotEmpty;

import lombok.Data;


/*投稿のフォーム*/
@Data
public class TopicForm {

    private Long id;

    private Long userId;

    @ImageNotEmpty /*独自バリデーション*/
    @ImageByte(max = 2000000)/*独自バリデーション　maxは最大画像サイズ*/
    private MultipartFile image;

    private String imageData;

    private String path;

    @NotEmpty
    @Size(max = 1000)
    private String description;

    private UserForm user;

}
