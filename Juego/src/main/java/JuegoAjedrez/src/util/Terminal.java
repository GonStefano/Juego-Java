package JuegoAjedrez.src.util;

public class Terminal {
    // sacado de internet para limpiar la consola
    public static void clearConsole(boolean compatibleMode) {
        if (compatibleMode) {
            for (int i = 0; i < 50; i++) System.out.println();
        } else {
            try {
                String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            } catch (Exception e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
    }
}
