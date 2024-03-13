https://github.com/bitnami/containers/tree/main/bitnami/keycloak#how-to-use-this-image

### Start aplicação local
```bash
# Start
$ docker-compose -f up

# Down
$ docker-compose -f down

# O servidor iniciará na porta:8081 - acesse <http://localhost:8081/swagger-ui/index.html>
```

### Compose up:prod 
```bash
# Start

$ docker-compose -f .\compose-prod.yaml up

# Down
$ docker-compose -f ./compose-prod.yaml down


# O servidor iniciará na porta:8081 - acesse <http://localhost:8081/swagger-ui/index.html>
```
