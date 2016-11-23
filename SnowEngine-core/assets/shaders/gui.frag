#version 330 core

layout (location = 0) out vec4 color;

uniform sampler2D tex;

in DATA
{
    vec2 texCoord;
} fs_in;

void main()
{
    color = texture(tex, fs_in.texCoord);
    if (color.w < 0.1)
    {
        discard;
    }
}