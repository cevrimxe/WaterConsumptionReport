import java.util.HashMap;
import java.util.Map;
public class AboneFactory {
    private static final Map<String, Class<? extends IAbone>> aboneTypes = new HashMap<>();

    static {
        aboneTypes.put("Aile", Aile.class);
        aboneTypes.put("Emekli", Emekli.class);
        aboneTypes.put("Öğrenci", Ogrenci.class);
        aboneTypes.put("Ticarethane", Ticarethane.class);
    }

    public static IAbone createAbone(String type, String id, String firstName, String lastName, int usage, int unpaid2) {
        Class<? extends IAbone> aboneClass = aboneTypes.get(type);
        if (aboneClass != null) {
            try {
                return aboneClass.getConstructor(String.class, String.class, String.class, String.class, int.class, int.class)
                        .newInstance(id, firstName, lastName, type, usage, unpaid2);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Abone oluşturulurken hata oluştu: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("Geçersiz abone tipi: " + type);
    }
}
