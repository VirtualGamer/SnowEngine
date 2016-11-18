#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
    vec2 texCoord;
    vec2 position;
} fs_in;

const int MAX_LIGHTS = 10;

struct Light
{
    vec2 position;
    vec3 color;
    float radius;
};

uniform sampler2D tex;
uniform vec3 ambientLight = vec3(1.0, 1.0, 1.0);
uniform Light lights[MAX_LIGHTS];

vec3 calculateLight(Light light)
{
    float distance = length(light.position - fs_in.position);
    float intensity = clamp(1.0 - distance / light.radius, 0.0, 1.0);
    vec3 color = light.color * intensity;
    return color;
}

void main()
{
    vec4 textureColor = texture(tex, fs_in.texCoord);
    if (textureColor.w < 0.1)
    {
        discard;
    }
    vec4 totalLight = vec4(ambientLight, 1.0);
    for (int i = 0; i < MAX_LIGHTS; i++)
    {
        totalLight += vec4(calculateLight(lights[i]), 1.0);
    }
    color = clamp(textureColor * totalLight, 0.0, 1.0);
}