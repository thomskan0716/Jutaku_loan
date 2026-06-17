:: run_parallel_test.bat
:: Launches 10 processes at once in separate windows to verify parallel processing.
:: Each window keeps open (cmd /k) so you can read the [P1]~[P10] logs.
@echo off
cd /d "%~dp0"
start "P1"  cmd /k test1.bat
start "P2"  cmd /k test2.bat
start "P3"  cmd /k test3.bat
start "P4"  cmd /k test4.bat
start "P5"  cmd /k test5.bat
start "P6"  cmd /k test6.bat
start "P7"  cmd /k test7.bat
start "P8"  cmd /k test8.bat
start "P9"  cmd /k test9.bat
start "P10" cmd /k test10.bat
