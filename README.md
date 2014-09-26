[![Build Status](https://travis-ci.org/propensive/rapture-json.png?branch=scala-2.10)](https://travis-ci.org/propensive/rapture-json)

# Rapture JSON

Rapture JSON is a comprehensive library providing support for working with JSON in Scala.

### Status

Rapture JSON is *managed*. This means that the API is expected to continue to evolve, but all
API changes will be documented with instructions on how to upgrade.

### Availability

Rapture JSON 0.10.0 is available under the Apache 2.0 License from Maven Central with group ID
`com.propensive` and artifact ID `rapture-json_2.10`.

#### SBT

You can include Rapture JSON as a dependency in your own project by adding the following library
dependency to your build file:

```scala
libraryDependencies ++= Seq("com.propensive" %% "rapture-json" % "1.0.0")
```

#### Maven

If you use Maven, include the following dependency:

```xml
<dependency>
  <groupId>com.propensive</groupId>
  <artifactId>rapture-json_2.10</artifactId>
  <version>1.0.0<version>
</dependency>
```

#### Download

You can download Rapture JSON directly from the [Rapture website](http://rapture.io/)
Rapture JSON depends on Scala 2.10 and Rapture Core, but has no other dependencies.

#### Building from source

To build Rapture JSON from source, follow these steps:

```
git clone git@github.com:propensive/rapture-json.git
cd rapture-json
sbt package
```

If the compilation is successful, the compiled JAR file should be found in target/scala-2.10

## JSON Representation

Rapture JSON is designed to be agnostic about the JSON parser and choice of AST representation
used throughout the library. This means that a choice of JSON backend must be made in order to
use Rapture JSON. Whilst different backend libraries provide different features, all features of
Rapture JSON are available with every backend.

The choice of backend should therefore depend on other characteristics such as performance,
memory usage, required dependencies, integration with existing libraries, licensing and policy
choices.

The following backends are available:

 - Scala standard library JSON (Scala 2.10 only)
 - Jawn
 - Argonaut
 - JSON4S
 - Jackson

Work is ongoing to make Lift JSON, Spray JSON and Play JSON available too.

## The Json type

A JSON value, whether an array, object, boolean, number or string, is represented by an instance
of type `Json`. As JSON is inherently dynamically-typed, the `Json` type is used to provide a
safe and immutable wrapper around the JSON tree, whose type is not known at compile time.

Instances of `Json` consist of three things:

 - a reference to the root of a dynamically-typed JSON tree
 - a path into a node within the JSON tree
 - a reference to the parser used to create, modify and read the JSON tree

Although using `Json` objects will seem very intuitive, it is important to understand the
purpose of each of these.

```json
{
  "fruits": [
    {
      "name": "apple",
      "color": "red"
    },
    {
      "name": "banana",
      "color": "yellow"
    }
  ]
}
```

If we were to parse the above JSON source, we should get a tree consisting of an object
containing an array under the key "fruits", with two elements, each of which is an object
containing two fields, "name" and "color", both of hich are strings. Given this tree, we can
refer to an element within with a path of strings for indexing JSON objects, and integers for
indexing JSON arrays, for example, `fruits / 1 / name`, which would refer to the string
`"banana"`.

We could also look into the same tree with the path `fruits / 3 / mass`, though this wouldn't
exist on account of there being only two elements in the `fruits` list, but we would not know
this until we attempted it.

A `Json` instance represents both the JSON tree, and a lazily-evaluated path into that tree,
which may or may not point to a value. If we assume the full JSON tree is a starting point (most
likely originating from being parsed from source), `Json` instances can be created which hold
the same reference to the original tree, but point -- by means of a path -- to any subtree of
the original, without the performance cost of navigating the tree, or the requirement to safely
handle missing-value or type-mismatch errors which arise because the path attempts to access a
value which isn't available.

At some later point, if the JSON is to yield some useful data which we can do interesting things
with, we will need to perform the access, and assign a Scala type to it, as it passes from the
dynamic to the static world. It is at this point that all access failures will arise, so by
deferring them to a single point, they can be handled just once.

Additionally, every `Json` instance stores a reference to the backend which was used to create
it, and which will be used to access it. As Rapture JSON permits multiple different parsers to
be used alongside each other, it is important that the AST within each `Json` instance is
handled using the right backend.

### Accessing JSON values

`Json` instances implement Scala's `Dynamic` trait, providing a very natural way to refer to
object fields within a `Json` value just by calling that field name as if it were a method on
the `Json` instance. Additionally, integers may be applied to index into arrays.

For example, using the example JSON above, we can create a new `Json` instance pointing to the
string `"yellow"` as follows:

```scala
json.fruits(1).color
```

Remember, this is just creating a pointed to the `"yellow"` value; it's not been accessed yet.
To extract a value from a `Json` value, the `as` method is used. `as` takes a single type
parameter, and is the single point at which a JSON type-mismatch or missing-value exception can
occur.

```scala
json.fruits(1).color.as[String]
```

Rapture JSON uses Rapture Core's return-type strategies on the `as` method, thus allowing the


Note that calling `toString`, as happens automatically after every evaluation in the Scala REPL,
*will* cause the AST to be accessed, but any errors will be suppressed, and the `toString`
method will return `undefined`.




### Pattern matching on JSON



## Error messages



