package br.com.caioabrahao.voxelengine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public class Main {
    public static void main(String[] args) {
        try (Window window = new Window(1280, 720, "My Voxel Game")) {
            window.create();

            glClearColor(0.4f, 0.6f, 0.9f, 1.0f); // sky blue

            while (!window.shouldClose()) {
                glClear(GL_COLOR_BUFFER_BIT);
                window.swapBuffers();
                window.pollEvents();
            }
        }
    }
}
