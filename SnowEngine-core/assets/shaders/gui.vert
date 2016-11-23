#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texCoords;

uniform mat4 projection, model;

out DATA
{
    vec2 texCoord;
} vs_out;

void main()
{
    gl_Position = projection * model * position;
    vs_out.texCoord = texCoords;
}