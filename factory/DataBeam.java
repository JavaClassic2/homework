import java.lang.annotation.Annotation;

@CreationType
public class DataBeam {
    private CreationType getCreationType() {
        Annotation[] annotations = this.getClass().getAnnotations();
        for (Annotation a : annotations) {
            if (a instanceof CreationType) {
                return (CreationType) a;
            }
        }
        throw new UnsupportedOperationException();
    }

    public String getType() {
        return getCreationType().type();
    }

    public DataBeam clone() {
        return this;
    }
}