#version 330

layout (location = 0) out vec4 color;

in vec2 pass_textureCoords;

uniform vec3 colour;
uniform sampler2D fontAtlas;

void main()
{
    color = vec4(colour, texture(fontAtlas, pass_textureCoords).a);
}