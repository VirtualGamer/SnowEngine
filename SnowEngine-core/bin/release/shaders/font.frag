#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
    vec2 texCoord;
} fs_in;

uniform sampler2D tex;

void main()
{
    vec4 color = texture(tex, fs_in.texCoord);
    if (color.w < 0.1)
    {
        discard;
    }
}