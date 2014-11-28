/*
 * Copyright 2014 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.lazy.parallel.set.sorted;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.list.ParallelListIterable;
import com.gs.collections.impl.block.factory.Comparators;
import com.gs.collections.impl.factory.SortedSets;
import com.gs.collections.impl.lazy.parallel.list.ParallelListIterableTestCase;
import com.gs.collections.impl.list.mutable.FastList;

public class ParallelCollectSortedSetIterableTest extends ParallelListIterableTestCase
{
    @Override
    protected ParallelListIterable<Integer> classUnderTest()
    {
        return SortedSets.immutable.with(Comparators.reverseNaturalOrder(), 44, 43, 42, 41, 33, 32, 31, 22, 21, 10)
                .asParallel(this.executorService, 2)
                .collect(i -> i / 10);
    }

    @Override
    protected MutableList<Integer> getExpected()
    {
        return FastList.newListWith(4, 4, 4, 4, 3, 3, 3, 2, 2, 1);
    }
}
