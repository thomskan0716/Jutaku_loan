:: run_parallel_test.bat
:: Launches the 3 processes at once in separate windows to verify parallel processing.
:: Each window keeps open (cmd /k) so you can read the [P1]/[P2]/[P3] logs.
@echo off
cd /d "%~dp0"
start "P1" cmd /k test1.bat
start "P2" cmd /k test2.bat
start "P3" cmd /k test3.bat
