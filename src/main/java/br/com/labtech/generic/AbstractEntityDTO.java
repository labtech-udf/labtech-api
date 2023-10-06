package br.com.labtech.generic;

import br.com.labtech.constants.UtilsConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@JsonIgnoreProperties({"createdBy", "updatedBy", "created", "updated", "excluded"})
public abstract class AbstractEntityDTO implements Serializable {

    protected AbstractEntityDTO() {
        this.excluded = false;
    }

    private Long id;

    private String createdBy;

    private String updatedBy;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = UtilsConstants.DATE_TIME_PATTERN)
    private LocalDateTime created;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = UtilsConstants.DATE_TIME_PATTERN)
    private LocalDateTime updated;

    private Boolean excluded = false;

}


