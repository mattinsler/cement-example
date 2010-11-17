#!/bin/sh

dir=`dirname $0`

java -cp /Users/mattinsler/IdeaProjects/guiceymongo/guiceygenerator.jar com.lowereast.guiceymongo.data.generator.GuiceyDataGenerator -s $dir/src/main/java -p com.mattinsler.cement.example.model $dir/model/model.data
