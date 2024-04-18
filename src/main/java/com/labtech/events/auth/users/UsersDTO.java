package com.labtech.events.auth.users;

import com.labtech.events.constants.Enums.Roles_user;
import com.labtech.events.files.Arquivo;
import com.labtech.events.utils.AbstractEntityDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsersDTO extends AbstractEntityDTO {

  private UUID uid;

  private String name;

  private String email;

  private String password;

  private Arquivo foto_perfil;

  private Arquivo foto_capa;

  private List<Roles_user> roles;
}
