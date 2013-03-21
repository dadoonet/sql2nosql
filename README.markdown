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
$ git checkout 02-restify/end
```

Look at the `README` file to have details on the current step.


Build status
------------

Thanks to cloudbees for the [build status](https://buildhive.cloudbees.com): [![Build Status](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/badge/icon)](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/)

[![Test trends](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/test/trend)](https://buildhive.cloudbees.com/job/dadoonet/job/sql2nosql/)
