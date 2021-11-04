package br.com.develfoodspringweb.develfoodspringweb.configuration.valid;

import lombok.Data;

@Data
public class FormErrorDto {

    private String field;
    private String error;

    public FormErrorDto(String field, String error) {
        this.field = field;
        this.error = error;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 6d6d8c708e61ac6428f3688e8e0ab11cbcf8e097
