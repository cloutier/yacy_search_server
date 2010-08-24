/**
 *  Index
 *  Copyright 2005 by Michael Peter Christen; mc@yacy.net, Frankfurt a. M., Germany
 *  First released 26.10.2005 at http://yacy.net
 *  
 *  $LastChangedDate: 2010-06-16 17:11:21 +0200 (Mi, 16 Jun 2010) $
 *  $LastChangedRevision: 6922 $
 *  $LastChangedBy: orbiter $
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *  
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program in the file lgpl21.txt
 *  If not, see <http://www.gnu.org/licenses/>.
 */

package net.yacy.kelondro.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.yacy.kelondro.order.CloneableIterator;


public interface Index extends Iterable<Row.Entry> {

    public String filename(); // returns a unique identified for this index; can be a real or artificial file name
    public int size();
    public long mem();
    public boolean isEmpty();
    public Row row();
    public byte[] smallestKey();
    public byte[] largestKey();
    public boolean has(byte[] key); // use this only if there is no get in case that has returns true
    public Row.Entry get(byte[] key) throws IOException;
    public Row.Entry replace(Row.Entry row) throws RowSpaceExceededException, IOException;
    public void put(Row.Entry row) throws IOException, RowSpaceExceededException;
    public void addUnique(Row.Entry row) throws RowSpaceExceededException, IOException; // no double-check
    public ArrayList<RowCollection> removeDoubles() throws IOException, RowSpaceExceededException; // removes all elements that are double (to be used after all addUnique)
    public boolean delete(byte[] key) throws IOException;
    public Row.Entry remove(byte[] key) throws IOException;
    public Row.Entry removeOne() throws IOException;
    public List<Row.Entry> top(int count) throws IOException;
    public CloneableIterator<byte[]> keys(boolean up, byte[] firstKey) throws IOException; // iterates only the key
    public CloneableIterator<Row.Entry> rows(boolean up, byte[] firstKey) throws IOException; // iterates the whole row using the order of the keys
    public CloneableIterator<Row.Entry> rows() throws IOException; // iterates the whole row without any order
    public Iterator<Row.Entry> iterator();
    public void deleteOnExit();
    public void clear() throws IOException;
    public void close();
}