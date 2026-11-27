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

        float[] vertices = {
            -hw,  0f, -hh,  0, 0,
             hw,  0f, -hh,  1, 0,
             hw,  0f,  hh,  1, 1,
            -hw,  0f,  hh,  1, 0
        };
        
        int[] indices = new int[36]; 
            for (int f = 0; f < 6; f++) {
                int v = f * 4;
                int i = f * 6;
                indices[i]     = v;
                indices[i + 1] = v + 1;
                indices[i + 2] = v + 2;
                indices[i + 3] = v + 2;
                indices[i + 4] = v + 3;
                indices[i + 5] = v;
            }

        // = {0, 1, 2,  2, 3, 0}

        return new Mesh(vertices, indices);
    }
}
