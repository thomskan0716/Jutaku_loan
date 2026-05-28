package migration.common.szh_sms;

public enum E申込目的 {
    
    事前審査("10", "10", true),
    事前簡易審査("15", "10", true),   // Merge to 10
    正式審査("20", "20", true),
    正式簡易審査("30", "20", true),   // Merge to 20
    途上与信("90", null, false);      // Not migrated
    
    private final String oldCode;
    private final String newCode;
    private final boolean migrationTarget;
    
    E申込目的(String oldCode, String newCode, boolean migrationTarget) {
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
        
        for (E申込目的 e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.getNewCode();
            }
        }
        
        System.err.println("WARNING: 申込目的 mapping not found: " + oldCode);
        return oldCode;
    }
    
    public static boolean shouldMigrate(String oldCode) {
        if (oldCode == null || oldCode.isEmpty()) {
            return false;
        }
        
        for (E申込目的 e : values()) {
            if (e.getOldCode().equals(oldCode)) {
                return e.isMigrationTarget();
            }
        }
        
        return true;
    }
}
