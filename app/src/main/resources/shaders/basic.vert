#version 330 core

layout (location = 0) in vec3 aPos;

uniform mat4 uProjection;
uniform mat4 uView;
uniform mat4 uModel;

out vec3 vColor;

void main() {
    vColor = aPos + 0.5;   // the cube spans -0.5..0.5, so this gives colors in 0..1
    gl_Position = uProjection * uView * uModel * vec4(aPos, 1.0);
}