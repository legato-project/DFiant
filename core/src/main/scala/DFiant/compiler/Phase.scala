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

package DFiant.compiler
import DFiant._

trait Phase {
  def apply(design : DFDesign) : DFDesign
}

case class PhaseSeq(seq : Seq[Phase]) extends Phase {
  def apply(design : DFDesign): DFDesign = seq.foldLeft(design)((dsn, phase) => phase(dsn))
}


object Phase {
  trait Flattener extends Phase

  trait ConstantPropagation extends Phase

  trait Printer extends Phase
}