package migration.common.szh_sms;

public enum E連絡コード {
    
    // TODO: Add actual code mappings
    ;
    
    private final String oldCode;
    private final String newCode;
    private final boolean migrationTarget;
    
    E連絡コード(String oldCode, String newCode, boolean migrationTarget) {
        this.oldCode = oldCode;
        this.newCode = newCode;
        this.migrationTarget = migrationTarget;
    }
    
    public String getOldCode() {
        return oldCode;
    }
    
    public String getNewCode() {
        return newCode;
    }
    
    public boolean isMigrationTarget() {
        return migrationTarget;
    }
    
    public static String convert(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return null;
        }
        
        if (oldCode.startsWith("JR")) {
            System.out.println("INFO: JR code detected: " + oldCode);
        }
        
        for (E連絡コード e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.getNewCode();
            }
        }
        
        System.err.println("WARNING: 連絡コード mapping not found: " + oldCode);
        return oldCode;
    }
    
    public static boolean shouldMigrate(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return false;
        }
        
        for (E連絡コード e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.isMigrationTarget();
            }
        }
        
        return true;
    }
}
