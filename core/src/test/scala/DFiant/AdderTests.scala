/*
 *     This file is part of DFiant.
 *
 *     DFiant is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     DFiant is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with DFiant.  If not, see <https://www.gnu.org/licenses/>.
 */

package DFiant

import shapeless.test.illTyped

object `Op+Tests` {
  import GlobalDesign._
  val uint8 = DFUInt(8)
  val uint9 = DFUInt(9)
  uint8.extendable + uint9
  illTyped("""uint8 + uint9""")
  uint9 + uint8
  illTyped("""uint8 + 511""")
  uint8.extendable + 511
  uint8 + -1
  uint8 + -1L
  uint8 + BigInt(-1)
  uint9 + 511
  uint9 + 511L
  uint9 + BigInt(511)
  val one = 1
  uint8 + one
  val oneL = 1L
  uint8 + oneL
  uint8 + uint8 + uint8
  (uint8 + uint8).wc + uint9
  (uint8 + uint8).wc + (uint9 + uint9)
  illTyped("""(uint8 + uint8) + (uint8 + uint8).wc""")
}


object OpComparisonTests {
  import GlobalDesign._
  val uint8 = DFUInt(8)
  val uint9 = DFUInt(9)
  illTyped("""uint8 == uint9""")
  illTyped("""uint9 == uint8""")
//  illTyped("""uint8 == 511""")
  illTyped("""uint8 == -1""")
  illTyped("""uint8 == -1L""")
  uint9 == 511
  uint9 == 511L
  uint9 == BigInt(511)
  val one = 1
  uint8 == one
  val oneL = 1L
  uint8 == oneL
}