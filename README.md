# imma make a minecraft clone myself with no ai

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
