#!/bin/bash

if [ $# != 2 ]; then
    echo "USAGE: testscript.sh executable testFilesFolder"
    echo "For example:"
    echo "./testscript.sh \"java -cp ./bin edu.c3341.TokenizerTest\" data" 
    echo "Note that the use of \"\" is required when the executable is more than one word long."
    exit
fi

#Location of the data folder
FILE_DIR=$2

echo "Running command:" $1
echo "With files in folder:" $2

for FILE_NAME in $(ls $FILE_DIR/ | grep -v 'output'); do
    echo 'file:' $FILE_NAME
#    ACTUAL=$($1 $FILE_DIR/$FILE_NAME)
#    echo $ACTUAL
#    EXPECTED=$(tr -d '\15\32' < $FILE_DIR/${FILE_NAME}expected-output)
#    echo $EXPECTED
    #echo $FILE_DIR/${FILE_NAME}expected-output
    diff <($1 $FILE_DIR/$FILE_NAME)  <(tr -d '\15\32' < $FILE_DIR/${FILE_NAME}expected-output)
done

