@ECHO OFF
if $%1%$==$$ goto USAGE
if $%2%$==$$ goto USAGE

set TARGET_RT_JAR_FILE=%2

goto RUN

:NOTFOUND
echo ""
echo JDK rt.jar(or core.jar) File[%2] is not exist

:USAGE
echo ""
echo "USAGE: lwst.bat <type> <rt.jar(or core.jar)>"
echo     ex) lwst.bat sun15 rt.jar
echo ""
echo "<type>: sun15,ibm13,ibm14,zos13,as400"
echo ""
echo Please, see lwst.bat
echo ""
goto END

:RUN

set TYPE=%1
set OPT=-Djvm=%TYPE%

REM =================================================================
REM LWST JVM BUILD OPTIONS (default values are different, please check carefully )
set OPT=%OPT% -Dbuild_jdbc_datasource=true

set OPT=%OPT% -Dbuild_thread=true

set OPT=%OPT% -Dbuild_collection=false
REM set OPT=%OPT% -Dtype_collection=TRU64
REM set OPT=%OPT% -Dbuild_collection_map=false
REM set OPT=%OPT% -Dbuild_collection_list=false

set OPT=%OPT% -Dbuild_file=true
set OPT=%OPT% -Dbuild_socket=true
set OPT=%OPT% -Dbuild_xml=false


@ECHO ON
java %OPT% -jar jennifer.lwst-5.3.2.jar %TARGET_RT_JAR_FILE%
move lwst.out.jar lwst.jdk.jar

:END

REM #jar ufm jennifer.jar MANIFEST.MF
