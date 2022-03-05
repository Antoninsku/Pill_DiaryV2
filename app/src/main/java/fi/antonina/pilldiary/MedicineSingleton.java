package fi.antonina.pilldiary;

import java.util.ArrayList;

public class MedicineSingleton {
    private boolean active;
    private static final MedicineSingleton ourInstance = new MedicineSingleton();
    public ArrayList<MedicineType> medArrayList = new ArrayList<>();

    public static MedicineSingleton getInstance(){
        return ourInstance;
    }

    public ArrayList<MedicineType> getMedicine() {
        return medArrayList;
    }

    public ArrayList<String> getMedNames() {
        ArrayList<String> names = new ArrayList<>();
        for (MedicineType med : medArrayList) {
            names.add(med.getMedName());
        }
        return names;
    }

    public MedicineSingleton(){
        medArrayList = new ArrayList<>();
       // medArrayList.add(new MedicineType("Burana", "Good", "1", "Morning"));
    }

}
