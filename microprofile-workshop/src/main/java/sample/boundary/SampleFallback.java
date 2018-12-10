package sample.boundary;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class SampleFallback implements FallbackHandler<String> {

    @Override
    public String handle(ExecutionContext context) {
        return "my own";
    }
}
