package com.labtech.events.files;

import com.labtech.events.constants.SchemaConstants;
import com.labtech.events.utils.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "Arquivo_tb", schema = SchemaConstants.EVENTOS)
public class Arquivo extends AbstractEntity {
  private String name;
  private String type;
  private String url;
  private Long size;
  private String key;
  private UUID uid;
}