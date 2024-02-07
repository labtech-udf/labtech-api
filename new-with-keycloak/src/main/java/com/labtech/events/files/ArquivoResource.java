package com.labtech.events.files;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "arquivo")
//@Tag(name = "Arquivo", description = "Este é um resource de arquivo")
public class ArquivoResource {

  @GetMapping(value = "test")
  public String getText() {
    return "Working";
  }

}
