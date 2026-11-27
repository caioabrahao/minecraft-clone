package br.com.caioabrahao.voxelengine;

public class Shapes2D {
    public Mesh rectangle(float width, float height) {
        float hw = width / 2f;
        float hh = height / 2f;
        
        return new Mesh(
            new float[]{
                -hw, 0f, -hh,
                 hw, 0f, -hh,
                 hw, 0f,  hh,
                -hw, 0f,  hh
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


    public Mesh plane(float width, float length) {
        float hw = width / 2f;
        float hh = length / 2f;
        
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
}
