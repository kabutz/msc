#!/bin/bash

#java -Xbootclasspath/p:classpath OverheadExperiment $*
#java -cp bootclasspath:src/classpath OverheadExperiment $*
java -Xbootclasspath/p:bootclasspath -cp src/classpath OverheadExperiment $*
