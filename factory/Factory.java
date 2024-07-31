import java.util.HashMap;

public class Factory {
    HashMap<String, DataBeam> repository;

    public Factory() {
        repository = new HashMap<>();
    }

    public void registerType(DataBeam dataBeam) {
        repository.put(dataBeam.getClass().getSimpleName(), dataBeam);
    }

    public DataBeam getClass(String name) throws ClassNotFoundException {
        DataBeam data = repository.get(name);

        if (data == null) {
            throw new ClassNotFoundException();
        }

        if (data.getType().equals("SingleTone")) {
            return data;
        }
        else {
            return data.clone();
        }
    }
}

