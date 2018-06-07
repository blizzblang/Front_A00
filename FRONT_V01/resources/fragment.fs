#version 330

out vec4 fragColor;
uniform sampler2D texture_sampler;
in  vec2 outTexCoord;
void main()
{
	vec4 col = texture(texture_sampler, outTexCoord);
	if (col.a==0.)  discard;
	fragColor = col;
 
}