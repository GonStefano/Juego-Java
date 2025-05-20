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

Actualmente se encuentran en desarrollo dos minijuegos con gráficos en 2D. Serán implementados con tecnologías como JavaFX para ofrecer una experiencia visual interactiva.

---

##  Tecnologías Usadas

- Java
- JavaFX (para los juegos 2D)
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
