#!/usr/bin/env bash

#
# Use this shell script to compile (if necessary) your code and then execute it. Belw is an example of what might be found in this file if your program was written in Python 3.7
# python3.7 ./src/border_analytics.py ./input/Border_Crossing_Entry_Data.csv ./output/report.csv



##!/bin/sh
#ORDER="$1"
#COMPANY_ONE="$2"
#COMPANY_TWO="$3"
#if [ "$ORDER" = "1" ]; then
# java fortest  $COMPANY_ONE $COMPANY_TWO
#elif [ "$ORDER" = "2" ]; then
#  java fortest  $COMPANY_TWO $COMPANY_ONE
#else
#  echo "Unknown company: $ORDER";
#  exit 1;
#fi

java src/Analyzer input/Border_Crossing_Entry_Data.csv output/report.csv
