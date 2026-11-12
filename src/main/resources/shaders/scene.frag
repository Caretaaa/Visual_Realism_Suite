#version 330 core
in vec3 FragPos;in vec3 Normal;in vec2 UV;in vec4 FragPosLight;out vec4 FragColor;
uniform vec3 lightPos,viewPos,objectColor;uniform sampler2D imageTexture,shadowMap;uniform bool useBlinn,useTexture,flatMode;uniform float shadowBias;
float shadowAmount(vec3 n,vec3 l){vec3 p=FragPosLight.xyz/FragPosLight.w;p=p*0.5+0.5;if(p.z>1.0)return 0.0;float closest=texture(shadowMap,p.xy).r;float bias=max(shadowBias*(1.0-dot(n,l)),0.0005);return p.z-bias>closest?0.75:0.0;}
void main(){vec3 n=flatMode?normalize(cross(dFdx(FragPos),dFdy(FragPos))):normalize(Normal);vec3 l=normalize(lightPos-FragPos),v=normalize(viewPos-FragPos);float diff=max(dot(n,l),0.0);float spec=useBlinn?pow(max(dot(n,normalize(l+v)),0.0),32.0):pow(max(dot(v,reflect(-l,n)),0.0),16.0);vec3 base=useTexture?texture(imageTexture,UV).rgb:objectColor;float sh=shadowAmount(n,l);vec3 color=base*(0.18+(1.0-sh)*0.72*diff)+vec3(0.8)*(1.0-sh)*0.35*spec;FragColor=vec4(color,1.0);}
