#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 uvs;

uniform mat4 mvp, projection, view, model;

out vec2 uv;

void main()
{
    gl_Position = projection * view * model * position;
    uv = uvs;
}