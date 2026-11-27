package br.com.caioabrahao.voxelengine;

public class Shapes3D {

    public Mesh cube(float size) {
        float s = size / 2f;
        float[] vertices = {
            // x, y, z,   u, v
            // front (+Z)
            -s, -s,  s,   0, 0,    s, -s,  s,   1, 0,    s,  s,  s,   1, 1,   -s,  s,  s,   0, 1,
            // back (-Z)
            s, -s, -s,   0, 0,   -s, -s, -s,   1, 0,   -s,  s, -s,   1, 1,    s,  s, -s,   0, 1,
            // left (-X)
            -s, -s, -s,   0, 0,   -s, -s,  s,   1, 0,   -s,  s,  s,   1, 1,   -s,  s, -s,   0, 1,
            // right (+X)
            s, -s,  s,   0, 0,    s, -s, -s,   1, 0,    s,  s, -s,   1, 1,    s,  s,  s,   0, 1,
            // top (+Y)
            -s,  s,  s,   0, 0,    s,  s,  s,   1, 0,    s,  s, -s,   1, 1,   -s,  s, -s,   0, 1,
            // bottom (-Y)
            -s, -s, -s,   0, 0,    s, -s, -s,   1, 0,    s, -s,  s,   1, 1,   -s, -s,  s,   0, 1
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
            
        return new Mesh(vertices, indices);
    }
}