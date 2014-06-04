java7
rm -r src/classpath/* bootclasspath/* bootclasspath2/*
cd boot/src
javac -d ../../bootclasspath safe/UnsafeProvider.java
cd ../../boot2/src
javac -d ../../bootclasspath2 safe/*.java
cd ../../src
javac -cp ../bootclasspath -d classpath *.java safe/*.java
cd ..
