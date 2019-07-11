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

package DFiant.targetlib
import DFiant._
import internals._

object DFUIntOps {
  class Arithmetic[Kind <: DiSoOp.Kind](
    val leftWidth : Int, val rightWidth : Int, val resultWidth : Int)(
    implicit ctx : DFComponent.Context[Arithmetic[Kind]], kind : Kind
  ) extends DFComponent[Arithmetic[Kind]] {
    final val inLeft = DFUInt(leftWidth) <> IN
    final val inRight = DFUInt(rightWidth) <> IN
    final val outResult = DFUInt(resultWidth) <> OUT
    override protected def foldedRun: Unit = {
      val func = kind match {
        case _: DiSoOp.Kind.+ => DFUInt.Token.+
        case _: DiSoOp.Kind.- => DFUInt.Token.-
        case _: DiSoOp.Kind.* => DFUInt.Token.*
        case _ => throw new IllegalArgumentException("Unexptected operation")
      }
      setInitFunc(outResult)(LazyBox.Args2(this)(func, getInit(inLeft), getInit(inRight)))
    }
    final protected val foldedDiscoveryDependencyList = (outResult -> (inLeft :: inRight :: Nil)) :: Nil
  }

  type `Comp+` = Arithmetic[DiSoOp.Kind.+]
  type `Comp-` = Arithmetic[DiSoOp.Kind.-]
  type `Comp*` = Arithmetic[DiSoOp.Kind.*]

  class Relational[Kind <: DiSoOp.Kind](
    val leftWidth : Int, val rightWidth : Int)(
    implicit ctx : DFComponent.Context[Relational[Kind]], kind : Kind
  ) extends DFComponent[Relational[Kind]] {
    final val inLeft = DFUInt(leftWidth) <> IN
    final val inRight = DFUInt(rightWidth) <> IN
    final val outResult = DFBool() <> OUT
    override protected def foldedRun: Unit = {
      val func = kind match {
        case _: DiSoOp.Kind.== => DFUInt.Token.==
        case _: DiSoOp.Kind.!= => DFUInt.Token.!=
        case _: DiSoOp.Kind.< => DFUInt.Token.<
        case _: DiSoOp.Kind.> => DFUInt.Token.>
        case _: DiSoOp.Kind.<= => DFUInt.Token.<=
        case _: DiSoOp.Kind.>= => DFUInt.Token.>=
        case _ => throw new IllegalArgumentException("Unexptected operation")
      }
      setInitFunc(outResult)(LazyBox.Args2(this)(func, getInit(inLeft), getInit(inRight)))
    }
    final protected val foldedDiscoveryDependencyList = (outResult -> (inLeft :: inRight :: Nil)) :: Nil
  }

  type `Comp==` = Relational[DiSoOp.Kind.==]
  type `Comp!=` = Relational[DiSoOp.Kind.!=]
  type `Comp<`  = Relational[DiSoOp.Kind.<]
  type `Comp>`  = Relational[DiSoOp.Kind.>]
  type `Comp<=` = Relational[DiSoOp.Kind.<=]
  type `Comp>=` = Relational[DiSoOp.Kind.>=]

}