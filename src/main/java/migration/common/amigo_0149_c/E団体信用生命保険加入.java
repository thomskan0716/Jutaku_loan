package migration.common.amigo_0149_c;

import migration.common.ConvertibleEnum;

/** TODO: Populate enum values from original source. */
public enum E団体信用生命保険加入 implements ConvertibleEnum {
    ;
    private final String oldCode;
    private final String oldName;
    private final String newCode;
    private final String newName;
    E団体信用生命保険加入(String oldCode, String oldName, String newCode, String newName) {
        this.oldCode = oldCode; this.oldName = oldName;
        this.newCode = newCode; this.newName = newName;
    }
    @Override public String getOldCode() { return oldCode; }
    @Override public String getOldName() { return oldName; }
    @Override public String getNewCode() { return newCode; }
    @Override public String getNewName() { return newName; }
}
