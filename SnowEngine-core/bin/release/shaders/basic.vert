#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texCoords;

uniform mat4 mvp, projection, view, model;

out DATA
{
    vec2 texCoord;
    vec2 position;
} vs_out;

void main()
{
    gl_Position = projection * view * model * position;
    vs_out.texCoord = texCoords;
    vs_out.position = (model * position).xy;
}