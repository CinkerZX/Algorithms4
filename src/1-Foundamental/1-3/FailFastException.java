import java.util.ConcurrentModificationException;

public class FailFastException extends ConcurrentModificationException {
    public FailFastException(){
        super();
    }
    public FailFastException(boolean i, boolean j){
        if (!i || !j){
            new ConcurrentModificationException("Fail-fast exception");
        }
    }
}
