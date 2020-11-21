# microblog

A basic blog application.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Repository setup for Leiningen project
1. Create repo on github with name <repo>
2. Clone <repo>
3. Create Leiningen project in cloned repo `lein new compojure <repo> :force`

## Database

**Login to database:**

```
psql -U postgres --password
postgres
```

**Create database:**

```
DROP DATABASE IF EXISTS quilldb;
CREATE DATABASE quilldb;
\c quilldb;
```

Refer resources/schema.sql

View tables: `\dt`

**Sample Data:**

author_tbl:
```
INSERT INTO author_tbl(username, password) values ('aragorn', 'forfrodo');
INSERT INTO author_tbl(username, password) values ('gandalf', 'fly,you fools!');
```

## License

The source code for microblog is available under the GNU General Public License version 3. For more information, see LICENSE.
