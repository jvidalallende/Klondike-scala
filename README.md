[![Build Status](https://travis-ci.org/jvidalallende/Klondike-scala.svg?branch=master)](https://travis-ci.org/jvidalallende/Klondike-scala)
[![Coverage Status](https://coveralls.io/repos/github/jvidalallende/Klondike-scala/badge.svg?branch=master)](https://coveralls.io/github/jvidalallende/Klondike-scala?branch=master)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# Scala-TicTacToe

This is the final project for the 2018/19 edition of the
[MSc. in Software Craftsmanship](https://www.etsisi.upm.es/master-upm-software-craftsmanship)
from [Universidad Politécnica de Madrid](http://www.upm.es/).

## Author

[Juan Vidal Allende](https://github.com/jvidalallende)

## Description

This project consists in the design and implementation of a middle-sized application: the
[Klondike](https://en.wikipedia.org/wiki/Klondike_(solitaire)) game. The application will
be CLI-based, and will be implemented using the functional paradigm and with the
[Scala](https://www.scala-lang.org/) programming language.

## Goals

### Main Goals

* Explore the functional paradigm by implementing a known application
* Evaluate the differences between functional and imperative programming when using
   design patterns.

### Stretch goals

* If there is enough time, a continuous integration (CI) system will be used to develop the
  project, using free online services, such as [GitHub](https://github.com/) and
  [TravisCI](https://travis-ci.org/).

## Instructions

To run the tests and get the coverage information, just use this command:

    $ sbt clean coverage test coverageReport

Note that this instruments the code, so if you want to run tests from your IDE,
and the [sbt-coverage](https://github.com/scoverage/sbt-scoverage) plugin is not
properly configured, it may throw *java.lang.NoClassDefFoundError: scoverage/Invoker$*.
The fastest solution to the problem consists on rebuilding the project without
coverage:

    $ sbt clean test

## Changelog

* **[v0.3](https://github.com/jvidalallende/Klondike-scala/releases/tag/v0.3)**: Cleanup
  * Reorganized packages
  * Clean unused import
  * Add CardView tests to ensure that all draws use the same horizontal space

* **[v0.2](https://github.com/jvidalallende/Klondike-scala/releases/tag/v0.2)**: Complete working application
  * Works with both French and Spanish card types
  * Good test coverage of all relevant code, including views
  * Card view now draws the whole suit name instead of just initials

* **[v0.1](https://github.com/jvidalallende/Klondike-scala/releases/tag/v0.1)**: First working prototype
  * Deck is not reloaded
  * No way to finish the game (only manual exit)
  * Missing tests for main views and menus


## Related sites

* [CI server in TravisCI](https://travis-ci.org/jvidalallende/Klondike-scala)
* [Coverage report in Coveralls](https://coveralls.io/github/jvidalallende/Klondike-scala)
