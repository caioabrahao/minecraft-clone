package br.com.caioabrahao.voxelengine;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Game {

    private static final double TICK_DT = 1.0 / 60.0;

    private final Window window;
    private Shader shader;
    private Scene scene = new Scene();

    private final Camera camera = new Camera();
    private final Matrix4f projection = new Matrix4f();
    private final Matrix4f model = new Matrix4f();


    public Game(Window window) {
        this.window = window;
    }

    public void run() {
        init();

        double previous = glfwGetTime();
        double accumulator = 0.0;

        while (!window.shouldClose()) {
            double now = glfwGetTime();
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



    private void init() {
        glClearColor(0.4f, 0.6f, 0.9f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        
        shader = new Shader("/shaders/basic.vert", "/shaders/basic.frag");

        scene.add(new Shapes3D().cube(1f));
        scene.add(new Shapes2D().plane(2f, 2f));
        // Shapes2D shapes2d = new Shapes2D();

        // scene.add(
        //     shapes2d.rectangle(0.5f, 0.5f)
        // );
        
    }

    private float cubeAngle = 45.0f;

    private void update(double dt) {
        float speed = 4f * (float) dt;
        if (window.isKeyDown(GLFW_KEY_W)) camera.moveForward(speed);
        if (window.isKeyDown(GLFW_KEY_S)) camera.moveForward(-speed);
        if (window.isKeyDown(GLFW_KEY_D)) camera.moveRight(speed);
        if (window.isKeyDown(GLFW_KEY_A)) camera.moveRight(-speed);
        if (window.isKeyDown(GLFW_KEY_SPACE)) camera.moveUp(speed);
        if (window.isKeyDown(GLFW_KEY_LEFT_SHIFT)) camera.moveUp(-speed);

        float sensitivity = 0.1f;
        camera.rotate(
            (float) window.consumeMouseDx() * sensitivity,
            (float) -window.consumeMouseDy() * sensitivity   // screen Y points down
        );

        if(window.isKeyDown(GLFW_KEY_R)) {
            cubeAngle += 0.5f;
            model.identity().rotate((float) Math.toRadians(cubeAngle), new Vector3f(0, 1, 0));
        }
    }

    private void render() {
        if (window.getHeight() == 0) return; // minimized: avoid dividing by zero

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        shader.bind();

        projection.identity().perspective(
            (float) Math.toRadians(70.0),
            (float) window.getWidth() / window.getHeight(),
            0.1f, 1000f
        );

        shader.setUniform("uProjection", projection);
        shader.setUniform("uView", camera.getViewMatrix());
        shader.setUniform("uModel", model);   // identity for now

        scene.render();
    }

    private void cleanup() {
        // triangle.close();
        scene.close();
        shader.close();
    }
}
