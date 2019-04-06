#!/usr/bin/env bash

PROJECT_ROOT=`git rev-parse --show-toplevel`
SOURCES_ROOT="${PROJECT_ROOT}/src/main/scala/klondike/"
pushd .
cd ${SOURCES_ROOT}
for package_name in $(ls ${SOURCES_ROOT})
do
  echo "Packages importing ${package_name}:"
  grep -r "klondike.${package_name}" . | grep "import" | cut -d '/' -f2 | uniq | grep -v ${package_name}
  echo ""
done
popd
