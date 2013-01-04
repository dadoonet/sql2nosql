From SQL to NoSQL
=================

Introduction
------------

This is a demo project to show how to migrate from a legacy SQL project to a NoSQL project
using Couchbase and Elasticsearch.

You will find the following git branches:

* 01-legacy/start the legacy project where we start from
* 02-restify/start we start to create a REST service (and remove old interfaces)
* 02-restify/end REST Service fully working
* 03-couchbase-persistence/start we start to move from Hibernate to Couchbase for persistence
* 03-couchbase-persistence/end Couchbase persistency fully working
* 04-new-ui-angular/start we start build an AngularJS GUI on top of our REST services
* 04-new-ui-angular/end AngularJS fully working
* 05-search/start we start connecting Couchbase and Elasticsearch and add search to AngularJS
* 05-search/end AngularJS with search fully working
* 06-dashboards/start we start to build Kibana dashboards
* 06-dashboards/end Dashboards fully working

To go a given step, just use `git checkout` command. Eg:

```sh
$ git checkout 02-restify/begin
```

Look at the `README` file to have details on the current step.


Build status
------------

Thanks to cloudbees for the [build status](https://buildhive.cloudbees.com): [![Build Status](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/badge/icon)](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/)

[![Test trends](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/test/trend)](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/)

Installation
------------

Start the server using jetty

```sh
$ mvn install
$ cd demo-webapp
$ mvn jetty:run
```

You can use the shortcut:

```sh
$ ./run.sh
```

You can then access the application using your browser: [http://127.0.0.1:8080/](http://127.0.0.1:8080/)


REST Interface
--------------

Here are some basic commands:

## Create

```sh
# Create one person
$ curl -XPUT http://localhost:8080/api/1/person/ -d '{"name":"David Pilato"}'
```

## Read

```sh
# Read All Persons
$ curl -XGET http://localhost:8080/api/1/person/

# Read Person1
$ curl -XGET http://localhost:8080/api/1/person/1
```

## Update

```sh
# Update Person #1
$ curl -XPUT http://localhost:8080/api/1/person/1 -d '{"name":"Tugdual Grall"}'
```

## Delete

```sh
# Delete Person #1
$ curl -XDELETE http://localhost:8080/api/1/person/1
```

## Search

```sh
# Search for something (`a la google`)
$ curl -XPOST http://localhost:8080/api/1/person/_search -d 'q=John'
```

## Database Initialisation

```sh
# Initialize the database with 100 persons
$ curl -XPOST http://localhost:8080/api/1/person/_init
```

You can add a `size` parameter to inject a given number of person (default to `100`):

```sh
# Initialize the database with 1 000 000 persons
$ curl -XPOST http://localhost:8080/api/1/person/_init?size=1000000
```

