package br.com.labtech.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class GenericResource<D extends AbstractEntityDTO, R> {


    private GenericService<D> service;

    private final String url;

    public GenericResource(GenericService<D> service, String url) {
        this.service = service;
        this.url = url;
    }

    public ResponseEntity<?> createObject(D dto) throws Exception {
        D result = this.service.save(dto);
        return ResponseEntity.created(new URI(url + dto.getId()))
                .body(result);
    }

    public ResponseEntity<String> updateObject(D dto) throws Exception {
        D result = this.service.save(dto);
        return ResponseEntity.ok()
                .body(result.toString());
    }

    public ResponseEntity<D> findOne(Long id) {
        Optional<D> entity = this.service.findOneById(id);
        return entity.map(d -> ResponseEntity.status(HttpStatus.OK).body(d)).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    public ResponseEntity<String> delete(Long id) throws Exception {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public List<D> findAll() {
        return this.service.findAll();
    }

}
