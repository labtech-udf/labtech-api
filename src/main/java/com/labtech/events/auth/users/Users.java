package com.labtech.events.auth.users;

import com.labtech.events.constants.Enums.Roles_user;
import com.labtech.events.constants.SchemaConstants;
import com.labtech.events.files.Arquivo;
import com.labtech.events.utils.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users_tb", schema = SchemaConstants.EVENTOS)
public class Users extends AbstractEntity {

  private UUID uid;

  private String name;

  private String email;

  private String password;

  @ManyToOne
  private Arquivo foto_perfil;

  @ManyToOne
  private Arquivo foto_capa;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
  private Set<Roles_user> roles = new HashSet<>();

}
