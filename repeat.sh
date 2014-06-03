#!/bin/bash

./make.sh
times=$1
shift
for i in $(seq 1 $times)
do
  echo 'Running test #' $i ": $*"
  $*
done
