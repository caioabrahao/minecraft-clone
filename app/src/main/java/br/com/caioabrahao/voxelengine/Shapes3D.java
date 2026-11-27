package br.com.caioabrahao.voxelengine;

public class Shapes3D {

    public Mesh cube(float size) {
        float s = size / 2f;
        return new Mesh(
            new float[] {
                -s, -s, -s,   // 0
                 s, -s, -s,   // 1
                 s,  s, -s,   // 2
                -s,  s, -s,   // 3
                -s, -s,  s,   // 4
                 s, -s,  s,   // 5
                 s,  s,  s,   // 6
                -s,  s,  s    // 7
            },
            new int[] {
                4, 5, 6,  6, 7, 4,   // front  (+Z)
                1, 0, 3,  3, 2, 1,   // back   (-Z)
                0, 4, 7,  7, 3, 0,   // left   (-X)
                5, 1, 2,  2, 6, 5,   // right  (+X)
                7, 6, 2,  2, 3, 7,   // top    (+Y)
                0, 1, 5,  5, 4, 0    // bottom (-Y)
            }
        );
    }
}