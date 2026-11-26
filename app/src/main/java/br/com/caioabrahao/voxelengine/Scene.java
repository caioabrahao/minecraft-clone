package br.com.caioabrahao.voxelengine;

import java.util.ArrayList;
import java.util.List;

public class Scene implements AutoCloseable {

    private final List<Mesh> meshes = new ArrayList<>();

    public Mesh add(Mesh mesh){
        meshes.add(mesh);
        return mesh;
    }

    public void remove(Mesh mesh){
        if (meshes.remove(mesh)){
            mesh.close();
        }
    }

    public void render(){
        for (Mesh mesh : meshes){
            mesh.render();
        }
    }

    @Override 
    public void close(){
        for (Mesh mesh : meshes) {
            mesh.close();
        }
        meshes.clear();
    }

}
