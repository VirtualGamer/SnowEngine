#version 330 core

layout (location = 0) out vec4 color;

uniform sampler2D tex;
uniform vec4 ambientLight;

in DATA
{
    vec2 texCoord;
} fs_in;

struct Light
{
    vec2 position;
    vec3 color;
    float intensity;
};

void main()
{
    vec4 colour = texture(tex, fs_in.texCoord);
    if (colour.w < 0.1)
    {
        discard;
    }
    color = clamp(colour * ambientLight, 0.0, 1.0);
}