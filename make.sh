rm -r src/classpath/* bootclasspath/*
cd boot/src
javac -d ../../bootclasspath safe/UnsafeProvider.java
cd ../../src
javac -cp ../bootclasspath -d classpath *.java safe/*.java
cd ..
