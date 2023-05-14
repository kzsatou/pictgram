package com.example.pictgram.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ImageByteValidator implements ConstraintValidator<ImageByte, MultipartFile> {

	/*maxは画像の最大サイズ*/
    int max;

    @Override
    public void initialize(ImageByte annotation) {
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {
        if (image.getSize() > this.max) {
            return false;
        }
        return true;
    }
}
