package ho.artisan.holib.tool;

public class IsomericRegistrar extends Registrar {
    public IsomericRegistrar(String modid) {
        super(modid);
    }

    public void register(Registerable object) {
        object.register(this);
    }
}
