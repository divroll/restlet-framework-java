/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or CDL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.engine.util;

import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Range;
import org.restlet.representation.Representation;

/**
 * Range manipulation utilities.
 * 
 * @author Jerome Louvel
 */
public class RangeUtils {

    /**
     * Format {@code range} as a Content-Range header value
     * 
     * @param range
     *            Range to format
     * @param size
     *            Total size of the entity
     * @return {@code range} formatted
     */
    public static String formatContentRange(Range range, long size)
            throws Exception {
        final StringBuilder b = new StringBuilder("bytes ");

        if (range.getIndex() >= Range.INDEX_FIRST) {
            b.append(range.getIndex());
            b.append("-");
            if (range.getSize() != Range.SIZE_MAX) {
                b.append(range.getIndex() + range.getSize() - 1);
            } else {
                if (size != Representation.UNKNOWN_SIZE) {
                    b.append(size - 1);
                } else {
                    throw new IllegalArgumentException(
                            "The entity has an unknown size, can't determine the last byte position.");
                }
            }
        } else if (range.getIndex() == Range.INDEX_LAST) {
            if (range.getSize() != Range.SIZE_MAX) {
                if (size != Representation.UNKNOWN_SIZE) {
                    b.append(size - range.getSize());
                    b.append("-");
                    b.append(size - 1);
                } else {
                    throw new IllegalArgumentException(
                            "The entity has an unknown size, can't determine the last byte position.");
                }
            } else {
                // This is not a valid range.
                throw new IllegalArgumentException(
                        "The range provides no index and no size, it is invalid.");
            }
        }

        if (size != Representation.UNKNOWN_SIZE) {
            b.append("/").append(size);
        } else {
            b.append("/*");
        }

        return b.toString();
    }

    /**
     * Format {@code ranges} as a Range header value
     * 
     * @param ranges
     *            List of ranges to format
     * @return {@code ranges} formatted or null if the list is null or empty.
     */
    public static String formatRanges(List<Range> ranges) {
        if (ranges == null || ranges.isEmpty()) {
            return null;
        }

        final StringBuilder value = new StringBuilder("bytes=");
        for (int i = 0; i < ranges.size(); i++) {
            Range range = ranges.get(i);
            if (i > 0) {
                value.append(", ");
            }

            if (range.getIndex() >= Range.INDEX_FIRST) {
                value.append(range.getIndex());
                value.append("-");
                if (range.getSize() != Range.SIZE_MAX) {
                    value.append(range.getIndex() + range.getSize() - 1);
                }
            } else if (range.getIndex() == Range.INDEX_LAST) {
                value.append("-");
                if (range.getSize() != Range.SIZE_MAX) {
                    value.append(range.getSize());
                }
            }
        }

        return value.toString();
    }

    /**
     * Parse the Content-Range header value and update the given representation.
     * 
     * @param value
     *            Content-range header.
     * @param representation
     *            Representation to update.
     */
    public static void parseContentRange(String value,
            Representation representation) {
        String prefix = "bytes ";
        if (value != null && value.startsWith(prefix)) {
            value = value.substring(prefix.length());

            int index = value.indexOf("-");
            int index1 = value.indexOf("/");

            if (index != -1) {
                int startIndex = Integer.parseInt(value.substring(0, index));
                int endIndex = Integer.parseInt(value.substring(index + 1,
                        index1));

                representation.setRange(new Range(startIndex, endIndex
                        - startIndex + 1));
            }

            String strLength = value.substring(index1 + 1, value.length());
            if (!("*".equals(strLength))) {
                representation.setSize(Long.parseLong(strLength));
            }
        }
    }

    /**
     * Parse the Range header and returns the list of corresponding Range
     * objects.
     * 
     * @param rangeHeader
     *            The Range header value.
     * @return The list of corresponding Range objects.
     */
    public static List<Range> parseRangeHeader(String rangeHeader) {
        List<Range> result = new ArrayList<Range>();
        String prefix = "bytes=";
        if (rangeHeader != null && rangeHeader.startsWith(prefix)) {
            rangeHeader = rangeHeader.substring(prefix.length());

            String[] array = rangeHeader.split(",");
            for (int i = 0; i < array.length; i++) {
                String value = array[i].trim();
                long index = 0;
                long length = 0;
                if (value.startsWith("-")) {
                    index = Range.INDEX_LAST;
                    length = Long.parseLong(value.substring(1));
                } else if (value.endsWith("-")) {
                    index = Long.parseLong(value.substring(0,
                            value.length() - 1));
                    length = Range.SIZE_MAX;
                } else {
                    String[] tab = value.split("-");
                    if (tab.length == 2) {
                        index = Long.parseLong(tab[0]);
                        length = Long.parseLong(tab[1]) - index + 1;
                    }
                }
                result.add(new Range(index, length));
            }
        }

        return result;
    }
}
