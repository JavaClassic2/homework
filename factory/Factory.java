import java.lang.annotation.Annotation;
import java.util.HashMap;

public class Factory {
    HashMap<String, DataBean> repository;

    public Factory() {
        repository = new HashMap<>();
    }

    public void registerType(DataBean dataBeam) {
        repository.put(dataBeam.getClass().getSimpleName(), dataBeam);
    }

    public DataBean getClass(String name) throws ClassNotFoundException {
        DataBean data = repository.get(name);

        if (data == null) {
            throw new ClassNotFoundException();
        }

        if (getCreationType(data).equals(ClassType.SingleTone)) {
            return data;
        }

        return data.clone();
    }

    private ClassType getCreationType(DataBean data) {
        Annotation[] annotations = data.getClass().getAnnotations();

        for (Annotation a : annotations) {
            if (a instanceof CreationType) {
                return ((CreationType)a).type();
            }
        }

        throw new RuntimeException("CreationType이 정의되지 않음");
    }
}

