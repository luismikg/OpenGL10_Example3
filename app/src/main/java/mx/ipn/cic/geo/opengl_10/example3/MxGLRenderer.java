package mx.ipn.cic.geo.opengl_10.example3;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class MxGLRenderer implements Renderer {

    // Triangle instance
    private MxGLTriangle triangle;
    // Square instance
    private MxGLSquare square;

    // Angle For The Pyramid
    private float rtri;     
    // Angle For The Cube
    private float rquad; 
    
    // Instance the Triangle and Square objects
    public MxGLRenderer() {
        this.triangle = new MxGLTriangle();
        this.square = new MxGLSquare();
    }

    // The Surface is created/init()
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glShadeModel(GL10.GL_SMOOTH);            // Enable smooth shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black background
        gl.glClearDepthf(1.0f);                     // Depth buffer setup
        gl.glEnable(GL10.GL_DEPTH_TEST);            // Enables depth testing
        gl.glDepthFunc(GL10.GL_LEQUAL);             // The type of depth testing to do
        
        // Really nice perspective calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
    }

    // Here we do our drawing
    public void onDrawFrame(GL10 gl) {
        // Clear screen and depth buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);    
        gl.glLoadIdentity();                            // Reset the Current modelview matrix
        
        // Drawing
        // gl.glTranslatef(0.0f, -1.2f, -6.0f);         // Move down 1.0 unit and into the screen 6.0
        gl.glTranslatef(0.0f, -1.2f, -6.0f);
        gl.glRotatef(this.rquad, 1.0f, 0.0f, 0.0f);   // Rotate the square on the x axis ( NEW )
        this.square.draw(gl);                           // Draw the square
        
        // Reset the current modelview matrix
        gl.glLoadIdentity();                    
        
        gl.glTranslatef(0.0f, 1.3f, -6.0f);         // Move up 1.3 units and -6.0 into the screen
        gl.glRotatef(this.rtri, 0.0f, 1.0f, 0.0f); // Rotate the triangle on the y axis ( NEW )
        this.triangle.draw(gl);                       // Draw the triangle     
        
        // Rotation
        this.rtri += 0.2f;                            // Increase the rotation variable for the triangle ( NEW )
        this.rquad -= 0.15f;                          // Decrease the rotation variable for the quad ( NEW )
    }

    // If the surface changes, reset the view
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if(height == 0) {                             // Prevent a divide by zero by
            height = 1;                               // Making Height Equal One
        }

        gl.glViewport(0, 0, width, height);      // Reset the current viewport
        gl.glMatrixMode(GL10.GL_PROJECTION);          // Select the projection matrix
        gl.glLoadIdentity();                          // Reset the projection matrix

        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);           // Select the modelview matrix
        gl.glLoadIdentity();                          // Reset the modelview matrix
    }
}
