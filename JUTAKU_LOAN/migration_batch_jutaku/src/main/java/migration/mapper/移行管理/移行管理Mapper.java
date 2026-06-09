package migration.mapper.移行管理;

import migration.domain.移行管理.移行管理;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;

import java.sql.Timestamp;
import java.util.List;

/**
 * 移行管理 Mapper Implements parallel processing with FOR UPDATE SKIP LOCKED
 */
@Mapper
public interface 移行管理Mapper {
    
    /**
     * Select unprocessed records with FOR UPDATE SKIP LOCKED Lock is acquired when records are fetched from cursor
     */
    Cursor<移行管理> selectUnprocessedForUpdate();
    
    /**
     * Batch update status from TODO to RUNNING Called immediately after fetch to release locks
     */
    int updateStatusToRunning(@Param("records") List<移行管理> records, 
                               @Param("startTime") Timestamp startTime,
                               @Param("processId") String processId);
    
    /**
     * Update status to DONE after successful migration
     */
    int updateStatusToDone(@Param("申込番号") String 申込番号);

    /**
     * Update status to ERROR after migration failure
     */
    int updateStatusToError(@Param("申込番号") String 申込番号,
                            @Param("説明") String 説明);
    
    /**
     * Count records by status
     */
    long countByStatus(@Param("status") String status);
    
    /**
     * Get all records by status
     */
    List<移行管理> selectByStatus(@Param("status") String status);
}
