package br.com.caioabrahao.voxelengine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    private final Vector3f position = new Vector3f(0f, 0f, 3f);
    private final Vector3f front = new Vector3f();
    private final Vector3f right = new Vector3f();
    private final Vector3f target = new Vector3f();
    private final Vector3f worldUp = new Vector3f(0f, 1f, 0f);
    private final Matrix4f view = new Matrix4f();

    private float yaw = -90f;   // -90 means looking down the -Z axis
    private float pitch = 0f;

    public Camera() {
        updateVectors();
    }

    private void updateVectors() {
        double y = Math.toRadians(yaw);
        double p = Math.toRadians(pitch);
        front.set(
            (float) (Math.cos(y) * Math.cos(p)),
            (float) Math.sin(p),
            (float) (Math.sin(y) * Math.cos(p))
        ).normalize();
        front.cross(worldUp, right).normalize(); // right = front x up
    }

    public void rotate(float deltaYaw, float deltaPitch) {
        yaw += deltaYaw;
        pitch = Math.max(-89f, Math.min(89f, pitch + deltaPitch)); // avoid flipping over
        updateVectors();
    }

    public void moveForward(float amount) { position.fma(amount, front); }
    public void moveRight(float amount)   { position.fma(amount, right); }
    public void moveUp(float amount)      { position.y += amount; }

    public Matrix4f getViewMatrix() {
        position.add(front, target);              // look at the point just ahead of us
        return view.identity().lookAt(position, target, worldUp);
    }
}
