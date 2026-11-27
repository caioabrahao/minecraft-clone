# imma make a minecraft clone myself with no* ai

*im not vibe-coding, but im using AI to learn how opengl and graphics programming works. learn more about my personal AI usage policy at: _didnt do it yet_

## Development

### to run the thing

you need a **java SDK** installed, and **gradle** as well. after doing that (use chocolatey on windows, its super good), run it with:

```sh
gradle run
```

if it fails, fix it, idk.

## Day 1

Claude threw a bunch of code into me and now im trying to understand it. apparently GPUs dont render squares, only triangles (triangles are always on a single plain while squares can occupy different planes, making it more expensive to render). OpenGL java is very very long but it seems most of it is boilerplate. Im still trying to understand it tho.

so far:

- shader is what gives the color
- mesh is the group of vertices that connect and form a shape
- VBO is vertex buffer object that stores everything about every vertex
- VAO vertex array object which stores what is being used and maps each vertex association
- EBO element buffer object which stores the index of each vertex so you can render multiple triangles without rendering multiple vertices

## Day 2

i went from having a 2d triangle into a 3d rgb cube and a moving camera! crazyy stuff fr.

so.. im gonna watch a 3blue1brown video to understand more about the trigonometry of it all, cuz most of it is still a black box to me. but basically i now have a camera, a 3d shape (same as the 2d, but now using the z coordinate), the shaders updated to use uniforms, window class has now an input watching service. not gonna pretend i know it all, gonna learn and write later.

