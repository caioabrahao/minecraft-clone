package br.com.caioabrahao.voxelengine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class Texture implements AutoCloseable {

    private final int id;

    public Texture(String path) {
        byte[] bytes;
        try (InputStream in = Texture.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new RuntimeException("Texture not found: " + path);
            }
            bytes = in.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + path, e);
        }

        ByteBuffer fileBuffer = MemoryUtil.memAlloc(bytes.length);
        fileBuffer.put(bytes).flip();

        ByteBuffer pixels;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            STBImage.stbi_set_flip_vertically_on_load(true); // so UV (0,0) is bottom-left
            pixels = STBImage.stbi_load_from_memory(fileBuffer, w, h, channels, 4); // force RGBA
            if (pixels == null) {
                throw new RuntimeException("Failed to decode " + path + ": " + STBImage.stbi_failure_reason());
            }
            width = w.get(0);
            height = h.get(0);
        } finally {
            MemoryUtil.memFree(fileBuffer);
        }

        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        // NEAREST = crisp pixels instead of blurry smoothing (the Minecraft look)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        STBImage.stbi_image_free(pixels);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    @Override
    public void close() {
        glDeleteTextures(id);
    }
}