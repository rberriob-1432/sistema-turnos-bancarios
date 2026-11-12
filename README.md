# Sistema de Turnos Bancarios

## Descripción

Sistema de gestión de turnos bancarios desarrollado en Java 21, que permite registrar clientes, administrar turnos pendientes, procesar solicitudes, cancelar turnos y consultar el historial de atención.

El proyecto implementa estructuras de datos dinámicas como listas doblemente enlazadas, colas y pilas para organizar las operaciones del sistema y aplicar los principios de las estructuras de datos en un caso práctico.

## Funcionalidades

- Registrar nuevos turnos bancarios.
- Consultar todos los turnos registrados.
- Visualizar los turnos pendientes.
- Procesar el siguiente turno siguiendo el orden de llegada.
- Consultar el historial de turnos atendidos.
- Buscar turnos por su número.
- Cancelar turnos pendientes.
- Deshacer el último procesamiento.
- Generar reportes generales de los turnos registrados.

## Tipos de servicio

El sistema contempla cuatro tipos de servicios:

- Caja.
- Asesoría.
- Crédito.
- Apertura de cuenta.

## Estructuras de datos implementadas

### 1. Lista doblemente enlazada

Se utiliza para almacenar y administrar todos los turnos registrados. Permite insertar, eliminar, buscar y recorrer elementos en ambas direcciones.

### 2. Cola (FIFO)

Se utiliza para gestionar los turnos pendientes. El primer turno en ingresar es el primero en ser procesado, respetando el orden de llegada de los clientes.

### 3. Pila (LIFO)

Se utiliza para almacenar el historial de turnos atendidos y permitir deshacer el último procesamiento realizado.

## Tecnologías utilizadas

- **Java 21:** lenguaje de programación.
- **Maven:** administración del proyecto y sus dependencias.
- **JUnit 5:** ejecución de pruebas unitarias.

## Estructura del proyecto

```text
sistema-turnos-bancarios/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── banco/
│   │               ├── Main.java
│   │               ├── consola/
│   │               │   ├── EntradaConsola.java
│   │               │   └── Menu.java
│   │               ├── estructuras/
│   │               │   ├── Cola.java
│   │               │   ├── Lista.java
│   │               │   ├── Nodo.java
│   │               │   └── Pila.java
│   │               ├── modelo/
│   │               │   ├── EstadoTurno.java
│   │               │   ├── TipoServicio.java
│   │               │   └── TurnoBancario.java
│   │               └── sistema/
│   │                   ├── GeneradorReportes.java
│   │                   ├── GestorCancelaciones.java
│   │                   ├── GestorProcesamiento.java
│   │                   ├── GestorTurnos.java
│   │                   └── SistemaDelCaso.java
│   └── test/
│       └── java/
│           └── com/
│               └── banco/
│                   ├── estructuras/
│                   ├── modelo/
│                   └── sistema/
└── pom.xml
```

## Requisitos previos

Antes de ejecutar el proyecto, es necesario tener instalado:

- JDK 21 o superior.
- Apache Maven.

## Instalación y ejecución

**1. Clonar el repositorio**

```bash
git clone https://github.com/rberriob-1432/sistema-turnos-bancarios.git
```

**2. Ingresar al directorio**

```bash
cd sistema-turnos-bancarios
```

**3. Compilar el proyecto**

```bash
mvn clean compile
```

**4. Ejecutar la aplicación**

```bash
mvn exec:java -Dexec.mainClass="com.banco.Main"
```

## Ejecución de pruebas

El proyecto utiliza JUnit 5 para verificar el funcionamiento de las estructuras de datos, el modelo de turnos y las operaciones principales del sistema.

Para ejecutar todas las pruebas unitarias:

```bash
mvn test
```

Se cuenta con **65 pruebas unitarias** distribuidas en los siguientes componentes:

| Clase de prueba | Pruebas |
|---|---:|
| ColaTest | 11 |
| ListaTest | 19 |
| PilaTest | 11 |
| TurnoBancarioTest | 10 |
| SistemaDelCasoTest | 14 |
| **Total** | **65** |

## Objetivo del proyecto

Aplicar los conceptos de estructuras de datos mediante el desarrollo de un sistema de turnos bancarios, fortaleciendo el razonamiento lógico y la implementación de operaciones de inserción, eliminación, búsqueda, recorrido y administración de información.

## Autor

**Raul Alonso Berrio Blanquicett**

## Repositorio

[GitHub - Sistema de Turnos Bancarios](https://github.com/rberriob-1432/sistema-turnos-bancarios)