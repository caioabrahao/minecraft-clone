package br.com.caioabrahao.voxelengine;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public class Game {

    private static final double TICK_DT = 1.0 / 60.0;

    private final Window window;
    private Shader shader;
    private Mesh triangle;
    private Mesh triangle2;

    private Mesh rectangle (float width, float height){
        Mesh triang1 = new Mesh(new float[]{
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
        });
        Mesh triang2 = new Mesh(new float[] {
            0.5f, 0.5f, 0.0f,   
            -0.5f, 0.5f, 0.0f,   
            -0.5f, -0.5f, 0.0f,
        });
        return new Mesh[]{triag1, triag2};
    }

    public Game(Window window) {
        this.window = window;
    }

    public void run() {
        init();

        double previous = glfwGetTime();
        double accumulator = 0.0;

        while (!window.shouldClose()) {
            double now = glfwGetTime();
            // Clamp so a long stall (e.g. dragging the window) doesn't cause a burst of updates
            double frameTime = Math.min(now - previous, 0.25);
            previous = now;
            accumulator += frameTime;

            window.pollEvents();

            while (accumulator >= TICK_DT) {
                update(TICK_DT);
                accumulator -= TICK_DT;
            }

            render();
            window.swapBuffers();
        }

        cleanup();
    }

    private Mesh triangle(float x_pos, float y_pos) {
        if (x_pos == 0){
            x_pos = 1;
        }
        if (y_pos == 0){
            y_pos = 1;
        }
        triangle = new Mesh(new float[] {
            -0.5f*x_pos, -0.5f*y_pos, 0.0f,   // bottom left
            0.5f*x_pos, -0.5f*x_pos, 0.0f,   // bottom right
            0.5f*x_pos, 0.5f*y_pos, 0.0f,
        });

        return triangle;
    }

    private void init() {
        glClearColor(0.4f, 0.6f, 0.9f, 1.0f);

        shader = new Shader("/shaders/basic.vert", "/shaders/basic.frag");

        // triangle = new Mesh(new float[] {
        //     -0.5f, -0.5f, 0.0f,   // bottom left
        //     0.5f, -0.5f, 0.0f,   // bottom right
        //     0.5f, 0.5f, 0.0f,
        // });
        // triangle2 = new Mesh(new float[] {
        //     0.5f, 0.5f, 0.0f,   // bottom left
        //     -0.5f, 0.5f, 0.0f,   // bottom right
        //     -0.5f, -0.5f, 0.0f,
        // });
        rectangle(0, 0);
        
    }

    private void update(double dt) {
        // Nothing to update yet. Player movement and world logic will go here.
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        shader.bind();
        triangle.render();
        triangle2.render();
        rectangle.render();
    }

    private void cleanup() {
        triangle.close();
        shader.close();
    }
}
