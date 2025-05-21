# Proyecto de Minijuegos

##  Descripción General

Este proyecto tiene como objetivo desarrollar una colección de **minijuegos** en Java, organizados en dos categorías principales:

- **Juegos en consola** (3 juegos)
- **Juegos en 2D** (2 juegos)

---

##  Juegos en Consola

1. ###  Ajedrez  
   Juego de ajedrez jugable desde la consola. Los jugadores pueden mover piezas por turnos siguiendo las reglas básicas del ajedrez. Se incluye verificación de movimientos válidos, jaque y jaque mate.

2. ###  Buscaminas  
   Versión de consola del clásico buscaminas. El jugador debe descubrir celdas sin explotar minas. Si marca correctamente todas las minas y descubre el resto de celdas, gana la partida.

3. ###  Batalla por Turnos  
   Juego de combate por turnos entre el jugador y enemigos controlados por la IA. Incluye acciones como atacar, defender, usar habilidades especiales y ganar experiencia para subir de nivel.

---

##  Juegos en 2D

En esta sección se incluyen dos minijuegos con gráficos en 2D que están siendo desarrollados con JavaFX y Swing:

1. ###  SpaceRunner  
   Un juego arcade en el que el jugador controla una nave espacial que debe esquivar meteoritos y recolectar estrellas para aumentar su puntaje. A medida que el tiempo avanza, la dificultad aumenta con más obstáculos. El objetivo es sobrevivir el mayor tiempo posible mientras se alcanza la mayor puntuación.

2. ###  Buscaminas (Versión 2D)  
   Adaptación visual del clásico buscaminas. El jugador interactúa con una cuadrícula gráfica para descubrir celdas vacías o marcar posibles minas. El juego finaliza cuando se revelan todas las celdas seguras o se detona una mina. Esta versión ofrece una experiencia más intuitiva mediante interfaz gráfica.

---

##  Tecnologías Usadas

- Java
- JavaFX (para los juegos 2D)
- Swing
- Programación orientada a objetos
- Diseño modular por clases

---

##  Estructura del Proyecto

### Juego de Batalla por Turnos

#### Descripción
Este es un juego sencillo de batalla por turnos basado en consola, implementado en Java. El juego presenta jugadores y monstruos que se turnan para realizar acciones como atacar, curar o defender. El objetivo es derrotar al oponente reduciendo sus puntos de vida (HP) a cero.

---

#### Características

- Sistema de combate por turnos.
- Múltiples entidades: Jugador y Monstruo.
- Cada entidad tiene:
  - Puntos de vida (HP).
  - Puntos de maná (MP).
  - Puntos de experiencia (XP).
- Acciones disponibles:
  - Atacar.
  - Curar.
  - Defender.
- Interfaz de menú para controlar el flujo del juego.
- Posibilidad de guardar y cargar datos del jugador (si está implementado).

---

#### Clases y responsabilidades

##### Entidad (Clase abstracta)
- Clase base para todos los personajes del juego.
- Atributos: vida, maná, experiencia, nivel.
- Métodos comunes: atacar, curar, defender, verificar estado.

##### Jugador
- Hereda de `Entidad`.
- Representa al jugador controlado por el usuario.
- Puede subir de nivel y usar habilidades.

##### Monstruo
- Hereda de `Entidad`.
- Representa enemigos controlados por la IA.

##### Habilidad
- Representa una habilidad o acción que las entidades pueden usar.
- Tipos: ataque, curación, defensa.

##### Juego
- Controla el ciclo principal del juego.
- Administra el orden de turnos y la entrada/salida.
- Gestiona las transiciones de estado del juego.

---

#### Cómo jugar

1. Inicia el juego.
2. Elige crear un nuevo jugador o cargar uno existente.
3. En el menú principal puedes:
   - Iniciar una batalla contra un monstruo.
   - Ver las estadísticas del jugador.
   - Guardar y salir.
4. Durante la batalla:
   - Elige una acción (atacar, curar, defender).
   - Los turnos alternan entre el jugador y el monstruo hasta que uno pierda toda su vida.
5. Gana la batalla para obtener experiencia y subir de nivel.

---

#### Ejemplo de flujo de juego

```text
¡Bienvenido al Juego de Batalla por Turnos!

1) Nuevo Juego
2) Cargar Juego
3) Salir

Selecciona opción: 1

Introduce el nombre de tu jugador: Héroe

Menú Principal:
1) Iniciar Batalla
2) Ver Estadísticas
3) Guardar y Salir

Selecciona opción: 1

¡Batalla iniciada contra el Goblin!

Tu turno:
1) Atacar
2) Curar
3) Defender
Selecciona acción: 1

Atacas al Goblin y le haces 10 de daño.

Turno del Goblin...
El Goblin ataca y te hace 5 de daño.

...

¡Has ganado! Has obtenido 20 puntos de experiencia.

Volviendo al menú principal...
```
###  SpaceRunner (Juego en 2D)

**SpaceRunner** es un juego arcade en 2D donde el jugador controla una nave espacial que debe esquivar meteoritos y recolectar estrellas para aumentar su puntaje.

#####  Mecánica del Juego

- El jugador maneja una **nave espacial** que se desplaza en un entorno horizontal o vertical (según diseño).
- Caen **meteoritos** desde la parte superior de la nave. Si la nave colisiona con un meteorito, se pierde una vida de tres.
- Aparecen **estrellas** que el jugador debe recoger para acumular puntos.

#####  Controles

- Flechas del teclado (o teclas WASD) para mover la nave.

#####  Objetivo

- Sobrevivir el mayor tiempo posible mientras se recolectan estrellas.
- Obtener la **máxima puntuación posible**.

#####  Características Implementadas o Planeadas

- [x] Movimiento de la nave
- [x] Generación aleatoria de meteoritos
- [x] Recolección de estrellas
- [x] Sistema de puntuación
- [ ] Aumento progresivo de dificultad
- [ ] Menú de inicio y pantalla de Game Over

#####  Recursos Gráficos

- Imagenes para la nave, meteoritos y estrellas
- Fondo espacial animado (opcional)

#####  Tecnologías

- **JavaFX** para el renderizado gráfico y manejo de eventos
- **POO** para estructurar entidades como `Nave`, `Meteorito`, `Estrella`, `Juego`, etc.

### Juego de Ajedrez  
Juego de Ajedrez por Turnos en Consola

#### Descripción

Este es un juego de ajedrez basado en consola, implementado en Java. El juego permite a dos jugadores (sin IA) turnarse para mover piezas en un tablero de 8x8, siguiendo las reglas clásicas del ajedrez. Cada pieza se mueve de acuerdo a sus propias reglas, y el objetivo es dar jaque mate al rey del oponente.

#### Características

- Juego de ajedrez en modo texto desde la terminal.
- Turnos alternos entre jugador blanco y negro.
- Implementación completa de las piezas: Rey, Reina, Torre, Alfil, Caballo, Peón.
- Reglas estándar de movimiento y captura.
- Detección de movimientos inválidos y captura ilegal.
- Registro de estadísticas de las partidas en archivo externo.
- Interfaz básica con entrada de texto para movimientos.
- Manejo de excepciones personalizadas para errores de jugada.

#### Clases y responsabilidades

##### Pieza (Clase abstracta)

- Clase base para todas las piezas del juego.
- Atributos: color, posición.
- Métodos comunes: validación de movimiento, representación en texto.

##### Rey, Reina, Torre, Alfil, Caballo, Peón

- Heredan de `Pieza`.
- Cada clase implementa la lógica específica de movimiento.

##### Tablero

- Representa el estado del tablero de ajedrez.
- Maneja el movimiento de piezas, verificación de capturas y turnos.
- Muestra el tablero en la consola.

##### MainAjedrez

- Clase principal del juego.
- Controla el flujo del juego: turnos, ingreso de movimientos y finalización.

##### Estadistica

- Maneja la lectura y escritura de datos en un archivo de estadísticas (`estadisticas.txt`).
- Registra partidas jugadas, ganadas, perdidas, etc.

##### Excepciones personalizadas

- `MovimientoInvalidoExcepcion`: se lanza cuando un movimiento no es permitido por las reglas.
- `CapturaAliadaExcepcion`: impide capturar piezas del mismo color.

##### Utilidades

- `Colores`: Maneja la visualización en consola con colores (si está disponible).
- `Terminal`: Funciones auxiliares para entrada y salida de datos en la consola.

### Cómo jugar

1. Ejecuta el programa desde la clase `MainAjedrez`.
2. El tablero se muestra en consola con las piezas iniciales.
3. Introduce los movimientos en el formato solicitado (por ejemplo: `e2 e4`).
4. El turno alterna entre jugadores.
5. Las piezas se mueven según las reglas del ajedrez.
6. El juego finaliza cuando hay jaque mate o se detiene manualmente.

### Ejemplo de flujo de juego
```text
Se inicia el tablero de ajedrez con las piezas en su posición inicial.

♙ Mueven las BLANCAS
Movimiento: Caballo de g1 a f3

♟ Mueven las NEGRAS
Movimiento: Peón de e7 a e5

♙ Mueven las BLANCAS
Movimiento: Peón de d2 a d4

♟ Mueven las NEGRAS
Movimiento: Peón de e5 captura en d4

♙ Mueven las BLANCAS
Movimiento: Alfil de c1 a f4

♟ Mueven las NEGRAS
Movimiento: Caballo de b8 a c6

♙ Mueven las BLANCAS
Movimiento: Reina de d1 a d2

♟ Mueven las NEGRAS
Movimiento: Peón de d7 a d6

♙ Mueven las BLANCAS
Movimiento: Enroque corto (Rey de e1 a g1, Torre de h1 a f1)

...

- Más adelante en la partida...

♙ Mueven las BLANCAS
Movimiento: Reina de f4 a f7


