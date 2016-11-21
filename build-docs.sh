#!/bin/bash

git checkout master
gradle javadoc
git checkout gh-pages
cp -r build/docs/javadoc docs
mkdocs build
cp -r build/docs/javadoc/* site/javadoc
