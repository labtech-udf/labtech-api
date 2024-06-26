package com.labtech.events.objDesenSus;


import com.labtech.events.utils.AbstractEntityDTO;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ObjDesenSusDTO extends AbstractEntityDTO {

  private String name;

  private String description;

  private boolean status;

}
