Buscaminas en Java (Consola)
Este proyecto está basado en el clásico juego Buscaminas, que se ejecuta en la consola. El jugador puede seleccionar entre diferentes niveles de dificultad y realizar acciones para descubrir celdas o marcar posibles minas con banderas.

Características
Juego clásico de Buscaminas sin interfaz gráfica.

Tres niveles de dificultad: Fácil, Medio y Difícil.

Modo bandera para marcar/desmarcar minas.

Evita colocar minas en la primera celda descubierta.

Detecta automáticamente si el jugador gana o pierde.

Representación visual sencilla del tablero en consola.

Lógica de juego

El tablero es una matriz de caracteres.

Los valores pueden ser:

' ' (espacio): celda vacía sin minas cercanas.

'1'..'8': cantidad de minas adyacentes.

'*': mina.

'F': celda marcada con bandera.
Las minas se colocan aleatoriamente después de la primera jugada.

Niveles de dificultad
Nivel	Tamaño tablero	Minas
Fácil	8x8	10
Medio	16x16	40
Difícil	16x30	99
