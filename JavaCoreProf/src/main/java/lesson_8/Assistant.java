package lesson_8;

public class Assistant {
    public Camera getCamera() {
        ICameraRoll ccRoll = new BlackAndWhiteCameraRoll();
        Camera camera = new Camera();
        camera.setCameraRoll(ccRoll);
        camera.doPhotograph();
        return camera;
    }
}
