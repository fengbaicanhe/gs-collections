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

package com.gs.collections.api.set.sorted;

import java.util.Comparator;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.Function2;
import com.gs.collections.api.block.function.primitive.BooleanFunction;
import com.gs.collections.api.block.function.primitive.ByteFunction;
import com.gs.collections.api.block.function.primitive.CharFunction;
import com.gs.collections.api.block.function.primitive.DoubleFunction;
import com.gs.collections.api.block.function.primitive.FloatFunction;
import com.gs.collections.api.block.function.primitive.IntFunction;
import com.gs.collections.api.block.function.primitive.LongFunction;
import com.gs.collections.api.block.function.primitive.ShortFunction;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.predicate.Predicate2;
import com.gs.collections.api.list.ListIterable;
import com.gs.collections.api.list.primitive.BooleanList;
import com.gs.collections.api.list.primitive.ByteList;
import com.gs.collections.api.list.primitive.CharList;
import com.gs.collections.api.list.primitive.DoubleList;
import com.gs.collections.api.list.primitive.FloatList;
import com.gs.collections.api.list.primitive.IntList;
import com.gs.collections.api.list.primitive.LongList;
import com.gs.collections.api.list.primitive.ShortList;
import com.gs.collections.api.multimap.sortedset.SortedSetMultimap;
import com.gs.collections.api.ordered.SortedIterable;
import com.gs.collections.api.partition.set.sorted.PartitionSortedSet;
import com.gs.collections.api.set.SetIterable;
import com.gs.collections.api.tuple.Pair;

/**
 * An iterable whose items are unique and sorted by some comparator or their natural ordering.
 */
public interface SortedSetIterable<T>
        extends SetIterable<T>, Comparable<SortedSetIterable<T>>, SortedIterable<T>
{
    /**
     * Returns the comparator used to order the elements in this set, or null if this set uses the natural ordering of
     * its elements.
     */
    Comparator<? super T> comparator();

    /**
     * Returns the set of all objects that are a member of {@code this} or {@code set} or both. The union of [1, 2, 3]
     * and [2, 3, 4] is the set [1, 2, 3, 4]. If equal elements appear in both sets, then the output will contain the
     * copy from {@code this}.
     */
    SortedSetIterable<T> union(SetIterable<? extends T> set);

    /**
     * Returns the set of all objects that are members of both {@code this} and {@code set}. The intersection of
     * [1, 2, 3] and [2, 3, 4] is the set [2, 3]. The output will contain instances from {@code this}, not {@code set}.
     */
    SortedSetIterable<T> intersect(SetIterable<? extends T> set);

    /**
     * Returns the set of all members of {@code this} that are not members of {@code subtrahendSet}. The difference of
     * [1, 2, 3] and [2, 3, 4] is [1].
     */
    SortedSetIterable<T> difference(SetIterable<? extends T> subtrahendSet);

    /**
     * Returns the set of all objects that are a member of exactly one of {@code this} and {@code setB} (elements which
     * are in one of the sets, but not in both). For instance, for the sets [1, 2, 3] and [2, 3, 4], the symmetric
     * difference set is [1, 4] . It is the set difference of the union and the intersection.
     */
    SortedSetIterable<T> symmetricDifference(SetIterable<? extends T> setB);

    /**
     * Returns the set whose members are all possible subsets of {@code this}. For example, the powerset of [1, 2] is
     * [[], [1], [2], [1, 2]].
     */
    SortedSetIterable<SortedSetIterable<T>> powerSet();

    SortedSetIterable<T> select(Predicate<? super T> predicate);

    <P> SortedSetIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    SortedSetIterable<T> reject(Predicate<? super T> predicate);

    <P> SortedSetIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionSortedSet<T> partition(Predicate<? super T> predicate);

    <P> PartitionSortedSet<T> partitionWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionSortedSet<T> partitionWhile(Predicate<? super T> predicate);

    <S> SortedSetIterable<S> selectInstancesOf(Class<S> clazz);

    <V> ListIterable<V> collect(Function<? super T, ? extends V> function);

    BooleanList collectBoolean(BooleanFunction<? super T> booleanFunction);

    ByteList collectByte(ByteFunction<? super T> byteFunction);

    CharList collectChar(CharFunction<? super T> charFunction);

    DoubleList collectDouble(DoubleFunction<? super T> doubleFunction);

    FloatList collectFloat(FloatFunction<? super T> floatFunction);

    IntList collectInt(IntFunction<? super T> intFunction);

    LongList collectLong(LongFunction<? super T> longFunction);

    ShortList collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    SortedSetIterable<T> distinct();

    SortedSetIterable<T> takeWhile(Predicate<? super T> predicate);

    SortedSetIterable<T> dropWhile(Predicate<? super T> predicate);

    <V> SortedSetMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> SortedSetMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    SortedSetIterable<Pair<T, Integer>> zipWithIndex();

    /**
     * Converts the SortedSetIterable to an immutable implementation. Returns this for immutable sets.
     *
     * @since 5.0
     */
    ImmutableSortedSet<T> toImmutable();
}
