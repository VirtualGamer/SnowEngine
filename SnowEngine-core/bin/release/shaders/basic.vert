#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec4 color;

uniform mat4 mvp, projection, view, model;

out vec4 colour;

void main()
{
    gl_Position = mvp * position;
    colour = color;
}