[![Build Status](https://travis-ci.org/cosminj/atpfx.svg?branch=master)](https://travis-ci.org/cosminj/atpfx)
[![Coverage Status](https://img.shields.io/coveralls/cosminj/atpfx.svg?branch=master)](https://coveralls.io/r/cosminj/atpfx?branch=master)
[![Heroku deployed](https://heroku-badge.herokuapp.com/?app=atpfx)](https://heroku-deployment-badges.herokuapp.com/?app=atpfx)

Deployed to heroku under: [https://atpfx.herokuapp.com](https://atpfx.herokuapp.com) 

ATPFX
=====
## Automatic Trade Platform for Foreign Exchange market

Foreign Exchange market is the largest financial market in the world. This project's intention is to offer a framework for programmers familiar with the forex market to deploy user-defined strategies to profit from the forex market. The users need to have access to the full power of Java language and the necessary APIs when implementing their strategies. Strategies may be based on time events, on values of indexes, or may be mechanical.

The capabilities of atpfx platform fall in a few categories:

 - getting historical and real-time information about the market
 - getting access to trade on the market
 - index calculations and share
 - ability to setup strategies that trigger on events
 - ability to backtest any strategy
 - external communication strategies, for user notification or user command
 - reporting strategies
 - Java language for modelling strategies as low or high level as needed
 - The platform should be different from other software on the market by supporting the open source approach, by avoiding the creation of a proprietary application specific language, and by offering full flexibility to programmers who intend to develop their own strategies.

For more information on the forex market, the project roadmap, and examples please contact the project owners.

## Data types diagram:
![alt text](/doc/diagrams/DataTypesDiagram.png "DataTypesDiagram.png")

## Data Input protocol convention:
Data from FXCM comes in messages. The messages can be status messages, or generic messages. Prices can be found in generic messages.
The generic messages can be parsed to get prices, time stamps.
The functionality for the FXCM data feed goes in class FXCMConnection.

Data is then put in an internal structure.
The internal structure ensures other data provider can be connected later to the system.

The internal structure is a class "candle" with following members included

- pair
- interval
- open time
- min
- max
- sell, a.k.a ask
- buy, a.k.a bid
- close time

The properties files may be

- accounts properties file, with username and passwords, at least one valid username/password
- pairs properties file, where each currency pair is associated with an ordinal number
- intervals properties file, where the names of intervals are associated with an ordinal number
- other configuration properties file, with the name of the file where data is stored
  or retrieved from, the name of the database used, and other details

## External dependencies (non public)

The maven projects uses two external libraries from FXCM : API and messaging. These two libraries can be downloaded from the FXCM java API forum here: [FXCM Forum](http://www.dailyfx.com/forex_forum/java-trading-api-support/63933-java-trading-api-builds-subscribe-updates.html)


## Future:

The project should actually switch to use the FIX Protocol API , store data in a big-data db like Cassandra clusters and be deployed on live OSGI runtimes.
