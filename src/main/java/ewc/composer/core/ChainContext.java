/*
 * MIT License
 *
 * Copyright (c) 2024 Decision-Driven Development
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ewc.composer.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * I am a context for a chain of operations. I provide a way to pass data between operations.
 * I can (and should, actually) be extended in order to provide application-specific amenities to
 * all the operations in the chain.
 *
 * @since 0.1.0
 */
public class ChainContext {
    /**
     * The storage for all the data that needs to be passed between operations. The data is stored
     * as a map of keys and suppliers of lists of strings. The suppliers are used to provide the
     * data only when it is needed, so the real data is not stored in the map until it is requested.
     */
    private final Map<String, Supplier<List<String>>> data;

    /**
     * Ctor.
     */
    public ChainContext() {
        this(new HashMap<>());
    }

    /**
     * Ctor.
     *
     * @param data The data to store in the context.
     */
    private ChainContext(final Map<String, Supplier<List<String>>> data) {
        this.data = data;
    }

    /**
     * Retrieves the data from the context by the key. If the data is not present, an empty list is
     * returned.
     *
     * @param key The key to retrieve the data by.
     * @return The data stored under the key or an empty list if the data is not present.
     */
    public List<String> fetch(final String key) {
        return this.data.getOrDefault(key, List::of).get();
    }

    /**
     * Adds the data to the context under the provided key.
     *
     * @param key The key to store the data under.
     * @param supplier The supplier of the data to store.
     */
    public void add(final String key, final Supplier<List<String>> supplier) {
        this.data.put(key, supplier);
    }
}
