package in.zuul.apigateway;

public class TenantInfo {
    public static final ThreadLocal ecoIdLocal = new ThreadLocal();

    public static void unSetEcoSystemId() {
        ecoIdLocal.remove();
    }

    public static String getEcoSystemId() {
        return (String) ecoIdLocal.get();
    }

    public static void setEcoSystemId(String ecoId) {
        ecoIdLocal.set(ecoId);
    }

    public static void clear() {
        unSetEcoSystemId();
    }

}
