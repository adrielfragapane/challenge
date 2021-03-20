# Frávega IT - Sucursal CRUD

Aplicación API realizada con Spring Boot y persistencia en base de datos MySQL.

## Compilación

Generar archivo .jar de aplicación Spring Boot (ignorando los tests debido a la existencia del caso de prueba no exitoso).

````
cd challenge 
docker
mvn package -Dmaven.test.skip=true
```

Levantar aplicación Sring Boot y base de datos MySQL con Docker-compose

```
cd challenge

docker build . -t challenge

docker-compose up
```

## API Docs


API con operaciones CRUD (GET - POST) y consulta por sucursal más cercana a un punto dado.

## Obtener todas las sucursales existentes

Request:

GET -  http://localhost:8085/sucursales

Ejemplo:

GET -  http://localhost:8085/sucursales

Respuesta:

```
[
    {
    "id": 1
    "direccion": "Dirección 1",
    "latitud": -34.6290682,
    "longitud": 23.4499947
    },
    {
    "id": 2
    "direccion": "Dirección 2",
    "latitud": -85.4856321,
    "longitud": -100.6847859
    },
    {
    "id": 3
    "direccion": "Dirección 3",
    "latitud": 15.4863254,
    "longitud": 26.579854
    }
]
```
## Obtener una sucursal por id

Request:

GET -  http://localhost:8085/sucursales/{id}

Ejemplo:

GET -  http://localhost:8085/sucursales/2

Respuesta:

```
{
    "id": 2
    "direccion": "Dirección 2",
    "latitud": -85.4856321,
    "longitud": -100.6847859
}
```

## Crear una nueva sucursal

Request:

POST -  http://localhost:8085/sucursales

Ejemplo:

POST -  http://localhost:8085/sucursales

Body:

```
{
    "direccion": "Dirección 4",
    "latitud": 63.1967358,
    "longitud": 28.5973547
}
```

Respuesta:

```
{
    "id": 4
    "direccion": "Dirección 4",
    "latitud": 63.1967358,
    "longitud": 28.5973547
}
```


## Obtener la sucursal más cercana geográficamente

El cálculo de la sucursal más cercana se realiza geométricamente, y no geográficamente, utilizando la latitud y longitud provistas.

Request:

GET -  http://localhost:8085/sucursales/miSucursal?latitud={latitud}&longitud={longitud}

Ejemplo:

GET -  http://localhost:8085/sucursales/miSucursal?latitud=-66.9863759&longitud=-80.9683574

Respuesta:

```
{
    "id": 2
    "direccion": "Dirección 2",
    "latitud": -85.4856321,
    "longitud": -100.6847859
    }
```

