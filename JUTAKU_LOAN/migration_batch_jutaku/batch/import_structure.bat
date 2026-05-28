@echo off
setlocal

set ORACLE_HOME=C:\oracle\product\12.2.0\dbhome_1
set ORACLE_SID=E00736SV0001
set PATH=%ORACLE_HOME%\bin;%PATH%

set DUMP_DIR=C:\Users\sun.ykawakamit\Desktop\静岡銀行_S-plus\branches\新帰属入\050000_データ移行#JTAKU_LOAN\dumps
set DATE_STR=%DATE:~0,4%%DATE:~5,2%%DATE:~8,2%
set DUMP_FILE=JUTAKU_STRUCTURE_%DATE_STR%.dmp
set LOG_FILE=JUTAKU_IMPORT_%DATE_STR%.log

echo Importing table structure...
echo ================================================
echo.
echo Target: E00736SV0001/SZH_SMS
echo Tables: 申込, 申込審査状況
echo Import Type: METADATA_ONLY (structure only, no data)
echo.
echo Dump File: %DUMP_DIR%\%DUMP_FILE%
echo Log File: %DUMP_DIR%\%LOG_FILE%
echo.
echo ================================================

REM Check if dump file exists
if not exist "%DUMP_DIR%\%DUMP_FILE%" (
    echo.
    echo ERROR: Dump file not found: %DUMP_DIR%\%DUMP_FILE%
    echo Please run export_structure.bat first!
    echo.
    pause
    exit /b 1
)

REM Execute import
impdp userid=SZH_SMS/password@E00736SV0001 ^
    directory=DATA_PUMP_DIR ^
    dumpfile=%DUMP_FILE% ^
    logfile=%LOG_FILE% ^
    tables=申込,申込審査状況 ^
    table_exists_action=REPLACE

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ================================================
    echo ERROR: Import failed with error code %ERRORLEVEL%
    echo ================================================
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ================================================
echo Import completed successfully!
echo ================================================
echo.
echo Verify tables were created (should be empty):
echo   SELECT * FROM 申込;
echo   SELECT * FROM 申込審査状況;
echo.
pause

endlocal
