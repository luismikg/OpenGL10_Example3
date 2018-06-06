package mx.ipn.cic.geo.opengl_10.example3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

// This class is an object representation of a Triangle containing
// the vertex information and drawing functionality, which is called
// by the renderer.
public class MxGLTriangle {

    // The buffer holding the vertices
    private FloatBuffer vertexBuffer;

    // The initial vertex definition
    private FloatBuffer colorBuffer;

    // The Triangle constructor. Initiate the buffers.
    private float vertices[] = {
            0.0f, 1.0f, 0.0f,   //Top
            -1.0f, -1.0f, 0.0f, //Bottom Left
            1.0f, -1.0f, 0.0f   //Bottom Right
    };

    // The initial color definition */
    private float colors[] = {
            1.0f, 0.0f, 0.0f, 1.0f, // Set the color to Red, last value 100% luminance
            0.0f, 1.0f, 0.0f, 1.0f, // Set the color to Green, last value 100% luminance
            0.0f, 0.0f, 1.0f, 1.0f  // Set the color to Blue, last value 100% luminance
    };


    // The Triangle constructor. Initiate the buffers.
    public MxGLTriangle() {

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(this.vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.vertexBuffer = byteBuf.asFloatBuffer();
        this.vertexBuffer.put(this.vertices);
        this.vertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(this.colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.colorBuffer = byteBuf.asFloatBuffer();
        this.colorBuffer.put(this.colors);
        this.colorBuffer.position(0);
    }

    // The object own drawing function.
    // Called from the renderer to redraw this instance with possible changes
    // in values.
    // @param gl - The GL context
    public void draw(GL10 gl) {
        // Set the face rotation
        gl.glFrontFace(GL10.GL_CW);

        // Point to our buffers
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, this.colorBuffer);

        // Enable the vertex and color state
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // Draw the vertices as triangles
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, this.vertices.length / 3);

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
