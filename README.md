# Memoriza la Secuencia 🎮

Un juego de memoria cliente-servidor en Java donde debes recordar y repetir secuencias de números cada vez más largas.

## Descripción

**Memoriza la Secuencia** es un juego de consola que pone a prueba tu memoria. El servidor muestra una secuencia de números durante 5 segundos y luego debes escribirla correctamente. Cada nivel superado añade un nuevo número aleatorio a la secuencia.

## Requisitos

- **Java 11** o superior
- **Gradle** (opcional, el wrapper está incluido)

## Estructura del Proyecto

```
Memoriza_Secuencia/
├── src/
│   ├── main/java/
│   │   ├── Juego.java           # Modelo de datos del juego
│   │   ├── ServidorJuego.java   # Servidor TCP
│   │   ├── ClienteJuego.java    # Cliente TCP
│   │   └── ClienteHandler.java  # Lógica del juego por cliente
│   └── test/java/
│       ├── JuegoTest.java              # Tests del modelo
│       └── ValidadorSecuenciaTest.java # Tests de validación
├── build.gradle                 # Configuración de Gradle
├── settings.gradle
└── README.md
```

## Compilación

### Opción 1: Con Gradle instalado (recomendado)

Si tienes Gradle instalado en tu sistema:

```bash
gradle build
gradle test
```

### Opción 2: Sin Gradle (compilación manual)

```bash
# Crear directorios de salida
mkdir -p build/classes/java/main

# Compilar código fuente
javac -encoding UTF-8 -d build/classes/java/main src/main/java/*.java
```

## Ejecución

### Paso 1: Iniciar el Servidor

Abre una terminal y ejecuta:

```bash
# Con Gradle
gradlew run -PmainClass=ServidorJuego

# O directamente con Java
java -cp build/classes/java/main ServidorJuego
```

El servidor mostrará:
```
========================================
   SERVIDOR MEMORIZA LA SECUENCIA
========================================
Puerto: 9876
Pulsa Ctrl+C para detener
----------------------------------------
Esperando conexiones...
```

### Paso 2: Iniciar el Cliente

En **otra terminal**, ejecuta:

```bash
# Con Gradle
gradlew run -PmainClass=ClienteJuego

# O directamente con Java
java -cp build/classes/java/main ClienteJuego
```

## Cómo Jugar

1. **Observa** la secuencia de números que aparece (tienes 5 segundos)
2. **Memoriza** los números en orden
3. **Escribe** la secuencia separada por comas cuando se te pida
   - Ejemplo: `8,3,5`
4. Si aciertas, pasas al siguiente nivel con un número adicional
5. Si fallas, puedes elegir reiniciar o salir

### Ejemplo de Juego

```
SERVIDOR DICE: *****BIENVENID@ A MEMORIZA LA SECUENCIA*****
INSTRUCCIONES DEL JUEGO: memoriza la secuencia de números...

SECUENCIA: [8, 3, 5]
Memoriza la secuencia, tienes 5 segundos TikTak TikTak

Escribe la secuencia separada por comas (ej: 2,1,9):
> 8,3,5
Pasas al siguiente nivel.
Se añade un nuevo número a la secuencia:
```

## Ejecutar Tests

```bash
# Con Gradle Wrapper
gradlew test

# Ver resultados detallados
gradlew test --info
```

Los resultados de los tests se encuentran en `build/reports/tests/test/index.html`

## Generar Javadoc

```bash
gradlew javadoc
```

La documentación se genera en `build/docs/javadoc/`

## Puerto Personalizado

Puedes especificar un puerto diferente al iniciar el servidor:

```bash
java -cp build/classes/java/main ServidorJuego 8080
```

## Autor

Proyecto educativo de programación en Java con sockets.
