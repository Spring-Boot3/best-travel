# Notas sobre el Transactional en Spring

El uso de `@Transactional` en Spring permite manejar transacciones en la base de datos, pudiendo emplear un `commit` o un `rollback` dependiendo de si se completa la transacción o no.

Cuando `@Transactional` se usa de forma global, se genera una transacción por cada función en nuestro bloque de código. Sin embargo, a esta instrucción global se le pueden agregar instrucciones específicas.

Por ejemplo, en el servicio `ReservationService` en la función `create`, la transacción se abre o inicia en el momento en que se ejecuta la instrucción del repositorio.

## Queries

### Primer Query

El primer query se realiza para obtener el hotel:

```java
var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow();
```

### Segundo Query

El segundo query se realiza para obtener el cliente:

```java
var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow();
```

### Tercer Query

El tercer query se realiza para el guardado de la informacion de la reservacion:

```java
var reservationPersisted = this.reservationRepository.save(reservationToPersist);
```

### Cuarto Query

El cuarto query Inserta un incrementable en la tabla de Tickets:

```java 
customerHelper.incrase(customer.getDni(), ReservationService.class);
```

Todo esto es una sola transacción, por lo que si algo falla, se hará un `rollback` y no se guardará nada en la base de datos.

## @Transactional(propagation = Propagation.MANDATORY)

La anotación `@Transactional(propagation = Propagation.MANDATORY)` se usa para indicar que una transacción ya 
debe estar en progreso. Si no es así, se lanzará una excepción.

## @Transactional(propagation = Propagation.REQUIRES_NEW)

La anotación `@Transactional(propagation = Propagation.REQUIRES_NEW)` se usa para indicar que en cada una de nuestras queries se
hara la creacion de una nueva transaccion para cada una de ellas.

## @Transactional(propagation = Propagation.NESTED)

La anotación `@Transactional(propagation = Propagation.NESTED)` se usa para indicar lo contrario de `REQUIRES_NEW`, es decir, que se
ejecurta una sola transaccion para todas las queries.

### Entonces Propagation nos ayuda para el manejo de transaccionalidad en Spring.

### Tambien existe una propiedad llamada `readOnly` que se puede usar en la anotacion `@Transactional` para indicar que la transaccion es de solo lectura.

Esto quiere decir que no se hara ningun tipo de escritura en la base de datos, solo lectura. 
