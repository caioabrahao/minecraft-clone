package br.com.caioabrahao.voxelengine;

public class Shapes2D {
    public Mesh rectangle(float width, float height) {
        float hw = width / 2f;
        float hh = height / 2f;
        
        return new Mesh(
            new float[]{
                -hw, -hh, 0f,
                 hw, -hh, 0f,
                 hw,  hh, 0f,
                -hw,  hh, 0f
            },
            new int[] {0, 1, 2,  2, 3, 0}
        );
    }

    public Mesh triangle(float width, float height) {
        float hw = width / 2f;
        float hh = height / 2f;
        
        return new Mesh(
            new float[]{
                -hw, -hh, 0f,
                 hw, -hh, 0f,
                 hw,  hh, 0f
            },
            new int[] {0, 1, 2}
        );
    }
}
