package br.com.caioabrahao.voxelengine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

public class Scene implements AutoCloseable {

    public record SceneObject(Mesh mesh, Matrix4f model) {}
    private final List<SceneObject> sceneObjects = new ArrayList<>();

    public Mesh add(Mesh mesh){
        sceneObjects.add(new SceneObject(mesh, new Matrix4f()));
        return mesh;
    }

    // public void remove(Mesh mesh){
    //     if (sceneObjects.remove(mesh)){
    //         mesh.close();
    //     }
    // }
    public Matrix4f referenceModel(int objectIndex) {
        return sceneObjects.get(objectIndex).model();
    }

    public void render(Shader shader){
        for (SceneObject obj : sceneObjects){
            shader.setUniform("uModel", obj.model());
            obj.mesh.render();
        }
    }

    @Override 
    public void close(){
        for (SceneObject obj : sceneObjects) {
            obj.mesh.close();
        }
        sceneObjects.clear();
    }

}
