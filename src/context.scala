/**********************************************************************************************\
* Rapture JSON Library                                                                         *
* Version 1.0.4                                                                                *
*                                                                                              *
* The primary distribution site is                                                             *
*                                                                                              *
*   http://rapture.io/                                                                         *
*                                                                                              *
* Copyright 2010-2014 Jon Pretty, Propensive Ltd.                                              *
*                                                                                              *
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file    *
* except in compliance with the License. You may obtain a copy of the License at               *
*                                                                                              *
*   http://www.apache.org/licenses/LICENSE-2.0                                                 *
*                                                                                              *
* Unless required by applicable law or agreed to in writing, software distributed under the    *
* License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,    *
* either express or implied. See the License for the specific language governing permissions   *
* and limitations under the License.                                                           *
\**********************************************************************************************/
package rapture.json

import rapture.core._
import rapture.data._

/** Provides support for JSON literals, in the form json" { } " or json""" { } """.
  * Interpolation is used to substitute variable names into the JSON, and to extract values
  * from a JSON string. */
class JsonStrings[R <: JsonAst](sc: StringContext)(implicit parser: Parser[String, R])
    extends {
  object json extends DataContext(Json, sc, parser)
}

class JsonBufferStrings[R <: JsonBufferAst](sc: StringContext)(implicit parser: Parser[String, R])
    extends {
  object jsonBuffer extends DataContext(JsonBuffer, sc, parser)
}
