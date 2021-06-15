package examples;

public class ConstructorInjection {

    private Dependency dependency;

    public ConstructorInjection(Dependency dependency) {
        this.dependency = dependency;
    }
}
