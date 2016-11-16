#version 330 core

layout (location = 0) out vec4 color;

uniform sampler2D tex;

in vec2 uv;

void main()
{
    color = texture(tex, uv);
    if (color.w < 1.0)
    {
        discard;
    }
}