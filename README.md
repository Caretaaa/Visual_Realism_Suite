# Visual_Realism_Suite

3D Visual Realism Suite

Standalone Java 17 / LWJGL 3 / OpenGL 3.3 sample implementing the five indicators in the Week 11 module. It creates a UV sphere and ground plane, switches Phong/Blinn-Phong and flat/smooth normals, exposes depth and culling toggles, samples a PNG texture with selectable filtering, and renders a depth shadow map in two passes.

## Run

Requires Java 17+, Maven, and a desktop OpenGL 3.3 driver. From this directory run `mvn compile exec:java`. The included `assets/checker.png` is the texture input. Controls: B shading model, F smooth/flat normals, Z depth test, C back-face culling, T texture filter, Esc quit. The window title reports rolling FPS; VSync is disabled for measurement. The Maven native classifier is set to Windows; change it to the matching LWJGL classifier when running on another operating system.

The project is a standalone reference because the Week 10 `ThreeDViewing` project was not attached. Capture the requested five screenshots and measure FPS on the target machine before submitting; this package contains no fabricated runtime measurements.
