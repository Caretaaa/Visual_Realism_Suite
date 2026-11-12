package com.grafkom.viewing3d;
import static org.lwjgl.opengl.GL20.*;
import org.joml.*;
public final class ShaderProgram {
  private final int id;
  public ShaderProgram(String vertex,String fragment) { int v=compile(GL_VERTEX_SHADER,vertex), f=compile(GL_FRAGMENT_SHADER,fragment); id=glCreateProgram();glAttachShader(id,v);glAttachShader(id,f);glLinkProgram(id);if(glGetProgrami(id,GL_LINK_STATUS)==0)throw new IllegalStateException(glGetProgramInfoLog(id));glDeleteShader(v);glDeleteShader(f); }
  private static int compile(int type,String src){int s=glCreateShader(type);glShaderSource(s,src);glCompileShader(s);if(glGetShaderi(s,GL_COMPILE_STATUS)==0)throw new IllegalStateException(glGetShaderInfoLog(s));return s;}
  public void use(){glUseProgram(id);} public void set(String n,int x){glUniform1i(glGetUniformLocation(id,n),x);} public void set(String n,float x){glUniform1f(glGetUniformLocation(id,n),x);}
  public void set(String n,Vector3f v){glUniform3f(glGetUniformLocation(id,n),v.x,v.y,v.z);} public void set(String n,Matrix4f m){try(var s=org.lwjgl.system.MemoryStack.stackPush()){glUniformMatrix4fv(glGetUniformLocation(id,n),false,m.get(s.mallocFloat(16)));}}
}
