package migration.common.amigo_0149_c;

import migration.common.ConvertibleEnum;

/** TODO: Populate enum values from original source. */
public enum E就学同居有無 implements ConvertibleEnum {
    ;
    private final String oldCode;
    private final String oldName;
    private final String newCode;
    private final String newName;
    E就学同居有無(String oldCode, String oldName, String newCode, String newName) {
        this.oldCode = oldCode; this.oldName = oldName;
        this.newCode = newCode; this.newName = newName;
    }
    @Override public String getOldCode() { return oldCode; }
    @Override public String getOldName() { return oldName; }
    @Override public String getNewCode() { return newCode; }
    @Override public String getNewName() { return newName; }
}
