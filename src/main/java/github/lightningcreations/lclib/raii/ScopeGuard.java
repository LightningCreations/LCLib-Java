package github.lightningcreations.lclib.raii;

public class ScopeGuard implements AutoCloseable {

    private final Runnable atClose;

    public ScopeGuard(Runnable atClose){
        this.atClose = atClose;
    }

    @Override
    public void close(){
        atClose.run();
    }
}
