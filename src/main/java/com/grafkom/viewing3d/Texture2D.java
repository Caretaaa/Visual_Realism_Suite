package com.grafkom.viewing3d;
import org.lwjgl.stb.STBImage;import org.lwjgl.system.MemoryStack;import java.nio.*;import static org.lwjgl.opengl.GL11.*;import static org.lwjgl.opengl.GL13.*;import static org.lwjgl.opengl.GL30.*;
public final class Texture2D implements AutoCloseable {
 private final int id;private boolean mipmapped=true;
 public Texture2D(String path){id=glGenTextures();glBindTexture(GL_TEXTURE_2D,id);STBImage.stbi_set_flip_vertically_on_load(true);try(MemoryStack s=MemoryStack.stackPush()){IntBuffer w=s.mallocInt(1),h=s.mallocInt(1),c=s.mallocInt(1);ByteBuffer pixels=STBImage.stbi_load(path,w,h,c,4);if(pixels==null)throw new IllegalStateException("Texture load failed: "+STBImage.stbi_failure_reason());glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA8,w.get(0),h.get(0),0,GL_RGBA,GL_UNSIGNED_BYTE,pixels);STBImage.stbi_image_free(pixels);}glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);setMipmapped(true);}
 public void setMipmapped(boolean on){mipmapped=on;glBindTexture(GL_TEXTURE_2D,id);glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,on?GL_LINEAR_MIPMAP_LINEAR:GL_NEAREST);glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,on?GL_LINEAR:GL_NEAREST);if(on)glGenerateMipmap(GL_TEXTURE_2D);}
 public void bind(int unit){glActiveTexture(GL_TEXTURE0+unit);glBindTexture(GL_TEXTURE_2D,id);}public boolean isMipmapped(){return mipmapped;}public void close(){glDeleteTextures(id);}
}
