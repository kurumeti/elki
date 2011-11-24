package de.lmu.ifi.dbs.elki.datasource.filter;
/*
This file is part of ELKI:
Environment for Developing KDD-Applications Supported by Index-Structures

Copyright (C) 2011
Ludwig-Maximilians-Universität München
Lehr- und Forschungseinheit für Datenbanksysteme
ELKI Development Team

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import gnu.trove.map.hash.TIntFloatHashMap;

import java.util.BitSet;

import de.lmu.ifi.dbs.elki.data.SparseFloatVector;
import de.lmu.ifi.dbs.elki.data.type.SimpleTypeInformation;
import de.lmu.ifi.dbs.elki.data.type.TypeUtil;
import de.lmu.ifi.dbs.elki.data.type.VectorFieldTypeInformation;
import de.lmu.ifi.dbs.elki.utilities.Util;
import de.lmu.ifi.dbs.elki.utilities.optionhandling.parameterization.Parameterization;

/**
 * <p>
 * Parser to project the ParsingResult obtained by a suitable base parser onto a
 * selected subset of attributes.
 * </p>
 * 
 * @author Arthur Zimek
 */
public class SparseFloatVectorProjectionFilter extends AbstractFeatureSelectionFilter<SparseFloatVector> {
  /**
   * Constructor.
   * 
   * @param selectedAttributes
   */
  public SparseFloatVectorProjectionFilter(BitSet selectedAttributes) {
    super(selectedAttributes);
  }

  @Override
  protected SparseFloatVector filterSingleObject(SparseFloatVector obj) {
    return Util.project(obj, getSelectedAttributes());
  }

  @Override
  protected SimpleTypeInformation<? super SparseFloatVector> getInputTypeRestriction() {
    return TypeUtil.SPARSE_FLOAT_FIELD;
  }

  @Override
  protected SimpleTypeInformation<? super SparseFloatVector> convertedType(SimpleTypeInformation<SparseFloatVector> in) {
    final TIntFloatHashMap emptyMap = new TIntFloatHashMap();
    return new VectorFieldTypeInformation<SparseFloatVector>(SparseFloatVector.class, getDimensionality(), new SparseFloatVector(emptyMap, getDimensionality()));
  }

  /**
   * Parameterization class.
   *
   * @author Erich Schubert
   *
   * @apiviz.exclude
   */
  public static class Parameterizer extends AbstractFeatureSelectionFilter.Parameterizer<SparseFloatVector> {
    @Override
    protected void makeOptions(Parameterization config) {
      super.makeOptions(config);
    }

    @Override
    protected SparseFloatVectorProjectionFilter makeInstance() {
      return new SparseFloatVectorProjectionFilter(selectedAttributes);
    }
  }
}