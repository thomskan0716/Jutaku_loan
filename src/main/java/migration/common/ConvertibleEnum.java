package migration.common;

public interface ConvertibleEnum {
    String getOldCode();
    String getOldName();
    public String getNewCode();
    public String getNewName();
}
