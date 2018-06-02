#version 330

out vec4 fragColor;
uniform sampler2D texture_sampler;
in  vec2 outTexCoord;
void main()
{
  fragColor = texture(texture_sampler, outTexCoord);
 
}