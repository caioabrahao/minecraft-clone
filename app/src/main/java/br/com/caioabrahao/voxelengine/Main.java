package br.com.caioabrahao.voxelengine;

public class Main {
    public static void main(String[] args) {
        try (Window window = new Window(1280, 720, "Cavern Game")) {
            window.create();
            new Game(window).run();
        }
    }
}
