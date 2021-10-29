#!/bin/sh

if [ ! "$#" = "2" ]; then 
  echo ""
  echo "USAGE: ./lwst.sh <type> <rt.jar(or core.jar)>"
  echo "    ex) ./lwst.sh sun15 /usr/local/jdk1.5.0.1/jre/lib/rt.jar"
  echo ""
  echo "<type>: sun15,ibm13,ibm14,zos13,as400"
  echo ""
  echo ""
  exit
fi

echo "$1 $2"

TARGET_RT_JAR_FILE=$2
TYPE=$1

OPT="-Djvm=$TYPE"

# =================================================================
# LWST JVM BUILD OPTIONS (default values are different, please check carefully )
OPT="$OPT -Dbuild_jdbc_datasource=true"

OPT="$OPT -Dbuild_thread=true"

OPT="$OPT -Dbuild_collection=false"
#OPT="$OPT -Dtype_collection=TRU64"
#OPT="$OPT -Dbuild_collection_map=false"
#OPT="$OPT -Dbuild_collection_list=false"

OPT="$OPT -Dbuild_file=true"
OPT="$OPT -Dbuild_socket=true"
OPT="$OPT -Dbuild_xml=false"


echo $OPT
java $OPT -jar jennifer.lwst-5.3.2.jar $TARGET_RT_JAR_FILE
mv lwst.out.jar lwst.jdk.jar


#jar ufm jennifer.jar MANIFEST.MF